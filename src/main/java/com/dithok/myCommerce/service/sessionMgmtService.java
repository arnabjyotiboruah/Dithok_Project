package com.dithok.myCommerce.service;

import com.dithok.myCommerce.dto.sessionDto;

public interface sessionMgmtService {

    public int checksession(sessionDto Status);
    public int productCatelogSession(String operation);
    public void renewSession();
    public int setSessionTimeOut(int timeInMinute);
    public int logoutSession();
}