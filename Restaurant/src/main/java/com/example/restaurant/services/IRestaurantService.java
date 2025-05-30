package com.example.restaurant.services;

import com.example.restaurant.dto.RestaurantDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IRestaurantService {

    RestaurantDto add(RestaurantDto productDto);

    RestaurantDto update(String idProduct, Map<Object,Object> fields);

    boolean delete(String idProduct);


    Page<RestaurantDto> getRestaurant(int pageNbr, int pageSize);

    RestaurantDto getRestaurant(String id);

    RestaurantDto getRestaurantByName(String name);
}