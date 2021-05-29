package com.dithok.myCommerce.service;

import java.util.List;

import com.dithok.myCommerce.dto.SmsDto;
import com.dithok.myCommerce.model.SmsModel;

public interface SmsService {
    public List<SmsModel> getAllSmsDetails();
    public List<SmsModel> getByPhoneNumber(String phoneNumber);
    public int saveSmsDetails(SmsDto smsdetail);


}
