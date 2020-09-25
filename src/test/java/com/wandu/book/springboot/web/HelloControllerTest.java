package com.wandu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //테스트 진행시 JUnit 내장 실행자 외에 다른 실행자 실행. 연결하는 역할을 함.(여기서는 SpringRunner 실행)
@WebMvcTest(controllers = HelloController.class)
    // 여러 스프링 테스트 어노테이션 중 Web에 집중할 수 있는 어노테이션. Controller, ControllerAdvice 사용 가능.
    // 단 @Service, @Component, @Repository 등은 사용 불가
public class HelloControllerTest {
    @Autowired //스프링이 관리하는 빈을 주입받음
    private MockMvc mvc; //웹API를 테스트할 때 사용. 스프링 MVC 테스트의 시작.

    @Test
    public void hello가_리턴() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMVC를 통해 /hello로 HTTP GET 요청함. 체이닝 기능 지원(아래처럼 이어서 선언가능)
                .andExpect(status().isOk()) //mvc.perform의 결과 검증. HTTP Header의 상태를 검증. 여기서는 200인지 아닌지를 검증
                .andExpect(content().string(hello));
                    //mvc.perform의 결과 검증. 응답 본문의 내용을 검증. 여기선 "hello" 리턴하므로 이 값이 맞는지 검증.
    }
}
