package com.alethe.opf.dto;


import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserMstResponse {

	private Integer user_id;

	private String loginid;

	private String user_fname;

	private String user_lname;

	private String user_email;

	private String user_contact;

	private String user_password;

	private String user_type;

	private String user_role;

	private Integer user_parent;

	private Date created_on;

	private Integer created_by;

	private Date modified_on;

	private Integer modified_by;

	private Integer is_deleted;

	private Integer is_active;

	private String user_role_name;

	public UserMstResponse(Integer user_id, String loginid, String user_fname, String user_lname, String user_email,
			String user_contact, String user_password, String user_type, String user_role, Integer user_parent,
			Date created_on, Integer created_by, Date modified_on, Integer modified_by, Integer is_deleted,
			Integer is_active, String user_role_name) {
		super();
		this.user_id = user_id;
		this.loginid = loginid;
		this.user_fname = user_fname;
		this.user_lname = user_lname;
		this.user_email = user_email;
		this.user_contact = user_contact;
		this.user_password = user_password;
		this.user_type = user_type;
		this.user_role = user_role;
		this.user_parent = user_parent;
		this.created_on = created_on;
		this.created_by = created_by;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.is_deleted = is_deleted;
		this.is_active = is_active;
		this.user_role_name = user_role_name;
	}
	
	

}
