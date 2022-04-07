package com.example.deli.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Ordering {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 하나의 음식점에서 여러개의 주문
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany // 하나의 주문에 여러 개의 주문 목록
    @Column(nullable = false)
    private List<FoodOrder> foods;

    @Column(nullable = false)
    private int totalPrice;

    public Ordering(Restaurant restaurant, List<FoodOrder> foods, int totalPrice){
        this.restaurant = restaurant;
        this.foods = foods;
        this.totalPrice = totalPrice + restaurant.getDeliveryFee();
    }
}
