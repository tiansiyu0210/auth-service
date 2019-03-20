package com.onevote.authservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService oneVoteUserDetailsService;

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
//      //  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////        password 方案一：明文存储，用于测试，不能用于生产
////        String finalPassword = "123456";
////        password 方案二：用 BCrypt 对密码编码
////        String finalPassword = bCryptPasswordEncoder.encode("123456");
//        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("123").authorities("USER").build());
//        manager.createUser(User.withUsername("user_2").password("123").authorities("USER").build());
//        return manager;
 //   }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.parentAuthenticationManager(authenticationManagerBean())
                .userDetailsService(oneVoteUserDetailsService);
    }


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
