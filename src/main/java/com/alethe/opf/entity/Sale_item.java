package com.alethe.opf.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Kunal Kumar
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sale_item")
public class Sale_item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private Long quotation2_id; // | bigint | | |
	private Integer sort_order; // | integer | | |
	private Timestamp created_on; // | timestamp without time zone | | | now()
	private Integer created_by; // | integer | | | 0
	private Timestamp modified_on; // | timestamp without time zone | | |
	private Integer modified_by; // | integer | | |
	private Integer is_deleted; // | integer | | |


	
}
