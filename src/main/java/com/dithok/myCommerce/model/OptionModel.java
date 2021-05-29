//package com.dithok.myCommerce.model;
//
//import java.math.BigInteger;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EntityListeners;
//import javax.persistence.FetchType;
//import javax.persistence.ForeignKey;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.IdClass;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotBlank;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//
//
//@Entity 
//@Table(name="options", 
//		uniqueConstraints=
//	    @UniqueConstraint(name = "UK_option", columnNames = {"product_id", "optionName"})
//      )
//@EntityListeners(AuditingEntityListener.class)
//@IdClass(OptionsCompositeKey.class)
//@org.hibernate.annotations.Table( comment = "Product Option information i.e man or woman or babyboy or babygirl etc", appliesTo = "options" )
//public class OptionModel {
//	
//	@Id
//	@Column(insertable=false, updatable=false, columnDefinition="BIGINT(20) NOT NULL COMMENT 'Product id to identify product'")
//	private long product_id;
//	
//	@Id
//	@SequenceGenerator(name = "optSeqGen", sequenceName = "optSeq", initialValue = 1, allocationSize = 1)
//	@GeneratedValue(generator = "optSeqGen")
//	@Column(columnDefinition="BIGINT(20) NOT NULL COMMENT 'option id to identify option size, color etc'")
//	private long option_id;
//	
//	@NotBlank
//	@Column (columnDefinition="VARCHAR (128) NOT NULL COMMENT 'Try to give unique name of the product'")
//	private String optionName;
//    
//	@Column(columnDefinition="TEXT NOT NULL COMMENT 'Decription of the product'")
//	private String optionDescription;
//
//	@ManyToOne(fetch = FetchType.LAZY,  cascade = {CascadeType.PERSIST})
//	@JoinColumn(foreignKey = @ForeignKey(name = "FK_option_product") , name = "product_id", referencedColumnName = "id", insertable=false, updatable=false)
//	private ProductModel product;
//
//	public long getOption_id() {
//		return option_id;
//	}
//
//	public void setOption_id(long option_id) {
//		this.option_id = option_id;
//	}
//
//	public String getOptionName() {
//		return optionName;
//	}
//
//	public void setOptionName(String optionName) {
//		this.optionName = optionName;
//	}
//
//	public String getOptionDescription() {
//		return optionDescription;
//	}
//
//	public void setOptionDescription(String optionDescription) {
//		this.optionDescription = optionDescription;
//	}
//
//	public long getProduct_id() {
//		return product_id;
//	}
//
//	public void setProduct_id(long product_id) {
//		this.product_id = product_id;
//	}
//
//	/**
//	 * @param product_id
//	 * @param option_id
//	 * @param optionName
//	 * @param optionDescription
//	 * @param product
//	 */
//	public OptionModel(long product_id, long option_id, @NotBlank String optionName, String optionDescription,
//			ProductModel product) {
//		this.product_id = product_id;
//		this.option_id = option_id;
//		this.optionName = optionName;
//		this.optionDescription = optionDescription;
//		this.product = product;
//	}
//
//	/**
//	 * @return the product
//	 */
//	public ProductModel getProduct() {
//		return product;
//	}
//
//	/**
//	 * @param product the product to set
//	 */
//	public void setProduct(ProductModel product) {
//		this.product = product;
//	}
//
//	/**
//	 * 
//	 */
//	public OptionModel() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public OptionModel(Object[] optionsRow) {
//		//this.optid = (optionsRow[0] != null)?((BigInteger)optionsRow[0]).longValue():0;
//		this.optionDescription = optionsRow[1].toString();
//		this.optionName = optionsRow[2].toString();
//		this.option_id = ((BigInteger)optionsRow[3]).longValue();
//		this.product_id = ((BigInteger)optionsRow[4]).longValue();
//	}
//	
//	}
//
//
