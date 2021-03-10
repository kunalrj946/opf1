package com.alethe.opf.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponse {

//	private static Logger logger = LoggerFactory.getLogger(OrderResponse.class);

	private Integer so_id;
	private String so_type = "SO";
//	private Integer tam;
//	private String tam_name;
	private String so_no;
	private Integer company_id;
	private String company_address;
	private String company_gstn;
	private String so_date;
	private Integer so_rev;
	private String so_reference;
	private String po_no;
	private String po_date;
	private String so_category_id;
	private String business_unit_id;
	private Integer am;
	private String am_name;
	private Integer customer_id;
	private String customer_name;
	private String customer_billing_name;
	private String customer_billing_address;
	private String customer_dispatch_address;
	private String customer_gstn;
	private String customer_segement;
	private String business_nature_id;
	private String primary_contact_name;
	private String primary_contact_phone;
	private String primary_contact_email;
	private String secondary_contact_name;
	private String secondary_contact_phone;
	private String secondary_contact_email;
	private Double total_purchase_amount;
	private Double total_sale_amount;
	private Double margin_amount;
	private Double margin_per;

	private Long po_attach1_id;
	private String po_attach1_name = "";
	private Long po_attach2_id;
	private String po_attach2_name = "";

	private Long so_attach1_id;
	private String so_attach1_name;
	private Long so_attach2_id;
	private String so_attach2_name = "";

	private String delivery_instruction;
	private Double other_expenses;
	private String payment_term;
	private String am_remark;
	private Date am_approved_on;
	private String tam_remark;
	private Date tam_approved_on;
	private String zm_remark;
	private Date zm_approved_on;
	private String cfo_remark;
	private Date cfo_approved_on;
	private Integer so_status;
	private String created_on;
	private Integer created_by;
	private Date modified_on;
	private Integer modified_by;
	private Integer is_deleted;

	public OrderResponse(Integer so_id, String so_type, String so_no, Integer company_id, String so_date,
			Integer so_rev, String so_reference, String po_no, String po_date, String so_category_id,
			String business_unit_id, Integer am, Integer customer_id, String customer_name,
			String customer_billing_name, String customer_billing_address, String customer_dispatch_address,
			String customer_gstn, String customer_segement, String business_nature_id, String primary_contact_name,
			String primary_contact_phone, String primary_contact_email, String secondary_contact_name,
			String secondary_contact_phone, String secondary_contact_email, Double total_purchase_amount,
			Double total_sale_amount, Double margin_amount, Double margin_per, Long po_attach1_id, Long po_attach2_id,
			Long so_attach1_id, Long so_attach2_id, String delivery_instruction, Double other_expenses,
			String payment_term, String am_remark, Date am_approved_on, String tam_remark, Date tam_approved_on,
			String zm_remark, Date zm_approved_on, String cfo_remark, Date cfo_approved_on, Integer so_status,
			String created_on, Integer created_by, Date modified_on, Integer modified_by, Integer is_deleted,
			String am_loginid, String am_fname, String am_lname, String file_name1, String file_name2,
			String file_name3, String file_name4 ,String bill_address1 ,String bill_address2  ,String company_gstn ) 
	{

		super();
		
		this.so_id = so_id;
		this.so_type = so_type;
		this.so_no = so_no;
//		this.tam = tam;
		this.company_id = company_id;

		if (so_date != null) {
			this.so_date = so_date.substring(0, 10);
		} else {

			this.so_date = so_date;

		}

		this.so_rev = so_rev;
		this.so_reference = so_reference;
		this.po_no = po_no;

		if (po_date != null) {
			this.po_date = po_date.substring(0, 10);
		} else {
			this.po_date = po_date;

		}
		this.so_category_id = so_category_id;
		this.business_unit_id = business_unit_id;
		this.am = am;
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_billing_name = customer_billing_name;
		this.customer_billing_address = customer_billing_address;
		this.customer_dispatch_address = customer_dispatch_address;
		this.customer_gstn = customer_gstn;
		this.customer_segement = customer_segement;
		this.business_nature_id = business_nature_id;
		this.primary_contact_name = primary_contact_name;
		this.primary_contact_phone = primary_contact_phone;
		this.primary_contact_email = primary_contact_email;
		this.secondary_contact_name = secondary_contact_name;
		this.secondary_contact_phone = secondary_contact_phone;
		this.secondary_contact_email = secondary_contact_email;
		this.total_purchase_amount = total_purchase_amount;
		this.total_sale_amount = total_sale_amount;
		this.margin_amount = margin_amount;
		this.margin_per = margin_per;
		this.po_attach1_id = po_attach1_id;
		this.po_attach2_id = po_attach2_id;
		this.so_attach1_id = so_attach1_id;
		this.so_attach2_id = so_attach2_id;
		this.delivery_instruction = delivery_instruction;
		this.other_expenses = other_expenses;
		this.payment_term = payment_term;
		this.am_remark = am_remark;
		this.am_approved_on = am_approved_on;
		this.tam_remark = tam_remark;
		this.tam_approved_on = tam_approved_on;
		this.zm_remark = zm_remark;
		this.zm_approved_on = zm_approved_on;
		this.cfo_remark = cfo_remark;
		this.cfo_approved_on = cfo_approved_on;
		this.so_status = so_status;

		if (created_on != null) {
			this.created_on = created_on.substring(0, 10);
		} else {
			this.created_on = created_on;

		}
		this.created_by = created_by;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
		this.is_deleted = is_deleted;

		if (am_loginid != null) {

			this.am_name = am_loginid;

		} else if (am_loginid == null) {

			if (am_fname != null && am_lname != null) {

				this.am_name = am_fname + " " + am_lname;

			} else if (!am_fname.isEmpty() || !am_lname.isEmpty()) {

				this.am_name = am_fname;
				this.am_name = am_lname;
			}

		}


		this.po_attach1_name = file_name1;
		this.po_attach2_name = file_name2;
		this.so_attach1_name = file_name3;
		this.so_attach2_name = file_name4;
		if(bill_address1!=null && bill_address2!=null) {

			this.company_address = "bill_address1: "+bill_address1 + ", bill_address2: " +bill_address2;
			
		}else if(bill_address1!=null && bill_address2==null) {
			
			this.company_address = "bill_address1: "+bill_address1 + ", bill_address2: ";
		
		}else if (bill_address1==null && bill_address2!=null) {
			
			this.company_address = "bill_address1: "+", bill_address2: " +bill_address2;
			
		}else {
			
			this.company_address="Not Available !";
		}

		this.company_gstn = company_gstn;

	}
	
	
	
	

	public Integer getSo_id() {
		return so_id;
	}

	public void setSo_id(Integer so_id) {
		this.so_id = so_id;
	}

	public String getSo_type() {
		return so_type;
	}

	public void setSo_type(String so_type) {
		this.so_type = so_type;
	}

	public String getSo_no() {
		return so_no;
	}

	public void setSo_no(String so_no) {
		this.so_no = so_no;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public String getCompany_gstn() {
		return company_gstn;
	}

	public void setCompany_gstn(String company_gstn) {
		this.company_gstn = company_gstn;
	}

	public String getSo_date() {
		return so_date;
	}

	public void setSo_date(String so_date) {
		this.so_date = so_date;
	}

	public Integer getSo_rev() {
		return so_rev;
	}

	public void setSo_rev(Integer so_rev) {
		this.so_rev = so_rev;
	}

	public String getSo_reference() {
		return so_reference;
	}

	public void setSo_reference(String so_reference) {
		this.so_reference = so_reference;
	}

	public String getPo_no() {
		return po_no;
	}

	public void setPo_no(String po_no) {
		this.po_no = po_no;
	}

	public String getPo_date() {
		return po_date;
	}

	public void setPo_date(String po_date) {
		this.po_date = po_date;
	}

	public String getSo_category_id() {
		return so_category_id;
	}

	public void setSo_category_id(String so_category_id) {
		this.so_category_id = so_category_id;
	}

	public String getBusiness_unit_id() {
		return business_unit_id;
	}

	public void setBusiness_unit_id(String business_unit_id) {
		this.business_unit_id = business_unit_id;
	}

	public Integer getAm() {
		return am;
	}

	public void setAm(Integer am) {
		this.am = am;
	}

	public String getAm_name() {
		return am_name;
	}

	public void setAm_name(String am_name) {
		this.am_name = am_name;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_billing_name() {
		return customer_billing_name;
	}

	public void setCustomer_billing_name(String customer_billing_name) {
		this.customer_billing_name = customer_billing_name;
	}

	public String getCustomer_billing_address() {
		return customer_billing_address;
	}

	public void setCustomer_billing_address(String customer_billing_address) {
		this.customer_billing_address = customer_billing_address;
	}

	public String getCustomer_dispatch_address() {
		return customer_dispatch_address;
	}

	public void setCustomer_dispatch_address(String customer_dispatch_address) {
		this.customer_dispatch_address = customer_dispatch_address;
	}

	public String getCustomer_gstn() {
		return customer_gstn;
	}

	public void setCustomer_gstn(String customer_gstn) {
		this.customer_gstn = customer_gstn;
	}

	public String getCustomer_segement() {
		return customer_segement;
	}

	public void setCustomer_segement(String customer_segement) {
		this.customer_segement = customer_segement;
	}

	public String getBusiness_nature_id() {
		return business_nature_id;
	}

	public void setBusiness_nature_id(String business_nature_id) {
		this.business_nature_id = business_nature_id;
	}

	public String getPrimary_contact_name() {
		return primary_contact_name;
	}

	public void setPrimary_contact_name(String primary_contact_name) {
		this.primary_contact_name = primary_contact_name;
	}

	public String getPrimary_contact_phone() {
		return primary_contact_phone;
	}

	public void setPrimary_contact_phone(String primary_contact_phone) {
		this.primary_contact_phone = primary_contact_phone;
	}

	public String getPrimary_contact_email() {
		return primary_contact_email;
	}

	public void setPrimary_contact_email(String primary_contact_email) {
		this.primary_contact_email = primary_contact_email;
	}

	public String getSecondary_contact_name() {
		return secondary_contact_name;
	}

	public void setSecondary_contact_name(String secondary_contact_name) {
		this.secondary_contact_name = secondary_contact_name;
	}

	public String getSecondary_contact_phone() {
		return secondary_contact_phone;
	}

	public void setSecondary_contact_phone(String secondary_contact_phone) {
		this.secondary_contact_phone = secondary_contact_phone;
	}

	public String getSecondary_contact_email() {
		return secondary_contact_email;
	}

	public void setSecondary_contact_email(String secondary_contact_email) {
		this.secondary_contact_email = secondary_contact_email;
	}

	public Double getTotal_purchase_amount() {
		return total_purchase_amount;
	}

	public void setTotal_purchase_amount(Double total_purchase_amount) {
		this.total_purchase_amount = total_purchase_amount;
	}

	public Double getTotal_sale_amount() {
		return total_sale_amount;
	}

	public void setTotal_sale_amount(Double total_sale_amount) {
		this.total_sale_amount = total_sale_amount;
	}

	public Double getMargin_amount() {
		return margin_amount;
	}

	public void setMargin_amount(Double margin_amount) {
		this.margin_amount = margin_amount;
	}

	public Double getMargin_per() {
		return margin_per;
	}

	public void setMargin_per(Double margin_per) {
		this.margin_per = margin_per;
	}

	public Long getPo_attach1_id() {
		return po_attach1_id;
	}

	public void setPo_attach1_id(Long po_attach1_id) {
		this.po_attach1_id = po_attach1_id;
	}

	public String getPo_attach1_name() {
		return po_attach1_name;
	}

	public void setPo_attach1_name(String po_attach1_name) {
		this.po_attach1_name = po_attach1_name;
	}

	public Long getPo_attach2_id() {
		return po_attach2_id;
	}

	public void setPo_attach2_id(Long po_attach2_id) {
		this.po_attach2_id = po_attach2_id;
	}

	public String getPo_attach2_name() {
		return po_attach2_name;
	}

	public void setPo_attach2_name(String po_attach2_name) {
		this.po_attach2_name = po_attach2_name;
	}

	public Long getSo_attach1_id() {
		return so_attach1_id;
	}

	public void setSo_attach1_id(Long so_attach1_id) {
		this.so_attach1_id = so_attach1_id;
	}

	public String getSo_attach1_name() {
		return so_attach1_name;
	}

	public void setSo_attach1_name(String so_attach1_name) {
		this.so_attach1_name = so_attach1_name;
	}

	public Long getSo_attach2_id() {
		return so_attach2_id;
	}

	public void setSo_attach2_id(Long so_attach2_id) {
		this.so_attach2_id = so_attach2_id;
	}

	public String getSo_attach2_name() {
		return so_attach2_name;
	}

	public void setSo_attach2_name(String so_attach2_name) {
		this.so_attach2_name = so_attach2_name;
	}

	public String getDelivery_instruction() {
		return delivery_instruction;
	}

	public void setDelivery_instruction(String delivery_instruction) {
		this.delivery_instruction = delivery_instruction;
	}

	public Double getOther_expenses() {
		return other_expenses;
	}

	public void setOther_expenses(Double other_expenses) {
		this.other_expenses = other_expenses;
	}

	public String getPayment_term() {
		return payment_term;
	}

	public void setPayment_term(String payment_term) {
		this.payment_term = payment_term;
	}

	public String getAm_remark() {
		return am_remark;
	}

	public void setAm_remark(String am_remark) {
		this.am_remark = am_remark;
	}

	public Date getAm_approved_on() {
		return am_approved_on;
	}

	public void setAm_approved_on(Date am_approved_on) {
		this.am_approved_on = am_approved_on;
	}

	public String getTam_remark() {
		return tam_remark;
	}

	public void setTam_remark(String tam_remark) {
		this.tam_remark = tam_remark;
	}

	public Date getTam_approved_on() {
		return tam_approved_on;
	}

	public void setTam_approved_on(Date tam_approved_on) {
		this.tam_approved_on = tam_approved_on;
	}

	public String getZm_remark() {
		return zm_remark;
	}

	public void setZm_remark(String zm_remark) {
		this.zm_remark = zm_remark;
	}

	public Date getZm_approved_on() {
		return zm_approved_on;
	}

	public void setZm_approved_on(Date zm_approved_on) {
		this.zm_approved_on = zm_approved_on;
	}

	public String getCfo_remark() {
		return cfo_remark;
	}

	public void setCfo_remark(String cfo_remark) {
		this.cfo_remark = cfo_remark;
	}

	public Date getCfo_approved_on() {
		return cfo_approved_on;
	}

	public void setCfo_approved_on(Date cfo_approved_on) {
		this.cfo_approved_on = cfo_approved_on;
	}

	public Integer getSo_status() {
		return so_status;
	}

	public void setSo_status(Integer so_status) {
		this.so_status = so_status;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public Integer getModified_by() {
		return modified_by;
	}

	public void setModified_by(Integer modified_by) {
		this.modified_by = modified_by;
	}

	public Integer getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Integer is_deleted) {
		this.is_deleted = is_deleted;
	}

	
	

}
