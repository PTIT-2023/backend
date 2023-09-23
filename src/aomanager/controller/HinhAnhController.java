package aomanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import aomanager.entity.HinhAnhEntity;
import aomanager.entity.SinhVatCanhEntity;
import aomanager.entity.ThayDoiGiaEntity;

@Controller
@Transactional
@RequestMapping("hinhanh")
public class HinhAnhController {
	
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
	
	public HinhAnhEntity getHinhAnh(int iDHA) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM HinhAnhEntity where iDHA = :iDHA");
		query.setParameter("iDHA", iDHA);
		HinhAnhEntity ha = (HinhAnhEntity) query.list().get(0);
		return ha;
	}
	
	public boolean insertHA(HinhAnhEntity ha) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(ha);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean deleteHA(HinhAnhEntity ha) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(ha);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	@RequestMapping(value = "/{maSVC}.htm", params = "linkPic")
	public String index(ModelMap model, @PathVariable("maSVC") String maSVC) {
		SinhVatCanhEntity sVC = this.getSinhVatCanh(maSVC);
		SVC = sVC;
		List<HinhAnhEntity> dSHA = sVC.getHinhAnhList();
		for (HinhAnhEntity i : dSHA) {
			System.out.println(i.getDuongDan());
		}
		model.addAttribute("name", sVC.getTen());
		model.addAttribute("dSHinhAnh", dSHA);
		return "quanly/sinhvatcanh/hinhanh/index";
	}
	
	@RequestMapping("/them")
	public String themHA(ModelMap model) {
		model.addAttribute("hinhAnh", new HinhAnhEntity());
		model.addAttribute("name", SVC.getTen());
		return "quanly/sinhvatcanh/hinhanh/them";
	}
	
	@RequestMapping("/themhinhanh") 
	public String inserHinhAnh(ModelMap model, @Validated @ModelAttribute("hinhAnh") HinhAnhEntity hinhAnh, BindingResult errors) {
		String maSVC = SVC.getMaSVC();
		hinhAnh.setMaSVC(SVC);
		boolean checkAdd = this.insertHA(hinhAnh);
		List<HinhAnhEntity> dSHA = this.getSinhVatCanh(maSVC).getHinhAnhList();
		for (HinhAnhEntity i : dSHA) {
			System.out.println(i.getDuongDan());
		}
		model.addAttribute("dSHinhAnh", dSHA);
		model.addAttribute("name", hinhAnh.getMaSVC().getTen());
		return "redirect:/hinhanh/" + SVC.getMaSVC() +".htm?linkPic";
	}
	
	@RequestMapping(value = "/{iDHA}.htm", params = "linkDelete")
	public String deleteHA(ModelMap model, @PathVariable("iDHA") int iDHA)  {
		HinhAnhEntity ha = this.getHinhAnh(iDHA);
		boolean checkDelete = this.deleteHA(ha);
		SinhVatCanhEntity sVC = ha.getMaSVC();
		String maSVC = sVC.getMaSVC();
		List<HinhAnhEntity> dSHA =  this.getSinhVatCanh(maSVC).getHinhAnhList();
		for (HinhAnhEntity i : dSHA) {
			System.out.println(i.getDuongDan());
		}
		model.addAttribute("dSHinhAnh", dSHA);
		model.addAttribute("name", ha.getMaSVC().getTen());
		return "redirect:/hinhanh/" + SVC.getMaSVC() +".htm?linkPic";
	}
}
