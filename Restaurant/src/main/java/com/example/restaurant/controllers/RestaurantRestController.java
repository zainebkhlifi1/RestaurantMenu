package com.example.restaurant.controllers;

import com.example.restaurant.dto.RestaurantDto;
import com.example.restaurant.services.IRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantService restaurantService;

    @PostMapping
    public RestaurantDto add(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.add(restaurantDto);
    }

    @PatchMapping("/{id}")
    public RestaurantDto patchUpdate(@RequestBody Map<Object, Object> fields, @PathVariable String id) {
        return restaurantService.update(id, fields);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return restaurantService.delete(id);
    }

    @GetMapping
    public Page<RestaurantDto> getRestaurants(@RequestParam int pageNbr, @RequestParam int pageSize) {
        return restaurantService.getRestaurants(pageNbr, pageSize);
    }

    @GetMapping("/{id}")
    public RestaurantDto getRestaurant(@PathVariable String id) {
        return restaurantService.getRestaurant(id);
    }

    @GetMapping("/name/{name}")
    public RestaurantDto getRestaurantByName(@PathVariable String name) {
        return restaurantService.getRestaurantByName(name);
    }
}
