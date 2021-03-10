package com.alethe.opf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alethe.opf.dto.CustomerPair;
import com.alethe.opf.entity.Customer_mst;
/**
 * Created by Kunal Kumar
 */
@Repository
public interface Customer_mst_repo extends JpaRepository<Customer_mst, Integer> {
	
	
	@Query("SELECT new  com.alethe.opf.dto.CustomerPair(C.customer_id , C.customer_name) from Customer_mst C WHERE lower(C.customer_name) like lower(concat('%', :customer_name,'%'))")
	public List<CustomerPair> getCustomerPair(@Param("customer_name") String customer_name);

} 
