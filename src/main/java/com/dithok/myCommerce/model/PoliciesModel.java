package com.dithok.myCommerce.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class PoliciesModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long policy_id;
	
	private String name;
	
	private String execSeq;
	

	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_GroupsModel"),name="groupModel_id", referencedColumnName = "group_id")
	private GroupsModel group;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_PolicyCreator"),name="creator_id", referencedColumnName = "id")
	private UserModel creator;
	
	private String description;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	private boolean status;

	public long getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(long policy_id) {
		this.policy_id = policy_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getExecSeq() {
		return execSeq;
	}

	public void setExecSeq(String execSeq) {
		this.execSeq = execSeq;
	}
	
	

	public GroupsModel getGroup() {
		return group;
	}

	public void setGroup(GroupsModel group) {
		this.group = group;
	}
	
	
	
	
	

	public UserModel getCreator() {
		return creator;
	}

	public void setCreator(UserModel creator) {
		this.creator = creator;
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

	public PoliciesModel(long policy_id, String name, String execSeq, GroupsModel group, UserModel creator,
			String description, Date createdDate, Date modifiedDate, boolean status) {
		super();
		this.policy_id = policy_id;
		this.name = name;
		this.execSeq = execSeq;
		this.group = group;
		this.creator = creator;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
	}

	public PoliciesModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(mappedBy="policy", cascade=CascadeType.PERSIST)
	@JsonIgnore
	private Set<ResourcePolicyModel> resourcePolicy = new HashSet<ResourcePolicyModel>();

	public Set<ResourcePolicyModel> getResourcePolicy() {
		return resourcePolicy;
	}

	public void setResourcePolicy(Set<ResourcePolicyModel> resourcePolicy) {
		this.resourcePolicy = resourcePolicy;
	}
	
	
	
}
