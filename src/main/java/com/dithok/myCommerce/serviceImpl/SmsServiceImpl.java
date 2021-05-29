package com.dithok.myCommerce.serviceImpl;

import java.util.List;

import com.dithok.myCommerce.Repo.SmsRepository;
import com.dithok.myCommerce.Repo.UserRepository;
import com.dithok.myCommerce.dto.SmsDto;
import com.dithok.myCommerce.model.SmsModel;
import com.dithok.myCommerce.model.UserModel;
import com.dithok.myCommerce.service.SmsService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    SmsRepository smsRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public List<SmsModel> getAllSmsDetails() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SmsModel> getByPhoneNumber(String phoneNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int saveSmsDetails(SmsDto smsdetail) {
        SmsModel newSmsModel;
        newSmsModel = smsRepo.findBySmsId(smsdetail.getSmsId());
        if(newSmsModel == null){
            newSmsModel = new SmsModel();
        }
        
        BeanUtils.copyProperties(smsdetail, newSmsModel);
        // System.out.println("__-----_____--- " + smsdetail.getDeliveredAt());
        UserModel referenceUser = userRepo.findByPhoneNumber(smsdetail.getPhoneNumber().substring(2));
        if (referenceUser != null) {
            newSmsModel.setUser(referenceUser);
            SmsModel res = smsRepo.save(newSmsModel);
            return 1;
        }
        return 0;
        // try {
        // } catch (Exception e) {
        // e.printStackTrace();
        // throw e;
        // }
    }

}
