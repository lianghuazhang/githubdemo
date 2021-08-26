package com.example.springbootlogliz01.service;

import com.example.springbootlogliz01.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 用户操作的方法
 */
public interface UserService {

    public void add(User user);

    public void update(User user);

    //根据id查询用户
    Mono<User> getUserById(Integer id);

    //查询所有用户
    Flux<User> getAllUser();

    //添加用户
    Mono<Void> saveUserInfo(Mono<User> user);
}
