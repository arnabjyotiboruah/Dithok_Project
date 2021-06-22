package com.dithok.myCommerce.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.core.io.Resource;

import com.dithok.myCommerce.Repo.AuditLogRepository;
import com.dithok.myCommerce.dto.EditUserDto;
import com.dithok.myCommerce.dto.ForgotPasswordDto;
import com.dithok.myCommerce.dto.GenerateOtp;
import com.dithok.myCommerce.dto.LoginDto;
import com.dithok.myCommerce.dto.RegisterDto;
import com.dithok.myCommerce.dto.ResetPasswordDto;
import com.dithok.myCommerce.dto.UserAddressDto;
import com.dithok.myCommerce.exception.MyCommerceException;
import com.dithok.myCommerce.model.AuditLog;
import com.dithok.myCommerce.model.PdfModel;
import com.dithok.myCommerce.model.UserModel;
import com.dithok.myCommerce.service.AuditLogService;
import com.dithok.myCommerce.service.UserServiceInterface;

import com.dithok.myCommerce.service.sessionMgmtService;
import com.dithok.myCommerce.singleton.Singleton;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.xml.sax.SAXException;

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
    
    @Autowired
    AuditLogService auditlogService;
    
    @Autowired
	private AuditLogRepository repository;
  
    
    
    Logger logger = LoggerFactory.getLogger(UserController.class); 

    

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
            		session.setAttribute("loggedInUser", loginUser.getEmail());
            		Singleton single = Singleton.getInstance();
            		single.logInMessage(auditlogService,loginUser.getEmail());
            	}
            	logger.info("User Logged In");
                return new ResponseEntity<>(new MyCommerceException(1,userId), HttpStatus.OK);
          
            }
            logger.error("User login failed because password did not match with Database");
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
                System.out.println(session.getAttribute("loggedInUser").toString());
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
	@RequestMapping(value = "/api/auditLogDetails", method = RequestMethod.GET, produces =  { MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json")
	public List<AuditLog> findAll() {
		logger.info("User Viewed Audit Log");
		logger.debug("This is a debug message.");
        logger.info("This is an info message.");
        logger.warn("This is a warn message.");
        logger.error("This is an error message.");
		return auditlogService.findAll();
	}
	@RequestMapping(value = "/api/getauditLogDetails/{date}", method = RequestMethod.GET, produces =  { MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json")
	public List<AuditLog> findByDate(@PathVariable("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date searchDate){
	    return auditlogService.findByDate(searchDate);
	    		
	}
	@RequestMapping(value="/group/cookie", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getCookie() {
		 return session.getAttribute("loggedInUser").toString();
	}
  	      
	
	@RequestMapping(value = "/api/IP", method = RequestMethod.GET, produces =  { MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json")
	public void PythonScript() throws IOException {
		InetAddress IP=InetAddress.getLocalHost();
		System.out.println(IP.toString());
    }

	@RequestMapping(value = "/api/downloadFile", method = RequestMethod.GET)
    public StreamingResponseBody getSteamingFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"DataSync.zip\"");
        InputStream inputStream = new FileInputStream(new File("C:/Users/hp/Desktop/DataSync/DataSync.zip"));
        logger.info("User Downloaded Data Sync Application");
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            Singleton single = Singleton.getInstance();
    		single.syncDownloadMessage(auditlogService,session.getAttribute("loggedInUser").toString());
        };
    }
	@GetMapping("/api/report/auditLog")
	public ResponseEntity<Resource> generateExcelReport() throws IOException, DocumentException {
		List<AuditLog> auditlog = repository.findAll();

		Document document = new Document(PageSize.A4, 25, 25, 25, 25);

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		PdfWriter.getInstance(document, os);

		document.open();

		Paragraph title = new Paragraph("Audit Log Report",
				FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD, new BaseColor(0, 0, 255)));

		document.add(title);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		Paragraph title2= new Paragraph("Report generated on " + df.format(new Date()),FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD));
		document.add(title2);

		PdfPTable table = new PdfPTable(3);
		table.setSpacingBefore(25);
		table.setSpacingAfter(25);

		PdfPCell c1 = new PdfPCell(new Phrase("TIMESTAMP",FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 255))));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(c1);

		PdfPCell c2 = new PdfPCell(new Phrase("USERNAME",FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 255))));
		c2.setHorizontalAlignment(Element.ALIGN_CENTER);
		c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(c2);

		PdfPCell c3 = new PdfPCell(new Phrase("DETAILS",FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 255))));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(c3);


		for (AuditLog auditlog1 : auditlog) {
			table.addCell(String.valueOf(auditlog1.getTimestamp()));
			table.addCell(auditlog1.getUserName());
			table.addCell(auditlog1.getDetails());
		}

		document.add(table);
		
		document.close();

		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=AuditLogPdfReport.pdf");

		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);

		return response;
	}
	@RequestMapping(value = "/downloaddebug", method = RequestMethod.GET) 
	public ResponseEntity<Object> downloadFiledebug() throws IOException  {
	   String filename = "C:/Users/hp/Downloads/Dithok_Project-Animesh/logs/debug.log";
	   File file = new File(filename);
	   InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	   HttpHeaders headers = new HttpHeaders();
   
	   headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
	   headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	   headers.add("Pragma", "no-cache");
	   headers.add("Expires", "0");
	      
	   ResponseEntity<Object> 
	   responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
	      MediaType.parseMediaType("application/txt")).body(resource);
	   return responseEntity;
	}

	@RequestMapping(value = "/downloaderror", method = RequestMethod.GET) 
	public ResponseEntity<Object> downloadFile() throws IOException  {
	   String filename = "C:/Users/hp/Downloads/Dithok_Project-Animesh/logs/error.log";
	   File file = new File(filename);
	   InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	   HttpHeaders headers = new HttpHeaders();
   
	   headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
	   headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	   headers.add("Pragma", "no-cache");
	   headers.add("Expires", "0");
	      
	   ResponseEntity<Object> 
	   responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
	      MediaType.parseMediaType("application/txt")).body(resource);
	   return responseEntity;
	}
}
