package com.alethe.opf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alethe.opf.entity.Business_nature_mst;
/**
 * Created by Kunal Kumar
 */
@Repository
public interface Business_nature_mst_repo extends JpaRepository<Business_nature_mst, String>{
	
	

}
