package com.example.restaurant.repositories;

import com.example.restaurant.entities.Restaurant;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

    public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

    Optional<Restaurant> findByName(String name);
}