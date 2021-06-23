package com.dithok.myCommerce.controller;

import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dithok.myCommerce.dto.EditUserDto;
import com.dithok.myCommerce.dto.ForgotPasswordDto;
import com.dithok.myCommerce.dto.GenerateOtp;
import com.dithok.myCommerce.dto.LoginDto;
import com.dithok.myCommerce.dto.RegisterDto;
import com.dithok.myCommerce.dto.ResetPasswordDto;
import com.dithok.myCommerce.dto.UserAddressDto;
import com.dithok.myCommerce.exception.MyCommerceException;
import com.dithok.myCommerce.model.UserModel;
import com.dithok.myCommerce.service.UserServiceInterface;

import com.dithok.myCommerce.service.sessionMgmtService;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {


    
    @Autowired
    UserServiceInterface userServiceInterface;



    @Autowired
    HttpServletRequest request;

    @Autowired 
    HttpSession session;

    @Autowired
    HttpServletResponse response;

    @Autowired
    sessionMgmtService sessionmgmtservice;
    
    


    

    //JSON input
	@RequestMapping(
			value = "/api/registerUser",
			method = RequestMethod.POST,
			produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
			headers = "Accept=application/json",
			consumes = "application/json"
		)
    ResponseEntity<MyCommerceException> registerUserJson(@RequestBody RegisterDto newUserRegister) {
        
        try{
			
            userServiceInterface.createUser(newUserRegister);
            
            return new ResponseEntity<MyCommerceException>(new MyCommerceException(1, "New User Details added to the database"), HttpStatus.OK);
        
        } catch (Exception E) {
           	String causeString = E.getMessage();
    			Exception exe = new Exception();

    			exe.initCause(E);
    			if (exe.getCause() != null && exe.getCause().getCause() != null)
    				causeString = causeString + " : " + exe.getCause().getCause().getLocalizedMessage();

    			return new ResponseEntity<MyCommerceException>(new MyCommerceException(0,"Error: "+ causeString), HttpStatus.BAD_REQUEST);
       }
    }
 
    /* 
    **          User     
    **     Login controller
    */
 
	@RequestMapping(
			value = "/api/login",
			method = RequestMethod.POST,
			produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
			headers = "Accept=application/json",
			consumes = "application/json"
		)
    ResponseEntity<MyCommerceException> saveUserJson(@RequestBody LoginDto loginUser) {
        
        try{

            if(userServiceInterface.isUserValid(loginUser.getEmail(), loginUser.getPassword()))
            {
            	UserModel user = userServiceInterface.findUserByEmail(loginUser.getEmail());
            	String userId = "";
            	if(user!=null)
            	{
            		userId = user.getId().toString();
            	}
                return new ResponseEntity<>(new MyCommerceException(1,userId), HttpStatus.OK);
          
            }
            return new ResponseEntity<MyCommerceException>(new MyCommerceException(2, "Password dont match"), HttpStatus.OK);
        
        }catch(Exception E){
           	    String causeString = E.getMessage();
    			Exception exe = new Exception();

    			exe.initCause(E);
    			if (exe.getCause() != null && exe.getCause().getCause() != null)
    				causeString = causeString + " : " + exe.getCause().getCause().getLocalizedMessage();
    			else
    				causeString = causeString + " : " + "Error: Invalid Email id";
    			return new ResponseEntity<MyCommerceException>(new MyCommerceException(0,"Error: "+ causeString), HttpStatus.BAD_REQUEST);
           }
    }
    /**
     **        Activate/deactivate user
     ** 
     **/

    @RequestMapping(
			value = "/api/toggleuseractive",
			method = RequestMethod.POST,
			produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
			headers = "Accept=application/json",
			consumes = "application/json"
		)
    public ResponseEntity<MyCommerceException> toggleUserActive(@RequestBody ObjectNode objectNode){    
		try {
			String email = objectNode.get("email").asText().toString(); 
            int output = userServiceInterface.toggleUserActiveStatus(email);
			return new ResponseEntity<MyCommerceException>(new MyCommerceException(output, "User Active status updated"), HttpStatus.OK);
		} catch (Exception E) {
			return new ResponseEntity<MyCommerceException>(new MyCommerceException(0, "Error: "+ E), HttpStatus.BAD_REQUEST);
		}
    }



 	@RequestMapping(
			value = "/api/reset",
			method = RequestMethod.POST,
			produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
			headers = "Accept=application/json",
			consumes = "application/json"
		)
    ResponseEntity<MyCommerceException> resetUserPasswordJson(@RequestBody ResetPasswordDto user) {
        
        try{

            if( userServiceInterface.resetUserPassword(user.getEmail(), user.getPassword(), user.getNewPassword()))
                return new ResponseEntity<MyCommerceException>(new MyCommerceException(1, "Password Successfully Changed"), HttpStatus.OK);
            return new ResponseEntity<MyCommerceException>(new MyCommerceException(2, "Failed to reset password"), HttpStatus.OK);
        
        }catch(Exception E){
        	throw E;
        }
    }
    /* 
    **          Generate OTP    
    **           Controller
    */

	@RequestMapping(
			value = "/api/generateotp",
			method = RequestMethod.POST,
			produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
			headers = "Accept=application/json",
			consumes = "application/json"
		)
    ResponseEntity<MyCommerceException> generateOtpJson(@RequestBody GenerateOtp oneTP) {
        
        try{

            int status = userServiceInterface.generateOtp(oneTP.getEmail());
                if(status==1)
                    return new ResponseEntity<MyCommerceException>(new MyCommerceException(1, "OTP Successfully Generated"), HttpStatus.OK);
                else if(status==-1)
                    return new ResponseEntity<MyCommerceException>(new MyCommerceException(2, "Invalid Email id"), HttpStatus.OK);
                else
                    return new ResponseEntity<MyCommerceException>(new MyCommerceException(3, "OTP cannot be created"), HttpStatus.OK);   
    
            }catch(Exception E){
                return new ResponseEntity<MyCommerceException>(new MyCommerceException(0, "Error: "+ E), HttpStatus.BAD_REQUEST);
            }
    }
	
    /* 
    **          Forgot Password/New Password    
    **                  Controller
    */

	@RequestMapping(
			value = "/api/newpassword",
			method = RequestMethod.POST,
			produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
			headers = "Accept=application/json",
			consumes = "application/json"
		)
    ResponseEntity<MyCommerceException> generateNewPasswordJson(@RequestBody ForgotPasswordDto user) {
        
        try{
            int status = userServiceInterface.setNewPassword(user.getEmail(), user.getOtp(), user.getNewPassword());
            if(status == 1)
                return new ResponseEntity<MyCommerceException>(new MyCommerceException(1, "Password Successfully Changed"), HttpStatus.OK);
            else if(status == 2)
                return new ResponseEntity<MyCommerceException>(new MyCommerceException(2, "OTP dont match"), HttpStatus.OK);
            else if(status == 3)
                return new ResponseEntity<MyCommerceException>(new MyCommerceException(3, "OTP expired"), HttpStatus.OK);
            else 
                return new ResponseEntity<MyCommerceException>(new MyCommerceException(0, "Error: Invalid Email id"), HttpStatus.OK);
        }catch(Exception E){
            return new ResponseEntity<MyCommerceException>(new MyCommerceException(0, "Error: "+ E), HttpStatus.BAD_REQUEST);
        }
    }
    /* 
    **          List all users   
    **                  
    */
    
	@RequestMapping(
			value = "/api/getAlluserdetails", 
			method = RequestMethod.GET, 
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE }, 
			headers = "Accept=application/json"
	)
    public ResponseEntity<List<UserModel>> getAllUserDetailsJson() {
	        
	        try{
			    session.setAttribute("all_User_Details", "all_User_Details");
                sessionmgmtservice.renewSession();
	            return new ResponseEntity<List<UserModel>>(userServiceInterface.findAll(), HttpStatus.OK);
	        }catch(Exception E){
	            return new ResponseEntity<List<UserModel>>(HttpStatus.BAD_REQUEST);
	        }
	 }
		
    /**
     * Helper function
     */

    private Pattern pattern = Pattern.compile("\\d+");
 
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false; 
        }
        return pattern.matcher(strNum).matches();   
    }


    /* 
    **          Search user   
    **                  
    */
    @RequestMapping(
        method = RequestMethod.POST, 
        value = "/api/searchuser", 
		produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
		headers = "Accept=application/json",
		consumes = "application/json"
    )
    public ResponseEntity<UserModel> searchUser (@RequestBody ObjectNode objectNode) {
        try{
			String userId = objectNode.get("userId").asText().toString(); 
            if(isNumeric(userId)){
                return new ResponseEntity<UserModel>(userServiceInterface.findUserByPhoneNumber(userId), HttpStatus.OK);
            }
    
            return new ResponseEntity<UserModel>(userServiceInterface.findUserByEmail(userId), HttpStatus.OK);

        }catch(Exception E){
            return new ResponseEntity<UserModel>(HttpStatus.BAD_REQUEST);
        } 
    }

    

    @RequestMapping(method = RequestMethod.POST, value = "/api/findById", 
    		consumes = "application/json", headers = "Accept=application/json")
        public ResponseEntity<UserModel> findUser (@RequestBody ObjectNode objectNode) {
            try{

            	long userId = Long.parseLong(objectNode.get("userId").asText()); 
                UserModel userData = userServiceInterface.findUserById(userId);
                return new ResponseEntity<UserModel>(userData, HttpStatus.OK);

            }catch(Exception E){
                return new ResponseEntity<UserModel>(HttpStatus.BAD_REQUEST);
            } 
        }
    /*
    **  Search User by Pincode
    **
    */

    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/searchUserbyPincode", 
        headers = "Accept=application/json"
    )
    public ResponseEntity<List<UserModel>> serachUserByPincode(@RequestBody ObjectNode objectNode) {
        try{
			String Pincode = objectNode.get("Pincode").asText().toString(); 
            return new ResponseEntity<List<UserModel>>(userServiceInterface.findUserByPincode(Pincode), HttpStatus.OK); 
        }catch(Exception E){
            return new ResponseEntity<List<UserModel>>(HttpStatus.BAD_REQUEST);
        }
    }

    /*
    **      Add User address
    **
    */

    
	@RequestMapping(
			value = "/api/addUserAddress",
			method = RequestMethod.POST,
			produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
			headers = "Accept=application/json",
			consumes = "application/json"
		)
        public ResponseEntity<MyCommerceException> addAddressJson(@RequestBody UserAddressDto address) {
            try{
                UserModel user = userServiceInterface.findUserByEmail(address.getEmail());
                Boolean status = userServiceInterface.addAddress(address, user);

                if(status)
                    return new ResponseEntity<MyCommerceException>(new MyCommerceException(1, "Address added Successfully"), HttpStatus.OK);
                
                return new ResponseEntity<MyCommerceException>(new MyCommerceException(2, "Address cannot be added. Try Again!!"), HttpStatus.OK);
                
            }catch(Exception E){
            	String causeString = E.getMessage();
    			Exception exe = new Exception();

    			exe.initCause(E);
    			if (exe.getCause() != null && exe.getCause().getCause() != null)
    				causeString = causeString + " : " + exe.getCause().getCause().getLocalizedMessage();

    			return new ResponseEntity<MyCommerceException>(new MyCommerceException(0,"Error: "+ causeString), HttpStatus.BAD_REQUEST);
            }

    		 
        }

    /**
     * 
     * Edit User Details
     * 
     */


	@RequestMapping(
			value = "/api/edituser",
			method = RequestMethod.POST,
			produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
			headers = "Accept=application/json",
			consumes = "application/json"
		)
    public ResponseEntity<MyCommerceException> editUserJson(@RequestBody EditUserDto userEntity) 
	{
            try{
                
                int status = userServiceInterface.updateUserDetail(userEntity);
                
                if(status==1)
                    return new ResponseEntity<MyCommerceException>(new MyCommerceException(1, "User details updated succesfully"), HttpStatus.OK);
                else if(status == 2)
                    return new ResponseEntity<MyCommerceException>(new MyCommerceException(2, "Email id is already registered"), HttpStatus.OK);
                else if(status == 3)
                    return new ResponseEntity<MyCommerceException>(new MyCommerceException(3, "Phone number is already registered"), HttpStatus.OK);
                    
                return new ResponseEntity<MyCommerceException>(new MyCommerceException(2, "Update failed. Something Wrong with the data. Try Again!!"), HttpStatus.OK);
                
            }catch(Exception E){
                return new ResponseEntity<MyCommerceException>(new MyCommerceException(0, "Error: "+ E), HttpStatus.BAD_REQUEST);
            }

	}
}