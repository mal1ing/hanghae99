package com.example.deli.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 메소드 자동생성
@NoArgsConstructor  //기본 생성자 자동 생성
@Entity // DB 테이블 생성
public class FoodOrder {

    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // 자동으로 인덱스 증가
    private Long id;

    @ManyToOne                                       //다대일(하나의 음식을 여러번 주문목록으로 넣기)
    @JoinColumn(name = "food_id", nullable = false) //열설정
    private Food food;                              //조인하면 Food 테이블 열 참조 가능(RestaurantID, FoodId 등)

    @Column(nullable = false)       //열 설정(무조건 입력)
    private int quantity;           //수량

    @Column(nullable = false)       //열 설정(무조건 입력)
    private int price;              //주문가격

    //선택한 음식, 수량에 따른 생성자
    public FoodOrder(Food food,  int quantity){
        this.food = food;
        this.quantity  = quantity;
        this.price = food.getPrice() * quantity;
    }

}
