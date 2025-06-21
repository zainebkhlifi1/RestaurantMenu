package com.example.menu.repositories;

import com.example.menu.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, String> {

    Optional<Menu> findByName(String name);
}