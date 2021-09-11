package com.kdt.lecture.domain.multikey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Parent2 {
    @EmbeddedId
    private ParentId2 id;
}
