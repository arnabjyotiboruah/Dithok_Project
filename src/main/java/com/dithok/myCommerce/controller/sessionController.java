package com.dithok.myCommerce.controller;

import com.dithok.myCommerce.dto.sessionDto;
import com.dithok.myCommerce.service.sessionMgmtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
@RestController
@RequestMapping("/")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class sessionController 
{

    @Autowired
    sessionMgmtService sessionmgmtservice;

    /**
     * 
     * 
     * Session Controller
     * 
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/checkSession",consumes="application/json")
    boolean checkSession(@RequestBody sessionDto status){
       try{
        int Cookiestatus = sessionmgmtservice.checksession(status);
        if(Cookiestatus==1){ 
            return true;//Session Created
        }
        else
        {   
            
            if(Cookiestatus==2)
            {
                System.out.println("new login page/resubmit form for expired session");
                return false;//new login page/resubmit form for expired session
            }
            else if(Cookiestatus==3){
                System.out.println("valid session");
                return true;//valid session
            }
            else
            { System.out.println("Invalid Request");
            return false;//Invalid Request
            }

        }
    }catch(Exception e){
        System.out.println(e);
        return false;
    }
        
    }


    /**
     * 
     * Session Time-out setter
     */
    @RequestMapping(
        method=RequestMethod.POST,
        value="/api/setSessionTimeout",
        consumes = "application/json"
    )
    public Object setSessionTimeout(@RequestBody sessionDto resParam){

        int timeParam=resParam.getSessionInactiveInterval();
        int sesTime=sessionmgmtservice.setSessionTimeOut(timeParam);

        JSONObject obj=new JSONObject();
        if(sesTime==-1)
        {
            
            obj.put("message","session unavailabe");
            obj.put("sessionInactiveInterval",sesTime);
            return obj;
        }
        else{
            obj.put("message","Session Inactive Interval set");
            obj.put("sessionInactiveInterval",sesTime);
            return obj;
        }
        
    }





    @RequestMapping(
        method=RequestMethod.POST,
        value="/api/logoutSession"
    )
    public Object logoutSession(){

        // int timeParam=resParam.getSessionInactiveInterval();
        int val =sessionmgmtservice.logoutSession();
        JSONObject obj=new JSONObject();
        if(val==-1)
        {
            
            obj.put("message","session unavailabe");
            obj.put("sessionInactiveInterval",val);
            return obj;
        }
        else{
            obj.put("message","Session Inactive Interval set");
            obj.put("sessionInactiveInterval",val);
            return obj;
        }
        
    }

}