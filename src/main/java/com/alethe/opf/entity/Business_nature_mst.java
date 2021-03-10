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
@Table(name = "business_nature_mst")
public class Business_nature_mst {

	@Id
	@Column(columnDefinition = "character varying(3)")
	private String business_nature_id; // | character varying(3) | | not null |
	private String business_nature_name; // | character varying(50) | | not null |

}
