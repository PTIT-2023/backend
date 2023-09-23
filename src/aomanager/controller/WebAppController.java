package aomanager.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import aomanager.entity.SinhVatCanhEntity;
import aomanager.entity.ThayDoiGiaEntity;
import aomanager.model.InputSearchModel;

@Controller
@Transactional
@RequestMapping("webapp")
public class WebAppController {
	
	@Autowired
	SessionFactory factory;
	
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
	
	@RequestMapping("/index")
	public String index(ModelMap model) {
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
		model.addAttribute("taiKhoan", LogController.tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/index";
	}
}
