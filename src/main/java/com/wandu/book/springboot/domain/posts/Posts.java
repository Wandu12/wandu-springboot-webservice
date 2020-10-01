package com.wandu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//주요 어노테이션을 클래스에 가까운 순서대로 둠. 이러헥 하면 추후 롬복이 필요 없을 경우 쉽게 삭제 가능
// 롬복은 필수 어노테이션은 아님. 그러나 서비스 초기 구축단계에서는 테이블 설계가 빈번히 변경되는데,
// 이때 롬복의 어노테이션들은 코드 변경량을 최소화시켜주므로 적극적으로 사용함.
@Getter //롬복의 어노테이션. 클래스 내 모든 필드의 Getter 메서드를 자동 생성.
@NoArgsConstructor //롬복의 어노테이션. 기본 생성자 자동 추가. public Posts() {}와 같은 효과.
@Entity //JPA의 어노테이션. 테이블과 링크될 클래스임을 나타냄. 일반적으로 카멜 표기법 클래스와 언더스코어 표기법 테이블을 매칭
public class Posts { //실제 DB의 테이블과 매칭될 클래스. Entity 클래스라고도 함
                    // JPA 사용시 DB 데이터에 작업할 경우 이 Entity 클래스의 수정을 통해 작업함
                    // 해당 클래스의 인스턴스 값들이 언제 어디에서 변해야 하는지 코드상으로 명확히 구분하기 힘드므로,
                    // Entity 클래스에서는 절대 Setter 메서드를 만들지 않는다!
                    // 필드 값 변경이 필요할 경우 목적과 의도를 명시한 메서드 추가가 필수적임.
    @Id //해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        //PK 생성 규칙. GenerationType.IDENTITY 옵션을 추가해야 auto_increment가 됨
    private Long id;

    @Column(length = 500, nullable = false)
        //해당 테이블의 칼럼을 나타냄. 선언하지 않아도 해당 클래스 필드는 모두 칼럼이 됨
        //보통 기본값 외 추가로 변경이 필요한 옵션이 있으면 사용.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
