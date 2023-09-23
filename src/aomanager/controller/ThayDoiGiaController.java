package aomanager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import aomanager.entity.SinhVatCanhEntity;
import aomanager.entity.ThayDoiGiaEntity;

@Controller
@Transactional
@RequestMapping("thaydoigia")
public class ThayDoiGiaController {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static SinhVatCanhEntity SVC = null;
	
	@Autowired
	SessionFactory factory;
	
	public SinhVatCanhEntity getSinhVatCanh(String maSVC) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM SinhVatCanhEntity where maSVC = :ma");
		query.setParameter("ma", maSVC);
		SinhVatCanhEntity sinhVatCanh = (SinhVatCanhEntity) query.list().get(0);
		return sinhVatCanh;
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
	
	public ThayDoiGiaEntity getTDG(int id) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM ThayDoiGiaEntity where id = :id");
		query.setParameter("id", id);
		ThayDoiGiaEntity tdg = (ThayDoiGiaEntity) query.list().get(0);
		return tdg;
	}
	
	public boolean insertGia(ThayDoiGiaEntity gia) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(gia);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean deleteGia(ThayDoiGiaEntity tdg) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(tdg);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	@RequestMapping("/gia")
	public String gia(HttpServletRequest request, ModelMap model) {
		PagedListHolder pagedListHolder = new PagedListHolder(this.getDSSinhVatCanh());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "quanly/sinhvatcanh/gia/index";
	}
	
	@RequestMapping(value = "/{maSVC}.htm", params = "linkAdd")
	public String themGia(ModelMap model, @PathVariable("maSVC") String maSVC) {
		SinhVatCanhEntity sVC = this.getSinhVatCanh(maSVC);
		SVC = sVC;
		model.addAttribute("gia", new ThayDoiGiaEntity());
		model.addAttribute("sVC", sVC);
		return "quanly/sinhvatcanh/gia/themgia";
	}
	
	@RequestMapping("themgia")
	public String insertGia(HttpServletRequest request, ModelMap model, @Validated @ModelAttribute("gia") ThayDoiGiaEntity gia, BindingResult errors) {
		gia.setMaSVC(SVC);
		gia.setMaNV(LogController.tKNVHT.getNhanVien());
		boolean checkAdd = this.insertGia(gia);
		String msg = "";
		String url = "";
		if(checkAdd == true) {
			msg = "Thêm giá thành công!";
			url = "quanly/sinhvatcanh/gia/index";
			PagedListHolder pagedListHolder = new PagedListHolder(this.getDSSinhVatCanh());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);
		}
		else {
			msg = "Thêm giá thất bại!";
			url = "quanly/sinhvatcanh/gia/themgia";
		}
		model.addAttribute("msg", msg);
		return url;
	}
	
	@RequestMapping(value = "/{maSVC}.htm", params = "linkDelete")
	public String xoaGia(ModelMap model, @PathVariable("maSVC") String maSVC) {
		SinhVatCanhEntity sVC = this.getSinhVatCanh(maSVC);
		SVC = sVC;
		List<ThayDoiGiaEntity> tdg = sVC.getThayDoiGiaList();
		for (ThayDoiGiaEntity i : tdg) {
			System.out.println(i.getGia());
		}
		model.addAttribute("name", sVC.getTen());
		model.addAttribute("dSGia", tdg);
		return "quanly/sinhvatcanh/gia/xemgia";
	}
	
	@RequestMapping(value = "/{id}.htm", params = "Delete")
	public String deleteGia(ModelMap model, @PathVariable("id") int id) {
		String msg = "";
		ThayDoiGiaEntity tdg = this.getTDG(id);
		boolean checkDelete = this.deleteGia(tdg);
		if(checkDelete == true) {
			msg = "Xoá giá cho sản phẩm thành công!";
		} else msg = "Xoá giá cho sản phẩm thất bại";
		List<ThayDoiGiaEntity> tdgList = tdg.getMaSVC().getThayDoiGiaList();
		for (ThayDoiGiaEntity i : tdgList) {
			System.out.println(i.getGia() + "1");
		}
		model.addAttribute("name", SVC.getTen());
		model.addAttribute("dSGia", tdgList);
		model.addAttribute("msg", msg);
		return "quanly/sinhvatcanh/gia/xemgia";
	}
}
