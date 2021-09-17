package com.kdt.lecture.order.service;

import com.kdt.lecture.domain.order.OrderRepository;
import com.kdt.lecture.domain.order.OrderStatus;
import com.kdt.lecture.order.dto.*;
import javassist.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    String uuid = UUID.randomUUID().toString();

    @BeforeEach
    void save_test() {
        // Given
        OrderDto orderDto = OrderDto.builder()
                .uuid(uuid)
                .memo("문앞 보관 해주세요.")
                .orderDatetime(LocalDateTime.now())
                .orderStatus(OrderStatus.OPENED)
                .memberDto(
                        MemberDto.builder()
                                .name("강홍구")
                                .nickName("guppy.kang")
                                .address("서울시 동작구만 움직이면 쏜다.")
                                .age(33)
                                .description("---")
                                .build()
                )
                .orderItemDtos(List.of(
                        OrderItemDto.builder()
                                .price(1000)
                                .quantity(100)
                                .itemDtos(List.of(
                                        ItemDto.builder()
                                                .type(ItemType.FOOD)
                                                .chef("백종원")
                                                .price(1000)
                                                .build()
                                ))
                                .build()
                ))
                .build();

        // When
        String savedUuid = orderService.save(orderDto);

        // Then
        assertThat(uuid).isEqualTo(savedUuid);
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
    }


    @Test
    void findOneTest() throws NotFoundException {
        // Given
        String orderUuid = uuid;

        // When
        OrderDto one = orderService.findOne(uuid);

        // Then
        assertThat(one.getUuid()).isEqualTo(orderUuid);
    }

    @Test
    void findAllTest() {
        // Given
        PageRequest page = PageRequest.of(0, 10);

        // When
        Page<OrderDto> all = orderService.findAll(page);

        // Then
        assertThat(all.getTotalElements()).isEqualTo(1);
    }
}