package com.wandu.book.springboot.service.posts;

import com.wandu.book.springboot.domain.posts.Posts;
import com.wandu.book.springboot.domain.posts.PostsRepository;
import com.wandu.book.springboot.web.dto.PostsListResponseDto;
import com.wandu.book.springboot.web.dto.PostsResponseDto;
import com.wandu.book.springboot.web.dto.PostsSaveRequestDto;
import com.wandu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor //스프링에서 Bean을 주입하는 방식 1)@Autowired(권장하지 않음) 2)setter 3)생성자(권장 방식)
                        // 생성자로 Bean 객체를 받도록 하면 @Autowired와 동일한 효과. @RequiredArgsConstructor가 생성자 생성
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) { //JPA의 영속성 컨텍스트로 인해 update 기능에 DB에 쿼리를 날리는 부분이 없다
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //CUD 기능이 없는 서비스 메서드에서 해당 옵션을 주면 좋음
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
            //람다식 사용. .map(posts -> new PostsListResponseDto(posts)) 임.
            //postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto로 변환->List로 반환하는 메서드
    }
}
