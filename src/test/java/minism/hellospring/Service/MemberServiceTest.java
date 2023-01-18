package minism.hellospring.Service;

import minism.hellospring.domain.Member;
import minism.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    // MemberService와 MemberServiceTest에서 MemoryMemberRepository를 각각 new로 생성해서 사용하면
    // 사실상 같은 repository를 써야 함에도 다른 repository를 사용하게 되어 문제 발생
//    MemberService memberService = new MemberService(memberRepository);
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 때문에 아래와 같이 수정(DI Injection)
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        // member2를 join할 경우 IllegalStateException이 나는지 검증
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 에러 메시지로 검증하고 싶은 경우
       /* IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");*/

        /*try{
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}