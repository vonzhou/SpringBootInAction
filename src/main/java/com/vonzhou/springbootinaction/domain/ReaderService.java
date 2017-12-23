package com.vonzhou.springbootinaction.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by vonzhou on 2017/12/23.
 */
@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    public Reader byId(long id) {
        return null;
    }
}
