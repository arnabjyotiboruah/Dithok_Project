package com.dithok.myCommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dithok.myCommerce.model.SmsModel;
import org.springframework.data.repository.query.Param;


@Repository
public interface SmsRepository  extends JpaRepository<SmsModel, Long> {
//	public ProductModel findById(long product_Id);
//	public Iterable<ProductModel> findBySupplierId(long supplier_id);
//
//	@Query(value ="SELECT * FROM product pr WHERE pr.product_id= :product_Id", nativeQuery = true)
//	public List<ProductModel> findProductById(@Param("product_Id") long product_Id);
//	
	@Query(value ="SELECT * FROM sms s WHERE s.sms_id = :sms_id", nativeQuery = true)
	public SmsModel  findBySmsId(@Param("sms_id") long sms_id);



}

