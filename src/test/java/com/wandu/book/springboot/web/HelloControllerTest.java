package com.wandu.book.springboot.web;

import com.wandu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) //테스트 진행시 JUnit 내장 실행자 외에 다른 실행자 실행. 연결하는 역할을 함.(여기서는 SpringRunner 실행)
@WebMvcTest(controllers = HelloController.class,
    excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
            //@WebMvcTest는 @ControllerAdvice와 @Controller를 읽고 
            // @Repository, @Service, @Component는 읽지 않으므로 오류를 없애기 위해 스캔 대상에서 SecutiryConfig를 제거함
    })
    // 여러 스프링 테스트 어노테이션 중 Web에 집중할 수 있는 어노테이션. Controller, ControllerAdvice 사용 가능.
    // 단 @Service, @Component, @Repository 등은 사용 불가
public class HelloControllerTest {
    @Autowired //스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; //웹API를 테스트할 때 사용. 스프링 MVC 테스트의 시작.

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMVC를 통해 /hello로 HTTP GET 요청함. 체이닝 기능 지원(아래처럼 이어서 선언가능)
                .andExpect(status().isOk()) //mvc.perform의 결과 검증. HTTP Header의 상태를 검증. 여기서는 200인지 아닌지를 검증
                .andExpect(content().string(hello));
                    //mvc.perform의 결과 검증. 응답 본문의 내용을 검증. 여기선 "hello" 리턴하므로 이 값이 맞는지 검증.
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                    //param: API 테스트에 사용될 요청 파라미터를 설정. 단, 값은 String만 허용됨.(숫자/날짜도 문자열로 변경 필수)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
                    //jsonPath: JSON 응닶값을 필드별로 검증할 수 있는 메서드. $를 기준으로 필드명을 명시함.
                    // 여기서는 name과 amount를 검증하므로 $.name, $.amount로 검증함.
    }
}
