package com.alethe.opf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alethe.opf.dto.UserMstResponse;
import com.alethe.opf.entity.User_mstJoin;

@Repository
public interface UserRoleJoinRepo extends JpaRepository<User_mstJoin, String> {

	
	@Query("SELECT new com.alethe.opf.dto.UserMstResponse( u.user_id ,u.loginid, u.user_fname, u.user_lname ,u.user_email ,"
			+ "u.user_contact ,u.user_password ,u.user_type,u.user_role, u.user_parent, u.created_on, u.created_by, "
			+ "u.modified_on, u.modified_by, u.is_deleted, u.is_active, r.userRoleName )" + " FROM User_mstJoin  u "
			+ "LEFT JOIN  u.userRoleJoin    as  r  ON r.userRoleId = u.user_role")

	public List<UserMstResponse> getAllUserJoin();
	
	
	
}
