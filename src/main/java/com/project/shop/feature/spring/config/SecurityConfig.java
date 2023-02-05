package com.project.shop.feature.spring.config;

import com.project.shop.feature.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                        .authorizeRequests()
                        .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                        .antMatchers("/guest/**").access("hasRole('ROLE_GUEST')")
                        .anyRequest().permitAll()
                        .and()

                        .formLogin()
                        .loginPage("/login/")
                        .loginProcessingUrl("/login/do")
                        .defaultSuccessUrl("/sell/")
                        .permitAll()
                        .and()

                        .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
                        .logoutSuccessUrl("/sell/")
                        .invalidateHttpSession(true)
                        .and()

                        .exceptionHandling()
                        .accessDeniedPage("/error/denied");

                        httpSecurity.httpBasic().disable();
                        httpSecurity.csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(memberService).passwordEncoder(bCryptPasswordEncoder());
    }


}
