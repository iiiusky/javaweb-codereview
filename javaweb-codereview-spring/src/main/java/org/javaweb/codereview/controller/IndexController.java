package org.javaweb.codereview.controller;

import org.javaweb.codereview.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class IndexController {

	@RequestMapping("/index.php")
	public String index() {
		return "/index.html";
	}

}
