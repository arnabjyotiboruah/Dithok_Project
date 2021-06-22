package com.dithok.myCommerce.service;

import java.util.List;

import com.dithok.myCommerce.model.RulesModel;

public interface RulesService {
	public RulesModel addRule(RulesModel rule);
	public RulesModel remove(long id);
	public RulesModel updateRule(RulesModel group);
	public List<RulesModel> listAll();
	public RulesModel getById(long id);

}
