package com.alethe.opf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Table(name = "user_type_mst")
public class User_type_mst {
	@Id
	@Column(name = "user_type_id", length = 3, nullable = false)
	@JsonProperty("userTypeId")
	private String userTypeId; // | character varying(3) | | not null |

	@Column(name = "user_type_name", length = 50, nullable = false)
	@JsonProperty("userTypeName")
	private String userTypeName; // | character varying(50) | | not null |

//	@Column(name = "created_on" ,nullable = true, columnDefinition = "timestamp without time zone default now()")
//	private Timestamp created_on = Utility.getCurrentTime(); // | timestamp without time zone | | | now()
//
//	@Column(name = "created_by" ,nullable = false, columnDefinition = "integer default 1")
//	private Integer created_by = 1; // | integer | | not null | 1
//
//	@Column(name = "modified_on" ,nullable = true)
//	private Timestamp modified_on; // | timestamp without time zone | | |
//
//	@Column(name = "modified_by" ,nullable = true)
//	private Integer modified_by ; // | integer | | |
//
//	
//	@Column(name = "is_deleted" ,nullable = false, columnDefinition = "integer default 0")
//	private Integer is_deleted =0; // | integer | | not null | 0

}
