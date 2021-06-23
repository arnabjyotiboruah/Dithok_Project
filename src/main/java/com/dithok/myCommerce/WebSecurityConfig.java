package com.dithok.myCommerce;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	     http.csrf().disable();
	}
	
//	//Used to allow all the users to the specified access point
//    @Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests()
//		.antMatchers(HttpMethod.POST,"/api/*")
//		.permitAll()
//		.anyRequest().authenticated();
//		
//		http.csrf().disable().authorizeRequests()
//		.antMatchers(HttpMethod.GET,"/api/*")
//		.permitAll().and().cors().and().csrf().disable();
//		//.anyRequest().authenticated().;
//    }

    
    //To create custom mapping for the model
    @Bean
    public ModelMapper modelMapper()
    {
    	ModelMapper mapper = new ModelMapper();
    	return mapper;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
