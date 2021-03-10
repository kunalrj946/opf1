package com.alethe.opf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alethe.opf.entity.User_type_mst;
/**
 * Created by Kunal Kumar
 */
@Repository
public interface User_type_mst_repository  extends JpaRepository<User_type_mst, String>{
	
	Optional<User_type_mst> findByUserTypeId(String userTypeId);
	
	@Query(value = "SELECT u.user_type_id ,u.user_type_name FROM user_type_mst u" , nativeQuery = true)
	List<User_type_mst> findAllType();

}
