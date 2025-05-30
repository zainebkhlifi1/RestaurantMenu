package com.example.springcrudmongodb.services;

import com.example.restaurant.dto.RestaurantDto;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.repositories.RestaurantRepository;
import com.example.springcrudmongodb.dto.ProductDto;
import com.example.springcrudmongodb.entities.Product;
import com.example.springcrudmongodb.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IRestaurantServiceImp implements com.example.springcrudmongodb.services.IRestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final com.example.springcrudmongodb.mappers.RestaurantMapper restaurantMapper;



    @Override
    public RestaurantDto add(RestaurantDto restaurantDto) {
        Restaurant restaurant = productMapper.mapToEntity(restaurantDto);
        restaurant.setCreatedAt(LocalDateTime.now());
        return productMapper.mapToDto(restaurantRepository.save(restaurant));
    }

    @Override
    public RestaurantDto update(String idProduct, Map<Object, Object> fields) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + idProduct));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Product.class, (String) key);
            field.setAccessible(true);

            if(field.getType().equals(LocalDate.class)){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-d");
                LocalDate localDate = LocalDate.parse((String) value, formatter);
                ReflectionUtils.setField(field, product , localDate);
            }else {
                ReflectionUtils.setField(field, product , value);
            }

        });
        product.setUpdatedAt(LocalDateTime.now());

        return productMapper.mapToDto(productRepository.save(product));
    }

    @Override
    public boolean delete(String idProduct) {
         productRepository.deleteById(idProduct);
        return productRepository.existsById(idProduct);
    }

    @Override
    public Page<ProductDto> getProducts(int pageNbr, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNbr,pageSize))
                .map(productMapper::mapToDto);
    }

    @Override
    public ProductDto getProduct(String id) {
        return productRepository.findById(id)
                .map(productMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("product not found"));
    }

    @Override
    public ProductDto getProductByName(String name) {
        return productRepository.findByName(name)
                .map(productMapper::mapToDto)
                .orElseThrow(() ->new IllegalArgumentException("product not found with this name"));
    }


}