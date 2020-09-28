package com.wandu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//보통 MyBatis에서는 Dao라 부르는 DB Layer 접근자를 JPA에서는 Repository로 부르며, 인터페이스로 생성함.
// 인터페이스 생성 후 JpaRepository<Entity 클래스, PK타입>을 상속하면 기본 CRUD 메서드가 자동으로 생성됨.
// @Repository 추가할 필요도 없음. 단, Entity 클래스와 기본 Entity Repository는 함께 위치해야 함.
// 그래서 프로젝트 규모가 커져도 함께 움직을 수 있도록 도메인 패키지에서 함께 관리함.

//Web(Conroller), Service, Repository, Dto, Domain 레이어 중 비즈니스 처리를 담당하는 레이어: Domain.
// (기존의, 서비스로 처리했던 방식을 트랜잭션 스크립트라고 함. 이 때는 모든 로직이 서비스 클래스 내부에서 처리됨.)
// ->서비스 계층이 무의미하고, 객체가 데이터 덩어리 이상의 역할을 할 수 없음
// but 도메인 모델에서 처리할 경우 각 도메인이 각자 본인의 이벤트 처리를 하며, 서비스 메서드는 트랜잭션과 도메인 간 순서만 보장함.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
