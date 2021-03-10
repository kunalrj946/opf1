package com.alethe.opf.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alethe.opf.entity.Sale_order;

/**
 * Created by Kunal Kumar
 */
@Repository
public interface Sale_order_repo extends JpaRepository<Sale_order, Integer> {

		
	@Query("select o.so_status from Sale_order o where o.so_id =:so_id")
	public int getStatus(@Param("so_id") Integer so_id);

	@Query("select o.created_by from Sale_order o where o.so_id =:so_id")
	public int getCreatedBy(@Param("so_id") Integer so_id);

	@Modifying
	@Query("update Sale_order o set o.cfo_remark = :remark ,o.cfo_approved_on=:date, o.so_status =:status where o.so_id = :so_id")
	public int updateCfoRemark(@Param("status") Integer status, @Param("remark") String remark,
			@Param("date") Timestamp date, @Param("so_id") Integer so_id);

	@Modifying
	@Query("update Sale_order o set o.zm_remark = :remark ,o.zm_approved_on=:date, o.so_status =:status where o.so_id = :so_id")
	public int updateZmRemark(@Param("status") Integer status, @Param("remark") String remark,
			@Param("date") Timestamp date, @Param("so_id") Integer so_id);

	@Modifying
	@Query("update Sale_order o set o.tam_remark = :remark ,o.tam_approved_on =:date , o.so_status =:status where o.so_id = :so_id")
	public int updateTamRemark(@Param("status") Integer status, @Param("remark") String remark,
			@Param("date") Timestamp date, @Param("so_id") Integer so_id);

	@Modifying
	@Query("update Sale_order o set o.am_remark = :remark ,o.am_approved_on=:date ,o.so_status =:status where o.so_id = :so_id")
	public Integer updateAmRemark(@Param("status") int status, @Param("remark") String remark,
			@Param("date") Timestamp date, @Param("so_id") int so_id);

	@Modifying
	@Query("update Sale_order o set o.so_status =:status where o.so_id = :so_id")
	public int updateStatusFinalByAdm(@Param("status") Integer status, @Param("so_id") Integer so_id);

	
	
	
	@Query(value = "select COUNT (*) FROM sale_order ", nativeQuery = true)
	public Integer getTotalSo();
	
	@Query(value = "select COUNT (*) FROM sale_order WHERE (am=:am_id OR created_by=:am_id)", nativeQuery = true)
	public Integer getTotalSoByAmId(@Param("am_id")int am_id);
	
	@Query(value = "select COUNT (*) FROM sale_order WHERE (tam=:tam_id OR created_by=:tam_id OR am=:am_id OR created_by=:am_id)", nativeQuery = true)
	public Integer getTotalSoByTamId(@Param("tam_id")int tam_id ,@Param("am_id")int am_id);
	
	@Query(value = "select COUNT (*) FROM sale_order WHERE (tam=:tam_id OR am=:am_id OR created_by=:csd_id OR created_by=:tam_id OR created_by=:am_id) ", nativeQuery = true)
	public Integer getTotalSoByCsdId(@Param("csd_id")int csd_id ,@Param("am_id")int am_id  ,@Param("tam_id")int tam_id );
	
	
	
	
	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='0' ", nativeQuery = true)
	public Integer getTotalDraftSo();
	
	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='0' AND (am=:am_id OR created_by=:am_id)", nativeQuery = true)
	public Integer getTotalDraftSoByFso(@Param("am_id")int am_id );
	
	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='0' AND (tam=:tam_id OR created_by=:tam_id OR am=:am_id OR created_by=:am_id)", nativeQuery = true)
	public Integer getTotalDraftSoByTam(@Param("tam_id")int tam_id ,@Param("am_id")int am_id);
	
	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='0' AND (tam=:tam_id OR am=:am_id OR created_by=:csd_id OR created_by=:am_id OR created_by=:tam_id)", nativeQuery = true)
	public Integer getTotalDraftSoByCsd(@Param("csd_id")int csd_id ,@Param("tam_id")int tam_id ,@Param("am_id")int am_id);
	


	

	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='1'", nativeQuery = true)
	public Integer getTotalFinalSo();
	
	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='1' AND (created_by=:am_id OR am=:am_id)", nativeQuery = true)
	public Integer getTotalFinalSoByFsoId(@Param("am_id") int am_id);
	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='1' AND (created_by=:tam_id OR tam=:tam_id OR am=:am_id OR created_by=:am_id)", nativeQuery = true)
	public Integer getTotalFinalSoByTamId(@Param("tam_id") int tam_id ,@Param("am_id")int am_id);
	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='1' AND (created_by=:csd_id OR created_by=:tam_id OR created_by=:am_id OR am=:am_id OR tam=:tam_id)", nativeQuery = true)
	public Integer getTotalFinalSoByCsdId(@Param("csd_id") int csd_id ,@Param("am_id") int am_id ,@Param("tam_id") int tam_id);

	
	
	
	@Query(value = "select COUNT (*) FROM sale_order  where (so_status > '1' AND so_status < '31')", nativeQuery = true)
	public Integer getTotalPendingSo();
	
	@Query(value = "select COUNT (*) FROM sale_order  where (so_status > '1' AND so_status < '31') AND (created_by=:am_id OR am=:am_id)", nativeQuery = true)
	public Integer getTotalPendingSoByFso(@Param("am_id") int am_id);
	@Query(value = "select COUNT (*) FROM sale_order  where (so_status > '1' AND so_status < '31') AND (created_by=:tam_id OR am=:tam_id OR am=:am_id OR created_by=:am_id)", nativeQuery = true)
	public Integer getTotalPendingSoByTam(@Param("tam_id") int tam_id ,@Param("am_id")int am_id);
	@Query(value = "select COUNT (*) FROM sale_order  where (so_status > '1' AND so_status < '31') AND (created_by=:csd_id OR created_by=:tam_id OR created_by=:am_id OR am=:am_id OR tam=:tam_id)", nativeQuery = true)
	public Integer getTotalPendingSoByCsd(@Param("csd_id") int csd_id ,@Param("am_id") int am_id ,@Param("tam_id") int tam_id);
	
	
	
	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='31' AND (am=:am_id OR created_by=:am_id) ", nativeQuery = true)
	public Integer getTotalCfoApprovedSoByAm(@Param("am_id")int am_id);
	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='31' AND (tam=:tam_id OR created_by=:tam_id OR am=:am_id OR created_by=:am_id)", nativeQuery = true)
	public Integer getTotalCfoApprovedSoByTam(@Param("tam_id")int tam_id ,@Param("am_id")int am_id);
	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='31' AND (created_by=:csd_id OR tam=:tam_id OR am=:am_id OR created_by=:am_id OR created_by=:tam_id)", nativeQuery = true)
	public Integer getTotalCfoApprovedSoByCsd(@Param("csd_id")int csd_id ,@Param("tam_id")int tam_id ,@Param("am_id")int am_id);
	
	@Query(value = "select COUNT (*) FROM sale_order  where so_status ='31'", nativeQuery = true)
	public Integer getTotalCfoApprovedSo();
	
	
	
	
	
	
	
	
}
