package com.example.restaurant.repositories;

import com.example.restaurant.entities.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    Optional<Restaurant> findByName(String name);
}