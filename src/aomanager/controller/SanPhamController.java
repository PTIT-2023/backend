package aomanager.controller;

import java.text.Collator;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import aomanager.entity.CT_GHEntity;
import aomanager.entity.SinhVatCanhEntity;
import aomanager.entity.ThayDoiGiaEntity;
import aomanager.model.GioHangModel;
import aomanager.model.InputSearchModel;

@Controller
@Transactional
@RequestMapping("sanpham")
public class SanPhamController {
	
	@Autowired
	SessionFactory factory;

	List<SinhVatCanhEntity> getSPNgauNhien(int sl) {
		Session session = factory.getCurrentSession();
		String hql = "FROM SinhVatCanhEntity ORDER BY NEWID()";
		Query query = session.createQuery(hql).setMaxResults(sl);
		List<SinhVatCanhEntity> result = query.list();
		for(int i = 0; i < result.size(); i++) {
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
		Query query = session.createQuery("FROM SinhVatCanhEntity ORDER BY NEWID()");
		List<SinhVatCanhEntity> result = query.list();
		for(int i = 0; i < result.size(); i++) {
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
		for(int i = 0; i < result.size(); i++) {
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
	
	@ModelAttribute("svc")
	public List<SinhVatCanhEntity> getSVC() {
		Session session = factory.getCurrentSession();
		String hql = "FROM SinhVatCanhEntity";
		Query query = session.createQuery(hql);
		List<SinhVatCanhEntity> list = query.list();
		return list;
	}
	
	@RequestMapping("sanpham")
	public String index(ModelMap model, HttpServletRequest request) {
		List<SinhVatCanhEntity> list = this.getSP();
		for (int i = 0; i < list.size(); i++) {
			if((null == list.get(i).getHinhAnhList()) || 0 >= list.get(i).getHinhAnhList().size() || (list.get(i).getGia() <= 0)) {
				list.remove(i);
				i--;
			}
		}
		for (SinhVatCanhEntity i : list) {
				System.out.println(i.getHinhAnhList().get(0).getDuongDan());
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
		model.addAttribute("dSSPBC", listSPBC);
		model.addAttribute("dSSPNN", listSPNN);
		//model.addAttribute("dSSanPham", list);
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(20);
		model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/sanpham/sanpham";
	}
	
	@RequestMapping(value = "/{maSVC}.htm", params = "linkInfor")
	public String chiTietSanPham(ModelMap model, @PathVariable("maSVC") String maSVC) {
		SinhVatCanhEntity sVC = this.get1SP(maSVC);
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
		model.addAttribute("dSSPBC", listSPBC);
		model.addAttribute("dSSPNN", listSPNN);
		model.addAttribute("sp", sVC);
		GioHangModel ctgh = new GioHangModel();
		ctgh.setMaSVC(sVC.getMaSVC());
		ctgh.setGia(sVC.getGia());
		model.addAttribute("giohang", ctgh);
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/sanpham/chitietsanpham";
	}
	
	@RequestMapping(value = "/{maSVC}.htm", params = "linkInfor2")
	public String chiTietSanPham2(ModelMap model, @PathVariable("maSVC") String maSVC) {
		SinhVatCanhEntity sVC = this.get1SP(maSVC);
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
		model.addAttribute("dSSPBC", listSPBC);
		model.addAttribute("dSSPNN", listSPNN);
		model.addAttribute("sp", sVC);
		GioHangModel ctgh = new GioHangModel();
		ctgh.setMaSVC(sVC.getMaSVC());
		ctgh.setGia(sVC.getGia());
		model.addAttribute("giohang", ctgh);
		model.addAttribute("msg", "Thêm vào giỏ hàng thành công!");
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/sanpham/chitietsanpham";
	}
	
	@RequestMapping(value = "/{maSVC}.htm", params = "linkInfor3")
	public String chiTietSanPham3(ModelMap model, @PathVariable("maSVC") String maSVC) {
		SinhVatCanhEntity sVC = this.get1SP(maSVC);
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
		model.addAttribute("dSSPBC", listSPBC);
		model.addAttribute("dSSPNN", listSPNN);
		model.addAttribute("sp", sVC);
		GioHangModel ctgh = new GioHangModel();
		ctgh.setMaSVC(sVC.getMaSVC());
		ctgh.setGia(sVC.getGia());
		model.addAttribute("giohang", ctgh);
		model.addAttribute("msg", "Không thể thêm vào giỏ hàng do không đủ số lượng!");
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/sanpham/chitietsanpham";
	}
	
	public static String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
	
	String chuanHoaChuoi(String str) {
		str = str.trim();
		str = str.replaceAll("\\s+", " ");
        str = str.toLowerCase();
        str = removeDiacriticalMarks(str);
		return str;
	}
	
	@RequestMapping("/timkiem")
	public String timKiem(ModelMap model, @ModelAttribute("inputSearch") InputSearchModel inputSearch) {
		List<SinhVatCanhEntity> list = this.getSP();
		List<SinhVatCanhEntity> listResult = new ArrayList<>();
		for (SinhVatCanhEntity sVC : list) {
			if((this.chuanHoaChuoi(sVC.getTen()).contains(this.chuanHoaChuoi(inputSearch.getInput()))) && (!inputSearch.getInput().trim().isEmpty())) {
				listResult.add(sVC);
			}
		}
		model.addAttribute("dSSanPham", listResult);
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		model.addAttribute("keyWord", inputSearch.getInput());
		return "webapp/sanpham/ketquatimkiem";
	}
}
