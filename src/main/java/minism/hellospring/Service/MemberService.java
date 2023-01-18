package minism.hellospring.Service;

import minism.hellospring.domain.Member;
import minism.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
// 클래스명에 커서 위치 후 ALT + ENTER -> 'Create Test': Test 코드 자동 생성

    // MemberService와 MemberServiceTest에서 MemoryMemberRepository를 각각 new로 생성해서 사용하면
    // 사실상 같은 repository를 써야 함에도 다른 repository를 사용하게 되어 문제 발생
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 때문에 아래와 같이 수정(DI Injection)

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        // 동명이인 가입 불가
        Optional<Member> result = memberRepository.findByName(member.getName()); // CTRL + ALT + V: 함수 단축키
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        // Optional로 직접 반환받는 것보다는 아래처럼 더 간결하게 작성하는 것이 좋다
        validateDuplicateMember(member); // CTRL + SHIFT + ALT + T: EXTRACT METHOD

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 특정 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
