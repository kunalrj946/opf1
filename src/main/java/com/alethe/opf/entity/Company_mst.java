package com.alethe.opf.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
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
@Table(schema = "public", name = "company_mst" )
public class Company_mst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer company_id;// | integer | | not null | nextval('company_mst_company_id_seq'::regclass)
	
	@Column(name = "company_name" ,nullable = false ,length = 500)
	private String company_name;// | character varying(500) | | not null |
	
	@Column(name = "company_billing_name" ,nullable = false ,length = 500)
	private String company_billing_name;// | character varying(500) | | not null |
	
	@Column(name = "bill_address1" ,nullable = false ,length = 500)
	private String bill_address1;// | character varying(500) | | not null |
	
	@Column(name = "bill_address2" ,nullable = false ,length = 500)
	private String bill_address2;// | character varying(500) | | not null |
	
	@Column(name = "bill_city" ,nullable = false ,length = 150)
	private String bill_city;// | character varying(150) | | not null |
	
	@Column(name = "bill_state" ,nullable = false ,length = 150)
	private String bill_state;// | character varying(150) | | not null |
	
	@Column(name = "bill_country" ,nullable = false ,length = 150)
	private String bill_country;// | character varying(150) | | not null |
	
	@Column(name = "bill_pin" ,nullable = false ,length = 10)
	private String bill_pin;// | character varying(10) | | not null |
	
	@Column(name = "disp_address1" ,nullable = false ,length = 500)
	private String disp_address1;// | character varying(500) | | not null |
	
	@Column(name = "disp_address2" ,nullable = false ,length = 500)
	private String disp_address2;// | character varying(500) | | not null |
	
	@Column(name = "disp_city" ,nullable = false ,length = 150)
	private String disp_city;// | character varying(150) | | not null |
	
	@Column(name = "disp_state" ,nullable = false ,length = 150)
	private String disp_state;// | character varying(150) | | not null |
	
	@Column(name = "disp_country" ,nullable = false ,length = 150)
	private String disp_country;// | character varying(150) | | not null |
	
	@Column(name = "disp_pin" ,nullable = false ,length = 10)
	private String disp_pin;// | character varying(10) | | not null |
	
	@Column(name = "so_initials" ,nullable = false ,length = 20 ,unique = true)
	private String so_initials;// | character varying(20) | | not null |
	
	@Column(name = "gstn" ,nullable = false ,length = 20 , unique = true)
	private String gstn;// | character varying(20) | | not null |
	
	@Column(name = "pan" ,nullable = false ,length = 20 , unique = true)
	private String pan;// | character varying(20) | | not null |
	
	@Column(name = "tan" ,nullable = false ,length = 20 , unique = true)
	private String tan;// | character varying(20) | | not null |
	
	@Column(name = "disclaimer" ,nullable = true ,columnDefinition = "text")
	private String disclaimer;// | text | | |
	
	@Column(name = "t_and_c" ,nullable = true ,columnDefinition = "text")
	private String t_and_c;// | text | | |
	
	@Column(name = "created_on" ,nullable = true , columnDefinition = "timestamp without time zone default now()")
	private Timestamp created_on;// | timestamp without time zone | | | now()
	
	@Column(name = "created_by" ,nullable = false ,columnDefinition = "integer default 1")
	private Integer created_by;// | integer | | not null | 1
	
	@Column(name = "modified_on" ,nullable = true)
	private Timestamp modified_on;// | timestamp without time zone | | |
	
	@Column(name = "modified_by" ,nullable = true)
	private Integer modified_by;// | integer | | |
	
	@Column(name = "is_active" ,nullable = false ,columnDefinition = "integer default 1" )
	private Integer is_active;// | integer | | not null | 1
	
	@Column(name = "is_deleted" ,nullable = false ,columnDefinition = "integer default 0" )
	private Integer is_deleted;// | integer | | not null | 0

	
}



//uniqueConstraints={@UniqueConstraint(columnNames = {"so_initials","gstn" , "tan" , "pan"})}