package com.example.deli.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter //set 메소드 자동생성
@Getter //get 메소드 자동생서
@NoArgsConstructor  // 기본 생성자 자동생성
public class FoodDto {
    private String name;        //음식명
    private int price;          //가격
}
