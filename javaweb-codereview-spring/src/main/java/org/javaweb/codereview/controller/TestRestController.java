package org.javaweb.codereview.controller;

import org.javaweb.codereview.entity.SysUser;
import org.javaweb.codereview.service.SysUserService;
import org.javaweb.core.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yz
 */
@RestController
public class TestRestController {

	@Resource
	private SysUserService sysUserService;

	@RequestMapping("/findUserById.php")
	public SysUser findUserById(String userId) {
		return sysUserService.findByUserId(userId);
	}

	@RequestMapping("/addUser.php")
	public SysUser addUser(SysUser sysUser) {
		if (StringUtils.isNotEmpty(sysUser.getUsername()) && StringUtils.isNotEmpty(sysUser.getPassword())) {
			return sysUserService.addUser(sysUser);
		}

		return null;
	}

}
