package aomanager.controller;

import java.util.ArrayList;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import aomanager.entity.LoaiEntity;
import aomanager.entity.NhanVienEntity;
import aomanager.entity.QuyenEntity;
import aomanager.entity.TaiKhoanEntity;
import aomanager.entity.ThayDoiGiaEntity;
import aomanager.entity.TrangThaiTKEntity;

@Controller
@Transactional
@RequestMapping("nhanvien")
public class NhanVienController {
	
	TaiKhoanEntity TK = null;
	
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
	
	public NhanVienEntity getNhanVien(String ma) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM NhanVienEntity where maNV = :ma");
		query.setParameter("ma", ma);
		NhanVienEntity nv = (NhanVienEntity) query.list().get(0);
		return nv;
	}
	
	public List<NhanVienEntity> getDSNhanVien() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM NhanVienEntity");
		List<NhanVienEntity> result = query.list();
		return result;
	}
	
	public boolean insertNhanVien(NhanVienEntity nV) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(nV);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean updateNhanVien(NhanVienEntity nV) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(nV);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	@ModelAttribute("gioitinh")
	public List<String> getGioiTinh() {
		List<String> list = new ArrayList<String>();
		list.add("Nam");
		list.add("Nữ");
		return list;
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
	public String index(HttpServletRequest request, ModelMap model) {
		PagedListHolder pagedListHolder = new PagedListHolder(this.getDSNhanVien());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "quanly/nhanvien/index";
	}
	
	@RequestMapping(value = "/{maTK}.htm", params="linkAddNhanVien")
	public String linkThem(ModelMap model, @PathVariable("maTK") String maTK) {
		TK = this.getTaiKhoan(maTK);
		model.addAttribute("taiKhoan", TK.getMaTK());
		model.addAttribute("nhanVien", new NhanVienEntity());
		model.addAttribute("loaiTrang", "taoNhanVien");
		return "quanly/taikhoan/themnguoidung";
	}
	
	@RequestMapping("/them")
	public String themNhanVien(ModelMap model, HttpServletRequest request, @Validated @ModelAttribute("nhanVien") NhanVienEntity nV, BindingResult errors) {
		nV.setMaTK(TK);
		boolean checkAdd = this.insertNhanVien(nV);
		String msg = "";
		String url = "";
		if(checkAdd == true) {
			msg = "Tạo nhân viên thành công!";
			url = "quanly/taikhoan/index";
			PagedListHolder pagedListHolder = new PagedListHolder(this.getDSTaiKhoan());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(5);
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("taiKhoan", new TaiKhoanEntity());
			model.addAttribute("loaiTrang", "them");
		} else {
			msg = "Tạo nhân viên thất bại, đã xảy ra lỗi!";
			url = "quanly/taikhoan/themnguoidung";
			model.addAttribute("loaiTrang", "taoNhanVien");
			model.addAttribute("taiKhoan", TK.getMaTK());
		}
		model.addAttribute("msg", msg);
		return url;
	}
	
	@RequestMapping(value = "/{maNV}.htm", params = "linkEdit")
	public String editNV(ModelMap model, @PathVariable("maNV") String maNV) {
		NhanVienEntity nhanVien = this.getNhanVien(maNV);
		TK = nhanVien.getMaTK();
		model.addAttribute("nhanVien", nhanVien);
		return "quanly/nhanvien/sua";
	}
	
	@RequestMapping(value = "sua", params = "btnSave")
	public String suaNhanVien(ModelMap model, HttpServletRequest request, @Validated @ModelAttribute("nhanVien") NhanVienEntity nV, BindingResult errors) {
		nV.setMaTK(TK);
		boolean checkUpdate = this.updateNhanVien(nV);
		String msg = "";
		String url = "";
		if(checkUpdate == true) {
			msg = "Chỉnh sửa nhân viên thành công!";
			url = "quanly/nhanvien/index";
			PagedListHolder pagedListHolder = new PagedListHolder(this.getDSNhanVien());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);
		} else {
			msg = "Chỉnh sửa nhân viên thất bại, đã xảy ra lỗi!";
			url = "quanly/nhanvien/sua";
		}
		model.addAttribute("msg", msg);
		return url;
	}
}
