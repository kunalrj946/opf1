package com.alethe.opf.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.alethe.opf.dto.SoRequest;
import com.alethe.opf.dto.SoStatusPojo;
import com.alethe.opf.exception.ResourceNotFoundException;
import com.alethe.opf.repository.Token_destory_repo;
import com.alethe.opf.service.UserServiceI;
import com.alethe.opf.utility.JwtUtil;

/**
 * Created by Kunal Kumar
 */
@RestController
public class OrderController {

	@Autowired
	private UserServiceI orderService;

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private Token_destory_repo tokenDestroy;

	private static Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@PreAuthorize("hasRole('ADM') or hasRole('COD') or hasRole('TAM') or hasRole('CFO') or hasRole('CSD') or hasRole('FSO')")
	@CrossOrigin
	@GetMapping("/sale_order/getById/{order_id}")
	public Object getOrderById(@PathVariable(value = "order_id") Integer orderId,
			@RequestHeader(name = "Authorization") String token) throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<>();
		String loginid = null;
		String serverToken = "";
		boolean isEqual = false;

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		loginid = jwtutil.extractUsername(token);

		try {
			serverToken = tokenDestroy.getToken(token);
//			logger.debug("check message " + serverToken);

		} catch (NullPointerException n) {

			result.put("message", "session expired !");
//			logger.debug("message for NullPointerException ");

		}

		if (serverToken != null) {
			isEqual = serverToken.contentEquals(token.trim());
			logger.debug("is token matching  : " + isEqual);
		}

		if (isEqual && serverToken != null) {
			result = orderService.getOrderById(orderId, loginid);
		} else {

			result.put("message", "SESSION EXPIRED !");

		}
		return ResponseEntity.ok().body(result);
	}

	@PreAuthorize("hasRole('ADM') or hasRole('COD') or hasRole('TAM') or hasRole('CFO') or hasRole('CSD') or hasRole('FSO')")
	@CrossOrigin
	@PostMapping("/sale_order/add")
	public Object createOrder(@RequestBody SoRequest order, @RequestHeader(name = "Authorization") String token)
			throws ResourceNotFoundException {

		HashMap<String, Object> result = new HashMap<>();
		String loginid = null;
		String serverToken = "";
		boolean isEqual = false;

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		loginid = jwtutil.extractUsername(token);

		logger.debug(order.toString());

		try {
			serverToken = tokenDestroy.getToken(token);

		} catch (NullPointerException n) {

			result.put("message", "session expired !");
		}

		if (serverToken != null) {
			isEqual = serverToken.contentEquals(token.trim());
			logger.debug("is token matching  : " + isEqual);
		}
		if (isEqual && serverToken != null) {

			result = orderService.createOrder(order, loginid);

		} else {

			result.put("message", "SESSION EXPIRED !");

			return result;

		}
		return result;
	}

	@CrossOrigin
	@PutMapping("/sale_order/updateById/{order_id}")
	public Object updateOrder(@PathVariable(value = "order_id") Integer orderId, @RequestBody SoRequest details,
			@RequestHeader(name = "Authorization") String token) throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<>();
		String loginid = null;
		String serverToken = "";
		boolean isEqual = false;

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		loginid = jwtutil.extractUsername(token);

		logger.debug(details.toString());

		try {
			serverToken = tokenDestroy.getToken(token);

		} catch (NullPointerException n) {

			result.put("message", "session expired !");
		}

		if (serverToken != null) {
			isEqual = serverToken.contentEquals(token.trim());
			logger.debug("is token matching  : " + isEqual);
		}

		if (isEqual && serverToken != null) {

			result = orderService.upsertOrder(details, orderId, loginid);
		} else {

			result.put("message", "SESSION EXPIRED !");

			return result;
		}
		return ResponseEntity.ok(result);
	}

	@PreAuthorize("hasRole('ADM') or hasRole('TAM') or hasRole('CFO') or hasRole('FSO') or hasRole('CSD')")
	@CrossOrigin
	@PutMapping("/sale_order/updateStatusById/{so_id}")
	public Object updateStatus(@PathVariable(value = "so_id") Integer so_id, @RequestBody SoStatusPojo req,
			@RequestHeader(name = "Authorization") String token) throws ResourceNotFoundException {
		String loginid = null;
		HashMap<String, Object> result = new HashMap<>();
		String serverToken = "";
		boolean isEqual = false;

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		loginid = jwtutil.extractUsername(token);

		logger.debug(req.toString());

		try {
			serverToken = tokenDestroy.getToken(token);

		} catch (NullPointerException n) {

			result.put("message", "session expired !");
		}

		if (serverToken != null) {
			isEqual = serverToken.contentEquals(token.trim());
			logger.debug("is token matching  : " + isEqual);
		}

		if (isEqual && serverToken != null) {

			if (req.getSo_status() != null) {

				logger.debug("so_id request :" + so_id + " , loginid req : " + loginid);
				result = orderService.updateStatus(req, so_id, loginid);
			} else {

				result.put("message", "so_status must be present !");
			}
		} else {

			result.put("message", "SESSION EXPIRED !");

			return result;

		}

		return ResponseEntity.ok(result);
	}

	@CrossOrigin
	@DeleteMapping("/sale_order/deleteById/{order_id}")
	public HashMap<String, Object> deleteOrder(@PathVariable(value = "order_id") Integer orderId,
			@RequestHeader(name = "Authorization") String token) throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<>();
		String serverToken = "";
		boolean isEqual = false;

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
//		String loginid = jwtutil.extractUsername(token);

		try {
			serverToken = tokenDestroy.getToken(token);

		} catch (NullPointerException n) {

			result.put("message", "session expired !");
		}

		if (serverToken != null) {
			isEqual = serverToken.contentEquals(token.trim());
			logger.debug("is token matching  : " + isEqual);
		}

		if (isEqual && serverToken != null) {
			result = orderService.deleteAllOrders(orderId);
		} else {

			result.put("message", "SESSION EXPIRED ! PLEASE LOGIN AGAIN !");

			return result;
		}
		return result;
	}

	@CrossOrigin
	@DeleteMapping("/sale_item/deleteById/{item_id}")
	public HashMap<String, Object> deleteItem(@PathVariable(value = "item_id") Integer itemId,
			@RequestHeader(name = "Authorization") String token) throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<>();

		result = orderService.deleteItem(itemId);

		return result;
	}

	@PreAuthorize("hasRole('ADM') or hasRole('COD') or hasRole('TAM') or hasRole('CFO') or hasRole('CSD') or hasRole('FSO')")
	@CrossOrigin
	@GetMapping("/sale_order/getAllOrder")
	public Object getOrder(@RequestHeader(name = "Authorization") String token) throws ResourceNotFoundException {

		String loginid = null;
		HashMap<String, Object> result = new HashMap<>();
		String serverToken = "";
		boolean isEqual = false;

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		loginid = jwtutil.extractUsername(token);

		try {
			serverToken = tokenDestroy.getToken(token);

		} catch (NullPointerException n) {

			result.put("message", "session expired !");
		}

		if (serverToken != null) {
			isEqual = serverToken.contentEquals(token.trim());
			logger.debug("is token matching  : " + isEqual);
		}

		if (isEqual) {

			return orderService.getAllOrders(loginid);

		} else {

			result.put("message", "SESSION EXPIRED ! PLEASE LOGIN AGAIN !");

			return result;

		}

	}

	
	@PreAuthorize("hasRole('ADM') or hasRole('COD') or hasRole('TAM') or hasRole('CFO') or hasRole('CSD') or hasRole('FSO')")
	@CrossOrigin
	@GetMapping("/getDashBoardStatus")
	public HashMap<String, Object> Dashboard(@RequestHeader(name = "Authorization") String token) throws Exception {
		String loginid="";
		HashMap<String , Object> result = new HashMap<String, Object>();
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		loginid = jwtutil.extractUsername(token);
		result = orderService.getDashBoardStatus(loginid);
		
		
		return result;

	}
}
