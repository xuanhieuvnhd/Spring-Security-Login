package com.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//Lớp cấu hình spring security Tạo ra bộ lọc servlet bảo vệ URL ứng dụng,xác nhận user và password
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //cấu hình xác thực bộ nhớ với thông tin đăng nhập và vai trò của người dùng.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}12345").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
    }
    //cấu hình bảo mật dựa trên web cho tất cả các yêu cầu HTTP
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //URL không dc bảo mật,ai cũng có thể truy cập
        http.authorizeRequests().antMatchers("/").permitAll()
                //URL được bảo mật,chỉ vai trò "USER" mới có thể truy cập
                .and()
                .authorizeRequests().antMatchers("/user**").hasRole("USER")
                ////URL được bảo mật,chỉ vai trò "ADMIN" mới có thể truy cập
                .and()
                .authorizeRequests().antMatchers("/admin**").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

}
