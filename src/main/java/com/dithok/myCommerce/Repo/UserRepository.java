package com.dithok.myCommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.dithok.myCommerce.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	//public UserModel findByEmail(String email);
	public UserModel findById(long userId);
	public UserModel findByPhoneNumber(String phoneNumber);
	public List<UserModel> findAll();

	@Query(value="select s.* from users s where s.zip_code = :pincode", nativeQuery = true)
    public List<UserModel> findByPincode(@Param("pincode") String pincode);
	
	@Query(value="select s.* from users s where s.email = :email", nativeQuery = true)
    public UserModel findByEmail(@Param("email") String email);
}
