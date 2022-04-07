package com.example.deli.controller;

import com.example.deli.dto.FoodDto;
import com.example.deli.dto.FoodResponseDto;
import com.example.deli.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor    //의존성 주입, final 필드에 대해 생성자를 생성, @Autowuired 대신 사용
@RestController             // Json으로 데이터를 주고받음을 선언
public class FoodController {
    private final FoodService foodService;

    //음식명 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodDto){
        foodService.saveFood(restaurantId, foodDto);
    }

    //음식점별 음식명 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDto> getFoods(@PathVariable Long restaurantId){
        return foodService.listFood(restaurantId);
    }
}

