package com.example.restaurant.entities;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
//@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringBootApplication
public class Restaurant implements Serializable {
//    @Indexed
    @Setter(AccessLevel.MODULE)
    String id;
    @Id
    String name;
    String address;
    String phone;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}