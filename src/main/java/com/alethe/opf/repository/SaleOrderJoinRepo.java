package com.alethe.opf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alethe.opf.dto.OrderResponse;
import com.alethe.opf.entity.SaleOrderJoin;

@Repository
public interface SaleOrderJoinRepo extends JpaRepository<SaleOrderJoin, Long> {

	@Query("SELECT new com.alethe.opf.dto.OrderResponse(o.so_id, o.so_type, o.so_no, o.company_id,o.so_date, o.so_rev,"
			+ "o.so_reference, o.po_no, o.po_date,o.so_category_id, o.business_unit_id, o.am, o.customer_id, o.customer_name,"
			+ "o.customer_billing_name, o.customer_billing_address, o.customer_dispatch_address,o.customer_gstn, "
			+ "o.customer_segement, o.business_nature_id, o.primary_contact_name,o.primary_contact_phone, o.primary_contact_email,"
			+ "o.secondary_contact_name,o.secondary_contact_phone, o.secondary_contact_email, o.total_purchase_amount,"
			+ "o.total_sale_amount, o.margin_amount, o.margin_per, o.po_attach1_id, o.po_attach2_id,o.so_attach1_id, "
			+ "o.so_attach2_id, o.delivery_instruction, o.other_expenses,o.payment_term, o.am_remark, o.am_approved_on,"
			+ "o.tam_remark,o.tam_approved_on, o.zm_remark, o.zm_approved_on, o.cfo_remark,o.cfo_approved_on, o.so_status,"
			+ "o.created_on, o.created_by,o.modified_on, o.modified_by, o.is_deleted, u.loginid ,u.user_fname ,u.user_lname ,"
			+ "f.filename ,f2.filename, f3.filename ,f4.filename ,ca.bill_address1 ,ca.bill_address2 ,ca.gstn)"
			+ " FROM SaleOrderJoin  o " + "LEFT JOIN  o.am_name    as u  ON u.user_id = o.am "
			+ "LEFT JOIN  o.file1      as f  ON   f.file_id = o.po_attach1_id "
			+ "LEFT JOIN  o.file2      as f2 ON  f2.file_id = o.po_attach2_id "
			+ "LEFT JOIN  o.file3      as f3 ON  f3.file_id = o.so_attach1_id "
			+ "LEFT JOIN  o.file4      as f4 ON  f4.file_id = o.so_attach2_id " 
			+ "LEFT JOIN  o.company_address as ca ON ca.company_id = o.company_id "	+ "ORDER BY o.created_on DESC, o.so_no  DESC")

	public List<OrderResponse> getAllOrder();


	@Query("SELECT new com.alethe.opf.dto.OrderResponse(o.so_id, o.so_type , o.so_no, o.company_id,o.so_date, o.so_rev,"
			+ "o.so_reference, o.po_no, o.po_date,o.so_category_id, o.business_unit_id, o.am, o.customer_id, o.customer_name,"
			+ "o.customer_billing_name, o.customer_billing_address, o.customer_dispatch_address,o.customer_gstn, "
			+ "o.customer_segement, o.business_nature_id, o.primary_contact_name,o.primary_contact_phone, o.primary_contact_email,"
			+ "o.secondary_contact_name,o.secondary_contact_phone, o.secondary_contact_email, o.total_purchase_amount,"
			+ "o.total_sale_amount, o.margin_amount, o.margin_per, o.po_attach1_id, o.po_attach2_id,o.so_attach1_id, "
			+ "o.so_attach2_id, o.delivery_instruction, o.other_expenses,o.payment_term, o.am_remark, o.am_approved_on,"
			+ " o.tam_remark,o.tam_approved_on, o.zm_remark, o.zm_approved_on, o.cfo_remark,o.cfo_approved_on, o.so_status,"
			+ " o.created_on, o.created_by,o.modified_on, o.modified_by, o.is_deleted, u.loginid ,u.user_fname ,u.user_lname ,"
			+ " f.filename ,f2.filename, f3.filename ,f4.filename ,ca.bill_address1 ,ca.bill_address2 ,ca.gstn )"
			+ "FROM SaleOrderJoin as o " + "LEFT JOIN  o.am_name         as u ON u.user_id = o.am  "
			+ "LEFT JOIN  o.file1 as f  ON  f.file_id = o.po_attach1_id  "
			+ "LEFT JOIN  o.file2 as f2 ON  f2.file_id = o.po_attach2_id "
			+ "LEFT JOIN  o.file3 as f3 ON  f3.file_id = o.so_attach1_id "
			+ "LEFT JOIN  o.file4 as f4 ON  f4.file_id = o.so_attach2_id " 
			+ "LEFT JOIN  o.company_address as ca ON ca.company_id = o.company_id "	+ " WHERE (o.so_id = :id )"  + "ORDER BY o.created_on DESC, o.so_no  DESC ")

	public OrderResponse getById(@Param("id") Integer id);

	@Query("SELECT new com.alethe.opf.dto.OrderResponse(o.so_id, o.so_type,o.so_no, o.company_id,o.so_date, o.so_rev,"
			+ "o.so_reference, o.po_no, o.po_date,o.so_category_id, o.business_unit_id, o.am, o.customer_id, o.customer_name,"
			+ "o.customer_billing_name, o.customer_billing_address, o.customer_dispatch_address,o.customer_gstn, "
			+ "o.customer_segement, o.business_nature_id, o.primary_contact_name,o.primary_contact_phone, o.primary_contact_email,"
			+ "o.secondary_contact_name,o.secondary_contact_phone, o.secondary_contact_email, o.total_purchase_amount,"
			+ "o.total_sale_amount, o.margin_amount, o.margin_per, o.po_attach1_id, o.po_attach2_id,o.so_attach1_id, "
			+ "o.so_attach2_id, o.delivery_instruction, o.other_expenses,o.payment_term, o.am_remark, o.am_approved_on,"
			+ " o.tam_remark,o.tam_approved_on, o.zm_remark, o.zm_approved_on, o.cfo_remark,o.cfo_approved_on, o.so_status,"
			+ " o.created_on, o.created_by,o.modified_on, o.modified_by, o.is_deleted, u.loginid ,u.user_fname ,u.user_lname,"
			+ " f.filename ,f2.filename, f3.filename ,f4.filename ,ca.bill_address1 ,ca.bill_address2 ,ca.gstn )"
			+ "FROM SaleOrderJoin as o " + "LEFT JOIN  o.am_name         as u ON u.user_id = o.am  "
			+ "LEFT JOIN  o.file1 as f  ON  f.file_id = o.po_attach1_id  "
			+ "LEFT JOIN  o.file2 as f2 ON  f2.file_id = o.po_attach2_id "
			+ "LEFT JOIN  o.file3 as f3 ON  f3.file_id = o.so_attach1_id "
			+ "LEFT JOIN  o.file4 as f4 ON  f4.file_id = o.so_attach2_id " 
			+ "LEFT JOIN  o.company_address as ca ON ca.company_id = o.company_id "	+ " WHERE (o.am = :am_id OR o.created_by =:am_id)" + "ORDER BY o.created_on DESC, o.so_no  DESC ")

	public List<OrderResponse> getAmOrder(@Param("am_id") Integer am_id);

	@Query("SELECT new com.alethe.opf.dto.OrderResponse(o.so_id, o.so_type ,o.so_no, o.company_id,o.so_date, o.so_rev,"
			+ "o.so_reference, o.po_no, o.po_date,o.so_category_id, o.business_unit_id, o.am, o.customer_id, o.customer_name,"
			+ "o.customer_billing_name, o.customer_billing_address, o.customer_dispatch_address,o.customer_gstn, "
			+ "o.customer_segement, o.business_nature_id, o.primary_contact_name,o.primary_contact_phone, o.primary_contact_email,"
			+ "o.secondary_contact_name,o.secondary_contact_phone, o.secondary_contact_email, o.total_purchase_amount,"
			+ "o.total_sale_amount, o.margin_amount, o.margin_per, o.po_attach1_id, o.po_attach2_id,o.so_attach1_id, "
			+ "o.so_attach2_id, o.delivery_instruction, o.other_expenses,o.payment_term, o.am_remark, o.am_approved_on,"
			+ " o.tam_remark,o.tam_approved_on, o.zm_remark, o.zm_approved_on, o.cfo_remark,o.cfo_approved_on, o.so_status,"
			+ " o.created_on, o.created_by,o.modified_on, o.modified_by, o.is_deleted, u.loginid ,u.user_fname ,u.user_lname ,"
			+ " f.filename ,f2.filename, f3.filename ,f4.filename ,ca.bill_address1 ,ca.bill_address2 ,ca.gstn)"
			+ "FROM SaleOrderJoin as o " + "LEFT JOIN  o.am_name         as u ON u.user_id = o.am  "
			+ "LEFT JOIN  o.file1 as f  ON  f.file_id = o.po_attach1_id  "
			+ "LEFT JOIN  o.file2 as f2 ON  f2.file_id = o.po_attach2_id "
			+ "LEFT JOIN  o.file3 as f3 ON  f3.file_id = o.so_attach1_id "
			+ "LEFT JOIN  o.file4 as f4 ON  f4.file_id = o.so_attach2_id " 
			+ "LEFT JOIN  o.company_address as ca ON ca.company_id = o.company_id "	+ "WHERE (o.tam = :tam OR o.created_by =:tam OR o.am=:am_id OR o.created_by=:am_id )"  + "ORDER BY o.created_on DESC, o.so_no  DESC ")

	public List<OrderResponse> getTamOrder(@Param("tam") Integer tam, @Param("am_id") Integer am_id);

	@Query("SELECT new com.alethe.opf.dto.OrderResponse(o.so_id, o.so_type, o.so_no, o.company_id,o.so_date, o.so_rev,"
			+ "o.so_reference, o.po_no, o.po_date,o.so_category_id, o.business_unit_id, o.am, o.customer_id, o.customer_name,"
			+ "o.customer_billing_name, o.customer_billing_address, o.customer_dispatch_address,o.customer_gstn, "
			+ "o.customer_segement, o.business_nature_id, o.primary_contact_name,o.primary_contact_phone, o.primary_contact_email,"
			+ "o.secondary_contact_name,o.secondary_contact_phone, o.secondary_contact_email, o.total_purchase_amount,"
			+ "o.total_sale_amount, o.margin_amount, o.margin_per, o.po_attach1_id, o.po_attach2_id,o.so_attach1_id, "
			+ "o.so_attach2_id, o.delivery_instruction, o.other_expenses,o.payment_term, o.am_remark, o.am_approved_on,"
			+ " o.tam_remark,o.tam_approved_on, o.zm_remark, o.zm_approved_on, o.cfo_remark,o.cfo_approved_on, o.so_status,"
			+ " o.created_on, o.created_by,o.modified_on, o.modified_by, o.is_deleted, u.loginid ,u.user_fname ,u.user_lname,"
			+ " f.filename ,f2.filename, f3.filename ,f4.filename ,ca.bill_address1 ,ca.bill_address2 ,ca.gstn)"
			+ "FROM SaleOrderJoin as o " + "LEFT JOIN  o.am_name         as u ON u.user_id = o.am  "
			+ "LEFT JOIN  o.file1 as f  ON  f.file_id = o.po_attach1_id  "
			+ "LEFT JOIN  o.file2 as f2 ON  f2.file_id = o.po_attach2_id "
			+ "LEFT JOIN  o.file3 as f3 ON  f3.file_id = o.so_attach1_id "
			+ "LEFT JOIN  o.file4 as f4 ON  f4.file_id = o.so_attach2_id "
			+ "LEFT JOIN  o.company_address as ca ON ca.company_id = o.company_id "	+ "WHERE (o.created_by =:zm_id OR o.am =:amid OR o.tam=:tamid OR o.created_by =:amid OR o.created_by =:tamid)"  + "ORDER BY o.created_on DESC, o.so_no  DESC ")

	public List<OrderResponse> getZmOrder(@Param("zm_id") Integer zm_id, @Param("amid") Integer amid,
			@Param("tamid") Integer tamid);

}
