package com.example.restaurant.services;

import com.example.restaurant.clients.MenuClient;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.RestaurantDto;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.mappers.RestaurantMapper;
import com.example.restaurant.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IRestaurantServiceImpl implements IRestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final MenuClient menuClient;

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
        if (restaurantRepository.existsById(idRestaurant)){
//            throw new ResourceNotFoundException("UserProfile not found with id: " + idRestaurant);

        }
        restaurantRepository.deleteById(idRestaurant);

        return !restaurantRepository.existsById(idRestaurant);
    }

    @Override
    public List<Restaurant> getRestaurants(int pageNbr, int pageSize) {
        return restaurantRepository.findAll();
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

    @Override
    public List<MenuDto> fetchMenus() {
        return menuClient.getMenus(0, 5); // test avec page 0 et taille 5
    }

    @Override
    public void testCommunicationMenu() {
        List<MenuDto> menus = menuClient.getMenus(0, 5);
        System.out.println("Menus reçus du service Menu :");
        menus.forEach(menu -> System.out.println(menu.name() + " - " + menu.price()));
    }

}
