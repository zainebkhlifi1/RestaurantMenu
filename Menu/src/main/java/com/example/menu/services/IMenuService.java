package com.example.menu.services;

import com.example.menu.dto.MenuDto;
import com.example.menu.entities.Menu;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IMenuService {

    MenuDto add(MenuDto menuDto);

    MenuDto update(String idMenu, Map<Object, Object> fields);

    boolean delete(String idMenu);

    List<Menu> getMenus(int pageNbr, int pageSize);

    MenuDto getMenu(String id);

    MenuDto getMenuByName(String name);
}
