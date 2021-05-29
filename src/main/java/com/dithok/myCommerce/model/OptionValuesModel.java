//package com.dithok.myCommerce.model;
//
//import java.math.BigInteger;
//import java.util.Date;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EntityListeners;
//import javax.persistence.FetchType;
//import javax.persistence.ForeignKey;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.IdClass;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumns;
//import javax.persistence.ManyToOne;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotBlank;
//import org.hibernate.annotations.NotFound;
//import org.hibernate.annotations.NotFoundAction;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//@Entity
//@Table(name = "option_value", uniqueConstraints = @UniqueConstraint(name = "UK_option_value", columnNames = { "product_id", "option_id",
//		"optionValueName" }))
//@EntityListeners(AuditingEntityListener.class)
//@IdClass(OptionValuesCompositeKey.class)
//@org.hibernate.annotations.Table(comment = "Option value table", appliesTo = "option_value")
//
//public class OptionValuesModel {
//	@Id
//	@Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT 'Product id to identify product'")
//	private long product_id;
//	
//	@Id
//	@Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT 'option id to identify option size, color etc'")
//	private long option_id;
//
//	@Id
//	@SequenceGenerator(name = "optValueSeqGen", sequenceName = "optValueSeq", initialValue = 1, allocationSize = 1)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "optValueSeqGen")
//	@Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT 'Value ID for option e.g  option: Size --> value:(S/M/XL), should be inserted during product registartion'"
//	, updatable = false, nullable = false)
//	private long value_id;
//	
//	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
//	@NotFound(action = NotFoundAction.IGNORE)
//	@JoinColumns (foreignKey = @ForeignKey(name = "FK_OptionValue_Option"),value ={ 
//	@JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable=false, updatable=false),
//	@JoinColumn(name = "option_id", referencedColumnName = "option_id", insertable=false, updatable=false) 
//	})
//	private OptionModel option;
//
//
//
//	/**
//	 * @return the option
//	 */
//	public OptionModel getOption() {
//		return option;
//	}
//
//	/**
//	 * @param option the option to set
//	 */
//	public void setOption(OptionModel option) {
//		this.option = option;
//	}
//
//	@NotBlank
//	@Column(columnDefinition = "VARCHAR (128) NOT NULL COMMENT 'Name of the option value'")
//	private String optionValueName;
//
//	@CreatedDate
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date createdAt;
//
//	@LastModifiedDate
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date updatedAt;
//
//	public long getProduct_id() {
//		return product_id;
//	}
//
//	public void setProduct_id(long product_id) {
//		this.product_id = product_id;
//	}
//
//	public long getOption_id() {
//		return option_id;
//	}
//
//	public void setOption_id(long option_id) {
//		this.option_id = option_id;
//	}
//
//	public long getValue_id() {
//		return value_id;
//	}
//
//
//
//	public void setValue_id(long value_id) {
//		this.value_id = value_id;
//	}
//
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public String getOptionValueName() {
//		return optionValueName;
//	}
//
//	public void setOptionValueName(String optionValueName) {
//		this.optionValueName = optionValueName;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public Date getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(Date updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	/**
//	 * 
//	 */
//	public OptionValuesModel() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public OptionValuesModel(Object[] optionsRow) {
//		// this.optValId = (optionsRow[0] !=
//		// null)?((BigInteger)optionsRow[0]).longValue():0;
//		this.optionValueName = optionsRow[2].toString();
//	//	this.option_id = ((BigInteger) optionsRow[3]).longValue();
//	//	this.product_id = ((BigInteger) optionsRow[4]).longValue();
//		this.value_id = ((BigInteger) optionsRow[6]).longValue();
//	}
//
//	/**
//	 * @param product_id
//	 * @param option_id
//	 * @param value_id
//	 * @param option
//	 * @param optionValueName
//	 * @param createdAt
//	 * @param updatedAt
//	 */
//	public OptionValuesModel(long product_id, long option_id, long value_id, OptionModel option,
//			@NotBlank String optionValueName, Date createdAt, Date updatedAt) {
//		this.product_id = product_id;
//		this.option_id = option_id;
//		this.value_id = value_id;
//		this.option = option;
//		this.optionValueName = optionValueName;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//	}
//
//}