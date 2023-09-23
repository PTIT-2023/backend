package aomanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

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

import aomanager.entity.KhachHangEntity;
import aomanager.entity.QuyenEntity;
import aomanager.entity.TaiKhoanEntity;
import aomanager.entity.TrangThaiTKEntity;
import aomanager.model.ThongTinModel;

@Transactional
@Controller
@RequestMapping("khachhang")
public class KhachHangController {

	TaiKhoanEntity TK = null;

	@Autowired
	SessionFactory factory;

	public TaiKhoanEntity getTaiKhoan(String maTK) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM TaiKhoanEntity where maTK = :ma");
		query.setParameter("ma", maTK);
		TaiKhoanEntity taiKhoan = (TaiKhoanEntity) query.list().get(0);
		return taiKhoan;
	}

	public List<TaiKhoanEntity> getDSTaiKhoan() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM TaiKhoanEntity");
		List<TaiKhoanEntity> result = query.list();
		return result;
	}

	public KhachHangEntity getKhachHang(String ma) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM KhachHangEntity where maKH = :ma");
		query.setParameter("ma", ma);
		KhachHangEntity nv = (KhachHangEntity) query.list().get(0);
		return nv;
	}

	public List<KhachHangEntity> getDSKhachHang() {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM KhachHangEntity");
		List<KhachHangEntity> result = query.list();
		return result;
	}

	public boolean insertKhachHang(KhachHangEntity kH) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(kH);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public boolean updateKhachHang(KhachHangEntity kH) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(kH);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@ModelAttribute("gioitinh")
	public List<String> getGioiTinh() {
		List<String> list = new ArrayList<String>();
		list.add("Nam");
		list.add("Nữ");
		return list;
	}

	@ModelAttribute("quyen")
	public List<QuyenEntity> getQuyen() {
		Session session = factory.getCurrentSession();
		String hql = "FROM QuyenEntity";
		Query query = session.createQuery(hql);
		List<QuyenEntity> list = query.list();
		return list;
	}

	@ModelAttribute("trangthai")
	public List<TrangThaiTKEntity> getTrangThai() {
		Session session = factory.getCurrentSession();
		String hql = "FROM TrangThaiTKEntity";
		Query query = session.createQuery(hql);
		List<TrangThaiTKEntity> list = query.list();
		return list;
	}

	// List<SinhVatCanhEntity> getSPNgauNhien() {
	// Session session = factory.getCurrentSession();
	// String hql = "FROM SinhVatCanhEntity ORDER BY NEWID()";
	// Query query = session.createQuery(hql).setMaxResults(5);
	// List<SinhVatCanhEntity> result = query.list();
	// return result;
	// }

	@RequestMapping("/index")
	public String index(HttpServletRequest request, ModelMap model) {
		PagedListHolder pagedListHolder = new PagedListHolder(this.getDSKhachHang());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(10);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "quanly/khachhang/index";
	}

	@RequestMapping(value = "/{maTK}.htm", params = "linkAddKhachHang")
	public String linkThem(ModelMap model, @PathVariable("maTK") String maTK) {
		TK = this.getTaiKhoan(maTK);
		model.addAttribute("taiKhoan", TK.getMaTK());
		model.addAttribute("khachHang", new KhachHangEntity());
		model.addAttribute("loaiTrang", "taoKhachHang");
		return "quanly/taikhoan/themnguoidung";
	}

	@RequestMapping("/them")
	public String themKhachHang(ModelMap model, HttpServletRequest request,
			@Validated @ModelAttribute("khachHang") KhachHangEntity kH, BindingResult errors) {
		kH.setMaTK(TK);
		boolean checkUpdate = this.insertKhachHang(kH);
		String msg = "";
		String url = "";
		if (checkUpdate == true) {
			msg = "Tạo khách hàng thành công!";
			url = "quanly/taikhoan/index";
			PagedListHolder pagedListHolder = new PagedListHolder(this.getDSTaiKhoan());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(5);
			model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("taiKhoan", new TaiKhoanEntity());
			model.addAttribute("loaiTrang", "them");
		} else {
			msg = "Tạo khách hàng thất bại, đã xảy ra lỗi!";
			url = "quanly/taikhoan/themnguoidung";
			model.addAttribute("loaiTrang", "taoKhachHang");
			model.addAttribute("taiKhoan", TK.getMaTK());
		}
		model.addAttribute("msg", msg);
		return url;
	}

	@RequestMapping(value = "/{maKH}.htm", params = "linkEdit")
	public String editKH(ModelMap model, @PathVariable("maKH") String maKH) {
		KhachHangEntity khachHang = this.getKhachHang(maKH);
		TK = khachHang.getMaTK();
		model.addAttribute("khachHang", khachHang);
		return "quanly/khachhang/sua";
	}

	@RequestMapping(value = "sua", params = "btnSave")
	public String suaKhachHang(ModelMap model, HttpServletRequest request,
			@Validated @ModelAttribute("khachHang") KhachHangEntity kH, BindingResult errors) {
		kH.setMaTK(TK);
		boolean checkUpdate = this.updateKhachHang(kH);
		;
		String msg = "";
		String url = "";
		if (checkUpdate == true) {
			msg = "Chỉnh sửa khách hàng thành công!";
			url = "quanly/khachhang/index";
			PagedListHolder pagedListHolder = new PagedListHolder(this.getDSKhachHang());
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setMaxLinkedPages(5);
			pagedListHolder.setPageSize(10);
			model.addAttribute("pagedListHolder", pagedListHolder);
		} else {
			msg = "Chỉnh sửa khách hàng thất bại, đã xảy ra lỗi!";
			url = "quanly/khachhang/sua";
		}
		model.addAttribute("msg", msg);
		return url;
	}

	@RequestMapping("dangki")
	public String dangKi(ModelMap model) {
		ThongTinModel thongTin = new ThongTinModel();
		model.addAttribute("thongTin", thongTin);
		return "dangki";
	}

	public boolean insertTaiKhoan(TaiKhoanEntity tK) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(tK);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@RequestMapping("dangkitaikhoan")
	public String dangKiTaiKhoan(ModelMap model, @Valid @ModelAttribute("thongTin") ThongTinModel thongTin, BindingResult errors) {
		if (errors.hasFieldErrors()) {
			return "dangki";
		}
		
		boolean checkAdd;
		String email = thongTin.getEmail();
		String matKhau = thongTin.getMatKhau();
		String maTK = email.substring(0, email.indexOf("@"));
		QuyenEntity maQuyen = new QuyenEntity("KH", "Khách hàng");
		TrangThaiTKEntity maTT = new TrangThaiTKEntity("TT1", "Hoạt động");
		TaiKhoanEntity taiKhoan = new TaiKhoanEntity(maTK, email, matKhau, maQuyen, maTT);
		checkAdd = this.insertTaiKhoan(taiKhoan);
		
		if(checkAdd == false) {
			model.addAttribute("msg", "Đã xảy ra lỗi, không thể thêm tài khoản!");
			return "dangki";
		} else {
			String ho = thongTin.getHo();
			String ten = thongTin.getTen();
			String gioiTinh = thongTin.getGioiTinh();
			String diaChi = thongTin.getDiaChi();
			String sDT = thongTin.getsDT();
			String maSoThue = null;
			if(!thongTin.getMaSoThue().isEmpty()) {
				maSoThue = thongTin.getMaSoThue();
			}
			KhachHangEntity khachHang = new KhachHangEntity(maTK, ho, ten, gioiTinh, diaChi, sDT, maSoThue, taiKhoan);
			checkAdd = this.insertKhachHang(khachHang);
			if(checkAdd == false) {
				model.addAttribute("msg", "Đã xảy ra lỗi, không thể thêm tài khoản!");
				return "dangki";
			}
		}
		model.addAttribute("msg", "Đăng ký tài khoản thành công!");
		TaiKhoanEntity taiKhoanTemp = new TaiKhoanEntity();
		model.addAttribute("taiKhoan", taiKhoanTemp);
		return "index";
	}
}
