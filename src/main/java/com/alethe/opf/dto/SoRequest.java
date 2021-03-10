package com.alethe.opf.dto;

import java.util.List;

import com.alethe.opf.entity.Sale_item;
import com.alethe.opf.entity.Sale_order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SoRequest {
	
	
	private Sale_order sale_order;
	private List<Sale_item>  sale_item;

}
