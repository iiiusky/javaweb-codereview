package org.javaweb.codereview.service;

import org.javaweb.codereview.entity.SysUser;

/**
 * @author yz
 */
public interface SysUserService {

	SysUser findByUserId(String userId);

	SysUser addUser(SysUser sysUser);

}
