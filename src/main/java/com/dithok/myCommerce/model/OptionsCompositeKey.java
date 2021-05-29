//package com.dithok.myCommerce.model;
//
//import java.io.Serializable;
//
//public class OptionsCompositeKey implements Serializable{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 7407464989239595721L;
//	private long product_id;
//	private long option_id;
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (int) (option_id ^ (option_id >>> 32));
//		result = prime * result + (int) (product_id ^ (product_id >>> 32));
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
//		OptionsCompositeKey other = (OptionsCompositeKey) obj;
//		if (option_id != other.option_id)
//			return false;
//		if (product_id != other.product_id)
//			return false;
//		return true;
//	}
//	
//	/**
//	 * 
//	 */
//	public OptionsCompositeKey() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	/**
//	 * @param product_id
//	 * @param option_id
//	 */
//	public OptionsCompositeKey(long product_id, long option_id) {
//		this.product_id = product_id;
//		this.option_id = option_id;
//	}
//
//}
