package com.alethe.opf.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "customer_segment_mst")
public class Customer_segment_mst {
	
	@Id()
	@Column(name ="customer_segment_id", nullable = false , columnDefinition = "character varying(3)")
	private String     customer_segment_id ;  //  | character varying(3)        |           | not null | 
	
	@Column(name ="customer_segment_name")
	private String     customer_segment_name ;  //   | character varying(50)       |           | not null | 

	
	
//	@Column(name ="created_on")
//	private Timestamp  created_on ;  //              | timestamp without time zone |           |          | now()
//	
//	@Column(name ="created_by")
//	private Integer    created_by  ;  //             | integer                     |           | not null | 1
//	
//	@Column(name ="modified_on")
//	private Timestamp  modified_on  ;  //            | timestamp without time zone |           |          | 
//	
//	@Column(name ="modified_by")
//	private Integer    modified_by   ;  //           | integer                     |           |          | 
//	
//	@Column(name ="is_deleted")
//	private Integer    is_deleted   ;  //            | integer                     |           | not null | 0
//	

	
}
