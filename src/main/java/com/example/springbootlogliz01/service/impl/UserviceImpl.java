package com.example.springbootlogliz01.service.impl;


import com.example.springbootlogliz01.pojo.User;
import com.example.springbootlogliz01.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserviceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserviceImpl.class);


    @Override
    public void add(User user) {
        log.info("user add get into !!!"+user.toString());
//        throw new RuntimeException("yichang");
    }

    @Override
    public void update(User user) {
        log.info("update add get into !!!");
    }
}
