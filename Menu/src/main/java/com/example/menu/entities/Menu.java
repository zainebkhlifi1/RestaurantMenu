package com.example.menu.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

//@Document
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Menu implements Serializable {

    @Id
    String id;
    String name;
    private Double price;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}