package com.alethe.opf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alethe.opf.entity.Sale_item;

/**
 * Created by Kunal Kumar
 */
@Repository
public interface Sale_item_repo extends JpaRepository<Sale_item, Integer> {

	@Query(value = "SELECT * FROM sale_item  WHERE so_no = :so_no AND so_rev =:so_rev", nativeQuery = true)
	List<Sale_item> findBySoNo(@Param("so_no") String so_no , @Param("so_rev") Integer so_rev);
	
	@Modifying
	@Query(value = "DELETE FROM sale_item WHERE so_no = :so_no AND so_rev =:so_rev" , nativeQuery = true)
	void deleteItem(@Param("so_no") String so_no ,@Param("so_rev") Integer so_rev);

}
