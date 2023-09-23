package aomanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("test")
public class TestController {
	
	@RequestMapping("test")
	public String index() {
		return "test/test";
	}
	
	@RequestMapping(value = "test2", method = RequestMethod.POST)
	public String index2(HttpServletRequest request) {
		String sata = request.getParameter("abc");
		System.out.println(sata);
		return "test/test";
	}
}
