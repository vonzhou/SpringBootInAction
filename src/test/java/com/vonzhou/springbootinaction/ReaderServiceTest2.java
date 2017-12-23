package com.vonzhou.springbootinaction;

import com.vonzhou.springbootinaction.domain.ReaderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring boot 1.5没有了SpringApplicationConfiguration, 使用SpringBootTest注解
 * Created by vonzhou on 2017/12/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadingListApplication.class)
@Profile("prod")
public class ReaderServiceTest2 {

    @Autowired
    private ReaderService readerService;

    @Test
    public void byId() {
        Assert.assertTrue(readerService.byId(1L) == null);
    }
}
