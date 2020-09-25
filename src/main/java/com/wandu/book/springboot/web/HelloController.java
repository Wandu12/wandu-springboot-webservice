package com.wandu.book.springboot.web;

import com.wandu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
public class HelloController {

    @GetMapping("/hello") //Get의 요청을 받을 수 있는 API를 만들어줌(@RequestMapping(method=RequestMethod.GET 과 같음)
    public String hello() {
        return "hello";
    }

    //ResponseDto 사용 가능하도록 코드 추가
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     //RequestParam: 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
                                     // 외부에서 name(@RequestParam("name")이란 이름으로 넘긴 파라미터를
                                     // 메서드 파라미터 name(String name)에 저장
                                     @RequestParam("amount") int amount) {
                                    // name과 amount는 API를 호출하는 곳에서 넘겨준 값들임
        return new HelloResponseDto(name, amount);
    }
}
