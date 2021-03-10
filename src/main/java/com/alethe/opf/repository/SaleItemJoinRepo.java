package com.alethe.opf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alethe.opf.dto.ItemResponse;
import com.alethe.opf.entity.SaleItemJoin;

@Repository
public interface SaleItemJoinRepo extends JpaRepository<SaleItemJoin, Integer> {

	@Query("SELECT new com.alethe.opf.dto.ItemResponse(s.item_id, s.so_no, s.so_rev, s.hsn_code, s.part_no,"
			+ " s.description, s.total_qty, s.unit_sale_price, s.sale_amt_wot, s.sale_gst, s.sale_wt,"
			+ " s.unit_purchase_price, s.purchase_amt_wot, s.purchase_gst, s.purchase_wt, s.profit, "
			+ " s.profit_per, s.quotation1_id, s.quotation2_id, s.sort_order, s.created_on, s.created_by,"
			+ " s.modified_on, s.modified_by,s.is_deleted, " + "t.filename , u.filename)" + " FROM SaleItemJoin s  "
			+ " LEFT JOIN  s.file1 t ON  t.file_id = s.quotation1_id  "
			+ " LEFT JOIN  s.file2 u ON  u.file_id = s.quotation2_id")

	public List<ItemResponse> getItemData();

	@Query("SELECT new com.alethe.opf.dto.ItemResponse(s.item_id, s.so_no, s.so_rev, s.hsn_code, s.part_no,"
			+ " s.description, s.total_qty, s.unit_sale_price, s.sale_amt_wot, s.sale_gst, s.sale_wt,"
			+ " s.unit_purchase_price, s.purchase_amt_wot, s.purchase_gst, s.purchase_wt, s.profit, "
			+ " s.profit_per, s.quotation1_id, s.quotation2_id, s.sort_order, s.created_on, s.created_by,"
			+ " s.modified_on, s.modified_by,s.is_deleted, " + "t.filename , u.filename)" + " FROM SaleItemJoin as s "
			+ " LEFT JOIN  s.file1 as t ON  t.file_id = s.quotation1_id  "
			+ " LEFT JOIN  s.file2 as u ON  u.file_id = s.quotation2_id "
			+ " WHERE (s.so_no = :so_no AND s.so_rev = :so_rev) ")

	public List<ItemResponse> getItemDataBySoNo(@Param("so_no") String so_no, @Param("so_rev") Integer so_rev);

}
