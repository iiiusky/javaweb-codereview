package org.javaweb.codereview.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author sky
 */
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {

	/**
	 * 用户ID
	 */
	@Id
	@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
	@GeneratedValue(generator = "jpa-uuid")
	@Column(name = "user_id", length = 32)
	@JSONField(name = "user_id")
	private String userId;

	/**
	 * 用户名
	 */
	@JSONField(name = "username")
	private String username;

	/**
	 * 用户密码
	 */
	@JSONField(name = "password")
	private String password;

	/**
	 * 用户昵称
	 */
	@JSONField(name = "nick")
	private String nick;

	/**
	 * 真实姓名
	 */
	@JSONField(name = "real_name")
	private String realName;

	/**
	 * 邮箱
	 */
	@JSONField(name = "email")
	private String email;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}