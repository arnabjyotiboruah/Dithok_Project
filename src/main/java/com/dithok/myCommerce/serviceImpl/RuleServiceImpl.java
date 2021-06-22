package com.dithok.myCommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dithok.myCommerce.Repo.RulesRepository;
import com.dithok.myCommerce.model.RulesModel;
import com.dithok.myCommerce.service.RulesService;

@Service
public class RuleServiceImpl implements RulesService{

	@Autowired
	private RulesRepository repo;
	
	
	@Override
	public RulesModel addRule(RulesModel rule) {
		return repo.save(rule);
	}

	@Override
	public RulesModel remove(long id) {
		RulesModel rule = repo.findById(id).get();
		if(rule.isStatus() == true) {
			rule.setStatus(false);
			repo.save(rule);
			return rule;
		}
		return rule;
	}

	@Override
	public RulesModel updateRule(RulesModel rule) {
		RulesModel updateRule = repo.findById(rule.getRule_id()).get();
		updateRule.setExecSeq(rule.getExecSeq());
		updateRule.setName(rule.getName());
		updateRule.setPath(rule.getPath());
		updateRule.setTriggerName(rule.getTriggerName());
		updateRule.setType(rule.getType());
		return repo.save(updateRule);
	}

	@Override
	public List<RulesModel> listAll() {
		return repo.findAll();
	}

	@Override
	public RulesModel getById(long id) {
		return repo.findById(id).get();
	}

	@Override
	public List<RulesModel> findByPolicyId(long id) {
		return repo.findByPolicyId(id);
	}

}
