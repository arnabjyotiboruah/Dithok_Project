package com.dithok.myCommerce.service;

import java.util.List;

import com.dithok.myCommerce.dto.EditUserDto;
import com.dithok.myCommerce.dto.RegisterDto;
//import com.dithok.myCommerce.dto.ShoppingCartDto;
import com.dithok.myCommerce.dto.UserAddressDto;
import com.dithok.myCommerce.model.UserModel;


public interface UserServiceInterface {

	public long createUser(RegisterDto user);
	public Boolean isUserValid(String email, String password);
	
	public Boolean resetUserPassword(String email, String password, String newPassword);
	public int generateOtp(String email);
	public int setNewPassword(String email, String Otp, String newPassword);
	public Boolean isUserIDValid(long userId);
	public Boolean isProductIDValid(long productId);
	//public Boolean addToCart(ShoppingCartDto cart);
	//public Boolean deleteItemByUserFromCart(ShoppingCartDto shoppingCartDto);
	public Boolean deleteItemFromCart(long userId, long productId);
	//public Boolean modifyCart(ShoppingCartDto shoppingCartDt);
	public String getRazorpayId(String email);
	public List<UserModel> findAll();
	public int toggleUserActiveStatus(String email);
	public UserModel findUserByEmail(String email);
	public UserModel findUserByPhoneNumber(String phoneNumber);
	public List<UserModel> findUserByPincode(String pincode);
	public Boolean addAddress(UserAddressDto address, UserModel user);
	public int updateUserDetail(EditUserDto user);

	public UserModel findUserById(long id);
	public long getIdByEmail(String email);

}
