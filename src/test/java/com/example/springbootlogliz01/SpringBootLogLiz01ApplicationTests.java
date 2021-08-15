package com.example.springbootlogliz01;

import com.example.springbootlogliz01.pojo.User;
import com.example.springbootlogliz01.service.UserService;
import com.example.springbootlogliz01.service.impl.UserviceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootLogLiz01ApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        Logger log = LoggerFactory.getLogger(getClass());
//        日志级别 trace<debug<info<warn<error
        log.trace("trace 日志");
        log.debug("调试信息");
        log.info("自定义信息");
        log.warn("警告信息");
        log.error("异常信息");
        User user = new User();
        user.setAge(18);
        user.setName("liz");
        user.setSex("男");
        userService.add(user);
}

}
