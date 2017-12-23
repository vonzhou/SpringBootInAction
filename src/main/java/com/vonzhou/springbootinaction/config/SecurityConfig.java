package com.vonzhou.springbootinaction.config;

import com.vonzhou.springbootinaction.domain.ReaderUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderUserDetailsService readerUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().and().formLogin().loginPage("/login").failureUrl("/login?error=true");
        http.authorizeRequests().antMatchers("/login", "/style.css", "/beans", "/metrics", "/health").permitAll();
        http.authorizeRequests().antMatchers("/**").hasAuthority("ROLE_READER");
        http.authorizeRequests().anyRequest().denyAll();

        // 暂时通过禁掉csrf解决POST方法403的问题;也可以在form表单中嵌入csrf token解决.
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(readerUserDetailsService);
    }

}
