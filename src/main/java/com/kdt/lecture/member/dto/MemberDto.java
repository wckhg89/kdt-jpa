package com.kdt.lecture.member.dto;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private String nickName;
    private int age;
    private String address;
    private String description;
}
