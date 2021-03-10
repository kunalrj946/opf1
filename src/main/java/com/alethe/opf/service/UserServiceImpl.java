package com.alethe.opf.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alethe.opf.dto.AuthRequest;
import com.alethe.opf.dto.ItemResponse;
import com.alethe.opf.dto.OrderResponse;
import com.alethe.opf.dto.SODashBoard;
import com.alethe.opf.dto.SoRequest;
import com.alethe.opf.dto.SoStatusPojo;
import com.alethe.opf.dto.UserMstResponse;
import com.alethe.opf.dto.UserPair;
import com.alethe.opf.dto.UserParent;
import com.alethe.opf.entity.Sale_item;
import com.alethe.opf.entity.Sale_order;
import com.alethe.opf.entity.Token_destroy;
import com.alethe.opf.entity.User_role_mst;
import com.alethe.opf.entity.User_type_mst;
import com.alethe.opf.entity.Users;
import com.alethe.opf.exception.ResourceNotFoundException;
import com.alethe.opf.repository.Company_mst_repo;
import com.alethe.opf.repository.EmQuery;
import com.alethe.opf.repository.SaleItemJoinRepo;
import com.alethe.opf.repository.SaleOrderJoinRepo;
import com.alethe.opf.repository.Sale_item_repo;
import com.alethe.opf.repository.Sale_order_repo;
import com.alethe.opf.repository.Token_destory_repo;
import com.alethe.opf.repository.UserRepository;
import com.alethe.opf.repository.UserRoleJoinRepo;
import com.alethe.opf.repository.User_role_mst_repo;
import com.alethe.opf.repository.User_type_mst_repository;
import com.alethe.opf.utility.JwtUtil;
import com.alethe.opf.utility.Utility;

/**
 * Created by Kunal Kumar
 */
@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private Sale_order_repo saleOrder;

	@Autowired
	private Company_mst_repo comp_repo;

	@Autowired
	private Sale_item_repo saleItem;

	@Autowired
	private SaleOrderJoinRepo soRepo;

	@Autowired
	private EmQuery query;

	@Autowired
	private SaleItemJoinRepo sijr;

	@Autowired
	private UserRoleJoinRepo userRepoJoin;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private User_role_mst_repo userRoleRepo;

	@Autowired
	private User_type_mst_repository userTypeRepo;

	@Autowired
	private Token_destory_repo tokenDestroy;

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


	@Transactional
	@Override
	public HashMap<String, Object> getToken(AuthRequest authRequest)
			throws UsernameNotFoundException, ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
//		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Users> user = null;

		user = userRepo.findByLoginid(authRequest.getLoginid());
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Exist " + authRequest.getLoginid()));

//		logger.debug("db password : "+ user.get().getUser_password());
//		boolean isMatch = Utility.getBase64Decode(authRequest.getPassword()) == user.get().getUser_password();
//		logger.debug("user password : " +Utility.getBase64Decode(authRequest.getPassword()));

//		logger.debug("is matching the password : " + isMatch);

		int userid = user.get().getUser_id();
		logger.debug("userId :" + userid);
		String role = user.get().getUser_role();
		String type = user.get().getUser_type();
		String userName = "", isUser_active = "";

		if (user.get().getIs_active() != 0) {

			isUser_active = "Active";
		} else {

			isUser_active = "Inactive";
		}

		if (!user.get().getUser_fname().isEmpty() && !user.get().getUser_lname().isEmpty()) {

			userName = user.get().getUser_fname() + " " + user.get().getUser_lname();

		} else if (user.get().getUser_fname().isEmpty() && !user.get().getUser_lname().isEmpty()) {

			userName = user.get().getUser_lname();

		} else if (!user.get().getUser_fname().isEmpty() && user.get().getUser_lname().isEmpty()) {

			userName = user.get().getUser_fname();

		} else {

			userName = "Unkonwn User ";
		}

		if (type.equalsIgnoreCase("ADM")) {

			Optional<User_type_mst> userType = userTypeRepo.findByUserTypeId(type);
			userType.orElseThrow(() -> new ResourceNotFoundException(
					"user type not found for this login_id -> " + authRequest.getLoginid()));

			logger.debug("userType :" + userType);
			logger.debug("usertype :" + userType.get().getUserTypeName());

			content.put("user_id", userid);
			content.put("user_type", userType.get().getUserTypeName());
			content.put("user_name", userName);
			content.put("user_status", isUser_active);

		} else if (type.equalsIgnoreCase("USR")) {

			Optional<User_type_mst> userType = userTypeRepo.findByUserTypeId(type);
			userType.orElseThrow(() -> new ResourceNotFoundException(
					"user type not found for this loginid -> " + authRequest.getLoginid()));

			logger.debug("userType :" + userType);

			Optional<User_role_mst> userRole = userRoleRepo.findByUserRoleId(role);
			userRole.orElseThrow(() -> new ResourceNotFoundException(
					"user role  not found for this login_id -> " + authRequest.getLoginid()));
			logger.debug("userRole :" + userRole);

			logger.debug("usertype :" + userType.get().getUserTypeName());

			logger.debug("userrole :" + userRole.get().getUserRoleName());

			logger.debug("Role is : " + role + " , " + "type is : " + type + "user type : "
					+ userType.get().getUserTypeName() + " ," + "user role : " + userRole.get().getUserRoleName());

			content.put("user_id", userid);
			content.put("user_role", userRole.get().getUserRoleName());
			content.put("user_type", userType.get().getUserTypeName());
			content.put("user_name", userName);
			content.put("user_status", isUser_active);

		} else if (type.equalsIgnoreCase("VWR")) {

			Optional<User_type_mst> userType = userTypeRepo.findByUserTypeId(type);
			userType.orElseThrow(() -> new ResourceNotFoundException(
					"user type  not found for this login_id -> " + authRequest.getLoginid()));

			logger.debug("userType :" + userType);

			content.put("user_id", userid);
			content.put("user_type", userType.get().getUserTypeName());
			content.put("user_name", userName);
			content.put("user_status", isUser_active);
		}

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getLoginid(), authRequest.getPassword()));
		String token = jwtUtil.generateToken(authRequest.getLoginid());

		logger.debug("<<<access_token>>> is : " + token);

		content.put("access_token", token);

		Token_destroy token1 = new Token_destroy();
		token1.setId(userid);
		token1.setToken(token);
		token1.setUsername(authRequest.getLoginid());
		tokenDestroy.save(token1);

		content.put("error", Boolean.FALSE);

//		response.put("content", content);

		return content;
	}

	@Override
	@Transactional
	public HashMap<String, Object> logout(String token, String loginid) throws ResourceNotFoundException {

		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
//		Optional<Users> user = null;

		tokenDestroy.deleteToken(token, loginid);
		content.put("message", "server logout successfull !");

		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> addUser(Users user) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		Users result = userRepo.save(user);

		if (result != null) {
			errorMsg = "user added !";
			hasError = false;
			content.put("collection", result);
			content.put("error", hasError);
			content.put("message", errorMsg);

			response.put("content", content);
		} else {

			throw new ResourceNotFoundException("data not found !");
		}
		return response;
	}

	@Transactional
	@Override
	public HashMap<String, Object> getAllUsers() throws ResourceNotFoundException {

		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

//		List<Users> result = userRepo.findAll();
		List<UserMstResponse> result = userRepoJoin.getAllUserJoin();

		if (!result.isEmpty()) {
			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", result);
			content.put("error", hasError);
			content.put("message", errorMsg);

			response.put("content", content);
		} else {

			throw new ResourceNotFoundException("data not found !");
		}
		return response;
	}

	@Transactional
	@Override
	public HashMap<String, Object> getUserById(Integer id) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		Optional<Users> result = userRepo.findById(id);
		result.orElseThrow(() -> new ResourceNotFoundException("user not found for this id " + id));

		errorMsg = "data is found !";
		hasError = false;
		content.put("result", result);
		content.put("error", hasError);
		content.put("message", errorMsg);

		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> updateUserById(Users user, Integer id) throws ResourceNotFoundException {

		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;
		Users data = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id :: " + id));

		data.setLoginid(user.getLoginid());
		data.setUser_fname(user.getUser_fname());
		data.setUser_lname(user.getUser_lname());
		data.setUser_email(user.getUser_email());
		data.setUser_contact(user.getUser_contact());
		data.setUser_password(user.getUser_password());
		data.setUser_type(user.getUser_type());
		data.setUser_role(user.getUser_role());
		data.setUser_parent(user.getUser_parent());
		data.setModified_on(user.getModified_on());
		data.setModified_by(user.getModified_by());
		data.setIs_deleted(user.getIs_deleted());
		data.setIs_active(user.getIs_active());

		final Users result = userRepo.save(data);

		errorMsg = "user updated !";
		hasError = false;
		content.put("collection", result);
		content.put("error", hasError);
		content.put("message", errorMsg);
		response.put("content", content);
		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> deleteUserById(Integer id) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;
		Users um = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id :: " + id));

		userRepo.delete(um);
		errorMsg = "delted successfull !";
		content.put("error", hasError);
		content.put("message", errorMsg);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> createOrder(SoRequest req, String loginid) throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Users> user = null;
		boolean hasError = false;
		String errorMsg = null;
		user = userRepo.findByLoginid(loginid);
		user.orElseThrow(() -> new UsernameNotFoundException("invalid token !"));

		List<Sale_item> item = new ArrayList<Sale_item>();
		List<Sale_item> si = req.getSale_item();
		Sale_order so = req.getSale_order();

		if (user.get().getUser_role().equalsIgnoreCase("COD") || user.get().getUser_type().equalsIgnoreCase("ADM")) {

			logger.debug("sale_order data insert process..." + "\n");
			so.setSo_type("SO");
			Sale_order res = saleOrder.save(so);
			logger.debug("OpfApplication : " + "Sale order inserted !" + "\n");
			result.put("sale_order", res);

			for (Sale_item sale_item : si) {

				sale_item.setSo_no(so.getSo_no());
				sale_item.setSo_rev(so.getSo_rev());
				sale_item.setCreated_on(so.getCreated_on());
				sale_item.setCreated_by(so.getCreated_by());
				sale_item.setIs_deleted(so.getIs_deleted());

				Sale_item result2 = saleItem.save(sale_item);
				item.add(result2);

				logger.debug("updated sale item :" + result2 + "\n");
				result.put("sale_item", item);
			}

		} else if (user.get().getUser_role().equalsIgnoreCase("CFO")) {

			if (so.getCreated_by() == user.get().getUser_id()) {

				logger.debug("sale_order data insert process..." + "\n");
				so.setSo_type("SO");
				Sale_order res = saleOrder.save(so);
				logger.debug("OpfApplication : " + "Sale order inserted !" + "\n");
				result.put("sale_order", res);

				for (Sale_item sale_item : si) {

					sale_item.setSo_no(so.getSo_no());
					sale_item.setSo_rev(so.getSo_rev());
					sale_item.setCreated_on(so.getCreated_on());
					sale_item.setCreated_by(so.getCreated_by());
					sale_item.setIs_deleted(so.getIs_deleted());

					Sale_item result2 = saleItem.save(sale_item);
					item.add(result2);

					logger.debug("updated sale item :" + result2 + "\n");
					result.put("sale_item", item);
				}

			} else {

				throw new ResourceNotFoundException("Please give your id in created_by column !");
			}

		} else if (user.get().getUser_role().equalsIgnoreCase("CSD")) {

			if (so.getCreated_by() == user.get().getUser_id()) {

				logger.debug("sale_order data insert process..." + "\n");
				so.setSo_type("SO");
				Sale_order res = saleOrder.save(so);
				logger.debug("OpfApplication : " + "Sale order inserted !" + "\n");
				result.put("sale_order", res);

				for (Sale_item sale_item : si) {

					sale_item.setSo_no(so.getSo_no());
					sale_item.setSo_rev(so.getSo_rev());
					sale_item.setCreated_on(so.getCreated_on());
					sale_item.setCreated_by(so.getCreated_by());
					sale_item.setIs_deleted(so.getIs_deleted());

					Sale_item result2 = saleItem.save(sale_item);
					item.add(result2);

					logger.debug("updated sale item :" + result2 + "\n");
					result.put("sale_item", item);
				}

			} else {

				throw new ResourceNotFoundException("Please give your id in created_by column !");
			}

		} else if (user.get().getUser_role().equalsIgnoreCase("TAM")) {

			if (so.getCreated_by() == user.get().getUser_id()) {

				logger.debug("sale_order data insert process..." + "\n");
				so.setSo_type("SO");
				Sale_order res = saleOrder.save(so);
				logger.debug("OpfApplication : " + "Sale order inserted !" + "\n");
				result.put("sale_order", res);

				for (Sale_item sale_item : si) {

					sale_item.setSo_no(so.getSo_no());
					sale_item.setSo_rev(so.getSo_rev());
					sale_item.setCreated_on(so.getCreated_on());
					sale_item.setCreated_by(so.getCreated_by());
					sale_item.setIs_deleted(so.getIs_deleted());

					Sale_item result2 = saleItem.save(sale_item);
					item.add(result2);

					logger.debug("updated sale item :" + result2 + "\n");
					result.put("sale_item", item);
				}

			} else {

				throw new ResourceNotFoundException("Please give your id in created_by column !");
			}

		} else if (user.get().getUser_role().equalsIgnoreCase("FSO")) {

			if (so.getCreated_by() == user.get().getUser_id()) {

				logger.debug("sale_order data insert process..." + "\n");
				so.setSo_type("SO");
				Sale_order res = saleOrder.save(so);
				logger.debug("OpfApplication : " + "Sale order inserted !" + "\n");
				result.put("sale_order", res);

				for (Sale_item sale_item : si) {

					sale_item.setSo_no(so.getSo_no());
					sale_item.setSo_rev(so.getSo_rev());
					sale_item.setCreated_on(so.getCreated_on());
					sale_item.setCreated_by(so.getCreated_by());
					sale_item.setIs_deleted(so.getIs_deleted());

					Sale_item result2 = saleItem.save(sale_item);
					item.add(result2);

					logger.debug("updated sale item :" + result2 + "\n");
					result.put("sale_item", item);
				}

			} else {

				throw new ResourceNotFoundException("Please give your id in created_by column !");
			}

		}

		logger.debug("OpfApplication ::" + "all sale item inserted !" + "\n");

		errorMsg = "data inserted !";
		hasError = false;
		content.put("collection", result);
		content.put("error", hasError);
		content.put("message", errorMsg);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> upsertOrder(SoRequest req, Integer so_id, String loginid)
			throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Users> user = null;
		String so_status = null;
		Integer status = null;
		Integer reslt = null;
		boolean hasError = false;
		String errorMsg = null;
		user = userRepo.findByLoginid(loginid);
		user.orElseThrow(() -> new UsernameNotFoundException("invalid token !"));
		logger.debug("user_type :" + user.get().getUser_type() + " and " + "user_role : " + user.get().getUser_role());

		Sale_order order = req.getSale_order();
		List<Sale_item> item = new ArrayList<Sale_item>();
		status = saleOrder.getStatus(so_id);

		if (order.getSo_status() == 0 && status == 0) {

			Sale_order so = saleOrder.findById(so_id).orElseThrow(
					() -> new ResourceNotFoundException("Data could not found for this so_id :: " + so_id + "\n"));

			so.setTam(req.getSale_order().getTam());
			so.setSo_no(req.getSale_order().getSo_no());
			so.setCompany_id(req.getSale_order().getCompany_id());
			so.setSo_date(req.getSale_order().getSo_date());
			so.setSo_rev(req.getSale_order().getSo_rev());
			so.setSo_reference(req.getSale_order().getSo_reference());
			so.setPo_no(req.getSale_order().getPo_no());
			so.setPo_date(req.getSale_order().getPo_date());
			so.setSo_category_id(req.getSale_order().getSo_category_id());
			so.setBusiness_unit_id(req.getSale_order().getBusiness_unit_id());
			so.setAm(req.getSale_order().getAm());
			so.setCustomer_id(req.getSale_order().getCustomer_id());
			so.setCustomer_name(req.getSale_order().getCustomer_name());
			so.setCustomer_billing_name(req.getSale_order().getCustomer_billing_name());
			so.setCustomer_billing_address(req.getSale_order().getCustomer_billing_address());
			so.setCustomer_dispatch_address(req.getSale_order().getCustomer_dispatch_address());
			so.setCustomer_gstn(req.getSale_order().getCustomer_gstn());
			so.setCustomer_segement(req.getSale_order().getCustomer_segement());
			so.setBusiness_nature_id(req.getSale_order().getBusiness_nature_id());
			so.setPrimary_contact_name(req.getSale_order().getPrimary_contact_name());
			so.setPrimary_contact_phone(req.getSale_order().getPrimary_contact_phone());
			so.setPrimary_contact_email(req.getSale_order().getPrimary_contact_email());
			so.setSecondary_contact_name(req.getSale_order().getSecondary_contact_name());
			so.setSecondary_contact_phone(req.getSale_order().getSecondary_contact_phone());
			so.setSecondary_contact_email(req.getSale_order().getSecondary_contact_email());
			so.setTotal_purchase_amount(req.getSale_order().getTotal_purchase_amount());
			so.setTotal_sale_amount(req.getSale_order().getTotal_sale_amount());
			so.setMargin_amount(req.getSale_order().getMargin_amount());
			so.setMargin_per(req.getSale_order().getMargin_per());
			so.setPo_attach1_id(req.getSale_order().getPo_attach1_id());
			so.setPo_attach2_id(req.getSale_order().getPo_attach2_id());
			so.setSo_attach1_id(req.getSale_order().getSo_attach1_id());
			so.setSo_attach2_id(req.getSale_order().getSo_attach2_id());
			so.setDelivery_instruction(req.getSale_order().getDelivery_instruction());
			so.setOther_expenses(req.getSale_order().getOther_expenses());
			so.setPayment_term(req.getSale_order().getPayment_term());
			so.setAm_remark(req.getSale_order().getAm_remark());
			so.setAm_approved_on(req.getSale_order().getAm_approved_on());
			so.setTam_remark(req.getSale_order().getTam_remark());
			so.setTam_approved_on(req.getSale_order().getTam_approved_on());
			so.setZm_remark(req.getSale_order().getZm_remark());
			so.setZm_approved_on(req.getSale_order().getZm_approved_on());
			so.setCfo_remark(req.getSale_order().getCfo_remark());
			so.setCfo_approved_on(req.getSale_order().getCfo_approved_on());
			so.setSo_status(req.getSale_order().getSo_status());
			so.setModified_on(req.getSale_order().getModified_on());
			so.setModified_by(req.getSale_order().getModified_by());
			so.setIs_deleted(req.getSale_order().getIs_deleted());

			Sale_order res = saleOrder.save(so);
			result.put("sale_order", res);
			logger.debug("OpfApplication ::" + "sale order updated !" + "\n");

			List<Sale_item> si = req.getSale_item();

			for (Sale_item sale_item : si) {

				if (sale_item.getItem_id() == null) {

					sale_item.setSo_no(req.getSale_order().getSo_no());
					sale_item.setSo_rev(req.getSale_order().getSo_rev());
					Sale_item r = saleItem.save(sale_item);

					item.add(r);
					result.put("sale_item", item);

				} else {
					Sale_item si2 = saleItem.findById(sale_item.getItem_id())
							.orElseThrow(() -> new ResourceNotFoundException(
									"item not found for this id :" + sale_item.getItem_id()));
					logger.debug("sale item by id : " + sale_item.getItem_id() + " is : " + si2 + "\n");

					si2.setSo_no(req.getSale_order().getSo_no());
					si2.setSo_rev(req.getSale_order().getSo_rev());
					si2.setHsn_code(sale_item.getHsn_code());
					si2.setPart_no(sale_item.getPart_no());
					si2.setDescription(sale_item.getDescription());
					si2.setTotal_qty(sale_item.getTotal_qty());
					si2.setUnit_sale_price(sale_item.getUnit_sale_price());
					si2.setSale_amt_wot(sale_item.getSale_amt_wot());
					si2.setSale_gst(sale_item.getSale_gst());
					si2.setSale_wt(sale_item.getSale_wt());
					si2.setUnit_purchase_price(sale_item.getUnit_purchase_price());
					si2.setPurchase_amt_wot(sale_item.getPurchase_amt_wot());
					si2.setPurchase_gst(sale_item.getPurchase_gst());
					si2.setPurchase_wt(sale_item.getPurchase_wt());
					si2.setProfit(sale_item.getProfit());
					si2.setProfit_per(sale_item.getProfit_per());
					si2.setQuotation1_id(sale_item.getQuotation1_id());
					si2.setQuotation2_id(sale_item.getQuotation2_id());
					si2.setSort_order(sale_item.getSort_order());
					si2.setModified_on(req.getSale_order().getModified_on());
					si2.setModified_by(req.getSale_order().getModified_by());
					si2.setIs_deleted(req.getSale_order().getIs_deleted());

					Sale_item result2 = saleItem.save(si2);
					logger.debug("updated sale item :" + result2 + "\n");
					item.add(result2);
					result.put("sale_item", item);

				}

			}
			errorMsg = "data upserted !";
			hasError = false;
			content.put("message", errorMsg);
			content.put("error", hasError);
			content.put("collection", result);

		} else if (status >= 1 && order.getSo_status() == 1) {

			so_status = "final !";
			errorMsg = "sale_order already final or approved !";
			hasError = true;
			content.put("so_status", so_status);
			content.put("message", errorMsg);
			content.put("error", hasError);

		} else if (order.getSo_status() == 1 && status == 0) {

			Sale_order so = saleOrder.findById(so_id).orElseThrow(
					() -> new ResourceNotFoundException("Data could not found for this so_id :: " + so_id + "\n"));

			so.setTam(req.getSale_order().getTam());
			so.setSo_no(req.getSale_order().getSo_no());
			so.setCompany_id(req.getSale_order().getCompany_id());
			so.setSo_date(req.getSale_order().getSo_date());
			so.setSo_rev(req.getSale_order().getSo_rev());
			so.setSo_reference(req.getSale_order().getSo_reference());
			so.setPo_no(req.getSale_order().getPo_no());
			so.setPo_date(req.getSale_order().getPo_date());
			so.setSo_category_id(req.getSale_order().getSo_category_id());
			so.setBusiness_unit_id(req.getSale_order().getBusiness_unit_id());
			so.setAm(req.getSale_order().getAm());
			so.setCustomer_id(req.getSale_order().getCustomer_id());
			so.setCustomer_name(req.getSale_order().getCustomer_name());
			so.setCustomer_billing_name(req.getSale_order().getCustomer_billing_name());
			so.setCustomer_billing_address(req.getSale_order().getCustomer_billing_address());
			so.setCustomer_dispatch_address(req.getSale_order().getCustomer_dispatch_address());
			so.setCustomer_gstn(req.getSale_order().getCustomer_gstn());
			so.setCustomer_segement(req.getSale_order().getCustomer_segement());
			so.setBusiness_nature_id(req.getSale_order().getBusiness_nature_id());
			so.setPrimary_contact_name(req.getSale_order().getPrimary_contact_name());
			so.setPrimary_contact_phone(req.getSale_order().getPrimary_contact_phone());
			so.setPrimary_contact_email(req.getSale_order().getPrimary_contact_email());
			so.setSecondary_contact_name(req.getSale_order().getSecondary_contact_name());
			so.setSecondary_contact_phone(req.getSale_order().getSecondary_contact_phone());
			so.setSecondary_contact_email(req.getSale_order().getSecondary_contact_email());
			so.setTotal_purchase_amount(req.getSale_order().getTotal_purchase_amount());
			so.setTotal_sale_amount(req.getSale_order().getTotal_sale_amount());
			so.setMargin_amount(req.getSale_order().getMargin_amount());
			so.setMargin_per(req.getSale_order().getMargin_per());
			so.setPo_attach1_id(req.getSale_order().getPo_attach1_id());
			so.setPo_attach2_id(req.getSale_order().getPo_attach2_id());
			so.setSo_attach1_id(req.getSale_order().getSo_attach1_id());
			so.setSo_attach2_id(req.getSale_order().getSo_attach2_id());
			so.setDelivery_instruction(req.getSale_order().getDelivery_instruction());
			so.setOther_expenses(req.getSale_order().getOther_expenses());
			so.setPayment_term(req.getSale_order().getPayment_term());
			so.setAm_remark(req.getSale_order().getAm_remark());
			so.setAm_approved_on(req.getSale_order().getAm_approved_on());
			so.setTam_remark(req.getSale_order().getTam_remark());
			so.setTam_approved_on(req.getSale_order().getTam_approved_on());
			so.setZm_remark(req.getSale_order().getZm_remark());
			so.setZm_approved_on(req.getSale_order().getZm_approved_on());
			so.setCfo_remark(req.getSale_order().getCfo_remark());
			so.setCfo_approved_on(req.getSale_order().getCfo_approved_on());
			so.setSo_status(req.getSale_order().getSo_status());
			so.setModified_on(req.getSale_order().getModified_on());
			so.setModified_by(req.getSale_order().getModified_by());
			so.setIs_deleted(req.getSale_order().getIs_deleted());

			Sale_order res = saleOrder.save(so);
			result.put("sale_order", res);
			logger.debug("OpfApplication ::" + "sale order updated !" + "\n");

			List<Sale_item> si = req.getSale_item();

			for (Sale_item sale_item : si) {

				if (sale_item.getItem_id() == null) {

					sale_item.setSo_no(req.getSale_order().getSo_no());
					sale_item.setSo_rev(req.getSale_order().getSo_rev());
					Sale_item r = saleItem.save(sale_item);

					item.add(r);
					result.put("sale_item", item);

				} else {
					Sale_item si2 = saleItem.findById(sale_item.getItem_id())
							.orElseThrow(() -> new ResourceNotFoundException(
									"item not found for this id :" + sale_item.getItem_id()));
					logger.debug("sale item by id : " + sale_item.getItem_id() + " is : " + si2 + "\n");

					si2.setSo_no(req.getSale_order().getSo_no());
					si2.setSo_rev(req.getSale_order().getSo_rev());
					si2.setHsn_code(sale_item.getHsn_code());
					si2.setPart_no(sale_item.getPart_no());
					si2.setDescription(sale_item.getDescription());
					si2.setTotal_qty(sale_item.getTotal_qty());
					si2.setUnit_sale_price(sale_item.getUnit_sale_price());
					si2.setSale_amt_wot(sale_item.getSale_amt_wot());
					si2.setSale_gst(sale_item.getSale_gst());
					si2.setSale_wt(sale_item.getSale_wt());
					si2.setUnit_purchase_price(sale_item.getUnit_purchase_price());
					si2.setPurchase_amt_wot(sale_item.getPurchase_amt_wot());
					si2.setPurchase_gst(sale_item.getPurchase_gst());
					si2.setPurchase_wt(sale_item.getPurchase_wt());
					si2.setProfit(sale_item.getProfit());
					si2.setProfit_per(sale_item.getProfit_per());
					si2.setQuotation1_id(sale_item.getQuotation1_id());
					si2.setQuotation2_id(sale_item.getQuotation2_id());
					si2.setSort_order(sale_item.getSort_order());
					si2.setModified_on(req.getSale_order().getModified_on());
					si2.setModified_by(req.getSale_order().getModified_by());
					si2.setIs_deleted(req.getSale_order().getIs_deleted());

					Sale_item result2 = saleItem.save(si2);
					logger.debug("updated sale item :" + result2 + "\n");
					item.add(result2);
					result.put("sale_item", item);
				}
			}

			reslt = saleOrder.updateStatusFinalByAdm(order.getSo_status(), so_id);

			logger.debug("final updated status : " + reslt);

			if (reslt == 0) {
				so_status = "draft !";
				errorMsg = "final could not updated !";
				hasError = true;
				content.put("so_status", so_status);
				content.put("message", errorMsg);
				content.put("error", hasError);

			} else {

				so_status = "final done  !";
				errorMsg = "final updated !";
				hasError = false;
				content.put("so_status", so_status);
				content.put("message", errorMsg);
				content.put("error", hasError);

			}
		}

		response.put("content", content);
		return response;

	}

	@Override
	@Transactional
	public HashMap<String, Object> updateStatus(SoStatusPojo req, Integer so_id, String loginid)
			throws ResourceNotFoundException {
//		HashMap<String, Object> result= new HashMap<String, Object>();
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Users> user = null;
		String so_status = null;
		Integer status = null;
		Integer reslt = null;
		boolean hasError = false;
		String errorMsg = null;

		logger.debug("data is : " + req);
		user = userRepo.findByLoginid(loginid);
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Exist " + loginid));
		logger.debug("role is : " + user.get().getUser_role() + " and type is :" + user.get().getUser_type());

		if (user.get().getUser_role().equalsIgnoreCase("CFO") || user.get().getUser_type().equalsIgnoreCase("ADM")) {
			status = saleOrder.getStatus(so_id);

			if (status != 31 & status == 15 & req.getSo_status() == 16) {
				reslt = saleOrder.updateCfoRemark(req.getSo_status() + (status), req.getCfo_remark(),
						req.getCfo_approved_on(), so_id);
				status = saleOrder.getStatus(so_id);

				if (reslt != 0 & status == 31) {

					so_status = "cfo_approved";
					errorMsg = "status of CFO , updated !";
					hasError = false;
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				} else {
					errorMsg = "status of cfo , couldn't updated !";
					hasError = true;
					so_status = "cfo_approval pending !";
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				}
			} else if (status == 31) {

				errorMsg = "Already approved by CFO !";
				hasError = true;
				so_status = "approved !";
				content.put("so_status", so_status);
				content.put("message", errorMsg);
				content.put("error", hasError);

			} else if (status == 0) {

				errorMsg = "so is in draft mode ! please do final first ! ";
				hasError = true;
				so_status = "pending !";
				content.put("so_status", so_status);
				content.put("message", errorMsg);
				content.put("error", hasError);

			} else if (status <= 7) {

				reslt = saleOrder.updateCfoRemark(31, req.getCfo_remark(), req.getCfo_approved_on(), so_id);
				status = saleOrder.getStatus(so_id);

				if (reslt != 0 & status == 31) {

					so_status = "cfo_approved";
					errorMsg = "status of CFO , updated !";
					hasError = false;
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				} else {
					errorMsg = "status of cfo , couldn't updated !";
					hasError = true;
					so_status = "cfo_approval pending !";
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				}

			} else {
				errorMsg = "something went wrong !";
				hasError = true;
				content.put("message", errorMsg);
				content.put("error", hasError);

			}
		} else if (user.get().getUser_role().equalsIgnoreCase("CSD")) {

			status = saleOrder.getStatus(so_id);

			if (status != 15 & status == 7 & req.getSo_status() == 8) {

				reslt = saleOrder.updateZmRemark(req.getSo_status() + (status), req.getZm_remark(),
						req.getZm_approved_on(), so_id);
				status = saleOrder.getStatus(so_id);

				if (reslt != 0 & status == 15) {
					so_status = "zm_approved";
					errorMsg = "status of ZM , updated !";
					hasError = false;
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				} else {
					errorMsg = "so_status of ZM , couldn't updated !";
					hasError = true;
					so_status = "zm_approval pending !";
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				}
			} else if (status >= 15) {

				errorMsg = "Already approved by ZM !";
				hasError = true;
				so_status = "Approved !";
				content.put("so_status", so_status);
				content.put("message", errorMsg);
				content.put("error", hasError);

			} else if (status == 0) {

				errorMsg = "so is in draft mode ! please do final first ! ";
				hasError = true;
				so_status = "pending !";
				content.put("so_status", so_status);
				content.put("message", errorMsg);
				content.put("error", hasError);

			} else if (status == 1 || status == 3) {

				reslt = saleOrder.updateZmRemark(15, req.getZm_remark(), req.getZm_approved_on(), so_id);
				status = saleOrder.getStatus(so_id);

				if (reslt != 0 & status == 15) {
					so_status = "zm_approved";
					errorMsg = "status of ZM , updated !";
					hasError = false;
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				} else {
					errorMsg = "so_status of ZM , couldn't updated !";
					hasError = true;
					so_status = "zm_approval pending !";
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				}

			} else {
				errorMsg = "something wrong !";
				hasError = true;
				content.put("message", errorMsg);
				content.put("error", hasError);

			}

		} else if (user.get().getUser_role().equalsIgnoreCase("TAM")) {

			logger.debug(" welcome tam !");
			status = saleOrder.getStatus(so_id);
			logger.debug(" current status is : " + status);

			if (status != 7 & status == 3 & req.getSo_status() == 4) {
				reslt = saleOrder.updateTamRemark(req.getSo_status() + (status), req.getTam_remark(),
						req.getTam_approved_on(), so_id);
				status = saleOrder.getStatus(so_id);

				if (reslt != 0 & status == 7) {

					so_status = "tam_approved";
					errorMsg = "so_status of TAM updated !";
					hasError = false;
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				} else {
					errorMsg = "so_status of TAM couldn't updated !";
					hasError = true;
					so_status = "tam_approval pending !";
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				}
			} else if (status >= 7) {

				errorMsg = "Already approved by Team Area Manager !";
				hasError = true;
				so_status = "approved !";
				content.put("so_status", so_status);
				content.put("message", errorMsg);
				content.put("error", hasError);

			} else if (status == 0) {

				errorMsg = "so is in draft mode ! please do final first ! Or contact with admin";
				hasError = true;
				so_status = "pending !";
				content.put("so_status", so_status);
				content.put("message", errorMsg);
				content.put("error", hasError);

			} else if (status == 1) {

				reslt = saleOrder.updateTamRemark(7, req.getTam_remark(), req.getTam_approved_on(), so_id);
				status = saleOrder.getStatus(so_id);

				if (reslt != 0 & status == 7) {

					so_status = "tam_approved";
					errorMsg = "so_status of TAM updated !";
					hasError = false;
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				} else {
					errorMsg = "so_status of TAM couldn't updated !";
					hasError = true;
					so_status = "tam_approval pending !";
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				}

			} else {
				errorMsg = "something wrong !";
				hasError = true;
				content.put("message", errorMsg);
				content.put("error", hasError);

			}
		} else if (user.get().getUser_role().equalsIgnoreCase("FSO")) {

			status = saleOrder.getStatus(so_id);
			logger.debug("current so_status in db : " + status);
			logger.debug("so_status req :" + req.getSo_status() + " am_remark req :" + req.getAm_remark()
					+ " am approved on : " + req.getAm_approved_on() + " so_id is : " + so_id);

			if (status != 3 && status == 1 & req.getSo_status() == 2) {
				reslt = saleOrder.updateAmRemark(req.getSo_status() + (status), req.getAm_remark(),
						req.getAm_approved_on(), so_id);

				status = saleOrder.getStatus(so_id);

				if (reslt != 0 & status == 3) {

					so_status = "am_approved";
					errorMsg = "so_status of AM , updated !";
					hasError = false;
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				} else {

					errorMsg = "so_status of AM , couldn't updated !";
					hasError = true;
					so_status = "am_approval pending !";
					content.put("so_status", so_status);
					content.put("message", errorMsg);
					content.put("error", hasError);

				}
			} else if (status >= 3) {

				errorMsg = "Already approved by Area Manager !";
				hasError = true;
				so_status = "approved !";
				content.put("so_status", so_status);
				content.put("message", errorMsg);
				content.put("error", hasError);

			} else if (status == 0) {

				errorMsg = "so is in draft mode ! please do final first !";
				hasError = true;
				so_status = "pending !";
				content.put("so_status", so_status);
				content.put("message", errorMsg);
				content.put("error", hasError);

			} else {
				errorMsg = "something wrong !";
				hasError = true;
				content.put("message", errorMsg);
				content.put("error", hasError);

			}
		}

		response.put("content", content);
		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> getOrderById(Integer id, String loginid) throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();

		Optional<Users> user = null;
		boolean hasError = false;
		String errorMsg = null;

		List<ItemResponse> res2 = null;
		user = userRepo.findByLoginid(loginid);
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Exist " + loginid));

//		Sale_order res = saleOrder.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id : " + id + " "));

		OrderResponse findByid = soRepo.getById(id);

		res2 = sijr.getItemDataBySoNo(findByid.getSo_no(), findByid.getSo_rev());

		result.put("sale_order", findByid);
		result.put("sale_item", res2);

		if (!res2.isEmpty()) {
			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", result);
			content.put("error", hasError);
			content.put("message", errorMsg);
		} else {

			errorMsg = "data not found !";
			hasError = true;
			content.put("collection", result);
			content.put("error", hasError);
			content.put("message", errorMsg);

		}

		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> getAllOrders(String loginid) throws ResourceNotFoundException {
		List<OrderResponse> ress = null;
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Users> user = null;
		boolean hasError = false;
		String errorMsg = null;
		user = userRepo.findByLoginid(loginid);
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Exist " + loginid));

		logger.debug("login_id  for getAllOrders is : " + loginid);
		logger.debug(
				"user id is :" + user.get().getUser_id() + " and " + "user_type is : " + user.get().getUser_type());

		if (user.get().getUser_type().equalsIgnoreCase("ADM") || user.get().getUser_role().equalsIgnoreCase("CFO")) {

			logger.debug("Getting all data for ADM ...");

			ress = soRepo.getAllOrder();
			

		} else if (user.get().getUser_role().equalsIgnoreCase("CSD")) {

			logger.debug("Getting all data for ZM ...");

			int tamid, amid;

			try {
				tamid = userRepo.getChildId(user.get().getUser_id());
				logger.debug("child of zm (tam_id):" + tamid);
			} catch (NullPointerException e) {

				tamid = -1;
			}
			try {
				amid = userRepo.getChildId(tamid);
				logger.debug("child of tam :(am_id) :" + amid);
			} catch (NullPointerException e) {

				amid = -1;
			}

			ress = soRepo.getZmOrder(user.get().getUser_id(), amid, tamid);

		} else if (user.get().getUser_role().equalsIgnoreCase("COD")) {

			logger.debug("Getting all data for COD ...");
			ress = soRepo.getAllOrder();

		} else if (user.get().getUser_role().equalsIgnoreCase("TAM")) {
			int amid;
			
			try {
				amid = userRepo.getChildId(user.get().getUser_id());
				logger.debug("child of tam :(am_id) :" + amid);
			} catch (NullPointerException e) {

				amid = -1;
			}

			ress = soRepo.getTamOrder(user.get().getUser_id() ,amid);
			logger.debug("data is : " + ress.toString());

		} else if (user.get().getUser_role().equalsIgnoreCase("FSO")) {

			ress = soRepo.getAmOrder(user.get().getUser_id());
		}

		if (!ress.isEmpty()) {

			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", ress);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);

		} else {

			throw new ResourceNotFoundException("data not found !");
		}

		return response;

	}

	@Override
	@Transactional
	public HashMap<String, Object> deleteAllOrders(Integer id) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
//		String errorMsg = null;
		Sale_order so = saleOrder.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id :: " + id));

		saleOrder.delete(so);
		saleItem.deleteItem(so.getSo_no(), so.getSo_rev());

		hasError = false;
		content.put("deleted", Boolean.TRUE);
		content.put("error", hasError);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> deleteItem(Integer id) throws ResourceNotFoundException {
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
//		String errorMsg = null;
		Sale_item si = saleItem.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found for this id :: " + id));

		saleItem.delete(si);

		hasError = false;
		content.put("deleted", Boolean.TRUE);
		content.put("error", hasError);
		response.put("content", content);

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> getSoGenerate(Long company_id, String date) throws Exception {
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;
		String soinitial, so_date, nxtval, fy, final_fy = "";
		int financial_year;
		BigInteger val;
		try {
			val = query.getNextval();
			logger.debug("nextval : " + val);
			nxtval = String.format("%05d", val);

			soinitial = comp_repo.getSoIntial(company_id);
			soinitial.trim();
			logger.debug("so_initial : " + soinitial);

			so_date = date.substring(0, 10);
			logger.debug("so_date : " + so_date);

			financial_year = Utility.getFY(so_date.toString());
			fy = String.valueOf(financial_year);
			fy = fy.substring(2, 4);
			final_fy = so_date.toString().substring(0, 4) + "-" + fy;

			logger.debug("financial year : " + final_fy);
			soinitial = soinitial + "/" + final_fy + "/" + nxtval;

			logger.info("So Generate : " + soinitial);

			result.put("so_generate", soinitial);

			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", result);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);

		} catch (NullPointerException e) {

			throw new ResourceNotFoundException("so_initial not found for this company_id : " + company_id);
		}

		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> getUsersKey(String loginid) throws ResourceNotFoundException {

		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;
		Optional<Users> user = null;
		UserPair userk = new UserPair();
		List<UserPair> userkey = new ArrayList<UserPair>();

		user = userRepo.findByLoginid(loginid);
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Exist " + loginid));

		if (user.get().getUser_fname().isEmpty()) {

			userk.setLoginid(user.get().getLoginid());
			userk.setUser_id(user.get().getUser_id());
			userkey.add(userk);

		} else if (!user.get().getUser_fname().isEmpty() && !user.get().getUser_lname().isEmpty()) {

			userk.setLoginid(user.get().getUser_fname() + " " + user.get().getUser_lname());
			userk.setUser_id(user.get().getUser_id());
			userkey.add(userk);

		} else if (!user.get().getUser_fname().isEmpty() && user.get().getUser_fname().isEmpty()) {

			userk.setLoginid(user.get().getUser_fname());
			userk.setUser_id(user.get().getUser_id());
			userkey.add(userk);

		} else {

			userk.setLoginid("Unknown");
			userk.setUser_id(user.get().getUser_id());
			userkey.add(userk);

		}

		if (user.get().getUser_role().equalsIgnoreCase("COD") || user.get().getUser_type().equalsIgnoreCase("ADM")) {

			List<UserPair> userkey1 = userRepo.getCodParentUserPair();

			if (!userkey1.isEmpty()) {
				errorMsg = "data is found !";
				hasError = false;
				content.put("collection", userkey1);
				content.put("error", hasError);
				content.put("message", errorMsg);
				response.put("content", content);
			} else {

				throw new ResourceNotFoundException("data not found !");
			}

		} else {

			errorMsg = "data is found !";
			hasError = false;
			content.put("collection", userkey);
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);
		}

		return response;
	}

	@Override
	public HashMap<String, Object> getParent(String roleId) {

		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;

		if (roleId.equalsIgnoreCase("FSO")) {

			List<UserParent> amParent = userRepo.getFsoParent();

			if (!amParent.isEmpty()) {
				errorMsg = "data is found !";
				hasError = false;
				content.put("collection", amParent);
				content.put("error", hasError);
				content.put("message", errorMsg);
				response.put("content", content);
			} else {

				errorMsg = "data is not found !";
				hasError = true;
				content.put("collection", amParent);
				content.put("error", hasError);
				content.put("message", errorMsg);
				response.put("content", content);
			}

		} else if (roleId.equalsIgnoreCase("TAM")) {

			List<UserParent> tamParent = userRepo.getTamParent();

			if (!tamParent.isEmpty()) {
				errorMsg = "data is found !";
				hasError = false;
				content.put("collection", tamParent);
				content.put("error", hasError);
				content.put("message", errorMsg);
				response.put("content", content);
			} else {

				errorMsg = "data is not found !";
				hasError = true;
				content.put("collection", tamParent);
				content.put("error", hasError);
				content.put("message", errorMsg);
				response.put("content", content);
			}
		} else if (roleId.equalsIgnoreCase("CSD") || roleId.equalsIgnoreCase("CFO")) {

			List<UserParent> zmParent = userRepo.getCsdParent();

			if (!zmParent.isEmpty()) {
				errorMsg = "data is found !";
				hasError = false;
				content.put("collection", zmParent);
				content.put("error", hasError);
				content.put("message", errorMsg);
				response.put("content", content);
			} else {

				errorMsg = "data is not found !";
				hasError = true;
				content.put("collection", zmParent);
				content.put("error", hasError);
				content.put("message", errorMsg);
				response.put("content", content);
			}
		} else if (roleId.equalsIgnoreCase("COD")) {

			errorMsg = "There is no Parent of 'COD' !";
			hasError = true;
			content.put("error", hasError);
			content.put("message", errorMsg);
			response.put("content", content);

		}
		return response;
	}

	@Override
	@Transactional
	public HashMap<String, Object> getDashBoardStatus(String loginid) throws Exception {

		HashMap<String, Object> content = new HashMap<String, Object>();
		HashMap<String, Object> response = new HashMap<String, Object>();
		boolean hasError = false;
		String errorMsg = null;
		int tamid =-0, amid=-0;
		
		Optional<Users> user = null;

		user = userRepo.findByLoginid(loginid);
		user.orElseThrow(() -> new UsernameNotFoundException("invalid token !"));

		SODashBoard dashboard = new SODashBoard();
		
		
		if (user.get().getUser_role().equalsIgnoreCase("COD")) {
			
			logger.debug("COD dashboard ...");

			dashboard.setTotal_so(saleOrder.getTotalSo());
			dashboard.setDraft_mode_so(saleOrder.getTotalDraftSo());
			dashboard.setFinal_so(saleOrder.getTotalFinalSo());
			dashboard.setPending_so(saleOrder.getTotalPendingSo());
			dashboard.setCfo_approved_so(saleOrder.getTotalCfoApprovedSo());
			
			
		} else if (user.get().getUser_role().equalsIgnoreCase("FSO")) {
			
			logger.debug("AM dashboard ...");

			dashboard.setTotal_so(saleOrder.getTotalSoByAmId(user.get().getUser_id()));
			dashboard.setDraft_mode_so(saleOrder.getTotalDraftSoByFso(user.get().getUser_id()));
			dashboard.setFinal_so(saleOrder.getTotalFinalSoByFsoId(user.get().getUser_id()));
			dashboard.setPending_so(saleOrder.getTotalPendingSoByFso(user.get().getUser_id()));
			dashboard.setCfo_approved_so(saleOrder.getTotalCfoApprovedSoByAm(user.get().getUser_id()));


		} else if (user.get().getUser_role().equalsIgnoreCase("TAM")) {
			logger.debug("TAM dashboard ...");
			
			try {
				amid = userRepo.getChildId(user.get().getUser_id());
				logger.debug("child of tam :(am_id) :" + amid);
			} catch (NullPointerException e) {

				amid = -1;
				
			}

			dashboard.setTotal_so(saleOrder.getTotalSoByTamId(user.get().getUser_id() , amid));
			dashboard.setDraft_mode_so(saleOrder.getTotalDraftSoByTam(user.get().getUser_id(), amid));
			dashboard.setFinal_so(saleOrder.getTotalFinalSoByTamId(user.get().getUser_id(), amid));
			dashboard.setPending_so(saleOrder.getTotalPendingSoByTam(user.get().getUser_id(), amid));
			dashboard.setCfo_approved_so(saleOrder.getTotalCfoApprovedSoByTam(user.get().getUser_id(), amid));

			
		} else if (user.get().getUser_role().equalsIgnoreCase("CSD")) {

			logger.debug("CSD dashboard ...");

			try {
				tamid = userRepo.getChildId(user.get().getUser_id());
				logger.debug("child of zm (tam_id):" + tamid);
			} catch (NullPointerException e) {

				tamid = -1;
			}
			try {
				amid = userRepo.getChildId(tamid);
				logger.debug("child of tam :(am_id) :" + amid);
			} catch (NullPointerException e) {

				amid = -1;
			}
			
			

			dashboard.setTotal_so(saleOrder.getTotalSoByCsdId(user.get().getUser_id() , amid , tamid));
			dashboard.setDraft_mode_so(saleOrder.getTotalDraftSoByCsd(user.get().getUser_id(), tamid, amid));
			dashboard.setFinal_so(saleOrder.getTotalFinalSoByCsdId(user.get().getUser_id(), amid, tamid));
			dashboard.setPending_so(saleOrder.getTotalPendingSoByCsd(user.get().getUser_id(), amid, tamid));
			dashboard.setCfo_approved_so(saleOrder.getTotalCfoApprovedSoByCsd(user.get().getUser_id(), tamid, amid));

		} else if (user.get().getUser_role().equalsIgnoreCase("CFO")) {

			logger.debug("CFO dashboard ...");
			
			dashboard.setTotal_so(saleOrder.getTotalSo());
			dashboard.setDraft_mode_so(saleOrder.getTotalDraftSo());
			dashboard.setFinal_so(saleOrder.getTotalFinalSo());
			dashboard.setPending_so(saleOrder.getTotalPendingSo());
			dashboard.setCfo_approved_so(saleOrder.getTotalCfoApprovedSo());

			
		} else if (user.get().getUser_type().equalsIgnoreCase("ADM")) {
			
			logger.debug("ADM dashboard ...");
			dashboard.setTotal_so(saleOrder.getTotalSo());
			dashboard.setDraft_mode_so(saleOrder.getTotalDraftSo());
			dashboard.setFinal_so(saleOrder.getTotalFinalSo());
			dashboard.setPending_so(saleOrder.getTotalPendingSo());
			dashboard.setCfo_approved_so(saleOrder.getTotalCfoApprovedSo());

			
		}


		errorMsg = "data is found !";
		content.put("collection", dashboard);
		content.put("error", hasError);
		content.put("message", errorMsg);

		response.put("content", content);

		return response;
	}
}