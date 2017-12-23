package com.vonzhou.springbootinaction;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * 从 Spring boot 1.4 开始没有了 WebIntegrationTest 注解 使用 SpringBootTest
 * Created by vonzhou on 2017/12/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadingListApplication.class)
public class SimpleWebTests {

    @Test
    public void pageNotFound() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject("http://localhost:8080/notexist", String.class);
            Assert.fail("404");
        } catch (Exception e) {

        }
    }

    @Test
    public void login() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String res = restTemplate.getForObject("http://localhost:8080/login", String.class);
            System.out.println(res);
        } catch (Exception e) {

        }
    }
}
