package com.alethe.opf.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

/**
 * Created by Kunal Kumar
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sale_order")
public class SaleOrderJoin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer so_id; // | integer | | not null | nextval('sale_order_so_id_seq'::regclass)
	private String so_type = "SO";
	
	private Integer tam; // | character varying(2) | | not null | 'SO'::character varying

	@Column(nullable = false, unique = true)
	private String so_no; // | character varying(20) | | not null |

	private Integer company_id; // | integer | | not null |
	private String so_date; // | timestamp without time zone | | | now()

	@Column(nullable = false, unique = true)
	private Integer so_rev; // | integer | | not null | 1
	private String so_reference; // | character varying(200) | | not null |
	private String po_no; // | character varying(300) | | |
	private String po_date; // | timestamp without time zone | | |
	private String so_category_id; // | character varying(3) | | not null |
	private String business_unit_id; // | character varying(3) | | not null |
	private Integer am; // | integer | | not null |
	private Integer customer_id; // | integer | | not null |
	private String customer_name; // | character varying(500) | | not null |
	private String customer_billing_name; // EXTRA ADDING
	private String customer_billing_address; // | character varying(1500) | | not null |
	private String customer_dispatch_address; // | character varying(1500) | | not null |
	private String customer_gstn; // | character varying(20) | | not null |
	private String customer_segement; // | character varying(3) | | not null |
	private String business_nature_id; // | character varying(3) | | not null |
	private String primary_contact_name; // | character varying(100) | | not null |
	private String primary_contact_phone; // | character varying(100) | | not null |
	private String primary_contact_email; // | character varying(100) | | not null |
	private String secondary_contact_name; // | character varying(100) | | |
	private String secondary_contact_phone; // | character varying(100) | | |
	private String secondary_contact_email; // | character varying(100) | | |
	private Double total_purchase_amount; // | double precision | | |
	private Double total_sale_amount; // | double precision | | |
	private Double margin_amount; // | double precision | | |
	private Double margin_per; // | double precision | | |
	private Long po_attach1_id; // | bigint | | |
	private Long po_attach2_id; // | bigint | | |
	private Long so_attach1_id; // | bigint | | |
	private Long so_attach2_id; // | bigint | | |
	private String delivery_instruction; // | character varying(100) | | |
	private Double other_expenses; // | double precision | | |

	@Column(columnDefinition = "text")
	private String payment_term; // | text | | |

	@Column(columnDefinition = "text")
	private String am_remark; // | text | | |

	private Timestamp am_approved_on; // | timestamp without time zone | | |

	@Column(columnDefinition = "text")
	private String tam_remark; // | text | | |

	private Timestamp tam_approved_on; // | timestamp without time zone | | |

	@Column(columnDefinition = "text")
	private String zm_remark; // | text | | |

	private Timestamp zm_approved_on; // | timestamp without time zone | | |

	@Column(columnDefinition = "text")
	private String cfo_remark; // | text | | |

	private Timestamp cfo_approved_on; // | timestamp without time zone | | |
	private Integer so_status; // | integer | | |
	private String created_on; // | timestamp without time zone | | | now()
	private Integer created_by; // | integer | | | 0
	private Timestamp modified_on; // | timestamp without time zone | | |
	private Integer modified_by; // | integer | | |
	private Integer is_deleted; // | integer | | |

	@OneToMany(targetEntity = Users.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "am", updatable = false, insertable = false)
	private List<Users> am_name;

	@OneToMany(targetEntity = Users.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "tam", updatable = false, insertable = false)
	private List<Users> tam_name;
	
	@OneToMany(targetEntity = File_upload.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "file_id",referencedColumnName = "po_attach1_id" , nullable = true, updatable = false, insertable = false)
	private List<File_upload> file1;


	@OneToMany(targetEntity = File_upload.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "file_id",referencedColumnName = "po_attach2_id" , nullable = true, updatable = false, insertable = false)
	private List<File_upload> file2;

	@OneToMany(targetEntity = File_upload.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "file_id",referencedColumnName = "so_attach1_id" , nullable = true, updatable = false, insertable = false)
	private List<File_upload> file3;

	@OneToMany(targetEntity = File_upload.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "file_id",referencedColumnName = "so_attach2_id" , nullable = true, updatable = false, insertable = false)
	private List<File_upload> file4;


	@OneToMany(targetEntity = Company_mst.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "company_id",referencedColumnName = "company_id" , nullable = true, updatable = false, insertable = false)
	private List<Company_mst> company_address;

}
