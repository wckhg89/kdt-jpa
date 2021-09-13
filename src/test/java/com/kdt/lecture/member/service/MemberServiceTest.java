package com.kdt.lecture.member.service;

import com.kdt.lecture.domain.order.Member;
import com.kdt.lecture.domain.order.MemberRepository;
import com.kdt.lecture.member.dto.MemberDto;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    private Long memberId;

    @BeforeEach
    void setUp() {
        // Given
        MemberDto memberDto = MemberDto.builder()
                .name("kanghonggu")
                .nickName("guppy.kang")
                .age(33)
                .address("seoul")
                .description("--")
                .build();

        // When
        memberId = memberService.saveMember(memberDto);

        // Then
        log.info("{}", memberId);
    }

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @Test
    void update_test() throws NotFoundException {
        // Given
        MemberDto memberDto = MemberDto.builder()
                .name("kanghonggu2")
                .nickName("guppy.kang2")
                .age(33)
                .address("seoul2")
                .description("--2")
                .build();

        // When
        Long id = memberService.updateMember(memberId, memberDto);

        // Then
        Member member = memberRepository.findById(id).get();
        assertThat(member.getNickName()).isEqualTo(memberDto.getNickName());
    }

    @Test
    void get_test() throws NotFoundException {
        // Given
        Long id = memberId;

        // When
        MemberDto member = memberService.getMember(id);

        // Then
        log.info("{}", member);
    }
}