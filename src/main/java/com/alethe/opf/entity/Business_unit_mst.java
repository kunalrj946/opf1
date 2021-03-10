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
@Table(name = "business_unit_mst")
public class Business_unit_mst {

	@Id
	@Column(columnDefinition = "character varying(3)")
	private String business_unit_id; // | character varying(3) | | not null |
	private String business_unit_name; // | character varying(50) | | not null |
	// private Timestamp created_on; // | timestamp without time zone | | | now()
	// private Integer created_by; // | integer | | not null | 1
	// private Timestamp modified_on; // | timestamp without time zone | | |
	// private Integer modified_by; // | integer | | |
	// private Integer is_deleted; // | integer | | not null | 0

}
