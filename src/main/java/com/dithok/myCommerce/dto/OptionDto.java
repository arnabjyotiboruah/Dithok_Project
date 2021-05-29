//package com.dithok.myCommerce.dto;
//import java.util.List;
//
//import com.dithok.myCommerce.model.OptionModel;
//
//public class OptionDto {
//
//	//Option and Option_Value
//	private long product_id;
//	private long option_id;
//	private String optionName;
//	private String optionDescription;
//	private List<OptionValuesDto>  optionValuesDto;
//
//	/**
//	 * @return the product_id
//	 */
//	public long getProduct_id() {
//		return product_id;
//	}
//
//	/**
//	 * @param product_id the product_id to set
//	 */
//	public void setProduct_id(long product_id) {
//		this.product_id = product_id;
//	}
//
//	/**
//	 * @return the option_id
//	 */
//	public long getOption_id() {
//		return option_id;
//	}
//
//	/**
//	 * @param option_id the option_id to set
//	 */
//	public void setOption_id(long option_id) {
//		this.option_id = option_id;
//	}
//
//	/**
//	 * @return the optionName
//	 */
//	public String getOptionName() {
//		return optionName;
//	}
//
//	/**
//	 * @param optionName the optionName to set
//	 */
//	public void setOptionName(String optionName) {
//		this.optionName = optionName;
//	}
//
//	/**
//	 * @return the optionDescription
//	 */
//	public String getOptionDescription() {
//		return optionDescription;
//	}
//
//	/**
//	 * @param optionDescription the optionDescription to set
//	 */
//	public void setOptionDescription(String optionDescription) {
//		this.optionDescription = optionDescription;
//	}
//
//	/**
//	 * @return the optionValuesDto
//	 */
//	public List<OptionValuesDto> getOptionValuesDto() {
//		return optionValuesDto;
//	}
//
//	/**
//	 * @param optionValuesDto the optionValuesDto to set
//	 */
//	public void setOptionValuesDto(List<OptionValuesDto> optionValuesDto) {
//		this.optionValuesDto = optionValuesDto;
//	}
//
//	/**
//	 * 
//	 */
//	public OptionDto() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	
//	/**
//	 * @param product_id
//	 * @param option_id
//	 */
//	public OptionDto(long product_id, long option_id) {
//		this.product_id = product_id;
//		this.option_id = option_id;
//	}
//	
//	
//	/**
//	 * @param product_id
//	 * @param option_id
//	 * @param optionName
//	 * @param optionDescription
//	 * @param optionValuesDto
//	 */
//	public OptionDto(long product_id, long option_id, String optionName, String optionDescription) {
//		this.product_id = product_id;
//		this.option_id = option_id;
//		this.optionName = optionName;
//		this.optionDescription = optionDescription;
//		this.optionValuesDto = null;
//	}
//
//	public OptionDto(OptionModel option, List<OptionValuesDto> optionValuesDtoList) {
//		this.product_id = option.getProduct_id();
//		this.option_id = option.getOption_id();
//		this.optionName = option.getOptionName();
//		this.optionDescription = option.getOptionDescription();
//		this.optionValuesDto = optionValuesDtoList;
//	}
//
//	public OptionDto(Long product_id, Long option_id, String option_name,String option_desc,  List<OptionValuesDto> optionValuesDtoList) {
//		this.product_id = product_id;
//		this.option_id = option_id;
//		this.optionName = option_name;
//		this.optionDescription = option_desc;
//		this.optionValuesDto = optionValuesDtoList;
//	}
//	
//	
//}
//
//
