package com.example.deli.repository;

import com.example.deli.domain.Restaurant;
import com.example.deli.dto.RestaurantDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);   //음식점 이름 중복성 검사
}
