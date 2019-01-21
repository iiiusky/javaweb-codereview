package org.javaweb.codereview.service.impl;

import org.javaweb.codereview.entity.SysUser;
import org.javaweb.codereview.repository.SysUserRepository;
import org.javaweb.codereview.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yz
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserRepository sysUserRepository;

	@Override
	public SysUser findByUserId(String userId) {
		return sysUserRepository.findById(userId).get();
	}

	@Override
	public SysUser addUser(SysUser sysUser) {
		return sysUserRepository.save(sysUser);
	}

}
