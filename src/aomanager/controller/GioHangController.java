package aomanager.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

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

import aomanager.entity.CT_GHEntity;
import aomanager.entity.GioHangEntity;
import aomanager.entity.SinhVatCanhEntity;
import aomanager.entity.ThayDoiGiaEntity;
import aomanager.entity.TrangThaiGHEntity;
import aomanager.model.GioHangModel;
import aomanager.model.InputSearchModel;
import aomanager.model.PhieuDatHangModel;

@Transactional
@Controller
@RequestMapping("giohang")
public class GioHangController {

	long millis;

	public static List<GioHangModel> listGH = null;

	GioHangEntity GH = null;

	@Autowired
	SessionFactory factory;
	
	public String getGiaVN(int gia) {
		// TODO Auto-generated method stub
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		return formatter.format(gia)+" VNĐ";

	}
	
	public GioHangEntity getGioHang(String maGH) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM GioHangEntity WHERE maGH = :ma");
		query.setParameter("ma", maGH);
		GioHangEntity result = (GioHangEntity) query.list().get(0);
		return result;
	}
	
	public void updateSoLuong (String maSVC, int sLM) {
		Session session = factory.openSession();
		Query query = session.createQuery("UPDATE SinhVatCanhEntity svc SET svc.soLuongTon = :slm WHERE svc.maSVC = :ma");
		query.setParameter("slm", sLM);
		query.setParameter("ma", maSVC);
		query.executeUpdate();
	}

	List<SinhVatCanhEntity> getSPNgauNhien(int sl) {
		Session session = factory.getCurrentSession();
		String hql = "FROM SinhVatCanhEntity ORDER BY NEWID()";
		Query query = session.createQuery(hql).setMaxResults(sl);
		List<SinhVatCanhEntity> result = query.list();
		for (int i = 0; i < result.size(); i++) {
			SinhVatCanhEntity svc = result.get(i);
			result.get(i).setGia(this.layGiaHienTai(svc));
		}
		return result;
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

	public List<SinhVatCanhEntity> getSP() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM SinhVatCanhEntity");
		List<SinhVatCanhEntity> result = query.list();
		for (int i = 0; i < result.size(); i++) {
			SinhVatCanhEntity svc = result.get(i);
			result.get(i).setGia(this.layGiaHienTai(svc));
		}
		return result;
	}

	public List<SinhVatCanhEntity> getSPBC() {
		Session session = factory.getCurrentSession();
		String hql = "FROM SinhVatCanhEntity sp ORDER BY sp.luotMua DESC";
		Query query = session.createQuery(hql).setMaxResults(10);
		List<SinhVatCanhEntity> result = query.list();
		for (int i = 0; i < result.size(); i++) {
			SinhVatCanhEntity svc = result.get(i);
			result.get(i).setGia(this.layGiaHienTai(svc));
		}
		return result;
	}

	public SinhVatCanhEntity get1SP(String maSVC) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM SinhVatCanhEntity where maSVC = :ma");
		query.setParameter("ma", maSVC);
		SinhVatCanhEntity sinhVatCanh = (SinhVatCanhEntity) query.list().get(0);
		return sinhVatCanh;
	}

	String checkKhongTrungSp(List<GioHangModel> listCTGH, SinhVatCanhEntity SP) {
		if (listCTGH == null) {
			return "rong";
		}
		for (GioHangModel sp : listGH) {
			if (sp.getMaSVC().equals(SP.getMaSVC())) {
				return "trung";
			}
		}
		return "khongtrung";
	}

	public boolean insertGioHang(GioHangEntity gH) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(gH);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public boolean insertCTGH(CT_GHEntity cTGH) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(cTGH);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@RequestMapping("index")
	public String index(ModelMap model) {
		int tongTien = 0;
		if (listGH != null) {
			for (GioHangModel i : listGH) {
				tongTien = tongTien + i.getGia();
			}
		}
		model.addAttribute("dSSP", listGH);
		model.addAttribute("tongTien", this.getGiaVN(tongTien));
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/giohang/giohang";
	}

	@RequestMapping(value = "them")
	public String themVaoGioHang(ModelMap model, @ModelAttribute("giohang") GioHangModel gioHang) {
		String msg = "";
		boolean checkSL = true;
		GioHangModel gh = new GioHangModel();
		gh.setGia(gioHang.getGia() * gioHang.getSoLuong());
		String maSVC = gioHang.getMaSVC();
		SinhVatCanhEntity sVC = this.get1SP(maSVC);
		if (gioHang.getSoLuong() > sVC.getSoLuongTon()) {
			msg = "Không thể thêm vào giỏ hàng do không đủ số lượng!";
			checkSL = false;
		} else {
			gh.setMaSVC(maSVC);
			gh.setTen(sVC.getTen());
			gh.setSoLuong(gioHang.getSoLuong());
			gh.setDuongDan(sVC.getHinhAnhList().get(0).getDuongDan());
			String check = this.checkKhongTrungSp(listGH, sVC);
			if (check.equals("rong")) {
				listGH = new ArrayList<>();
				listGH.add(gh);
				msg = "Thêm sản phẩm vào giỏ hàng thành công!";
			} else if (check.equals("trung")) {
				for (GioHangModel sp : listGH) {
					if (sp.getMaSVC().equals(sVC.getMaSVC())) {
						sp.setSoLuong(gh.getSoLuong() + sp.getSoLuong());
						;
						sp.setGia(sp.getGia() + gh.getGia());
					}
				}
				msg = "Thêm sản phẩm vào giỏ hàng thành công!";
			} else if (check.equals("khongtrung")) {
				listGH.add(gh);
				msg = "Thêm sản phẩm vào giỏ hàng thành công!";
			}
		}
		List<SinhVatCanhEntity> listSPNN = this.getSPNgauNhien(10);
		for (int i = 0; i < listSPNN.size(); i++) {
			if((null == listSPNN.get(i).getHinhAnhList()) || (listSPNN.get(i).getHinhAnhList().size() <= 0) || (listSPNN.get(i).getGia() <= 0)) {
				listSPNN.remove(i);
				i--;
			}
		}
		List<SinhVatCanhEntity> listSPBC = this.getSPBC();
		for (int i = 0; i < listSPBC.size(); i++) {
			if((null == listSPBC.get(i).getHinhAnhList()) || (listSPBC.get(i).getHinhAnhList().size() <= 0) || (listSPBC.get(i).getGia() <= 0)) {
				listSPBC.remove(i);
				i--;
			}
		}
		System.out.println("---------------------------------------------");
		if (listGH != null && listGH.size() > 0) {
			for (GioHangModel i : listGH) {
				System.out.println(i.getMaSVC() + " " + i.getGia() + " " + i.getSoLuong());
			}
		}
		System.out.println("---------------------------------------------");
		model.addAttribute("dSSPBC", listSPBC);
		model.addAttribute("dSSPNN", listSPNN);
		model.addAttribute("sp", sVC);
		CT_GHEntity ctgh = new CT_GHEntity();
		ctgh.setMaSVC(sVC);
		ctgh.setGia(sVC.getGia());
		model.addAttribute("giohang", ctgh);
		model.addAttribute("msg", msg);
		if(checkSL == false) {
			return "redirect:/sanpham/" + maSVC + ".htm?linkInfor3";
		}
		return "redirect:/sanpham/" + maSVC + ".htm?linkInfor2";
	}

	@RequestMapping(value = "/{maSVC}.htm", params = "linkDelete")
	public String xoaGioHang(ModelMap model, @PathVariable("maSVC") String maSVC) {
		for (int i = 0; i < listGH.size(); i++) {
			if (maSVC.equals(listGH.get(i).getMaSVC())) {
				listGH.remove(i);
			}
		}
		int tongTien = 0;
		if (listGH != null) {
			for (GioHangModel i : listGH) {
				tongTien = tongTien + i.getGia();
			}
		}
		model.addAttribute("dSSP", listGH);
		model.addAttribute("tongTien", this.getGiaVN(tongTien));
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/giohang/giohang";
	}

	@RequestMapping("dat")
	public String dat(ModelMap model) {
		int tongTien = 0;
		for (GioHangModel i : listGH) {
			tongTien = tongTien + i.getGia();
		}
		model.addAttribute("dSSP", listGH);
		model.addAttribute("tongTien", this.getGiaVN(tongTien));
		GioHangEntity phieuDatHang = new GioHangEntity();
		phieuDatHang.setMaGH("");
		phieuDatHang.setTen(LogController.tKKHHT.getKhachHang().getHo() + " " + LogController.tKKHHT.getKhachHang().getTen());
		phieuDatHang.setDiaChiNhan(LogController.tKKHHT.getKhachHang().getDiaChi());
		phieuDatHang.setsDTNhan(LogController.tKKHHT.getKhachHang().getsDT());
		millis = System.currentTimeMillis();
		java.util.Date dateDat = new java.util.Date(millis);
		Long dateGiao = dateDat.getTime() + 86400000 * 3;
		phieuDatHang.setNgayDat(dateDat);
		phieuDatHang.setNgayGiao(new Date(dateGiao));
		model.addAttribute("phieuDatHang", phieuDatHang);
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/giohang/dathang";
	}

	@RequestMapping("thatbai")
	public String thatbai(ModelMap model) {
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/giohang/thatbai";
	}

	@RequestMapping(value = "dathang", params = "btnSave")
	public String datHang(ModelMap model, @Valid @ModelAttribute("phieuDatHang") GioHangEntity phieuDatHang,
			BindingResult errors) {
		/*if(errors.hasErrors()) {
			model.addAttribute("dSSP", listGH);
			return "webapp/giohang/dathang";
		}*/
		millis = System.currentTimeMillis();
		phieuDatHang.setMaGH(String.valueOf(new java.util.Date(millis)));
		phieuDatHang.setMaKH(LogController.tKKHHT.getKhachHang());
		TrangThaiGHEntity tTGH = new TrangThaiGHEntity("TT1", "Vừa đặt hàng");
		phieuDatHang.setMaTT(tTGH);
		if(phieuDatHang.getTen().equals("")) {
			phieuDatHang.setTen(null);
		}
		if(phieuDatHang.getDiaChiNhan().equals("")) {
			phieuDatHang.setDiaChiNhan(null);
		}
		if(phieuDatHang.getsDTNhan().equals("")) {
			phieuDatHang.setsDTNhan(null);
		}
		if(phieuDatHang.getEmailNhan().equals("")) {
			phieuDatHang.setEmailNhan(null);
		}
		GH = phieuDatHang;
		boolean check = this.insertGioHang(phieuDatHang);
		String status = "";
		if (check == true) { 
			boolean check2 = true;
			for (GioHangModel i : listGH) {
				CT_GHEntity ctgh = new CT_GHEntity();
				ctgh.setSoLuong(i.getSoLuong());
				ctgh.setGia(i.getGia());
				ctgh.setMaSVC(this.get1SP(i.getMaSVC()));
				ctgh.setMaGH(GH);
				check2 = this.insertCTGH(ctgh);
			}
			if (check2 == true) {
				status = "success";
			} else {
				status = "fail";
			}
		} else {
			status = "fail";
		}
		if(status.equals("success")) {
			listGH = null;
			// Chỉnh số lượng tồn
			List<CT_GHEntity> listCTGH = this.getGioHang(phieuDatHang.getMaGH()).getcT_GHList();
			for (CT_GHEntity ctgh : listCTGH) {
				String maSVC = ctgh.getMaSVC().getMaSVC();
				int sLM = ctgh.getMaSVC().getSoLuongTon() - ctgh.getSoLuong();
				this.updateSoLuong(maSVC, sLM);
			}
			model.addAttribute("msg", "Đặt hàng thành công!");
			return "redirect:/donhang2/index.htm";
		}
		return "redirect:/giohang/thatbai.htm";
	}
}
