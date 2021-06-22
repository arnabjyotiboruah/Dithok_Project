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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name="access_groups" , uniqueConstraints = {
		@UniqueConstraint(name = "Uk_GroupName", columnNames = { "name"})
		})
@EntityListeners(AuditingEntityListener.class)
public class GroupsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT 'Access Group'")
	private Long group_id;
	

	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_GroupCreator"),name="creator_id", referencedColumnName = "id")
	private UserModel userCreator;
	
	private String name;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_GroupOwner"),name="owner_id", referencedColumnName = "id")
	private UserModel userOwner;

	private String description;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	private boolean status;

	
	public UserModel getUserCreator() {
		return userCreator;
	}

	public void setUserCreator(UserModel userCreator) {
		this.userCreator = userCreator;
	}

	public UserModel getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(UserModel userOwner) {
		this.userOwner = userOwner;
	}

	


	public Long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	

	public GroupsModel(Long group_id, UserModel userCreator, String name, UserModel userOwner, String description,
			Date createdDate, Date modifiedDate, boolean status) {
		super();
		this.group_id = group_id;
		this.userCreator = userCreator;
		this.name = name;
		this.userOwner = userOwner;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
	}

	public GroupsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(mappedBy="group", cascade=CascadeType.PERSIST)
	@JsonIgnore
	private Set<GroupUserModel> groupUser = new HashSet<GroupUserModel>();


	public Set<GroupUserModel> getGroupUser() {
		return groupUser;
	}

	public void setGroupUser(Set<GroupUserModel> groupUser) {
		this.groupUser = groupUser;
	}
	
	@OneToMany(mappedBy="groupJoin", cascade=CascadeType.PERSIST)
	@JsonIgnore
	private Set<GroupResourceModel> groupResource = new HashSet<GroupResourceModel>();



	public Set<GroupResourceModel> getGroupResource() {
		return groupResource;
	}



	public void setGroupResource(Set<GroupResourceModel> groupResource) {
		this.groupResource = groupResource;
	}
	
	@OneToMany(mappedBy="group", cascade=CascadeType.PERSIST)
	@JsonIgnore
	private Set<PoliciesModel> policy = new HashSet<PoliciesModel>();


	public Set<PoliciesModel> getPolicy() {
		return policy;
	}

	public void setPolicy(Set<PoliciesModel> policy) {
		this.policy = policy;
	}
	

	
	
	
}