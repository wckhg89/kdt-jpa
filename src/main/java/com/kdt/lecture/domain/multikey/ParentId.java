package com.kdt.lecture.domain.multikey;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ParentId implements Serializable {
    private String id1;
    private String id2;
}
