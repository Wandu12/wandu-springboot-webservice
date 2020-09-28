package com.wandu.book.springboot.web.domain.posts;


import com.wandu.book.springboot.domain.posts.Posts;
import com.wandu.book.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest //다른 설정 없이 실행할 경우 H2 DB를 자동으로 실행해줌
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After //JUnit에서 단위 테스트가 끝날 때마다 수행되는 메서드를 지정함.
        // 보통 배포 전 전체 테스트 수행시 테스트간 데이터 침범을 막기 위해 사용
        // 여러 테스트가 동시 수행시 테스트용 DB인 H2에 데이터가 그대로 남아 있어 다음 테스트 실행시 테스트가 실패할 수 있음.
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트용 게시글";
        String content = "테스트용 본문";

        postsRepository.save(Posts.builder()
                .title(title).content(content).author("exampleemail10229@gmail.com")
                .build());
            //postsRepository.save: 테이블 posts에 insert/update 쿼리를 수행함.
            // id값이 있다면 update, 없다면 insert 쿼리가 실행됨

        //when
        List<Posts> postsList = postsRepository.findAll(); //테이블 posts에 있는 모든 데이터를 조회해옴.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
