package com.vonzhou.springbootinaction;

import com.vonzhou.springbootinaction.domain.ReaderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 没有加载完整的 Spring boot
 * Created by vonzhou on 2017/12/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ReadingListApplication.class)
@Profile("prod")
public class ReaderServiceTest {

    @Autowired
    private ReaderService readerService;

    @Test
    public void byId() {
        Assert.assertTrue(readerService.byId(1L) == null);
    }
}
