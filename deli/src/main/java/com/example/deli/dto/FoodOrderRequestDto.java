package com.example.deli.dto;

import lombok.Getter;

@Getter
public class FoodOrderRequestDto {
    private Long id;        //음식id
    private int quantity;   //음식을 주문할 수량
}
