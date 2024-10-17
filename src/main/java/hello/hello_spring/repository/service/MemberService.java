package hello.hello_spring.repository.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;
// 커맨드 쉬프트 t 누르면 자동으로 test 생성해줌.
public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //회원가입
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                try {
                    throw new IllegalAccessException("이미 존재하는 회원입니다.");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
        });
    }
    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    // 회원 검색
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
