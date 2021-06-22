package com.dithok.myCommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.dithok.myCommerce.Repo.UserRepository;

import com.dithok.myCommerce.dto.RegisterDto;
//import com.dithok.myCommerce.dto.ShoppingCartDto;
import com.dithok.myCommerce.dto.UserAddressDto;
import com.dithok.myCommerce.exception.DBException;
import com.dithok.myCommerce.exception.DbErrorStatus;
import com.dithok.myCommerce.service.UserServiceInterface;
import com.dithok.myCommerce.model.AddressEmbeddable;
import com.dithok.myCommerce.model.NameEmbeddable;
import com.dithok.myCommerce.model.UserModel;
import com.dithok.myCommerce.dto.EditUserDto;
import com.dithok.myCommerce.dto.GenerateOtp;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	UserRepository userRepo;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	GenerateOtp otpObj;

	

	@Override
	public long createUser(RegisterDto user) {
		if (user.getPassword() == null || user.getPassword().trim().length()<6) {
			String strMsg = String.format("Password length can not be less than 6");
			throw new DBException(this.getClass(), this, DbErrorStatus.INVALID_DATA, strMsg);
		}
		UserModel newUser = new UserModel();
		BeanUtils.copyProperties(user, newUser);

		NameEmbeddable name = new NameEmbeddable(user.getFirstName(), user.getMiddleName(), user.getLastName());

		newUser.setName(name);
		newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		newUser.setActive(true);
		userRepo.save(newUser);

		return 0;
	}

	@Override
	public Boolean isUserValid(String username, String password) {

		UserModel userEntity = userRepo.findByEmail(username);

		if (bCryptPasswordEncoder.matches(password, userEntity.getPassword()))
			return true;

		return false;
	}

	@Override
	public long getIdByEmail(String email) {
		UserModel userEntity = userRepo.findByEmail(email);
		return userEntity.getId();
	}
	
	@Override
	public Boolean isUserIDValid(long userId) {
		// TODO Auto-generated method stub
		Boolean sucess = false;
		UserModel userEntity = null;
		userEntity = userRepo.findById(userId); // Session ID

		if (userEntity != null)
			sucess = true;

		return sucess;
	}

	@Override
	public Boolean resetUserPassword(String email, String password, String newPassword) {
		boolean sucess = false;
		if (newPassword == null || newPassword.trim().length()<6) {
			String strMsg = String.format("Password length can not be less than 6");
			throw new DBException(this.getClass(), this, DbErrorStatus.INVALID_DATA, strMsg);
		}
		UserModel userEntity = userRepo.findByEmail(email);
		if(userEntity==null)
		{
			String strMsg = String.format("User does not exist!");
			throw new DBException(this.getClass(), this, DbErrorStatus.INVALID_DATA, strMsg);
		}

		if (bCryptPasswordEncoder.matches(password, userEntity.getPassword())) {
			boolean isPassWordPolicyViolation = newPassword.startsWith(password.substring(0, 4));
			if(isPassWordPolicyViolation )
			{
				String strMsg = String.format("Frist four charcters of the new password can not be same with the last Password");
				throw new DBException(this.getClass(), this, DbErrorStatus.INVALID_DATA, strMsg);
			}
			int startIndexOfLastSubstring  = password.length()-4;
			String suffixString = password.substring(startIndexOfLastSubstring, password.length());
			isPassWordPolicyViolation = newPassword.endsWith(suffixString);
			if(isPassWordPolicyViolation )
			{
				String strMsg = String.format("Last four charcters of the new password can can not be same with the last Password");
				throw new DBException(this.getClass(), this, DbErrorStatus.INVALID_DATA, strMsg);
			}
			
			userEntity.setPassword(bCryptPasswordEncoder.encode(newPassword));
			userRepo.save(userEntity);
			sucess = true;
		}
		else {
			String strMsg = String.format("Old password does not matched");
			throw new DBException(this.getClass(), this, DbErrorStatus.INVALID_DATA, strMsg);
		}

		return sucess;

	}

	@Override
	public int setNewPassword(String email, String Otp, String newPassword) {

		if (newPassword == null || newPassword.trim().length()<6) {
			String strMsg = String.format("Password length can not be less than 6");
			throw new DBException(this.getClass(), this, DbErrorStatus.INVALID_DATA, strMsg);
		}
		
		UserModel userEntity = userRepo.findByEmail(email);

		if (userEntity == null)
			return 0; // User Dont exist

		if ((System.currentTimeMillis() - userEntity.getOTPCreatedTime()) <= 300000) { // Otp valid for 5 min only

			if (Otp.equals(userEntity.getOTP())) {
				userEntity.setPassword(bCryptPasswordEncoder.encode(newPassword));
				userRepo.save(userEntity);
				return 1; // Successful
			}
			return 2; // Otp dont match
		}

		return 3; // Otp expired

	}

	@Override
	public int generateOtp(String email) {

		String numbers = "0123456789";
		UserModel otpEntity = userRepo.findByEmail(email);

		if (otpEntity == null)
			return -1;
		else {
			// Using random method
			Random rndm_method = new Random();

			char[] otp = new char[6];

			for (int i = 0; i < 6; i++) {
				// Use of charAt() method : to get character value
				// Use of nextInt() as it is scanning the value as int
				otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
			}

			/// Saving OTP in database
			otpEntity.setOTP(String.valueOf(otp));
			otpEntity.setOTPCreatedTime(System.currentTimeMillis());
			userRepo.save(otpEntity);

			System.out.println(otp);

			return 1;
		}
	}

	@Override
	public List<UserModel> findAll() {
		try {
			return userRepo.findAll();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public int toggleUserActiveStatus(String email) {
		UserModel user = userRepo.findByEmail(email);
		try {
			if (user == null)
				return 2;
			else {
				if (user.getActive() == false)
					user.setActive(true);
				else
					user.setActive(false);

				userRepo.save(user);

				return user.getActive() ? 1 : 0;
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UserModel findUserByEmail(String email) {
		UserModel user = userRepo.findByEmail(email);
		return user;
	}

	@Override
	public UserModel findUserByPhoneNumber(String phoneNumber) {
		return userRepo.findByPhoneNumber(phoneNumber);
	}

	@Override
	public List<UserModel> findUserByPincode(String pincode) {
		return userRepo.findByPincode(pincode);
	}

	@Override
	public Boolean addAddress(UserAddressDto address, UserModel user) {
		try {
			AddressEmbeddable userAddress = new AddressEmbeddable(address.getHouse_number(), address.getStreet(),
					address.getCity(), address.getState(), address.getCountry(), address.getZip_code(),
					address.getLongitude(), address.getLatitude());
			user.setAddress(userAddress);
			userRepo.save(user);

		} catch (Exception E) {
			System.out.println(E);
			return false;
		}
		return true;
	}

	@Override
	public int updateUserDetail(EditUserDto user) {

		try {
			UserModel updateUser = userRepo.findById(user.getId());
			UserModel emailUser = userRepo.findByEmail(user.getEmail());
			UserModel phoneNumberUser = userRepo.findByPhoneNumber(user.getPhoneNumber());

			if (emailUser != updateUser && emailUser != null) {
				return 2;
			}

			if (phoneNumberUser != updateUser && phoneNumberUser != null) {
				return 3;
			}
			updateUser.setName(new NameEmbeddable(user.getFirstName(), user.getMiddleName(), user.getLastName()));
			updateUser.setAddress(new AddressEmbeddable(user.getHouse_number(), user.getStreet(), user.getCity(),
					user.getState(), user.getCountry(), user.getZip_code(), user.getLongitude(), user.getLatitude()));

			updateUser.setBirthDate(updateUser.getBirthDate());
			updateUser.setEmail(user.getEmail());
			updateUser.setPhoneNumber(user.getPhoneNumber());
			userRepo.save(updateUser);
		} catch (Exception E) {
			System.out.println(E);
			return 0;
		}
		return 1;
	}

	@Override
	public Boolean isProductIDValid(long productId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Boolean deleteItemFromCart(long userId, long productId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getRazorpayId(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public UserModel findUserById(long id)
	{
		return userRepo.findById(id);

	}


	


}