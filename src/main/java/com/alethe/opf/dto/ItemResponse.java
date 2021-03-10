package com.alethe.opf.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ItemResponse {

	private Integer item_id; // | integer | | not null | nextval('sale_item_item_id_seq'::regclass)
	private String so_no; // | character varying(20) | | not null |
	private Integer so_rev; // | integer | | not null |
	private String hsn_code; // | character varying(100) | | not null |
	private String part_no; // | character varying(100) | | not null |
	private String description; // | character varying(500) | | not null |
	private Integer total_qty; // | integer | | not null |
	private Double unit_sale_price; // | double precision | | not null |
	private Double sale_amt_wot; // | double precision | | not null |
	private Double sale_gst; // | double precision | | not null |
	private Double sale_wt; // | double precision | | not null |
	private Double unit_purchase_price; // | double precision | | not null |
	private Double purchase_amt_wot; // | double precision | | not null |
	private Double purchase_gst; // | double precision | | not null |
	private Double purchase_wt; // | double precision | | not null |
	private Double profit; // | double precision | | not null |
	private Double profit_per; // | double precision | | not null |
	private Long quotation1_id; // | bigint | | |
	private String quotation1_name="";
	private Long quotation2_id; // | bigint | | |
	private String quotation2_name="";
	private Integer sort_order; // | integer | | |
	private Date created_on; // | timestamp without time zone | | | now()
	private Integer created_by; // | integer | | | 0
	private Date modified_on; // | timestamp without time zone | | |
	private Integer modified_by; // | integer | | |
	private Integer is_deleted; // | integer | | |

	public ItemResponse(Integer item_id, String so_no, Integer so_rev, String hsn_code, String part_no,
			String description, Integer total_qty, Double unit_sale_price, Double sale_amt_wot, Double sale_gst,
			Double sale_wt, Double unit_purchase_price, Double purchase_amt_wot, Double purchase_gst,
			Double purchase_wt, Double profit, Double profit_per, Long quotation1_id, Long quotation2_id,
			Integer sort_order, Date created_on, Integer created_by, Date modified_on, Integer modified_by,
			Integer is_deleted, String quotation1_name, String quotation2_name) {
		super();
		this.item_id = item_id;
		this.so_no = so_no;
		this.so_rev = so_rev;
		this.hsn_code = hsn_code;
		this.part_no = part_no;
		this.description = description;
		this.total_qty = total_qty;
		this.unit_sale_price = unit_sale_price;
		this.sale_amt_wot = sale_amt_wot;
		this.sale_gst = sale_gst;
		this.sale_wt = sale_wt;
		this.unit_purchase_price = unit_purchase_price;
		this.purchase_amt_wot = purchase_amt_wot;
		this.purchase_gst = purchase_gst;
		this.purchase_wt = purchase_wt;
		this.profit = profit;
		this.profit_per = profit_per;
		this.quotation1_id = quotation1_id;
		this.quotation1_name = quotation1_name;
		this.quotation2_id = quotation2_id;
		this.quotation2_name = quotation2_name;
		this.sort_order = sort_order;
		this.created_on = created_on;
		this.created_by = created_by;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.is_deleted = is_deleted;
	}

}
