package minism.hellospring;

import jakarta.persistence.EntityManager;
import minism.hellospring.Service.MemberService;
import minism.hellospring.repository.JpaMemberRepository;
import minism.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // 자바 코드로 직접 스프링 빈 등록
    // Service와 Repository의 @Service, @Repository, @Autowired 어노테이션 제거하고 진행
    // 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 할 경우

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        // 인메모리 사용
//        return new MemoryMemberRepository();
        // 물리적 DB 사용
//        return new JdbcMemberRepository(dataSource);
        // JdbcTemplate 사용
//        return new JdbcTemplateMemberRepository(dataSource);
        // Jpa 사용
        return new JpaMemberRepository(em);
    }
}
