package com.example.deli.service;

import com.example.deli.domain.Food;
import com.example.deli.domain.FoodOrder;
import com.example.deli.domain.Ordering;
import com.example.deli.domain.Restaurant;
import com.example.deli.dto.FoodOrderDto;
import com.example.deli.dto.FoodOrderRequestDto;
import com.example.deli.dto.OrderRequestDto;
import com.example.deli.dto.OrderResponseDto;
import com.example.deli.repository.FoodOrderRepository;
import com.example.deli.repository.FoodRepository;
import com.example.deli.repository.OrderRepository;
import com.example.deli.repository.RestaurantRepository;
import com.example.deli.validator.OrderFoodValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderRepository foodorderRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponseDto saveOrder(OrderRequestDto orderRequestDto) {
        //음식점이 존재하는지 확인
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(() -> new NullPointerException("해당 음식점이 존재하지 않습니다."));

        List<FoodOrderDto> foods = new ArrayList<>();   //반환으로 줄 DTO에 담을 주문 foods
        List<FoodOrder> foodOrders = new ArrayList<>();  //DB에 저장할 주문 foods
        int totalPrice = 0;     //총 가격
        for (FoodOrderRequestDto foodOrderRequestDto : orderRequestDto.getFoods()) {
            //주문한 음식이 존재하는지 확인
            Food food = foodRepository.findByIDAndRestaurant(foodOrderRequestDto.getId(), restaurant);
            if (food == null) {
                throw new IllegalArgumentException("주문한 음식이 존재하지 않습니다.");
            }

            //음식별 수량 가져오기
            int quantity = foodOrderRequestDto.getQuantity();
            OrderFoodValidator.validateOrderFood(quantity, food.getPrice());

            //음식 주문 목록 생성 , DB 저장
            FoodOrder foodOrder = new FoodOrder(food, quantity);
            foodorderRepository.save(foodOrder);

            int price = foodOrder.getPrice();

            //주문 음식 Dto 생성
            FoodOrderDto foodOrderDto = new FoodOrderDto(food.getName(), quantity, price);

            totalPrice += price;    //총 가격 계산

            foodOrders.add(foodOrder);        //DB에 저장할 List
            foods.add(foodOrderDto);        //뻔한 음식 목록 List
        }

        OrderFoodValidator.validateOrderTotalPrice(restaurant.getMinOrderPrice(), totalPrice);
        Ordering ordering = new Ordering(restaurant, foodOrders, totalPrice);
        orderRepository.save(ordering);

        //반환 dto
        return new OrderResponseDto(restaurant.getName(), foods, ordering.getRestaurant().getDeliveryFee(), ordering.getTotalPrice());
    }

    public List<OrderResponseDto> findOrder() {
        List<Ordering> orders = orderRepository.findAll();

        List<OrderResponseDto> orderList = new ArrayList<>();
        for (Ordering order : orders) {
            List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
            for (FoodOrder foodOrder : order.getFoods()) {
                Food food = foodOrder.getFood();
                FoodOrderDto foodOrderDto = new FoodOrderDto(food.getName(), foodOrder.getQuantity(), foodOrder.getPrice());
                foodOrderDtoList.add(foodOrderDto);
            }
            Restaurant restaurant = order.getRestaurant();
            OrderResponseDto orderResponseDto = new OrderResponseDto(restaurant.getName(), foodOrderDtoList, restaurant.getDeliveryFee(), order.getTotalPrice());
            orderList.add(orderResponseDto);

        }
        return orderList;
    }
}

