package com.dithok.myCommerce.model;

import java.io.Serializable;

public class GroupResourceCompositeKey implements Serializable {
	private static final long serialVersionUID = 420535416446475722L;
	private long group_id;
	private long resource_id;
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
	public GroupResourceCompositeKey(long group_id, long resource_id) {
		super();
		this.group_id = group_id;
		this.resource_id = resource_id;
	}
	public GroupResourceCompositeKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
