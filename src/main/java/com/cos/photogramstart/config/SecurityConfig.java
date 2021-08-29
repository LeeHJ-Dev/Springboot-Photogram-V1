package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity  //해당파일로 시큐리티를 활성화 한다.
@Configuration  //IOC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * SpringBoot PasswordEncoder
     * @return
     */
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화된다.
         */
        //super.configure(http);


        /**
         *
         */
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/comment/**")
                    .authenticated()
                .anyRequest()
                    .permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin") //get
                .loginProcessingUrl("/auth/signin") //post
                .defaultSuccessUrl("/")
                ;




    }


}
