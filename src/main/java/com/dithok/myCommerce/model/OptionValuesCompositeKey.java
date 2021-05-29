//package com.dithok.myCommerce.model;
//
//import java.io.Serializable;
//
//public class OptionValuesCompositeKey implements Serializable{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 420535416446475722L;
//	private long product_id;
//	private long option_id;
//	private long value_id;
//	
//	
//	/**
//	 * @return the product_id
//	 */
//	public long getProduct_id() {
//		return product_id;
//	}
//	/**
//	 * @return the option_id
//	 */
//	public long getOption_id() {
//		return option_id;
//	}
//	/**
//	 * @return the value_id
//	 */
//	public long getValue_id() {
//		return value_id;
//	}
//	/**
//	 * @param product_id the product_id to set
//	 */
//	public void setProduct_id(long product_id) {
//		this.product_id = product_id;
//	}
//	/**
//	 * @param option_id the option_id to set
//	 */
//	public void setOption_id(long option_id) {
//		this.option_id = option_id;
//	}
//	/**
//	 * @param value_id the value_id to set
//	 */
//	public void setValue_id(long value_id) {
//		this.value_id = value_id;
//	}
//	/**
//	 * @param product_id
//	 * @param option_id
//	 * @param value_id
//	 */
//	public OptionValuesCompositeKey(long product_id, long option_id, long value_id) {
//		this.product_id = product_id;
//		this.option_id = option_id;
//		this.value_id = value_id;
//	}
//	
//	/**
//	 * 
//	 */
//	public OptionValuesCompositeKey() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	
//	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (int) (option_id ^ (option_id >>> 32));
//		result = prime * result + (int) (product_id ^ (product_id >>> 32));
//		result = prime * result + (int) (value_id ^ (value_id >>> 32));
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		OptionValuesCompositeKey other = (OptionValuesCompositeKey) obj;
//		if (option_id != other.option_id)
//			return false;
//		if (product_id != other.product_id)
//			return false;
//		if (value_id != other.value_id)
//			return false;
//		return true;
//	}
//	
//}
