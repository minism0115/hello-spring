package minism.hellospring.repository;

import minism.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 상속받은 인터페이스를 spring-data-jpa가 알아서 찾아가지고 구현체를 만들고 스프링 빈에 등록
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
