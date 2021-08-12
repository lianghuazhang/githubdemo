package com.example.springbootlogliz01;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootLogLiz01ApplicationTests {

    @Test
    void contextLoads() {
        Logger log = LoggerFactory.getLogger(getClass());
//        日志级别 trace<debug<info<warn<error
        log.trace("trace 日志");
        log.debug("调试信息");
        log.info("自定义信息");
        log.warn("警告信息");
        log.error("异常信息");
    }

}
