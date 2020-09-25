package com.wandu.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
public class HelloController {

    @GetMapping("/hello") //Get의 요청을 받을 수 있는 API를 만들어줌(@RequestMapping(method=RequestMethod.GET 과 같음)
    public String hello() {
        return "hello";
    }
}
