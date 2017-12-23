package com.vonzhou.springbootinaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by vonzhou on 2017/12/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadingListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleWebTests2 {

    @Value("${local.server.port}")
    private String port;

    @Test
    public void login() {
        try {
            System.out.println(port);
            RestTemplate restTemplate = new RestTemplate();
            String res = restTemplate.getForObject("http://localhost:{port}/login", String.class, port);
            System.out.println(res);
        } catch (Exception e) {

        }
    }
}
