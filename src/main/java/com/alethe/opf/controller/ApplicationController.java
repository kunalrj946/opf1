package com.alethe.opf.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.alethe.opf.entity.Company_mst;
import com.alethe.opf.entity.Customer_mst;
import com.alethe.opf.exception.ErrorDetails;
import com.alethe.opf.exception.ResourceNotFoundException;
import com.alethe.opf.service.OpfApplicationI;
import com.alethe.opf.service.UserServiceI;
import com.alethe.opf.utility.JwtUtil;

/**
 * Created by Kunal Kumar
 */

@RestController
public class ApplicationController {

	@Autowired
	private OpfApplicationI opfService;
	
	@Autowired
	private UserServiceI order;
	
	@Autowired
	private JwtUtil jwtutil;
	

	private static Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	@CrossOrigin
	@GetMapping("/company_mst/getAllData")
	public Object getAllData() throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<>();
		result = opfService.getCompAllData();

		return result;
	}

	@CrossOrigin
	@GetMapping("/company_mst/getById/{company_id}")
	public Object getCompanyById(@PathVariable(value = "company_id") Integer companyId)
			throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<>();
		
		result = opfService.getDataById(companyId);

		return ResponseEntity.ok().body(result);
	}

	@CrossOrigin
	@PostMapping("/company_mst/add")
	public HashMap<String, Object> createCompany(@RequestBody Company_mst company_mst) {
		logger.debug("YOU HAVE SENT THE DATA :" + company_mst.toString());
		HashMap<String, Object> result = new HashMap<>();
		result = opfService.createCompany(company_mst);
		return result;
	}

	@CrossOrigin
	@PutMapping("/company_mst/updateById/{company_id}")
	public Object updateCMST(@PathVariable(value = "company_id") Integer compnayId, @RequestBody Company_mst details)
			throws ResourceNotFoundException {
		logger.debug("YOU HAVE SENT THE DATA :" + details.toString());
		HashMap<String, Object> result = new HashMap<>();
		result = opfService.updateCompnay(details, compnayId);

		return ResponseEntity.ok(result);
	}

	@CrossOrigin
	@DeleteMapping("/company_mst/deleteById/{compnay_id}")
	public Object deleteCompnay(@PathVariable(value = "compnay_id") Integer companyId)
			throws ResourceNotFoundException {

		HashMap<String, Object> result = new HashMap<>();
		result = opfService.deleteCompnay(companyId);

		return result;
	}

	@CrossOrigin
	@GetMapping("/customer_mst/getAllData")
	public Object getCustomerMst() throws ResourceNotFoundException {

		HashMap<String, Object> result = opfService.getCustomerAllData();

		return result;
	}

	@CrossOrigin
	@GetMapping("/customer_mst/getById/{customer_id}")
	public Object getCustomerById(@PathVariable(value = "customer_id") Integer customerId)
			throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<>();
		result = opfService.getCustomerDataById(customerId);

		return ResponseEntity.ok().body(result);
	}

	@CrossOrigin
	@PostMapping("/customer_mst/add")
	public Object createCustomerMst(@RequestBody Customer_mst customer_mst) {

		logger.debug("YOU HAVE SENT THE DATA :" + customer_mst.toString());

		HashMap<String, Object> result = new HashMap<>();
		result = opfService.createCustomer(customer_mst);

		return result;
	}

	@CrossOrigin
	@PutMapping("/customer_mst/updateById/{customer_id}")
	public Object updateCustomerMst(@PathVariable(value = "customer_id") Integer customerId,
			@RequestBody Customer_mst details) throws ResourceNotFoundException {
		logger.debug("YOU HAVE SENT THE DATA :" + details.toString());
		HashMap<String, Object> result = new HashMap<>();
		result = opfService.updateCustomer(details, customerId);

		return ResponseEntity.ok(result);
	}

	@CrossOrigin
	@DeleteMapping("/customer_mst/deleteById/{customer_id}")
	public Object deleteCustomer(@PathVariable(value = "customer_id") Integer customerId)
			throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<>();
		result = opfService.deleteCustomer(customerId);

		return result;
	}

	@CrossOrigin
	@GetMapping("/getlistdata")
	@ResponseBody
	public Object getAllData(@RequestParam(required = true) String type , @RequestHeader(name = "Authorization") String token) throws ResourceNotFoundException {
		HashMap<String, Object> result = new HashMap<>();
		String loginid = null;
		
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		loginid = jwtutil.extractUsername(token);
		

		logger.debug("type is :" + type);
		switch (type.toLowerCase()) {

		case "customersegment":
			result = opfService.getAllSegMstData();
			break;

		case "userrole":
			result = opfService.getAllRoleMstData();
			break;
		case "usertype":
			result = opfService.getAllTypeMstData();

			break;
		case "businessunit":
			result = opfService.getAllBusinessUnitMstData();
			break;
		case "socategory":
			result = opfService.getAllSoCategoryMstData();
			break;
		case "businessnature":
			result = opfService.getAllBusinessNatureMstData();
			break;
		case "tam":
			result = order.getUsersKey(loginid);
			break;
			
		default:
			result.put("message", "parameter doesn't valid !");
			break;
		}

		return result;
	}

	@CrossOrigin
	@GetMapping("/getCustomerPartial")
	@ResponseBody
	public Object getPartialData(@RequestParam(required = true) String like) throws ResourceNotFoundException {

		HashMap<String, Object> result = new HashMap<>();
		logger.debug("type is :" + like);

		result = opfService.getCutomerType(like);

		return result;

	}

	@CrossOrigin
	@GetMapping("/sale_order/getSoGenerate")
	@ResponseBody
	public Object getSoGenerate(@RequestParam(required = true) Long company_id, String so_date)
			throws ParseException, Exception {
		HashMap<String, Object> result = new HashMap<>();

		try {
			result = order.getSoGenerate(company_id, so_date);
		} catch (StringIndexOutOfBoundsException e) {

			return new ErrorDetails(new Date() ,"so_date is invalid format , it should be this format: yyyy-MM-dd " ,Boolean.TRUE, "", 111107);
		}
		return result;

	}

	@CrossOrigin
	@PostMapping("/file_upload/uploadFile")
	public Object uploadFile(@RequestParam("file_data") MultipartFile file_data) throws IOException {
		HashMap<String, Object> result = new HashMap<>();

		try {

			result = opfService.storeFile(file_data);

			return ResponseEntity.status(HttpStatus.OK).body(result);

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.OK).body(e.toString());

		}

//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
//				.path(file.getFile_id().toString()).toUriString();

//		return new UploadFileResponse(file.getFile_id() , file.getFilename(),  file.getFile_ext(), size , "successfull uploaded !");

	}

	@CrossOrigin
	@PostMapping("/file_upload/uploadMultipleFiles")
	public Object uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {

		Object result = Arrays.asList(files).stream().map(file_data -> {
			try {
				return uploadFile(file_data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return file_data;

		}).collect(Collectors.toList());

		return result;
	}

	@CrossOrigin
	@GetMapping(path = { "/file_upload/getById/{fileId}" })
	public Object getImage(@PathVariable("fileId") Long fileId) throws IOException, ResourceNotFoundException {

		Object dbFile = opfService.downloadFile(fileId);

		return dbFile;
	}

	@CrossOrigin
	@GetMapping("/file_upload/getAllFiles")
	public Object getListFiles() {
		HashMap<String, Object> result = new HashMap<>();
		logger.debug("Getting the all data ..");

		result = opfService.getAllFiles();

		return ResponseEntity.status(HttpStatus.OK).body(result);

	}

	@CrossOrigin
	@DeleteMapping("/file_upload/deleteById/{file_id}")
	public Object deleteUser(@PathVariable(value = "file_id") Long filename) throws ResourceNotFoundException {
		logger.debug("processing for delete...");

		HashMap<String, Object> result = new HashMap<>();
		result = opfService.deleteFileByName(filename);

		return ResponseEntity.ok(result);
	}

}
