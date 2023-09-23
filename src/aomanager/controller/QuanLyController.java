package aomanager.controller;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("quanly")
public class QuanLyController {
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/index")
	public String index(ModelMap model) {
		model.addAttribute("taiKhoan", LogController.tKNVHT);
		return "quanly/index";
	}
}
