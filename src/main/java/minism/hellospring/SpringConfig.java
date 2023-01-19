package minism.hellospring;

import minism.hellospring.Service.MemberService;
import minism.hellospring.aop.TimeTraceAop;
import minism.hellospring.repository.JpaMemberRepository;
import minism.hellospring.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // 자바 코드로 직접 스프링 빈 등록
    // Service와 Repository의 @Service, @Repository, @Autowired 어노테이션 제거하고 진행
    // 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 할

    private MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // AOP는 특별하게 사용하는 거니까 @Component 어노테이션을 쓰는 것보다는 빈을 직접 등록해서 가시적으로 사용해주는 것이 더 좋다
    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }

    /*@Bean
    public MemberRepository memberRepository(){
        // 인메모리 사용
//        return new MemoryMemberRepository();
        // 물리적 DB 사용
//        return new JdbcMemberRepository(dataSource);
        // JdbcTemplate 사용
//        return new JdbcTemplateMemberRepository(dataSource);
        // Jpa 사용
//        return new JpaMemberRepository(em);
    }*/
}
