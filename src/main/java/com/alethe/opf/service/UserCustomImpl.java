package com.alethe.opf.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alethe.opf.entity.Users;

/**
 * Created by Kunal Kumar
 */
public class UserCustomImpl implements UserDetails {

	private static final long serialVersionUID = 8967385087694935891L;

	private String password;
	private String loginid;
	private List<GrantedAuthority> authority;
	private String type = "", role = "";
	private Integer parent;

	public UserCustomImpl() {
		super();
	}

	public UserCustomImpl(Users user) {
		super();
		this.password = user.getUser_password();
		this.loginid = user.getLoginid();
		this.parent = user.getUser_parent();

		type = "";
		role = "";
		type = user.getUser_type();
		type = "ROLE_" + type;

		role = user.getUser_role();
		role = "ROLE_" + role;

		if (user.getUser_type().equalsIgnoreCase("ADM")) {

			this.authority = Arrays.stream(type.split(",")).map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());

		} else if (user.getUser_type().equalsIgnoreCase("USR")) {

			this.authority = Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		} else if (user.getUser_type().equalsIgnoreCase("VWR")) {

			this.authority = Arrays.stream(type.split(",")).map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		}

	}

	public Integer getParent() {

		return parent;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authority;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {
		return loginid;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
