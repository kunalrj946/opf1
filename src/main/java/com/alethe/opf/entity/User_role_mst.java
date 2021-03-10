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
@Table(name = "user_role_mst")
public class User_role_mst {
	@Id
	@Column(name = "user_role_id", length = 3, nullable = false)
	@JsonProperty("userRoleId")
	private String userRoleId;

	@Column(name = "user_role_name")
	@JsonProperty("userRoleName")
	private String userRoleName;

//	@Column(name = "created_on")
//	private Timestamp created_on;
//
//	@Column(name = "created_by")
//	private Integer created_by;
//
//	@Column(name = "modified_on")
//	private Timestamp modified_on;
//
//	@Column(name = "modified_by")
//	private Integer modified_by;
//	
//	@Column(name = "is_deleted")
//	private Integer is_deleted;

}
