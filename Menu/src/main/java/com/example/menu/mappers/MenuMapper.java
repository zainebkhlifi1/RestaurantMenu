package com.example.menu.mappers;

import com.example.menu.dto.MenuDto;
import com.example.menu.entities.Menu;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link Menu} entities and {@link MenuDto} DTOs.
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
public interface MenuMapper {

    /**
     * Converts a {@link com.example.menu.dto.MenuDto} object to a {@link com.example.menu.entities.Menu} entity.
     *
     * @param menuDto the {@link com.example.menu.dto.MenuDto} object to be mapped
     * @return the corresponding {@link com.example.menu.entities.Menu} entity
     */
    Menu mapToEntity(MenuDto menuDto);

    /**
     * Converts a {@link Menu} entity to a {@link MenuDto} object.
     *
     * @param menu the {@link Menu} entity to be mapped
     * @return the corresponding {@link MenuDto} object
     */
    MenuDto mapToDto(Menu menu);
}