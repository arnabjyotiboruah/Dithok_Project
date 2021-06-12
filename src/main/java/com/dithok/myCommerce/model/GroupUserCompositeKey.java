package com.dithok.myCommerce.model;

import java.io.Serializable;

public class GroupUserCompositeKey implements Serializable{
	private static final long serialVersionUID = 420535416446475722L;
	private long group_id;
	private long user_id;
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
	public GroupUserCompositeKey(long group_id, long user_id) {
		super();
		this.group_id = group_id;
		this.user_id = user_id;
	}
	public GroupUserCompositeKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
