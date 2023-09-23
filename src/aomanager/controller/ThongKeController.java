package aomanager.controller;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import aomanager.entity.HoaDonEntity;
import aomanager.model.HoaDonModel;
import aomanager.model.ThongKeModel;

@Controller
@Transactional
@RequestMapping("thongke")
public class ThongKeController {
	
	String NBD;
	String NKT;
	int TONG;
	List<HoaDonModel> LISTTHONGKE = new ArrayList<>();
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat dateFormat2 = new SimpleDateFormat("MM/yyyy");
	DateFormat df1 = new SimpleDateFormat("dd");
	DateFormat df2 = new SimpleDateFormat("MM");
	DateFormat df3 = new SimpleDateFormat("yyyy");

	@Autowired
	SessionFactory factory;
	
	public String getGiaVN(int gia) {
		// TODO Auto-generated method stub
		DecimalFormat formatter = new DecimalFormat("###,###,###");

		return formatter.format(gia)+" VNĐ";

	}
	
	List<HoaDonEntity> getSoLieu(Date ngayBD, Date ngayKT) {
		List<HoaDonEntity> data = null;
		Session session = factory.getCurrentSession();
		String hql = "from HoaDonEntity where ngayTao >= :ngayBD and ngayTao <= :ngayKT order by ngayTao";
		Query query = session.createQuery(hql);
		query.setParameter("ngayBD", ngayBD);
		query.setParameter("ngayKT", ngayKT);
		data = query.list();
		return data;
	}
	
	@RequestMapping("/thongke")
	public String thongKe(ModelMap model) {
		model.addAttribute("thongKe", new ThongKeModel());
		return "quanly/thongke/thongke";
	}
	
	@RequestMapping("/laysolieu")
	public String laySoLieu(ModelMap model, @ModelAttribute("thongKe") ThongKeModel thongKe) throws ParseException {
		String bd = dateFormat.format(thongKe.getNgayBD());
		String kt = dateFormat.format(thongKe.getNgayKT());
		List<HoaDonEntity> listHD = this.getSoLieu(dateFormat.parse(bd), dateFormat.parse(kt));
		List<HoaDonModel> listTemp = new ArrayList<HoaDonModel>();
		int dem = 0;
		for(int i = 0; i < listHD.size(); i++) {
			if(i==0) {
				HoaDonModel hd = new HoaDonModel(listHD.get(i).getNgayTao(), listHD.get(i).getTongTien());
				listTemp.add(hd);
			} else if(listTemp.get(dem).getNgayTao().toString().substring(0, 7).equals(listHD.get(i).getNgayTao().toString().substring(0, 7))) {
				listTemp.get(dem).setTongTien(listTemp.get(dem).getTongTien() + listHD.get(i).getTongTien());
			} else {
				HoaDonModel hd = new HoaDonModel(listHD.get(i).getNgayTao(), listHD.get(i).getTongTien());
				listTemp.add(hd);
				dem ++;
			}
		}
		LISTTHONGKE = listTemp;
		int tong = 0;
		for (HoaDonModel i : listTemp) {
			//System.out.println(dateFormat2.format(i.getNgayTao()) + " " + i.getTongTien());
			tong = tong + i.getTongTien();
			String thangNam = dateFormat2.format(i.getNgayTao());
			i.setNgayTaoTN(thangNam);
		} 
		NBD = bd;
		NKT = kt;
		TONG = tong;
		model.addAttribute("nbd", bd);
		model.addAttribute("nkt", kt);
		model.addAttribute("thongKe", new ThongKeModel());
		model.addAttribute("kq", "kq");
		model.addAttribute("listKQ", listTemp);
		model.addAttribute("tong", this.getGiaVN(tong));
		return "quanly/thongke/thongke";
	}
    
    @RequestMapping(value = "/exportCSV", method = RequestMethod.GET)
	public String exportCsv(ModelMap model, HttpServletResponse servletResponse) throws IOException {
    	Date now = new Date();
		servletResponse.setContentType("text/csv");
		servletResponse.addHeader("Content-Disposition", "attachment; filename=\"ThongKe-" + new Date() + ".csv\"");
		try (CSVPrinter csvPrinter = new CSVPrinter(servletResponse.getWriter(), CSVFormat.EXCEL.withHeader("\ufeff"))) {
			csvPrinter.printRecord("THỐNG KÊ DOANH THU THEO THÁNG");
			csvPrinter.printRecord("CỬA HÀNG KINH DOANH THUỶ SINH VẬT CẢNH");
			csvPrinter.printRecord("Ngày bắt đầu: ", NBD);
			csvPrinter.printRecord("Ngày kết thúc: ", NKT);
			csvPrinter.printRecord("Nhân viên lập: ", LogController.tKNVHT.getNhanVien().getHo() + " " + LogController.tKNVHT.getNhanVien().getTen());
			csvPrinter.printRecord("Thời gian (Tháng - Năm)", "Doanh thu");
			for (HoaDonModel h : LISTTHONGKE) {
				csvPrinter.printRecord(dateFormat2.format(h.getNgayTao()), h.getTongTien() + " VND");
			}
			csvPrinter.printRecord("TỔNG CỘNG: ", TONG + " VND");
			csvPrinter.printRecord("TP HCM, ngày " + df1.format(now) + " tháng " + df2.format(now) + " năm " + df3.format(now));
			csvPrinter.flush();
			csvPrinter.close();
		} catch (IOException e) {
			System.out.println(e + "lỗi xuất file....");
		}
		model.addAttribute("thongKe", new ThongKeModel());
		return "quanly/thongke/thongke";
	}

}
