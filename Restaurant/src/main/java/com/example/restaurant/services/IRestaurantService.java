package com.example.restaurant.services;

import com.example.restaurant.dto.RestaurantDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IRestaurantService {

    RestaurantDto add(RestaurantDto restaurantDto);

    RestaurantDto update(String idRestaurant, Map<Object, Object> fields);

    boolean delete(String idRestaurant);

    Page<RestaurantDto> getRestaurants(int pageNbr, int pageSize);

    RestaurantDto getRestaurant(String id);

    RestaurantDto getRestaurantByName(String name);
}
