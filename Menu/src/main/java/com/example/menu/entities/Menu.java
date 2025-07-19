package com.example.menu.entities;



import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.Entity;
import javax.persistence.Id;
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