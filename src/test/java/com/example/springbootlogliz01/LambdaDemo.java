package com.example.springbootlogliz01;

import com.example.springbootlogliz01.lambda.LambdaTest;
import com.example.springbootlogliz01.service.UserService;
import com.example.springbootlogliz01.steam.SteamTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LambdaDemo {


    @Autowired
    private LambdaTest lambdaTest;

    @Autowired
    private SteamTest streamTest;

    @Test
    void lambdaTestMethod() {


//        streamTest.test3();
        streamTest.test5();
    }

}
