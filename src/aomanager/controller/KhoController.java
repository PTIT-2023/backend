package aomanager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import aomanager.entity.KhachHangEntity;
import aomanager.entity.LoaiEntity;
import aomanager.entity.SinhVatCanhEntity;
import aomanager.model.SoLuongModel;

@Transactional
@Controller
@RequestMapping("kho")
public class KhoController {
	
	SinhVatCanhEntity SVC;

	@Autowired
	SessionFactory factory;
	
	public void updateSoLuong (String maSVC, int sLM) {
		Session session = factory.openSession();
		Query query = session.createQuery("UPDATE SinhVatCanhEntity svc SET svc.soLuongTon = :slm WHERE svc.maSVC = :ma");
		query.setParameter("slm", sLM);
		query.setParameter("ma", maSVC);
		query.executeUpdate();
	}
	
	public SinhVatCanhEntity getSinhVatCanh(String maSVC) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM SinhVatCanhEntity where maSVC = :ma");
		query.setParameter("ma", maSVC);
		SinhVatCanhEntity sinhVatCanh = (SinhVatCanhEntity) query.list().get(0);
		return sinhVatCanh;
	}
	
	public List<SinhVatCanhEntity>  getDSSinhVatCanh(String maLoai) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM LoaiEntity where maLoai = :ma");
		query.setParameter("ma", maLoai);
		LoaiEntity loai = (LoaiEntity) query.list().get(0);
		List<SinhVatCanhEntity> list = loai.getSinhVatCanhList();
		for (SinhVatCanhEntity sinhVatCanhEntity : list) {
			System.out.println(sinhVatCanhEntity.getMaSVC());
		}
		return list;
	}
	
	@RequestMapping("/dongvat")
	public String indexDV(HttpServletRequest request, ModelMap model) {
		List<SinhVatCanhEntity> list = this.getDSSinhVatCanh("DV");
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("loaiTrang", "DV");
		return "quanly/kho/danhsach";
	}
	
	@RequestMapping("/thucvat")
	public String indexTV(HttpServletRequest request, ModelMap model) {
		List<SinhVatCanhEntity> list = this.getDSSinhVatCanh("TV");
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("loaiTrang", "TV");
		return "quanly/kho/danhsach";
	}
	
	@RequestMapping(value = "/{maSVC}.htm", params = "linkEdit")
	public String preThayDoiSoLuong(ModelMap model, @PathVariable("maSVC") String maSVC) {
		SinhVatCanhEntity sinhVatCanh = this.getSinhVatCanh(maSVC);
		SVC = sinhVatCanh;
		if(sinhVatCanh.getSoLuongTon() == null) {
			sinhVatCanh.setSoLuongTon(0);
		}
		SoLuongModel soLuong = new SoLuongModel(sinhVatCanh.getTen(), sinhVatCanh.getSoLuongTon(), 0);
		model.addAttribute("sinhVatCanh", sinhVatCanh);
		model.addAttribute("soLuong", soLuong);
		return "quanly/kho/thaydoisoluong";
	}
	
	@RequestMapping("/thaydoisoluong")
	public String thayDoiSoLuong(ModelMap model, @ModelAttribute("soLuong") SoLuongModel soLuong) {
		this.updateSoLuong(SVC.getMaSVC(), soLuong.getSoLuongMoi());
		model.addAttribute("msg", "Cập nhật số lượng thành công!");
		model.addAttribute("sinhVatCanh", SVC);
		if(SVC.getSoLuongTon() == null) {
			SVC.setSoLuongTon(0);
		}
		SoLuongModel soLuong2 = new SoLuongModel(SVC.getTen(), soLuong.getSoLuongMoi(), 0);
		model.addAttribute("soLuong", soLuong2);
		return "quanly/kho/thaydoisoluong";
	}
}
