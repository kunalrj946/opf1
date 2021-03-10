package com.alethe.opf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alethe.opf.entity.Customer_segment_mst;

/**
 * Created by Kunal Kumar
 */
@Repository
public interface Customer_segment_mst_repo extends JpaRepository<Customer_segment_mst, String> {

	@Query(value = "SELECT c.customer_segment_id ,c.customer_segment_name FROM customer_segment_mst c", nativeQuery = true)
	List<Customer_segment_mst> findAllSegment();

}
