package com.kdt.lecture.member.controller;

import com.kdt.lecture.ApiResponse;
import com.kdt.lecture.member.dto.MemberDto;
import com.kdt.lecture.member.service.MemberService;
import javassist.NotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ExceptionHandler
    private ApiResponse<String> exceptionHandle(Exception exception) {
        return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    private ApiResponse<String> notFoundHandle(NotFoundException exception) {
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @PostMapping("/members")
    public ApiResponse<Long> saveMember(@RequestBody MemberDto memberDto) {
        Long memberId = memberService.saveMember(memberDto);
        return ApiResponse.ok(memberId);
    }

    @PostMapping("/members/{id}")
    public ApiResponse<Long> updateMember(
            @PathVariable Long id,
            @RequestBody MemberDto memberDto
    ) throws NotFoundException {
        Long memberId = memberService.updateMember(id, memberDto);
        return ApiResponse.ok(memberId);
    }

    @GetMapping("/members/{id}")
    public ApiResponse<MemberDto> getMembers(
            @PathVariable Long id
    ) throws NotFoundException {
        MemberDto memberDto = memberService.getMember(id);
        return ApiResponse.ok(memberDto);
    }
}
