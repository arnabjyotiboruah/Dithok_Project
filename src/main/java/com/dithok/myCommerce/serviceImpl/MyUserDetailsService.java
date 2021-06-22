package com.dithok.myCommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dithok.myCommerce.Repo.UserRepository;
import com.dithok.myCommerce.model.MyUserDetails;
import com.dithok.myCommerce.model.UserModel;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserModel user = repo.findByEmail(email);
		return new MyUserDetails(user);
	}

}
