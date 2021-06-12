package com.dithok.myCommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dithok.myCommerce.Repo.PoliciesRepository;
import com.dithok.myCommerce.model.PoliciesModel;
import com.dithok.myCommerce.service.PoliciesService;

@Service
public class PolicyServiceImpl implements PoliciesService {
	@Autowired
	private PoliciesRepository repo;

	@Override
	public PoliciesModel addPolicy(PoliciesModel policy) {
		return repo.save(policy);
	}

	@Override
	public PoliciesModel remove(long id) {
		PoliciesModel policy = repo.findById(id).get();
		if(policy.isStatus() == true) {
			policy.setStatus(false);
			repo.save(policy);
			return policy;
		}
		return policy;
	}

	@Override
	public PoliciesModel updatePolicy(PoliciesModel policy) {
		PoliciesModel updatePolicy = repo.findById(policy.getPolicy_id()).get();
		updatePolicy.setDescription(policy.getDescription());
		updatePolicy.setExecSeq(policy.getExecSeq());
		updatePolicy.setName(policy.getName());
		return repo.save(updatePolicy);
	}

	@Override
	public List<PoliciesModel> listAll() {
		return repo.findAll();
	}

	@Override
	public PoliciesModel getById(long id) {
		return repo.findById(id).get();
	}

	

}
