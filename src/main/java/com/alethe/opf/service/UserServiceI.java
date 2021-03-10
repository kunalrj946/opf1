 package com.alethe.opf.service;

import java.text.ParseException;
import java.util.HashMap;

import com.alethe.opf.dto.AuthRequest;
import com.alethe.opf.dto.SoRequest;
import com.alethe.opf.dto.SoStatusPojo;
import com.alethe.opf.entity.Users;
import com.alethe.opf.exception.ResourceNotFoundException;

/**
 * Created by Kunal Kumar
 */
public interface UserServiceI {
	
	public abstract HashMap<String, Object> getToken(AuthRequest authRequest) throws Exception;
	public abstract HashMap<String, Object> logout(String token , String loginid) throws ResourceNotFoundException;
	public abstract HashMap<String, Object> addUser(Users user)throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getAllUsers()throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getUserById(Integer id) throws ResourceNotFoundException;
	public abstract HashMap<String, Object> updateUserById(Users user, Integer id) throws ResourceNotFoundException;
	public abstract HashMap<String, Object> deleteUserById(Integer id) throws ResourceNotFoundException;
	public abstract HashMap<String, Object> createOrder(SoRequest Order ,String loginid)throws ResourceNotFoundException;
	public abstract HashMap<String, Object> upsertOrder(SoRequest Order, Integer id, String loginid)
			throws ResourceNotFoundException;

	public abstract HashMap<String, Object> updateStatus(SoStatusPojo req, Integer id ,String loginid)throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getOrderById(Integer id ,String loginid) throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getAllOrders(String token)throws ResourceNotFoundException;
	public abstract HashMap<String, Object> deleteAllOrders(Integer id) throws ResourceNotFoundException;
	public abstract HashMap<String , Object> deleteItem(Integer id) throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getSoGenerate(Long company_id ,String date) throws ParseException, Exception;
	public abstract HashMap<String, Object> getUsersKey(String token) throws ResourceNotFoundException;

	public abstract HashMap<String ,Object> getParent(String roleId);
	
	public abstract HashMap<String , Object> getDashBoardStatus(String loginid) throws Exception;
	
}
