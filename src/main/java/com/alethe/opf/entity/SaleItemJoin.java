package com.alethe.opf.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "sale_item")
public class SaleItemJoin  {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer item_id; // | integer | | not null | nextval('sale_item_item_id_seq'::regclass)
	private String so_no; // | character varying(20) | | not null |
	private Integer so_rev; // | integer | | not null |
	private String hsn_code; // | character varying(100) | | not null |
	private String part_no; // | character varying(100) | | not null |
	private String description; // | character varying(500) | | not null |
//	@NotBlank(message = "total_qty can't be blank")
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

	
	@OneToMany(targetEntity = File_upload.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "file_id", referencedColumnName = "quotation1_id", nullable = true, updatable = false, insertable = false)
	private List<File_upload> file1;


	@OneToMany(targetEntity = File_upload.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "file_id", referencedColumnName = "quotation2_id", nullable = true, updatable = false, insertable = false)
	private List<File_upload> file2;


}
