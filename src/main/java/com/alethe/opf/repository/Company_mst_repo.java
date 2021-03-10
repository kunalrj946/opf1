package com.alethe.opf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alethe.opf.entity.Company_mst;
/**
 * Created by Kunal Kumar
 */
@Repository
public interface Company_mst_repo extends JpaRepository<Company_mst, Integer> {

	
	@Query(value = "SELECT so_initials from company_mst where company_id = :company_id" , nativeQuery = true)
	public String getSoIntial(@Param("company_id") Long company_id);

	
}
