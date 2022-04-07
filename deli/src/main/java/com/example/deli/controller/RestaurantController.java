package com.example.deli.controller;

import com.example.deli.domain.Restaurant;
import com.example.deli.dto.RestaurantDto;
import com.example.deli.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor    //의존성 주입, final필드에 대해 생성자를 생성
@RestController             // Json으로 데이터를 주고받음을 선언
public class RestaurantController {
    private final RestaurantService restaurantService;


    //음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantDto restaurantDto){
        return restaurantService.saveRestaurant(restaurantDto);
    }

    //음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant(){
        return restaurantService.listRestaurant();
    }
}
