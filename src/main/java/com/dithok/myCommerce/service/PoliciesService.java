package com.dithok.myCommerce.service;

import java.util.List;

import com.dithok.myCommerce.model.PoliciesModel;

public interface PoliciesService{
	public PoliciesModel addPolicy(PoliciesModel policy);
	public PoliciesModel remove(long id);
	public PoliciesModel updatePolicy(PoliciesModel policy);
	public List<PoliciesModel> listAll();
	public PoliciesModel getById(long id);
	public List<PoliciesModel> getByGroupId(long id);

}
