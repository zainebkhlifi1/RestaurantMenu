package com.example.menu.controllers;

import com.example.menu.dto.MenuDto;
import com.example.menu.entities.Menu;
import com.example.menu.services.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuRestController {

    private final IMenuService menuService;

    @PostMapping
    public MenuDto add(@RequestBody MenuDto menuDto) {
        return menuService.add(menuDto);
    }

    @PatchMapping("/{id}")
    public MenuDto patchUpdate(@RequestBody Map<Object, Object> fields, @PathVariable String id) {
        return menuService.update(id, fields);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return menuService.delete(id);
    }

    @GetMapping
    public List<Menu> getMenus(@RequestParam int pageNbr, @RequestParam int pageSize) {
        return menuService.getMenus(pageNbr, pageSize);
    }

    @GetMapping("/{id}")
    public MenuDto getMenu(@PathVariable String id) {
        return menuService.getMenu(id);
    }

    @GetMapping("/name/{name}")
    public MenuDto getMenuByName(@PathVariable String name) {
        return menuService.getMenuByName(name);
    }
}