package com.example.deli.validator;

import org.springframework.stereotype.Component;

@Component  // Bean 등록
public class OrderFoodValidator {

    public static void validateOrderFood(int quantity, int price){
        if(quantity < 1 || quantity > 100){
            throw new IllegalArgumentException("수량 1~100 입력");
        }
    }

    public static void validateOrderTotalPrice(int minOrderPrice, int totalPrice){
        if(minOrderPrice > totalPrice){
            throw new IllegalArgumentException("주문 금액이 최소주문 금액보단 커야함");
        }
    }
}
