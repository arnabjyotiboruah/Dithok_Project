package com.dithok.myCommerce;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
//		http.authorizeRequests().anyRequest().authenticated().and()
//		.formLogin().loginPage("/login").permitAll();
		http.authorizeRequests().antMatchers("/","static/stylesheet/css","static/js").permitAll().antMatchers("/**").hasRole("USER").and().formLogin();
	   
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	     auth.userDetailsService(userDetailsService);
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
