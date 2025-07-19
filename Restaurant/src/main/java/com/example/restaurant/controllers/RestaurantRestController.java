package com.example.restaurant.controllers;

import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.RestaurantDto;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.services.IRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
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
    public List<Restaurant> getRestaurants(@RequestParam int pageNbr, @RequestParam int pageSize) {
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
    @GetMapping("/menus")
    public ResponseEntity<List<MenuDto>> getMenus() {
        return ResponseEntity.ok(restaurantService.fetchMenus());
    }

@GetMapping("/test-menu")
public ResponseEntity<List<MenuDto>> testMenu() {
    List<MenuDto> menus = restaurantService.fetchMenus();  // récupère les menus via Feign
    return ResponseEntity.ok(menus);
}


}
