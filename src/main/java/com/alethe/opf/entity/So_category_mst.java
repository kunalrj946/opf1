package com.alethe.opf.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Created by Kunal Kumar
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "so_category_mst")
@Entity
public class So_category_mst {

	@Id
	private String so_category_id; // | character varying(3) | | not null |
	private String so_category_name; // | character varying(50) | | not null |
	// private Timestamp created_on ; // | timestamp without time zone | | | now()
	// private Integer created_by ; // | integer | | not null | 1
	// private Timestamp modified_on ; // | timestamp without time zone | | |
	// private Integer modified_by ; // | integer | | |
	// private Integer is_deleted ; // | integer | | not null | 0

}
