package services;

import models.Food;
import models.MenuItem;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MenuItemServiceTest {

    @Test
    void testAddMenuItem() {
        MenuItemService service = new MenuItemService();

        MenuItem food = new Food("F01","Burger",50000,10,"fastfood");

        service.add(food);

        assertEquals(1, service.list.size());
    }

    @Test
    void testDeleteMenuItem() {
        MenuItemService service = new MenuItemService();

        MenuItem food = new Food("F01","Burger",50000,10,"fastfood");

        service.add(food);
        service.delete(food);

        assertEquals(0, service.list.size());
    }

    @Test
    void testIsEmpty() {
        MenuItemService service = new MenuItemService();

        assertTrue(service.isEmpty());
    }

    @Test
    void testFindById() {
        MenuItemService service = new MenuItemService();

        MenuItem food = new Food("F01","Burger",50000,10,"fastfood");
        service.add(food);

        Optional<MenuItem> result = service.findById("F01");

        assertTrue(result.isPresent());
        assertEquals("Burger", result.get().getName());
    }

    @Test
    void testFindByName() {
        MenuItemService service = new MenuItemService();

        MenuItem food = new Food("F01","Burger",50000,10,"fastfood");
        service.add(food);

        Optional<List<MenuItem>> result = service.findByName("Burger");

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
    }

    @Test
    void testFindByPriceRange() {
        MenuItemService service = new MenuItemService();

        MenuItem food1 = new Food("F01","Burger",50000,10,"fastfood");
        MenuItem food2 = new Food("F02","Salad",30000,10,"healthy");

        service.add(food1);
        service.add(food2);

        Optional<List<MenuItem>> result = service.findByPriceRange(20000,40000);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
    }
}