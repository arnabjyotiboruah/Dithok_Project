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
@IdClass(GroupUserCompositeKey.class)
@Table(name="group_user")

public class GroupUserModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Id
	@Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT 'Access id to identify access group'")
	public long group_id;
	
	@Id
	@Column(columnDefinition = "BIGINT(20) NOT NULL COMMENT 'User id to identify users'")
	public long user_id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns (foreignKey = @ForeignKey(name = "FK_AccessGroup"),value ={ 
	@JoinColumn(name = "group_id", referencedColumnName = "group_id", insertable=false, updatable=false), 
	})
	@JsonIgnore
	private GroupsModel group;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumns (foreignKey = @ForeignKey(name = "FK_User"),value ={ 
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable=false, updatable=false), 
	})
	@JsonIgnore
	private UserModel user;


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


	public long getUser_id() {
		return user_id;
	}


	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}


	public GroupsModel getGroup() {
		return group;
	}


	public void setGroup(GroupsModel group) {
		this.group = group;
	}


	public UserModel getUser() {
		return user;
	}


	public void setUser(UserModel user) {
		this.user = user;
	}


	public GroupUserModel(int id, long group_id, long user_id, GroupsModel group, UserModel user) {
		super();
		this.id = id;
		this.group_id = group_id;
		this.user_id = user_id;
		this.group = group;
		this.user = user;
	}


	public GroupUserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
