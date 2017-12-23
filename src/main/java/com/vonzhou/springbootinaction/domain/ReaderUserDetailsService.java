package com.vonzhou.springbootinaction.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by vonzhou on 2017/12/23.
 */
@Service
public class ReaderUserDetailsService implements UserDetailsService {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = readerRepository.findOne(username);
        if (userDetails != null) {
            System.out.println(userDetails);
            return userDetails;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }
}
