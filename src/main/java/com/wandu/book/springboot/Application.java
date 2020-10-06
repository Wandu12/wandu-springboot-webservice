package com.wandu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaAuditing //JPA Auditing 비활성화(따로 분리)
// @SpringBootApplication이 있는 위치부터 설정을 읽어가므로, 이 클래스는 항상 프로젝트 최상단에 위치해야 함.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //내장 WAS 실행(스프링부트는 내장 WAS 사용을 권장)
    }
}
