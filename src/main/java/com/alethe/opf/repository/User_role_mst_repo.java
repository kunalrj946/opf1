package com.alethe.opf.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alethe.opf.entity.User_role_mst;
/**
 * Created by Kunal Kumar
 */

@Repository
public interface User_role_mst_repo  extends JpaRepository<User_role_mst, String>{
	
	Optional<User_role_mst> findByUserRoleId(String userRoleId);
	

	@Query(value = "SELECT u.user_role_id ,u.user_role_name FROM user_role_mst u",nativeQuery=true)
	List<User_role_mst> findAllRole();
	
}
