package com.kdt.lecture.member.service;

import com.kdt.lecture.domain.order.Member;
import com.kdt.lecture.domain.order.MemberRepository;
import com.kdt.lecture.member.dto.MemberDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Long saveMember(MemberDto memberDto) {
        Member entity = memberRepository.save(convert(memberDto));
        return entity.getId();
    }


    public Long updateMember(Long id, MemberDto memberDto) throws NotFoundException {
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("회원정보를 찾을 수 없습니다."));

        entity.setName(memberDto.getName());
        entity.setNickName(memberDto.getNickName());
        entity.setAge(memberDto.getAge());
        entity.setAddress(memberDto.getAddress());
        entity.setDescription(memberDto.getDescription());

        return entity.getId();
    }

    public MemberDto getMember(Long id) throws NotFoundException {
        return memberRepository.findById(id)
            .map(it -> MemberDto.builder()
                    .name(it.getName())
                    .nickName(it.getNickName())
                    .age(it.getAge())
                    .address(it.getAddress())
                    .description(it.getDescription())
            .build())
            .orElseThrow(() -> new NotFoundException("회원정보를 찾을 수 없습니다."));
    }

    private Member convert(MemberDto memberDto) {
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setNickName(memberDto.getNickName());
        member.setAge(memberDto.getAge());
        member.setAddress(memberDto.getAddress());
        member.setDescription(memberDto.getDescription());

        return member;
    }

}
