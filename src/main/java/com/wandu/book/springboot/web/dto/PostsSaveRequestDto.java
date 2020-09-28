package com.wandu.book.springboot.web.dto;

import com.wandu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto { //Controller와 Service에서 사용할 Dto 클래스 생성
                                // Entity 클래스와 형태가 유사하지만, Entity 클래스를 Request/Response 클래스로 사용해서는 안 됨
                                // Entity 클래스는 DB와 맞닿은 핵심 클래스이기 때문(Entity 클래스를 기준으로 테이블이 생성됨)
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
