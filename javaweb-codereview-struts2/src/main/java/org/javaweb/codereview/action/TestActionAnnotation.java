package org.javaweb.codereview.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 * @author yz
 */
@Action(
		value = "testAnnotation",
		results = {
				@Result(name = "success", location = "/index.jsp", type = "redirect")
		}
)
public class TestActionAnnotation extends ActionSupport {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String execute() {
		System.out.println(username);
		return SUCCESS;
	}

}


