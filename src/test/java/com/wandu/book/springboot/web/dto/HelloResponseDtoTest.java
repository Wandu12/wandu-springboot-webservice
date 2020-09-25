package com.wandu.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
    //JNunit 대신 assertj의 assertThat을 사용한 이유: 추가적 라이브러리 필요X, wkehddhkstjd ghkrtlfgl wldnjs.

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
            // assertj라는 테스트 검증 라이브러리의 검증 메서드. 검증하고 싶은 대상을 메서드 인자로 받음. 
            // 메서드 체이닝을 지원하여 .isEqualTo()처럼 메서드를 뒤이어 사용 가능
            // isEqualTo(): assertj의 동등 비교 메서드. assertThat과 isEqaulTo 값을 비교해 같아야 테스트 성공.
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
