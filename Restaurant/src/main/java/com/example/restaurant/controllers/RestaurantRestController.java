package com.example.restaurant.controllers;

import com.example.restaurant.dto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class RestaurantRestController{

    private final com.example.springcrudmongodb.services.IRestaurantService productService;

    @PostMapping
    public RestaurantDto add(@RequestBody RestaurantDto productDto) {
        return productService.add(restuarntDto);
    }

    @PatchMapping("{id}")
    public ProductDto patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable String id){
        return productService.update(id,fields);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable String id){
        return productService.delete(id);
    }


    @GetMapping
    public Page<ProductDto> getProducts(int pageNbr,int pageSize){
        return productService.getProducts(pageNbr,pageSize);
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }

    @GetMapping("name/{name}")
    public ProductDto getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }







}