package com.example.restaurant.clients;

import com.example.restaurant.dto.MenuDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.List;

@FeignClient(name = "Menu") // nom dans Eureka

public interface MenuClient {

    @GetMapping("/menu")
    List<MenuDto> getMenus(@RequestParam("pageNbr") int pageNbr,
                           @RequestParam("pageSize") int pageSize);
}
