package com.wandu.book.springboot.web.dto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController { //페이지 관련 컨트롤러로 사용
    @GetMapping("/")
    public String index() {
        return "index"; //머스태치 스타터가 컨트롤러에서 문자열 반환시 앞 경로와 뒤 파일 확장자를 자동으로 지정해줌
            // src/main/resources/templates/index.mustache가 되는 것
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save"; //_는 혼선을 주므로 잘 쓰지 않음
    }
}
