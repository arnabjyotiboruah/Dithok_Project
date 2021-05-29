/**
 * 
 *  Session attributes values
 *  "login":"1",
 *  "browsing":"2",
 *  "order_Page":"3",
 *  "productCatelog":"4"
 * 
 */
package com.dithok.myCommerce.serviceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dithok.myCommerce.dto.sessionDto;
import com.dithok.myCommerce.service.sessionMgmtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class sessionManagementImpl implements sessionMgmtService{

	@Autowired
	HttpServletResponse response;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;


	/**
	 * creation and checking session
	 */
    @Override
	public int checksession(sessionDto status)
	{
	
		if(request.getSession(false)!=null){
			System.out.println("Created:"+ session.getCreationTime());
			System.out.println("Last Accessed: " + session.getLastAccessedTime());
			System.out.println("Created - Last Accessed :" + (session.getCreationTime()-session.getLastAccessedTime()));

				return 3;			//valid session		
			
		}
		else if(status.getsessionstatus().equals("login")){
			request.getSession().setAttribute("login",1);
			session.setAttribute("product", 4);
			System.out.println("Created:"+ session.getCreationTime());
			System.out.println("Last Accessed: " + session.getLastAccessedTime());
			System.out.println("Created - Last Accessed :" + (session.getCreationTime()-session.getLastAccessedTime()));
			return 1;			//new browser log in with new session			
		}		
		else if(status.getsessionstatus().equals("browsing")){
			return 2;			//new login page/resubmit form for expired session
		}
		 return 4;			//Null request
	}

	/**
	 * Session implementation for product Catelog
	 */
	@Override
	public int productCatelogSession(String operation)
	{

		if(session.getLastAccessedTime()-session.getCreationTime()==0)
		{
			return 0;
		}
		return 0;
	}

	@Override
	public void renewSession(){

		int inactive= session.getMaxInactiveInterval();
		session.getCreationTime();
		Long last=session.getLastAccessedTime();
		Long present=System.currentTimeMillis();
		System.out.print("Present-last= ");
		System.out.println(present-last);
		System.out.print("last-inactive =");
		System.out.println(last-inactive);


	}

	@Override
	public int setSessionTimeOut(int Sessiontime){

		if(request.getSession(false)!=null)
		{
			int timetobeSet=Sessiontime*60;
			session.setMaxInactiveInterval(timetobeSet);
			int thesessiontime=(session.getMaxInactiveInterval())/60;

			return thesessiontime;

		}
		else
		{
			return -1;
		}
		
	}

	@Override
	public int logoutSession(){
		// System.out.println(request.getSession(false).toString());
		if(request.getSession(false)!=null)
		{
			session.invalidate();
			return 1;
		}
		else
		{
			return -1;
		}
		
	}

}