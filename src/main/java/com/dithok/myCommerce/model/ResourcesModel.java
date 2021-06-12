package com.dithok.myCommerce.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="resources" , uniqueConstraints = {
		@UniqueConstraint(name = "company_name", columnNames = { "companyName"})
		})
@EntityListeners(AuditingEntityListener.class)
public class ResourcesModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long res_id;


	private String type;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_ResourceCreator"),name="creator_id", referencedColumnName = "id")
	private UserModel creator;
	
	private String companyName;
	
//	Cannot use only "range" as column name as range is a reserved keyword
	private long range_Resource;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	private boolean status;



	public Long getRes_id() {
		return res_id;
	}



	public void setRes_id(Long res_id) {
		this.res_id = res_id;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public long getRange_Resource() {
		return range_Resource;
	}



	public void setRange_Resource(long range_Resource) {
		this.range_Resource = range_Resource;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public UserModel getCreator() {
		return creator;
	}



	public void setCreator(UserModel creator) {
		this.creator = creator;
	}



	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public Date getModifiedDate() {
		return modifiedDate;
	}



	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}

	

public ResourcesModel(Long res_id, String type, UserModel creator, String companyName, long range_Resource,
			Date createdDate, Date modifiedDate, boolean status, Set<GroupResourceModel> groupResource,
			Set<ResourcePolicyModel> resourcePolicy) {
		super();
		this.res_id = res_id;
		this.type = type;
		this.creator = creator;
		this.companyName = companyName;
		this.range_Resource = range_Resource;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.groupResource = groupResource;
		this.resourcePolicy = resourcePolicy;
	}



//	public ResourcesModel(Long res_id, String type, UserModel creator, String companyName, Date createdDate,
//			Date modifiedDate, boolean status) {
//		super();
//		this.res_id = res_id;
//		this.type = type;
//		this.creator = creator;
//		this.companyName = companyName;
//		this.createdDate = createdDate;
//		this.modifiedDate = modifiedDate;
//		this.status = status;
//	}
//
//
//
//	public ResourcesModel() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
	public ResourcesModel() {
	super();
	// TODO Auto-generated constructor stub
}







	@OneToMany(mappedBy="resourceJoin", cascade=CascadeType.PERSIST)
	@JsonIgnore
	private Set<GroupResourceModel> groupResource = new HashSet<GroupResourceModel>();



	public Set<GroupResourceModel> getGroupResource() {
		return groupResource;
	}



	public void setGroupResource(Set<GroupResourceModel> groupResource) {
		this.groupResource = groupResource;
	}
	
	@OneToMany(mappedBy="resource", cascade=CascadeType.PERSIST)
	@JsonIgnore
	private Set<ResourcePolicyModel> resourcePolicy = new HashSet<ResourcePolicyModel>();



	public Set<ResourcePolicyModel> getResourcePolicy() {
		return resourcePolicy;
	}



	public void setResourcePolicy(Set<ResourcePolicyModel> resourcePolicy) {
		this.resourcePolicy = resourcePolicy;
	}
	


	
	

}
