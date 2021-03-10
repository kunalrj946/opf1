package com.alethe.opf.service;

import java.io.IOException;
import java.util.HashMap;
import org.springframework.web.multipart.MultipartFile;
import com.alethe.opf.entity.Company_mst;
import com.alethe.opf.entity.Customer_mst;
import com.alethe.opf.exception.ResourceNotFoundException;

/**
 * Created by Kunal Kumar
 */

public interface OpfApplicationI {

	public abstract HashMap<String, Object> getCompAllData() throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getDataById(Integer id) throws ResourceNotFoundException;
	public abstract HashMap<String, Object> createCompany(Company_mst Commst);
	public abstract HashMap<String, Object> updateCompnay(Company_mst comp, Integer id)
			throws ResourceNotFoundException;
	public abstract HashMap<String, Object> deleteCompnay(Integer id) throws ResourceNotFoundException;

	
	public abstract HashMap<String, Object> getCustomerAllData() throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getCustomerDataById(Integer id) throws ResourceNotFoundException;
	public abstract HashMap<String, Object> createCustomer(Customer_mst Commst);
	public abstract HashMap<String, Object> updateCustomer(Customer_mst comp, Integer id)
			throws ResourceNotFoundException;
	public abstract HashMap<String, Object> deleteCustomer(Integer id) throws ResourceNotFoundException;

	
	public abstract HashMap<String, Object> storeFile(MultipartFile file) throws IOException ,ResourceNotFoundException;
	public abstract Object downloadFile(Long fileName) throws ResourceNotFoundException;
	public abstract HashMap<String, Object>  getAllFiles();
	public abstract HashMap<String, Object> deleteFileByName(Long fileName) throws ResourceNotFoundException;
	

	
	
	public abstract HashMap<String, Object> getAllSegMstData()throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getAllRoleMstData()throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getAllTypeMstData()throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getAllBusinessUnitMstData()throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getAllBusinessNatureMstData()throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getAllSoCategoryMstData()throws ResourceNotFoundException;
	public abstract HashMap<String, Object> getCutomerType(String title) throws ResourceNotFoundException;
	
	
	
	
	
	
	
	
	
}
