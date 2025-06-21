package com.example.restaurant.services;

import com.example.restaurant.dto.RestaurantDto;
import com.example.restaurant.entities.Restaurant;

import java.util.List;
import java.util.Map;

public interface IRestaurantService {

    RestaurantDto add(RestaurantDto restaurantDto);

    RestaurantDto update(String idRestaurant, Map<Object, Object> fields);

    boolean delete(String idRestaurant);

    List<Restaurant> getRestaurants(int pageNbr, int pageSize);

    RestaurantDto getRestaurant(String id);

    RestaurantDto getRestaurantByName(String name);
}
