package com.example.restaurant.mappers;

import com.example.restaurant.dto.RestaurantDto;
import com.example.restaurant.entities.Restaurant;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link Restaurant} entities and {@link RestaurantDto} DTOs.
 * <p>
 * This interface uses the <strong>MapStruct</strong> library to automatically generate
 * mapping implementations at compile time. The {@code @Mapper} annotation indicates that
 * this interface is a MapStruct mapper.
 * <p>
 * The {@code componentModel = "spring"} attribute specifies that the generated mapper
 * implementation should be a Spring bean, allowing it to be injected and managed by the
 * Spring framework.
 * <p>
 * MapStruct eliminates the need for manual mapping code by generating efficient and
 * type-safe mapping implementations.
 *
 * @see <a href="https://mapstruct.org/">MapStruct Documentation</a>
 */
@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    /**
     * Converts a {@link com.example.restaurant.dto.RestaurantDto} object to a {@link com.example.restaurant.entities.Restaurant} entity.
     *
     * @param restaurantDto the {@link com.example.restaurant.dto.RestaurantDto} object to be mapped
     * @return the corresponding {@link com.example.restaurant.entities.Restaurant} entity
     */
    Restaurant mapToEntity(RestaurantDto restaurantDto);

    /**
     * Converts a {@link Restaurant} entity to a {@link RestaurantDto} object.
     *
     * @param restaurant the {@link Restaurant} entity to be mapped
     * @return the corresponding {@link RestaurantDto} object
     */
    RestaurantDto mapToDto(Restaurant restaurant);
}