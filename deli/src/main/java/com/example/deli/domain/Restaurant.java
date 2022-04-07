package com.example.deli.domain;

import com.example.deli.dto.RestaurantDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //get 메소드 자동생성
@NoArgsConstructor  // 기본 생성자 생성
@Entity     // DB 테이블 설정
public class Restaurant {
    @Id// 기본키설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //자동으로 인덱스 증가
    private Long id;

    @Column(nullable = false)           //열 설정(무조건 입력, 중복 허용 x)
    private  String name;                //음식점 이름

    @Column(nullable = false)           //열 설정(무조건 입력, 중복 허용x)
    private int minOrderPrice;          //최소 주문 가격

    @Column(nullable = false)           //열 설정(무조건 입력, 중복 허용x)
    private int deliveryFee;            //기본 배달비

    //음식점 dto생성자
    public Restaurant(RestaurantDto restaurantDto){
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }
}
