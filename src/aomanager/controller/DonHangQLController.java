package aomanager.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import aomanager.entity.CT_GHEntity;
import aomanager.entity.GioHangEntity;
import aomanager.entity.HoaDonEntity;
import aomanager.entity.SinhVatCanhEntity;
import aomanager.entity.TaiKhoanEntity;
import aomanager.entity.TrangThaiGHEntity;
import aomanager.model.GioHangModel;

@Transactional
@Controller
@RequestMapping("donhang")
public class DonHangQLController {
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	long millis;
	
	int TongTien;
	
	public String getGiaVN(int gia) {
		// TODO Auto-generated method stub
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		return formatter.format(gia)+" VNĐ";

	}
	
	public static HashMap<String, String> hm_tien = new HashMap<String, String>() {
        {
            put("0", "không");
            put("1", "một");
            put("2", "hai");
            put("3", "ba");
            put("4", "bốn");
            put("5", "năm");
            put("6", "sáu");
            put("7", "bảy");
            put("8", "tám");
            put("9", "chín");
        }
    };
    public static HashMap<String, String> hm_hanh = new HashMap<String, String>() {
        {
            put("1", "đồng");
            put("2", "mươi");
            put("3", "trăm");
            put("4", "nghìn");
            put("5", "mươi");
            put("6", "trăm");
            put("7", "triệu");
            put("8", "mươi");
            put("9", "trăm");
            put("10", "tỷ");
            put("11", "mươi");
            put("12", "trăm");
            put("13", "nghìn");
            put("14", "mươi");
            put("15", "trăm");

        }
    };

    public static String ChuyenSangChu(String x) {
        StringBuilder kq = new StringBuilder();
        x = x.replace(".", "");
        String[] arr_temp = x.split(",");
        String m = arr_temp[0];
        int dem = m.length();
        String dau = "";
        int flag10 = 1;
        while (!m.equals("")) {
            if (m.length() <= 3 && m.length() > 1 && Long.parseLong(m) == 0) {

            } else {
                dau = m.substring(0, 1);
                if (dem % 3 == 1 && m.startsWith("1") && flag10 == 0) {
                    kq.append("mốt ");
                } else if (dem % 3 == 1 && m.startsWith("5") && flag10 == 0) {
                    kq.append("lăm ");
                } else if (dem % 3 == 2 && m.startsWith("1")) {
                    kq.append("mười ");
                    flag10 = 1;
                } else if (dem % 3 == 2 && m.startsWith("0") && m.charAt(1) != '0') {
                    //System.out.println("a  "+m.substring(1, 2));
                    kq.append("lẻ ");
                    flag10 = 1;
                } else {
                    if (!m.startsWith("0")) {
                        kq.append(hm_tien.get(dau)).append(" ");
                        flag10 = 0;
                    }
                }
                if (dem % 3 != 1 && m.startsWith("0")) {
                } else {
                    if (dem % 3 == 2 && (m.startsWith("1") || m.startsWith("0"))) {//mười
                    } else {
                        kq.append(hm_hanh.get(dem + "")).append(" ");
                    }
                }
            }
            m = m.substring(1);
            dem = m.length();
        }
        kq = new StringBuilder(kq.substring(0, kq.length() - 1));
        return kq.toString();
    }

	@Autowired
	SessionFactory factory;
	
	public void congLuotMua(String maSVC, int lMHT) {
		int lmm = lMHT + 1;
		Session session = factory.openSession();
		Query query = session.createQuery("UPDATE SinhVatCanhEntity svc SET svc.luotMua = :lmm WHERE svc.maSVC = :ma");
		query.setParameter("lmm", lmm);
		query.setParameter("ma", maSVC);
		query.executeUpdate();
	}
	
	public List<GioHangEntity> getGioHangTheoTT(String maTT) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM TrangThaiGHEntity WHERE maTT = :ma");
		query.setParameter("ma", maTT);
		TrangThaiGHEntity trangThai = (TrangThaiGHEntity) query.list().get(0);
		List<GioHangEntity> result = trangThai.getGioHang();
		return result;
	}
	
	public GioHangEntity getGioHang(String maGH) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM GioHangEntity WHERE maGH = :ma");
		query.setParameter("ma", maGH);
		GioHangEntity result = (GioHangEntity) query.list().get(0);
		return result;
	}
	
	public boolean insertHoaDon(HoaDonEntity hD) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(hD);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean updateGioHang(GioHangEntity gH) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(gH);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	@RequestMapping("/choxacnhan")
	public String choXacNhan(HttpServletRequest request, ModelMap model) {
		List<GioHangEntity> result = this.getGioHangTheoTT("TT1");
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
		return "quanly/donhang/choxacnhan";
	}
	
	public void updateSoLuong (String maSVC, int sLM) {
		Session session = factory.openSession();
		Query query = session.createQuery("UPDATE SinhVatCanhEntity svc SET svc.soLuongTon = :slm WHERE svc.maSVC = :ma");
		query.setParameter("slm", sLM);
		query.setParameter("ma", maSVC);
		query.executeUpdate();
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
		
		List<GioHangEntity> result = this.getGioHangTheoTT("TT1");
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
		model.addAttribute("msg2", "Huỷ đơn hàng thành công!");
		return "quanly/donhang/choxacnhan";
	}
	
	@RequestMapping(value = "/{maGH}.htm", params = "linkXacNhan")
	public String xacNhanDonHang(HttpServletRequest request, ModelMap model, @PathVariable("maGH") String maGH) throws ParseException {
		Session session = factory.openSession();
		Query query = session.createQuery("UPDATE GioHangEntity gh SET gh.maTT = 'TT2' WHERE gh.maGH = :ma");
		query.setParameter("ma", maGH);
		query.executeUpdate();
		
		List<GioHangEntity> result = this.getGioHangTheoTT("TT1");
		for(int i = 0; i < result.size(); i++) {
			for(int j = i +1; j< result.size(); j++) {
				if(result.get(i).getNgayDat().getTime() > result.get(j).getNgayDat().getTime()) {
					GioHangEntity temp = result.get(i);
					result.set(i, result.get(j));
					result.set(j,  temp);
				}
			}
		}
		
		//Lập hoá đơn
		HoaDonEntity hoaDon = new HoaDonEntity();
		millis = System.currentTimeMillis();
		hoaDon.setMaHD(String.valueOf(new java.util.Date(millis)));
		hoaDon.setNgayTao(new java.util.Date(millis));
		hoaDon.setTongTien(TongTien);
		if(this.getGioHang(maGH).getMaKH().getMaSoThue() != null && !this.getGioHang(maGH).getMaKH().getMaSoThue().equals("")) {
			hoaDon.setMaSoThue(this.getGioHang(maGH).getMaKH().getMaSoThue());
		}
		hoaDon.setMaNV(LogController.tKNVHT.getNhanVien());
		this.insertHoaDon(hoaDon);
		
		
		GioHangEntity gHUpdate = this.getGioHang(maGH);
		gHUpdate.setMaHD(hoaDon);
		
		PagedListHolder pagedListHolder = new PagedListHolder(result);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		for (GioHangEntity i : result) {
			System.out.println(i.getTen());
		}
		
		GioHangEntity gioHang = this.getGioHang(maGH);
		List<CT_GHEntity> listGH = gioHang.getcT_GHList();
		List<GioHangModel> listSP = new ArrayList<GioHangModel>();
		for (CT_GHEntity i : listGH) {
			GioHangModel gh  = new GioHangModel();
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
		model.addAttribute("gioHang", gHUpdate);
		model.addAttribute("nhanVien", LogController.tKNVHT.getNhanVien());
		model.addAttribute("hoaDon", hoaDon);
		model.addAttribute("ngayTao", dateFormat.format(hoaDon.getNgayTao()));
		model.addAttribute("msg1", "Xác nhận đơn hàng thành công!");
		model.addAttribute("dSSP", listSP);
		model.addAttribute("tongTien", this.getGiaVN(tongTien));
		model.addAttribute("tienChu", DonHangQLController.ChuyenSangChu(String.valueOf(tongTien)));
		return "quanly/donhang/choxacnhan";
	}
	
	@RequestMapping(value = "/{maGH}.htm", params = "linkChiTiet1")
	public String xemThongTin1(ModelMap model, @PathVariable("maGH") String maGH) {
		GioHangEntity gioHang = this.getGioHang(maGH);
		List<CT_GHEntity> listGH = gioHang.getcT_GHList();
		List<GioHangModel> listSP = new ArrayList<GioHangModel>();
		for (CT_GHEntity i : listGH) {
			GioHangModel gh  = new GioHangModel();
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
		TongTien = tongTien;
		model.addAttribute("dSSP", listSP);
		model.addAttribute("tongTien", this.getGiaVN(tongTien));
		model.addAttribute("gioHang", gioHang);
		model.addAttribute("loaiTrang", "choXacNhan");
		model.addAttribute("name", "chờ xác nhận");
		return "quanly/donhang/chitiet";
	}
	
	@RequestMapping("/daxacnhan")
	public String daXacNhan(HttpServletRequest request, ModelMap model) {
		List<GioHangEntity> result = this.getGioHangTheoTT("TT2");
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
		return "quanly/donhang/daxacnhan";
	}
	
	@RequestMapping(value = "/{maGH}.htm", params = "linkChiTiet2")
	public String xemThongTin2(ModelMap model, @PathVariable("maGH") String maGH) {
		GioHangEntity gioHang = this.getGioHang(maGH);
		List<CT_GHEntity> listGH = gioHang.getcT_GHList();
		List<GioHangModel> listSP = new ArrayList<GioHangModel>();
		for (CT_GHEntity i : listGH) {
			GioHangModel gh  = new GioHangModel();
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
		TongTien = tongTien;
		model.addAttribute("dSSP", listSP);
		model.addAttribute("tongTien", this.getGiaVN(tongTien));
		model.addAttribute("gioHang", gioHang);
		model.addAttribute("loaiTrang", "daXacNhan");
		model.addAttribute("name", "đã xác nhận");
		return "quanly/donhang/chitiet";
	}
	
	@RequestMapping(value = "/{maGH}.htm", params = "linkGiao")
	public String xacNhanGiaoDonHang(HttpServletRequest request, ModelMap model, @PathVariable("maGH") String maGH) {
		Session session = factory.openSession();
		Query query = session.createQuery("UPDATE GioHangEntity gh SET gh.maTT = 'TT3' WHERE gh.maGH = :ma");
		query.setParameter("ma", maGH);
		query.executeUpdate();
		
		List<GioHangEntity> result = this.getGioHangTheoTT("TT2");
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
		model.addAttribute("msg", "Xác nhận đơn hàng thành công!");
		return "quanly/donhang/daxacnhan";
	}
	
	@RequestMapping("/danggiao")
	public String dangGiao(HttpServletRequest request, ModelMap model) {
		List<GioHangEntity> result = this.getGioHangTheoTT("TT3");
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
		return "quanly/donhang/danggiao";
	}
	
	@RequestMapping(value = "/{maGH}.htm", params = "linkDaGiao")
	public String xacNhanDaGiaoDonHang(HttpServletRequest request, ModelMap model, @PathVariable("maGH") String maGH) {
		Session session = factory.openSession();
		Query query = session.createQuery("UPDATE GioHangEntity gh SET gh.maTT = 'TT4' WHERE gh.maGH = :ma");
		query.setParameter("ma", maGH);
		query.executeUpdate();
		
		//cộng lượt mua
		List<CT_GHEntity> listCTGH = this.getGioHang(maGH).getcT_GHList();
		for (CT_GHEntity ctgh : listCTGH) {
			String maSVC = ctgh.getMaSVC().getMaSVC();
			int lMHT = ctgh.getMaSVC().getLuotMua();
			this.congLuotMua(maSVC, lMHT);
		}
		
		List<GioHangEntity> result = this.getGioHangTheoTT("TT3");
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
		model.addAttribute("msg", "Xác nhận đã giao đơn hàng thành công!");
		return "quanly/donhang/danggiao";
	}
	
	@RequestMapping("/dagiao")
	public String daGiao(HttpServletRequest request, ModelMap model) {
		List<GioHangEntity> result = this.getGioHangTheoTT("TT4");
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
		return "quanly/donhang/dagiao";
	}
	
	@RequestMapping("/dahuy")
	public String daHuy(HttpServletRequest request, ModelMap model) {
		List<GioHangEntity> result = this.getGioHangTheoTT("TT5");
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
		return "quanly/donhang/dahuy";
	}
	
	@RequestMapping(value = "/{maGH}.htm", params = "linkChiTiet")
	public String xemThongTin(ModelMap model, @PathVariable("maGH") String maGH) {
		GioHangEntity gioHang = this.getGioHang(maGH);
		List<CT_GHEntity> listGH = gioHang.getcT_GHList();
		List<GioHangModel> listSP = new ArrayList<GioHangModel>();
		for (CT_GHEntity i : listGH) {
			GioHangModel gh  = new GioHangModel();
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
		TongTien = tongTien;
		model.addAttribute("dSSP", listSP);
		model.addAttribute("tongTien", this.getGiaVN(tongTien));
		model.addAttribute("gioHang", gioHang);
		return "quanly/donhang/chitiet";
	}
	
	@RequestMapping(value = "/{maGH}.htm", params = "linkXemHoaDon")
	public String xemThongTinHoaDon(ModelMap model, @PathVariable("maGH") String maGH) {
		HoaDonEntity hoaDon = this.getGioHang(maGH).getMaHD();
		List<CT_GHEntity> listGH = this.getGioHang(maGH).getcT_GHList();
		List<GioHangModel> listSP = new ArrayList<GioHangModel>();
		for (CT_GHEntity i : listGH) {
			GioHangModel gh  = new GioHangModel();
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
		model.addAttribute("gioHang", this.getGioHang(maGH));
		model.addAttribute("hoaDon", hoaDon);
		model.addAttribute("dSSP", listSP);
		model.addAttribute("tongTien", this.getGiaVN(tongTien));
		model.addAttribute("tienChu", DonHangQLController.ChuyenSangChu(String.valueOf(tongTien)));
		model.addAttribute("nhanVien", LogController.tKNVHT.getNhanVien());
		model.addAttribute("ngayTao", dateFormat.format(hoaDon.getNgayTao()));
		return "quanly/donhang/hoadon";
	}
}
