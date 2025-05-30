package com.example.restaurant.services;

import com.example.restaurant.dto.RestaurantDto;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.mappers.RestaurantMapper;
import com.example.restaurant.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IRestaurantServiceImpl implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public RestaurantDto add(RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantMapper.mapToEntity(restaurantDto);
        restaurant.setCreatedAt(LocalDateTime.now());
        return restaurantMapper.mapToDto(restaurantRepository.save(restaurant));
    }

    @Override
    public RestaurantDto update(String idRestaurant, Map<Object, Object> fields) {
        Restaurant restaurant = restaurantRepository.findById(idRestaurant)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found: " + idRestaurant));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, (String) key);
            if (field != null) {
                field.setAccessible(true);
                if (field.getType().equals(LocalDate.class)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.parse((String) value, formatter);
                    ReflectionUtils.setField(field, restaurant, localDate);
                } else {
                    ReflectionUtils.setField(field, restaurant, value);
                }
            }
        });

        restaurant.setUpdatedAt(LocalDateTime.now());
        return restaurantMapper.mapToDto(restaurantRepository.save(restaurant));
    }

    @Override
    public boolean delete(String idRestaurant) {
        restaurantRepository.deleteById(idRestaurant);
        return !restaurantRepository.existsById(idRestaurant);
    }

    @Override
    public Page<RestaurantDto> getRestaurants(int pageNbr, int pageSize) {
        return restaurantRepository.findAll(PageRequest.of(pageNbr, pageSize))
                .map(restaurantMapper::mapToDto);
    }

    @Override
    public RestaurantDto getRestaurant(String id) {
        return restaurantRepository.findById(id)
                .map(restaurantMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
    }

    @Override
    public RestaurantDto getRestaurantByName(String name) {
        return restaurantRepository.findByName(name)
                .map(restaurantMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with this name"));
    }
}
