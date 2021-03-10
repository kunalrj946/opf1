package com.alethe.opf.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserParent {
	
	private Integer user_id;
	private String user_role;
	private String login_id;
	
	public UserParent(Integer user_id, String user_role ,String login_id) {
		super();
		this.user_id = user_id;
		this.user_role = user_role;
		this.login_id = login_id;
	}
	


}
