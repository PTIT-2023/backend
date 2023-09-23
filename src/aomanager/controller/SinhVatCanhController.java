package aomanager.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import aomanager.entity.LoaiEntity;
import aomanager.entity.SinhVatCanhEntity;
import aomanager.entity.ThayDoiGiaEntity;
import aomanager.entity.TrangThaiSPEntity;

@Controller
@Transactional
@RequestMapping("sinhvatcanh")
public class SinhVatCanhController {

	@Autowired
	SessionFactory factory;
	
	public SinhVatCanhEntity getSinhVatCanh(String maSVC) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM SinhVatCanhEntity where maSVC = :ma");
		query.setParameter("ma", maSVC);
		SinhVatCanhEntity sinhVatCanh = (SinhVatCanhEntity) query.list().get(0);
		return sinhVatCanh;
	}
	
	public List<SinhVatCanhEntity> getDSSinhVatCanh() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM SinhVatCanhEntity");
		List<SinhVatCanhEntity> result = query.list();
		for(int i = 0; i < result.size(); i++) {
			SinhVatCanhEntity svc = result.get(i);
			result.get(i).setGia(this.layGiaHienTai(svc));
		}
		return result;
	}
	
	public boolean insertSinhVatCanh(SinhVatCanhEntity sVC) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(sVC);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean updateSinhVatCanh(SinhVatCanhEntity sVC) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(sVC);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean deleteSinhVatCanh(SinhVatCanhEntity sVC) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(sVC);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	public int layGiaHienTai(SinhVatCanhEntity sVC){
        List<ThayDoiGiaEntity> ds = sVC.getThayDoiGiaList();
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String now = simpleDateFormat.format(currentDate);
        Date hienTai = new Date();
        try{
            hienTai = simpleDateFormat.parse(now);
        } catch (Exception e){
            System.out.println(e);
        }
        long min = 100000000;
        int vt = -1;
        for(int i = 0; i < ds.size(); i++){
            long getDiff = hienTai.getTime() - ds.get(i).getNgayApDung().getTime();
            long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
            if(getDaysDiff >= 0 && getDaysDiff < min){
                min = getDaysDiff;
                vt = i;
            }
        }
        if((ds.size() > 0) && (vt >= 0)) return ds.get(vt).getGia();
        return 0;
    }
	
	@ModelAttribute("loai")
	public List<LoaiEntity> getLoai() {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiEntity";
		Query query = session.createQuery(hql);
		List<LoaiEntity> list = query.list();
		return list;
	}
	
	@ModelAttribute("trangthai")
	public List<TrangThaiSPEntity> getTrangThai() {
		Session session = factory.getCurrentSession();
		String hql = "FROM TrangThaiSPEntity";
		Query query = session.createQuery(hql);
		List<TrangThaiSPEntity> list = query.list();
		return list;
	}
	
	@RequestMapping("/index")
	public String indexSinhVatCanh(HttpServletRequest request, ModelMap model) {
		PagedListHolder pagedListHolder = new PagedListHolder(this.getDSSinhVatCanh());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "quanly/sinhvatcanh/index";
	}
	
	@RequestMapping("/them")
	public String prThemSinhVatCanh(ModelMap model) {
		model.addAttribute("sinhVatCanh", new SinhVatCanhEntity());
		model.addAttribute("title", "Thêm sinh vật cảnh");
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("loaiTrang", "themSVC");
		return "quanly/sinhvatcanh/infor";
	}
	
	@RequestMapping(value = "editsinhvatcanh", params = "btnAdd")
	public String themSinhVatCanh(HttpServletRequest request, ModelMap model, @Validated @ModelAttribute("sinhVatCanh") SinhVatCanhEntity sVC, BindingResult errors) {
		sVC.setSoLuongTon(0);
		sVC.setLuotMua(0);
		sVC.setGia(0);
		boolean checkAdd = this.insertSinhVatCanh(sVC);
		String msg = "";
		String url = "";
		if(checkAdd == true) {
			msg = "Thêm sinh vật cảnh thành công!";
			url = "quanly/sinhvatcanh/index";
			PagedListHolder pagedListHolder = new PagedListHolder(this.getDSSinhVatCanh());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);
		} else {
			msg = "Thêm sinh vật cảnh thất bại!";
			model.addAttribute("title", "Thêm sinh vật cảnh");
			model.addAttribute("btnStatus", "btnAdd");
			url = "quanly/sinhvatcanh/infor";
		}
		model.addAttribute("msg", msg);
		return url;
	}
	
	@RequestMapping(value = "/{maSVC}.htm", params = "linkInfor")
	public String inforSinhVatCanh(ModelMap model, @PathVariable("maSVC") String maSVC) {
		model.addAttribute("sinhVatCanh", this.getSinhVatCanh(maSVC));
		model.addAttribute("title", "Thông tin chi tiết & chỉnh sửa");
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("loaiTrang", "suaSVC");
		return "quanly/sinhvatcanh/infor";
	}
	
	@RequestMapping(value = "editsinhvatcanh", params = "btnEdit")
	public String suaSinhVatCanh(HttpServletRequest request, ModelMap model, @Validated @ModelAttribute("sinhVatCanh") SinhVatCanhEntity sVC, BindingResult errors) {
		boolean checkUpdate = this.updateSinhVatCanh(sVC);
		String msg = "";
		String url = "";
		if(checkUpdate == true) {
			msg = "Chỉnh sửa thông tin sinh vật cảnh thành công!";
			url = "quanly/sinhvatcanh/index";
			PagedListHolder pagedListHolder = new PagedListHolder(this.getDSSinhVatCanh());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);
		} else {
			msg = "Chỉnh sửa thông tin sinh vật cảnh thất bại!";
			url = "quanly/sinhvatcanh/infor";
			model.addAttribute("title", "Thông tin chi tiết & chỉnh sửa");
			model.addAttribute("btnStatus", "btnEdit");
			model.addAttribute("loaiTrang", "suaSVC");
		}
		model.addAttribute("msg", msg);
		return url;
	}
	
}
