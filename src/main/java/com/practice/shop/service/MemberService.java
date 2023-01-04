package com.practice.shop.service;

import com.practice.shop.entity.Member;
import com.practice.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전으로 콜백
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember  = memberRepository.findByEmail(member.getEmail());
        if (findMember!=null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
