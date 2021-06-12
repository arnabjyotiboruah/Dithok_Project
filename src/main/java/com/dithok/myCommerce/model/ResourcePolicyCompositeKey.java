package com.dithok.myCommerce.model;

import java.io.Serializable;

public class ResourcePolicyCompositeKey implements Serializable {
	private static final long serialVersionUID = 420535416446475722L;
	private long resource_id;
	private long policy_id;
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
	public ResourcePolicyCompositeKey(long resource_id, long policy_id) {
		super();
		this.resource_id = resource_id;
		this.policy_id = policy_id;
	}
	public ResourcePolicyCompositeKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
