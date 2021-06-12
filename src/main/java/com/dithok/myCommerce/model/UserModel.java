package com.dithok.myCommerce.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="Users" , uniqueConstraints = {
		@UniqueConstraint(name = "UK_Users_email", columnNames = { "email"}),
		@UniqueConstraint(name = "UK_Users_phoneNumber", columnNames = { "phoneNumber"})
		})
@EntityListeners(AuditingEntityListener.class)
public class UserModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@Valid 
    @Embedded
    private NameEmbeddable name;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank	
	@Length(min = 10, max = 10)
	private String phoneNumber;
	
	@NotBlank
	@NotNull
	@Length(min = 6)
	private String password;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@Valid
    @Embedded
    @AttributeOverrides(value = {
        @AttributeOverride(name = "addressLine1", column = @Column(name = "house_number")),
        @AttributeOverride(name = "addressLine2", column = @Column(name = "street"))
    })
    private AddressEmbeddable address;
	
    private Boolean active;
    
    @Column(columnDefinition = "varchar(30) default 'test_id'")
	private String razorpayId;
	
    private String type;
    
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(unique=false)
	@Length(min=6,max=6)
	private String OTP;
	
	@Column
	private long OTPCreatedTime;
    
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public NameEmbeddable getName() {
		return name;
	}



	public void setName(NameEmbeddable name) {
		this.name = name;
	}



	public Date getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public AddressEmbeddable getAddress() {
		return address;
	}



	public void setAddress(AddressEmbeddable address) {
		this.address = address;
	}



	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Date getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the razorpayId
	 */
	public String getRazorpayId() {
		return razorpayId;
	}

	public void setRazorpayId(String razorpayId) {
		this.razorpayId = razorpayId;
	}
	
	



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	/**
	 * @return the oTP
	 */
	public String getOTP() {
		return OTP;
	}

	/**
	 * @param oTP the oTP to set
	 */
	public void setOTP(String oTP) {
		this.OTP = oTP;
	}

	/**
	 * @param oTPCreatedTime the oTPCreatedTime to set
	 */
	public void setOTPCreatedTime(long OTPCreatedTime){
		this.OTPCreatedTime = OTPCreatedTime;
	}

	/**
	 * @return the oTPCreatedTime
	 */
	public long getOTPCreatedTime(){
		return OTPCreatedTime;
	}

	/**
	 * 
	 */
	



	public UserModel(Long id, @Valid NameEmbeddable name, @NotNull Date birthDate, @NotBlank @Email String email,
			@NotBlank @Length(min = 10, max = 10) String phoneNumber,
			@NotBlank @NotNull @Length(min = 6) String password, @Valid AddressEmbeddable address, Boolean active,
			String razorpayId, String type, Date createdAt, Date updatedAt, @Length(min = 6, max = 6) String oTP,
			long oTPCreatedTime) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.address = address;
		this.active = active;
		this.razorpayId = razorpayId;
		this.type = type;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		OTP = oTP;
		OTPCreatedTime = oTPCreatedTime;
	}



	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(mappedBy="user", cascade=CascadeType.PERSIST)
	@JsonIgnore
	private Set<GroupUserModel> groupUser = new HashSet<GroupUserModel>();

	public Set<GroupUserModel> getGroupUser() {
		return groupUser;
	}



	public void setGroupUser(Set<GroupUserModel> groupUser) {
		this.groupUser = groupUser;
	}
	

	/**
	 * @param id
	 * @param name
	 * @param birthDate
	 * @param email
	 * @param phoneNumber
	 * @param password
	 * @param address
	 * @param active
	 * @param razorpayId
	 * @param createdAt
	 * @param updatedAt
	 * @param oTP
	 * @param oTPCreatedTime
	 */
	
	
	
//	public UserModel(Long id, NameEmbeddable name, @NotNull Date birthDate, @NotBlank @Email String email,
//			@NotBlank @Length(min = 10, max = 10) String phoneNumber,
//			@NotBlank @NotNull @Length(min = 6) String password, AddressEmbeddable address, Boolean active,
//			String razorpayId, Date createdAt, Date updatedAt, @Length(min = 6, max = 6) String oTP,
//			long oTPCreatedTime) {
//		this.id = id;
//		this.name = name;
//		this.birthDate = birthDate;
//		this.email = email;
//		this.phoneNumber = phoneNumber;
//		this.password = password;
//		this.address = address;
//		this.active = active;
//		this.razorpayId = razorpayId;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//		OTP = oTP;
//		OTPCreatedTime = oTPCreatedTime;
//	}

}
