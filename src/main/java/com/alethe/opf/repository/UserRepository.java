package com.alethe.opf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alethe.opf.dto.UserPair;
import com.alethe.opf.dto.UserParent;
import com.alethe.opf.entity.Users;

/**
 * Created by Kunal Kumar
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByLoginid(String loginid);

	@Query("SELECT new  com.alethe.opf.dto.UserPair(c.user_id , c.loginid) from Users c WHERE (c.user_type = 'USR' AND (c.user_role ='TAM' OR c.user_role ='CSD' OR c.user_role ='CFO' OR c.user_role ='FSO'))")
	public List<UserPair> getCodParentUserPair();
	
	
//	@Query("SELECT new  com.alethe.opf.dto.UserPair(c.user_id , c.loginid) from Users c WHERE (c.user_type = 'USR' AND (c.user_role ='TAM' OR c.user_role ='CSD' OR c.user_role ='CFO' OR c.user_role ='FSO'))")
//	public List<UserPair> getFsoParentUserPair();
//
//	@Query("SELECT new  com.alethe.opf.dto.UserPair(c.user_id , c.loginid) from Users c WHERE (c.user_type = 'USR' AND (c.user_role ='CSD' OR c.user_role ='CFO' OR c.user_role ='TAM'))")
//	public List<UserPair> getTamParentUserPair();
//
//	@Query("SELECT new  com.alethe.opf.dto.UserPair(c.user_id , c.loginid) from Users c WHERE (c.user_type = 'USR' AND (c.user_role ='CFO' OR c.user_role ='CSD'))")
//	public List<UserPair> getCsdParentUserPair();
//	
//	
//	@Query("SELECT new  com.alethe.opf.dto.UserPair(c.user_id , c.loginid) from Users c WHERE (c.user_type = 'USR' AND (c.user_role ='CFO'))")
//	public List<UserPair> getCfoParentUserPair();

	@Query(value = "SELECT user_id from user_mst where user_parent =:parent_id", nativeQuery = true)
	public Integer getChildId(@Param("parent_id") Integer id);

	@Query(value = "SELECT user_id from user_mst where user_parent =:parent_id", nativeQuery = true)
	public List<Integer> getChildList(@Param("parent_id") Integer id);

	
	@Query("SELECT new com.alethe.opf.dto.UserParent( c.user_id,c.user_role ,c.loginid) from Users c WHERE (c.user_type ='USR' AND (c.user_role ='CFO'))")
	public List<UserParent> getCsdParent();

	@Query("SELECT new com.alethe.opf.dto.UserParent( c.user_id,c.user_role ,c.loginid) from Users c WHERE (c.user_type ='USR' AND (c.user_role ='CSD'))")
	public List<UserParent> getTamParent();

	@Query("SELECT new com.alethe.opf.dto.UserParent( c.user_id,c.user_role ,c.loginid) from Users c WHERE (c.user_type ='USR' AND (c.user_role ='TAM'))")
	public List<UserParent> getFsoParent();

}
