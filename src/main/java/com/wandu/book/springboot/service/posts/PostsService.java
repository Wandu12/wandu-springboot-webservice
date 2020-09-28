package com.wandu.book.springboot.service.posts;

import com.wandu.book.springboot.domain.posts.PostsRepository;
import com.wandu.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor //스프링에서 Bean을 주입하는 방식 1)@Autowired(권장하지 않음) 2)setter 3)생성자(권장 방식)
                        // 생성자로 Bean 객체를 받도록 하면 @Autowired와 동일한 효과. @RequiredArgsConstructor가 생성자 생성
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
