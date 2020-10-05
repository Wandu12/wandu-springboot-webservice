package com.wandu.book.springboot.web.dto;

import com.wandu.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController { //페이지 관련 컨트롤러로 사용
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
            //Model: 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장 가능.
            //postsService.findAlldesc()로 가져온 결과를 posts로 index.mustache에 전달함.
        model.addAttribute("posts", postsService.findAllDesc());
        return "index"; //머스태치 스타터가 컨트롤러에서 문자열 반환시 앞 경로와 뒤 파일 확장자를 자동으로 지정해줌
            // src/main/resources/templates/index.mustache가 되는 것
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save"; //_는 혼선을 주므로 잘 쓰지 않음
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
