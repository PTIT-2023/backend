package aomanager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import aomanager.entity.QuyenEntity;
import aomanager.entity.TaiKhoanEntity;
import aomanager.entity.TrangThaiTKEntity;
import aomanager.model.ThongTinModel;

@Controller
@Transactional
@RequestMapping("taikhoan")
public class TaiKhoanController {
	
	@Autowired
	SessionFactory factory;
	
	public TaiKhoanEntity getTaiKhoan(String maTK) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM TaiKhoanEntity where maTK = :ma");
		query.setParameter("ma", maTK);
		TaiKhoanEntity taiKhoan = (TaiKhoanEntity) query.list().get(0);
		return taiKhoan;
	}
	
	public List<TaiKhoanEntity> getDSTaiKhoan() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM TaiKhoanEntity");
		List<TaiKhoanEntity> result = query.list();
		return result;
	}
	
	public boolean insertTaiKhoan(TaiKhoanEntity tK) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(tK);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean updateTaiKhoan(TaiKhoanEntity tK) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(tK);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
		
	@ModelAttribute("quyen")
	public List<QuyenEntity> getQuyen() {
		Session session = factory.getCurrentSession();
		String hql = "FROM QuyenEntity";
		Query query = session.createQuery(hql);
		List<QuyenEntity> list = query.list();
		return list;
	}
	
	@ModelAttribute("trangthai")
	public List<TrangThaiTKEntity> getTrangThai() {
		Session session = factory.getCurrentSession();
		String hql = "FROM TrangThaiTKEntity";
		Query query = session.createQuery(hql);
		List<TrangThaiTKEntity> list = query.list();
		return list;
	}
	
	@RequestMapping("/index")
	public String indexTaiKhoan(HttpServletRequest request, ModelMap model) {
		PagedListHolder pagedListHolder = new PagedListHolder(this.getDSTaiKhoan());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("taiKhoan", new TaiKhoanEntity());
		model.addAttribute("loaiTrang", "them");
		model.addAttribute("btnStatus", "btnAdd");
		return "quanly/taikhoan/index";
	}
	
	@RequestMapping(value = "/edittaikhoan", params = "btnAdd")
	public String themTaiKhoan(HttpServletRequest request, ModelMap model, @Validated @ModelAttribute("taiKhoan") TaiKhoanEntity tK, BindingResult errors) {
		String msg = "";
		boolean checkAdd = this.insertTaiKhoan(tK);
		if(checkAdd == true){
			model.addAttribute("taiKhoan", new TaiKhoanEntity());
			msg = "Thêm tài khoản thành công!";
		} else msg = "Thêm tài khoản thất bại, đã xảy ra lỗi!";
		PagedListHolder pagedListHolder = new PagedListHolder(this.getDSTaiKhoan());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("msg", msg);
		model.addAttribute("loaiTrang", "them");
		model.addAttribute("btnStatus", "btnAdd");
		return "quanly/taikhoan/index";
	}
	
	@RequestMapping(value = "/{maTK}.htm", params = "linkEdit")
	public String preSuaTaiKhoan(HttpServletRequest request, ModelMap model, @PathVariable("maTK") String maTK) {
		model.addAttribute("taiKhoan", this.getTaiKhoan(maTK));
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("loaiTrang", "sua");
		PagedListHolder pagedListHolder = new PagedListHolder(this.getDSTaiKhoan());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "quanly/taikhoan/index";
	}
	
	@RequestMapping(value = "/edittaikhoan", params = "btnEdit")
	public String suaTaiKhoan(HttpServletRequest request, ModelMap model, @Validated @ModelAttribute("taiKhoan") TaiKhoanEntity tK, BindingResult errors) {
		boolean checkUpdate = this.updateTaiKhoan(tK);
		String msg = "";
		if(checkUpdate == true) {
			msg = "Sửa tài khoản thành công!";
			model.addAttribute("taiKhoan", new TaiKhoanEntity());
			model.addAttribute("loaiTrang", "them");
			model.addAttribute("btnStatus", "btnAdd");
		} else {
			msg = "Sửa tài khoản thất bại, đã xảy ra lỗi!";
			model.addAttribute("loaiTrang", "sua");
			model.addAttribute("btnStatus", "btnEdit");
		}
		model.addAttribute("msg", msg);
		PagedListHolder pagedListHolder = new PagedListHolder(this.getDSTaiKhoan());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "quanly/taikhoan/index";
	}
}
