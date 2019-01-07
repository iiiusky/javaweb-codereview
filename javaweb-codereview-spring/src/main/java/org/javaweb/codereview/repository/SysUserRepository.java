package org.javaweb.codereview.repository;

import org.javaweb.codereview.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yz
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, String>,
		PagingAndSortingRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {


}
