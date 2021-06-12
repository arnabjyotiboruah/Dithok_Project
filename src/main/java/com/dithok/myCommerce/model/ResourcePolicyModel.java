package com.dithok.myCommerce.model;

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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(ResourcePolicyCompositeKey.class)
@Table(name="resource_policy")
public class ResourcePolicyModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Id
	@Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT 'Resource id to identify resources'")
	public long resource_id;
	
	@Id
	@Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT 'Policy id to identify policies'")
	public long policy_id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns (foreignKey = @ForeignKey(name = "FK_resourceId"),value ={ 
	@JoinColumn(name = "resource_id", referencedColumnName = "res_id", insertable=false, updatable=false) 
	})
	@JsonIgnore
	private ResourcesModel resource;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns (foreignKey = @ForeignKey(name = "FK_Policy"),value ={ 
	@JoinColumn(name = "policy_id", referencedColumnName = "policy_id", insertable=false, updatable=false) 
	})
	@JsonIgnore
	private PoliciesModel policy;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public long getResource_id() {
		return resource_id;
	}


	public void setResource_id(long resource_id) {
		this.resource_id = resource_id;
	}


	public long getPolicy_id() {
		return policy_id;
	}


	public void setPolicy_id(long policy_id) {
		this.policy_id = policy_id;
	}


	public ResourcesModel getResource() {
		return resource;
	}


	public void setResource(ResourcesModel resource) {
		this.resource = resource;
	}


	public PoliciesModel getPolicy() {
		return policy;
	}


	public void setPolicy(PoliciesModel policy) {
		this.policy = policy;
	}


	public ResourcePolicyModel(int id, long resource_id, long policy_id, ResourcesModel resource,
			PoliciesModel policy) {
		super();
		this.id = id;
		this.resource_id = resource_id;
		this.policy_id = policy_id;
		this.resource = resource;
		this.policy = policy;
	}


	public ResourcePolicyModel() {
		super();
		// TODO Auto-generated constructor stub
	}


}