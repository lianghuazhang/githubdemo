package com.example.springbootlogliz01.service.impl;


import com.example.springbootlogliz01.pojo.User;
import com.example.springbootlogliz01.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

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

    private final Map<Integer,User> users = new HashMap<Integer,User>();

    public UserviceImpl(){
        this.users.put(1,new User("张三",18,"男"));
        this.users.put(1,new User("李四",19,"女"));
        this.users.put(1,new User("王五",20,"男"));
        this.users.put(1,new User("赵六",22,"女"));

    }

    @Override
    public Mono<User> getUserById(Integer id) {
        return Mono.justOrEmpty(this.users.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        return userMono.doOnNext(( p -> {
            int id = users.size()+1;
            users.put(id,p);
        })).thenEmpty(Mono.empty());
    }
}
