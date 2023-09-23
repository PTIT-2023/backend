package aomanager.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import aomanager.bean.Mailer;
import aomanager.entity.SinhVatCanhEntity;
import aomanager.entity.TaiKhoanEntity;
import aomanager.entity.ThayDoiGiaEntity;
import aomanager.model.EmailModel;
import aomanager.model.InputSearchModel;

@Controller
@Transactional
@RequestMapping("log")
public class LogController {

	public static TaiKhoanEntity tKNVHT = null;

	public static TaiKhoanEntity tKKHHT = null;

	@Autowired
	SessionFactory factory;
	
	@Autowired
	JavaMailSender mailer;
	
	public void send(String from, String to, String subject, String body) {
		try {
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			mailer.send(mail);
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
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

	List<SinhVatCanhEntity> getSPNgauNhien() {
		Session session = factory.getCurrentSession();
		String hql = "FROM SinhVatCanhEntity ORDER BY NEWID()";
		Query query = session.createQuery(hql).setMaxResults(5);
		List<SinhVatCanhEntity> result = query.list();
		return result;
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
	public String index(ModelMap model, @Validated @ModelAttribute("taiKhoan") TaiKhoanEntity tK,
			BindingResult errors) {
		return "index";
	}

	@RequestMapping("/login")
	public String login(ModelMap model, HttpSession ss, @ModelAttribute("taiKhoan") TaiKhoanEntity tK) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM TaiKhoanEntity WHERE email = :email AND matKhau = :matkhau");
		query.setParameter("email", tK.getEmail().trim());
		query.setParameter("matkhau", tK.getMatKhau());
		List<TaiKhoanEntity> result = query.list();
		String url = "";
		if (result.size() != 0) {
			TaiKhoanEntity taiKhoan = result.get(0);
			if ((taiKhoan.getNhanVien() != null || taiKhoan.getKhachHang() != null) && !taiKhoan.getMaTT().getmaTT().equals("TT2")) {
				if (taiKhoan.getMaQuyen().getMaQuyen().equals("NV")) {
					tKNVHT = taiKhoan;
					model.addAttribute("taiKhoan", tKNVHT);
					ss.setAttribute("taiKhoanQL", tKNVHT);
					url = "quanly/index";
				} else {
					List<SinhVatCanhEntity> list = this.getSPNgauNhien();
					List<SinhVatCanhEntity> listSPNN = this.getSPNgauNhien(10);
					for (int i = 0; i < listSPNN.size(); i++) {
						if ((null == listSPNN.get(i).getHinhAnhList()) || (listSPNN.get(i).getHinhAnhList().size() <= 0)
								|| (listSPNN.get(i).getGia() <= 0)) {
							listSPNN.remove(i);
							i--;
						}
					}
					List<SinhVatCanhEntity> listSPBC = this.getSPBC();
					for (int i = 0; i < listSPBC.size(); i++) {
						if ((null == listSPBC.get(i).getHinhAnhList()) || (listSPBC.get(i).getHinhAnhList().size() <= 0)
								|| (listSPBC.get(i).getGia() <= 0)) {
							listSPBC.remove(i);
							i--;
						}
					}
					model.addAttribute("dSSPBC", listSPBC);
					model.addAttribute("dSSPNN", listSPNN);
					model.addAttribute("dSSanPham", list);
					tKKHHT = taiKhoan;
					model.addAttribute("taiKhoan", tKKHHT);
					ss.setAttribute("taiKhoanKH", tKKHHT);
					model.addAttribute("inputSearch", new InputSearchModel());
					url = "webapp/index";
				}
			} else {
				url = "index";
				model.addAttribute("msgErr", "Tài khoản chưa được phép sử dụng!");
			}
		} else {
			url = "index";
			model.addAttribute("msgErr", "Kiểm tra lại tên đăng nhập và mật khẩu!");
		}
		return url;
	}

	@RequestMapping(value = "/nvlogout")
	public String logOutNV(ModelMap model, HttpSession ss, @Validated @ModelAttribute("taiKhoan") TaiKhoanEntity tK,
			BindingResult errors) {
		tKNVHT = null;
		ss.setAttribute("taiKhoanQL", tKNVHT);
		return "index";
	}

	@RequestMapping(value = "/khlogout")
	public String logOutKH(ModelMap model, HttpSession ss, @Validated @ModelAttribute("taiKhoan") TaiKhoanEntity tK,
			BindingResult errors) {
		GioHangController.listGH = null;
		tKKHHT = null;
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
		ss.setAttribute("taiKhoanKH", tKKHHT);
		model.addAttribute("taiKhoan", tKKHHT);
		model.addAttribute("inputSearch", new InputSearchModel());
		return "webapp/index";
	}
	
	@RequestMapping("quenmatkhau")
	public String quenMatKhau(ModelMap model) {
		model.addAttribute("email", new EmailModel());
		return "matkhau";
	}
	
	public String taoMatKhau() {
		Random generator = new Random();
		int value = generator.nextInt((999999 - 100000) + 1) + 100000;
		return String.valueOf(value); //tạo ra một chuỗi gồm 6 chữ số ngẫu nhiên
	}
	
	@RequestMapping("datlai")
	public String datLaiMK(ModelMap model, @ModelAttribute("email") EmailModel email) {
		String emailAdress = email.getEmail();
		Session session = factory.getCurrentSession();
		String hql = "FROM TaiKhoanEntity WHERE email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", emailAdress);
		List<TaiKhoanEntity> list = query.list();
		// session.close();
		String page = "";
		if (list.size() == 0) {
			model.addAttribute("msg", "Email này chưa đăng ký tài khoản!");
			page = "matkhau";
			model.addAttribute("email", new EmailModel());
		} else {
			String mk = taoMatKhau();
			// Cập nhật mật khẩu trong CSDL
			Session session2 = factory.openSession();
			Query query2 = session2.createQuery("UPDATE TaiKhoanEntity tk SET tk.matKhau = '" + mk + "' WHERE tk.email = :email");
			query2.setParameter("email", emailAdress);
			query2.executeUpdate();
			// gửi mail
			try {
				this.send("quocnhat71@gmail.com", emailAdress, "MẬT KHẨU MỚI - QUÊN MẬT KHẨU", "Mật khẩu mới của bạn là: " + mk);
				model.addAttribute("msg", "Mật khẩu mới đã được gửi đến email của bạn!");
			} catch (Exception e) {
				model.addAttribute("msg", "Gửi mail thất bại!");
			}
		}
		model.addAttribute("taiKhoan", new TaiKhoanEntity());
		return "index";
	}
}
