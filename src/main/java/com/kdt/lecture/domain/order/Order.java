package com.kdt.lecture.domain.order;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @Column(name = "id")
    private String uuid;

    @Column(name = "order_datetime", columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDatetime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Lob
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    public void setMember(Member member) {
        if(Objects.nonNull(member)) {
            this.member.getOrders().remove(this);
        }

        this.member = member;
        member.getOrders().add(this);
    }
}
