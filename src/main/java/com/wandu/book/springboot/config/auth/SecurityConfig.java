package com.wandu.book.springboot.config.auth;

import com.wandu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private  final CustomOAuth2UserService customOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면 사용 위해 해당 옵션 disable 함
                .and()
                    .authorizeRequests() //URL별 권한관리 설정하는 옵션. authorizeRequests를 선언해야 antMatchers 옵션 사용 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //antMatchers: 권한관리대상 지정 옵션
                    .anyRequest().authenticated() //anyRequest: 설정 값 이외 나머지 URL
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() //로그인 성공 후 사용자정보 가져올 때의 설정을 담당
                            .userService(customOAuth2UserService); //로그인 성공 시 후속 조치 진행할 인터페이스 구현체 등록 
    }
}
