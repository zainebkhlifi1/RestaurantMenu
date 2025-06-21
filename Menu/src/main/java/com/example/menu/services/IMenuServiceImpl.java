package com.example.menu.services;

import com.example.menu.dto.MenuDto;
import com.example.menu.entities.Menu;
import com.example.menu.mappers.MenuMapper;
import com.example.menu.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IMenuServiceImpl implements IMenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    @Override
    public MenuDto add(MenuDto menuDto) {
        Menu menu = menuMapper.mapToEntity(menuDto);
        return menuMapper.mapToDto(menuRepository.save(menu));
    }

    @Override
    public MenuDto update(String idMenu, Map<Object, Object> fields) {
        Menu menu = menuRepository.findById(idMenu)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found with id: " + idMenu));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Menu.class, (String) key);
            if (field != null) {
                field.setAccessible(true);
                if (field.getType().equals(LocalDate.class)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate localDate = LocalDate.parse((String) value, formatter);
                    ReflectionUtils.setField(field, menu, localDate);
                } else {
                    ReflectionUtils.setField(field, menu, value);
                }
            }
        });

        //menu.setUpdatedAt(LocalDateTime.now());
        return menuMapper.mapToDto(menuRepository.save(menu));
    }

    @Override
    public boolean delete(String idMenu) {
        menuRepository.deleteById(idMenu);
        return !menuRepository.existsById(idMenu);
    }

    @Override
    public List<Menu> getMenus(int pageNbr, int pageSize) {
        return menuRepository.findAll();
    }

    @Override
    public MenuDto getMenu(String id) {
        return menuRepository.findById(id)
                .map(menuMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found with id: " + id));
    }

    @Override
    public MenuDto getMenuByName(String name) {
        return menuRepository.findByName(name)
                .map(menuMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found with name: " + name));
    }
}
