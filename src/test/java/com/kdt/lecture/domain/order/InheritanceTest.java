package com.kdt.lecture.domain.order;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InheritanceTest {

    @Test
    void name() {
        Food food = new Food();
        food.setId(1L);
        food.setPrice(1000);
        food.setStockQuantity(100);
        food.setChef("100jong1");
    }
}
