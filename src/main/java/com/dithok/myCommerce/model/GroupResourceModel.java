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
@IdClass(GroupResourceCompositeKey.class)
@Table(name="group_resource")
public class GroupResourceModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Id
	@Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT 'Access id to identify access group'")
	public long group_id;
	
	@Id
	@Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT 'Resource id to identify resource'")
	public long resource_id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns (foreignKey = @ForeignKey(name = "FK_Group"),value ={ 
	@JoinColumn(name = "group_id", referencedColumnName = "group_id", insertable=false, updatable=false), 
	})
	@JsonIgnore
	private GroupsModel groupJoin;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns (foreignKey = @ForeignKey(name = "FK_Resource"),value ={ 
	@JoinColumn(name = "resource_id", referencedColumnName = "res_id", insertable=false, updatable=false), 
	})
	@JsonIgnore
	private ResourcesModel resourceJoin;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public long getGroup_id() {
		return group_id;
	}


	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}


	public long getResource_id() {
		return resource_id;
	}


	public void setResource_id(long resource_id) {
		this.resource_id = resource_id;
	}


	public GroupsModel getGroupJoin() {
		return groupJoin;
	}


	public void setGroupJoin(GroupsModel groupJoin) {
		this.groupJoin = groupJoin;
	}


	public ResourcesModel getResourceJoin() {
		return resourceJoin;
	}


	public void setResourceJoin(ResourcesModel resourceJoin) {
		this.resourceJoin = resourceJoin;
	}


	public GroupResourceModel(int id, long group_id, long resource_id, GroupsModel groupJoin,
			ResourcesModel resourceJoin) {
		super();
		this.id = id;
		this.group_id = group_id;
		this.resource_id = resource_id;
		this.groupJoin = groupJoin;
		this.resourceJoin = resourceJoin;
	}


	public GroupResourceModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	

}
