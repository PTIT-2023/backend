package aomanager.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import aomanager.entity.CT_GHEntity;
import aomanager.entity.GioHangEntity;
import aomanager.entity.TrangThaiGHEntity;
import aomanager.model.GioHangModel;
import aomanager.model.InputSearchModel;

@Transactional
@Controller
@RequestMapping("donhang2")
public class DonHangKHController {

	@Autowired
	SessionFactory factory;
	
	public String getGiaVN(int gia) {
		// TODO Auto-generated method stub
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		return formatter.format(gia)+" VNĐ";

	}
	
	public void updateSoLuong (String maSVC, int sLM) {
		Session session = factory.openSession();
		Query query = session.createQuery("UPDATE SinhVatCanhEntity svc SET svc.soLuongTon = :slm WHERE svc.maSVC = :ma");
		query.setParameter("slm", sLM);
		query.setParameter("ma", maSVC);
		query.executeUpdate();
	}
	
	public List<GioHangEntity> getGioHangTheoTT(String maTT, String maKH) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM TrangThaiGHEntity WHERE maTT = :ma");
		query.setParameter("ma", maTT);
		TrangThaiGHEntity trangThai = (TrangThaiGHEntity) query.list().get(0);
		List<GioHangEntity> result = trangThai.getGioHang();
		for(int i = 0; i < result.size(); i++) {
			if(!result.get(i).getMaKH().getMaKH().equals(maKH)) {
				result.remove(i);
				i--;
			}
		}
		return result;
	}
	
	public GioHangEntity getGioHang(String maGH) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM GioHangEntity WHERE maGH = :ma");
		query.setParameter("ma", maGH);
		GioHangEntity result = (GioHangEntity) query.list().get(0);
		return result;
	}
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, ModelMap model) {
		List<GioHangEntity> result = this.getGioHangTheoTT("TT1", LogController.tKKHHT.getKhachHang().getMaKH());
		for(int i = 0; i < result.size(); i++) {
			for(int j = i +1; j< result.size(); j++) {
				if(result.get(i).getNgayDat().getTime() < result.get(j).getNgayDat().getTime()) {
					GioHangEntity temp = result.get(i);
					result.set(i, result.get(j));
					result.set(j,  temp);
				}
			}
		}
		PagedListHolder pagedListHolder = new PagedListHolder(result);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		for (GioHangEntity i : result) {
			System.out.println(i.getTen());
		}
		model.addAttribute("taiKhoan",LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/donhang/choxacnhan";
	}
	
	@RequestMapping(value = "/{maGH}.htm", params = "linkChiTiet")
	public String xemThongTin(ModelMap model, @PathVariable("maGH") String maGH) {
		GioHangEntity gioHang = this.getGioHang(maGH);
		List<CT_GHEntity> listGH = gioHang.getcT_GHList();
		List<GioHangModel> listSP = new ArrayList<GioHangModel>();
		for (CT_GHEntity i : listGH) {
			GioHangModel gh  = new GioHangModel();
			gh.setMaSVC(i.getMaSVC().getMaSVC());
			gh.setDuongDan(i.getMaSVC().getHinhAnhList().get(0).getDuongDan());
			gh.setTen(i.getMaSVC().getTen());
			gh.setGia((int)i.getGia());
			gh.setSoLuong(i.getSoLuong());
			listSP.add(gh);
		}
		int tongTien = 0;
		if (listSP != null) {
			for (GioHangModel i : listSP) {
				tongTien = tongTien + (int)i.getGia();
			}
		}
		model.addAttribute("dSSP", listSP);
		model.addAttribute("tongTien", this.getGiaVN(tongTien));
		model.addAttribute("gioHang", gioHang);
		model.addAttribute("loaiTrang", "choXacNhan");
		model.addAttribute("name", "chờ xác nhận");
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/donhang/chitiet";
	}
	
	@RequestMapping(value = "/{maGH}.htm", params = "linkChiTiet2")
	public String xemThongTin2(ModelMap model, @PathVariable("maGH") String maGH) {
		GioHangEntity gioHang = this.getGioHang(maGH);
		List<CT_GHEntity> listGH = gioHang.getcT_GHList();
		List<GioHangModel> listSP = new ArrayList<GioHangModel>();
		for (CT_GHEntity i : listGH) {
			GioHangModel gh  = new GioHangModel();
			gh.setMaSVC(i.getMaSVC().getMaSVC());
			gh.setDuongDan(i.getMaSVC().getHinhAnhList().get(0).getDuongDan());
			gh.setTen(i.getMaSVC().getTen());
			gh.setGia((int)i.getGia());
			gh.setSoLuong(i.getSoLuong());
			listSP.add(gh);
		}
		int tongTien = 0;
		if (listSP != null) {
			for (GioHangModel i : listSP) {
				tongTien = tongTien + (int)i.getGia();
			}
		}
		model.addAttribute("dSSP", listSP);
		model.addAttribute("tongTien", this.getGiaVN(tongTien));
		model.addAttribute("gioHang", gioHang);
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/donhang/chitiet";
	}
	
	@RequestMapping(value = "/{maGH}.htm", params = "linkHuy")
	public String huyDonHang(HttpServletRequest request, ModelMap model, @PathVariable("maGH") String maGH) {
		Session session = factory.openSession();
		Query query = session.createQuery("UPDATE GioHangEntity gh SET gh.maTT = 'TT5' WHERE gh.maGH = :ma");
		query.setParameter("ma", maGH);
		query.executeUpdate();
		
		// Chỉnh số lượng tồn
		List<CT_GHEntity> listCTGH = this.getGioHang(maGH).getcT_GHList();
		for (CT_GHEntity ctgh : listCTGH) {
			String maSVC = ctgh.getMaSVC().getMaSVC();
			int sLM = ctgh.getMaSVC().getSoLuongTon() + ctgh.getSoLuong();
			this.updateSoLuong(maSVC, sLM);
		}
		
		List<GioHangEntity> result = this.getGioHangTheoTT("TT1", LogController.tKKHHT.getKhachHang().getMaKH());
		for(int i = 0; i < result.size(); i++) {
			for(int j = i +1; j< result.size(); j++) {
				if(result.get(i).getNgayDat().getTime() > result.get(j).getNgayDat().getTime()) {
					GioHangEntity temp = result.get(i);
					result.set(i, result.get(j));
					result.set(j,  temp);
				}
			}
		}
		PagedListHolder pagedListHolder = new PagedListHolder(result);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		for (GioHangEntity i : result) {
			System.out.println(i.getTen());
		}
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		return "redirect:/donhang2/index.htm";
	}
	
	@RequestMapping("/daxacnhan")
	public String daXacNhan(HttpServletRequest request, ModelMap model) {
		List<GioHangEntity> result = this.getGioHangTheoTT("TT2", LogController.tKKHHT.getKhachHang().getMaKH());
		for(int i = 0; i < result.size(); i++) {
			for(int j = i +1; j< result.size(); j++) {
				if(result.get(i).getNgayDat().getTime() > result.get(j).getNgayDat().getTime()) {
					GioHangEntity temp = result.get(i);
					result.set(i, result.get(j));
					result.set(j,  temp);
				}
			}
		}
		PagedListHolder pagedListHolder = new PagedListHolder(result);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		for (GioHangEntity i : result) {
			System.out.println(i.getTen());
		}
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/donhang/daxacnhan";
	}
	
	@RequestMapping("/danggiao")
	public String dangGiao(HttpServletRequest request, ModelMap model) {
		List<GioHangEntity> result = this.getGioHangTheoTT("TT3", LogController.tKKHHT.getKhachHang().getMaKH());
		for(int i = 0; i < result.size(); i++) {
			for(int j = i +1; j< result.size(); j++) {
				if(result.get(i).getNgayDat().getTime() > result.get(j).getNgayDat().getTime()) {
					GioHangEntity temp = result.get(i);
					result.set(i, result.get(j));
					result.set(j,  temp);
				}
			}
		}
		PagedListHolder pagedListHolder = new PagedListHolder(result);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		for (GioHangEntity i : result) {
			System.out.println(i.getTen());
		}
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/donhang/danggiao";
	}
	
	@RequestMapping("/dagiao")
	public String daGiao(HttpServletRequest request, ModelMap model) {
		List<GioHangEntity> result = this.getGioHangTheoTT("TT4", LogController.tKKHHT.getKhachHang().getMaKH());
		for(int i = 0; i < result.size(); i++) {
			for(int j = i +1; j< result.size(); j++) {
				if(result.get(i).getNgayDat().getTime() > result.get(j).getNgayDat().getTime()) {
					GioHangEntity temp = result.get(i);
					result.set(i, result.get(j));
					result.set(j,  temp);
				}
			}
		}
		PagedListHolder pagedListHolder = new PagedListHolder(result);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		for (GioHangEntity i : result) {
			System.out.println(i.getTen());
		}
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/donhang/dagiao";
	}
	
	@RequestMapping("/dahuy")
	public String daHuy(HttpServletRequest request, ModelMap model) {
		List<GioHangEntity> result = this.getGioHangTheoTT("TT5", LogController.tKKHHT.getKhachHang().getMaKH());
		for(int i = 0; i < result.size(); i++) {
			for(int j = i +1; j< result.size(); j++) {
				if(result.get(i).getNgayDat().getTime() > result.get(j).getNgayDat().getTime()) {
					GioHangEntity temp = result.get(i);
					result.set(i, result.get(j));
					result.set(j,  temp);
				}
			}
		}
		PagedListHolder pagedListHolder = new PagedListHolder(result);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		for (GioHangEntity i : result) {
			System.out.println(i.getTen());
		}
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/donhang/dahuy";
	}
}
