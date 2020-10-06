package com.wandu.book.springboot.config.auth.dto;

import com.wandu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { //User클래스를 직렬화하는 대신 직렬화 기능을 가진 세션Dto를 추가로 만든 것
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}