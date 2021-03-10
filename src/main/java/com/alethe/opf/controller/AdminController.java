package com.alethe.opf.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alethe.opf.dto.AuthRequest;
import com.alethe.opf.entity.Users;
import com.alethe.opf.exception.ResourceNotFoundException;
import com.alethe.opf.service.UserServiceI;
import com.alethe.opf.utility.JwtUtil;

/**
 * Created by Kunal Kumar
 */
@RestController
public class AdminController {

	@Autowired
	private UserServiceI userS;
	@Autowired
	private UserServiceI serv;

	@Autowired
	private JwtUtil jwtutil;

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);

	@CrossOrigin
	@PreAuthorize("hasRole('ADM')")
	@PostMapping("/user_mst/add")
	public Object addUserByAdmin(@RequestBody Users userMst, HttpServletRequest request, BindingResult brs)
			throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<String, Object>();

		logger.info("you have sent the data :" + userMst.toString());
//		 String pswd = userMst.getUser_password();
//		 String encrtpPSWD = encoder.encode(pswd);
//		 userMst.setUser_password(encrtpPSWD);
		if (!brs.hasErrors()) {
			result = userS.addUser(userMst);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@CrossOrigin
	@PreAuthorize("hasRole('ADM')")
	@GetMapping("/user_mst/getAllData")
	public Object getAllData() throws ResourceNotFoundException {

		HashMap<String, Object> result = new HashMap<String, Object>();
		result = userS.getAllUsers();

		return result;
	}

	@CrossOrigin
	@PreAuthorize("hasRole('ADM')")
	@GetMapping("/user_mst/getById/{user_id}")
	public Object getCompanyById(@PathVariable(value = "user_id") Integer user_id) throws ResourceNotFoundException {

		HashMap<String, Object> result = new HashMap<String, Object>();
		result = userS.getUserById(user_id);

		return result;
	}

	@CrossOrigin
	@PreAuthorize("hasRole('ADM')")
	@PutMapping("/user_mst/updateById/{user_id}")
	public Object updateUser(@PathVariable(value = "user_id") Integer user_id, @RequestBody Users users)
			throws ResourceNotFoundException {

		HashMap<String, Object> result = new HashMap<String, Object>();
		result = userS.updateUserById(users, user_id);

		return ResponseEntity.ok(result);
	}

	@CrossOrigin
	@PreAuthorize("hasRole('ADM')")
	@DeleteMapping("/user_mst/deleteById/{user_id}")
	public Object deleteUser(@PathVariable(value = "user_id") Integer userId) throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result = userS.deleteUserById(userId);

		return ResponseEntity.ok(result);
	}

	@CrossOrigin
	@GetMapping("/")
	public String welcome() {
		return "Welcome !!";
	}

	@CrossOrigin
	@PostMapping("/get-token")
	public HashMap<String, Object> generateToken(@RequestBody AuthRequest authRequest)
			throws Exception, UsernameNotFoundException {

		HashMap<String, Object> result = new HashMap<String, Object>();
//		encoder.encode(authRequest.getPassword());
//		authRequest.setPassword(encoder.encode(authRequest.getPassword()));
		result = serv.getToken(authRequest);
		return result;
	}

	@CrossOrigin
	@PostMapping("/server-logout")
	public HashMap<String, Object> destroyToken(@RequestHeader(name = "Authorization") String token)
			throws Exception, UsernameNotFoundException {

		logger.debug("logout executed ...");

		HashMap<String, Object> result = new HashMap<String, Object>();

		String loginid = null;

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		loginid = jwtutil.extractUsername(token);
//		logger.debug("username is :" +loginid);

		result = userS.logout(token, loginid);

		return result;
	}

	@CrossOrigin
	@PreAuthorize("hasRole('ADM')")
	@GetMapping("/user_mst/getParent")
	public HashMap<String, Object> getParent(@RequestHeader(name = "Authorization") String token,
			@RequestParam(required = true) String role) {

		HashMap<String, Object> result = new HashMap<String, Object>();

		result = userS.getParent(role);

		return result;
	}

}
