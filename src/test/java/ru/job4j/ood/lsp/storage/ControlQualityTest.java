package ru.job4j.ood.lsp.storage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.storage.model.Food;
import ru.job4j.ood.lsp.storage.model.Fruit;
import ru.job4j.ood.lsp.storage.model.Meat;
import ru.job4j.ood.lsp.storage.store.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenDistributeFoodThanItInCorrectStore() {
        Food product = new Food("Milk",
                LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(8),
                150);
        Store shop = new Shop();
        Store trash = new Trash();
        Store warehouse = new Warehouse();
        ControlQuality quality = new ControlQuality(List.of(shop, trash, warehouse));
        quality.distributeFood(product);
        assertTrue(warehouse.getStoreProducts().contains(product));
        assertEquals(1, warehouse.getStoreProducts().size());
        assertTrue(shop.getStoreProducts().isEmpty());
        assertTrue(trash.getStoreProducts().isEmpty());
    }

    @Test
    public void whenFoodInWarehouse() {
        Fruit apple = new Fruit("Apple",
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(10),
                150);
        Warehouse warehouse = new Warehouse();
        assertTrue(warehouse.accept(apple));
    }

    @Test
    public void whenMeatInShopWithPercentExpiryBetween25And75() {
        Meat beef = new Meat("Beef",
                LocalDate.now().minusDays(7),
                LocalDate.now().plusDays(8),
                500);
        AbstractStore shop = new Shop();
        assertTrue(shop.accept(beef));
    }

    @Test
    public void whenMeatInShopWithPercentExpiryBetween75And100AndMeatGetDiscount() {
        Meat beef = new Meat("Beef",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(3),
                500);
        Shop shop = new Shop();
        assertTrue(shop.accept(beef));
        assertEquals(400, beef.getPrice());
    }

    @Test
    public void whenFoodInTrashAndPercentExpiryEquals100() {
        Food food = new Food("Not fresh",
                LocalDate.now().minusDays(10),
                LocalDate.now(),
                0);
        Trash trash = new Trash();
        assertTrue(trash.accept(food));
        assertEquals(100, food.getPercentOfExpiry());
    }

    @Test
    public void whenFoodInTrashWithPercentExpiryMoreThan100() {
        Food food = new Food("Not fresh",
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(2),
                0);
        Trash trash = new Trash();
        assertTrue(trash.accept(food));
        assertTrue(food.getPercentOfExpiry() > 100);
    }

    @Test
    public void whenFoodsDistributeInStoreThanResort() {
        Fruit apple = new Fruit("Apple",
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(10),
                150);
        Meat beef = new Meat("Beef",
                LocalDate.now().minusDays(7),
                LocalDate.now().plusDays(8),
                500);
        Food not_fresh = new Food("Not fresh",
                LocalDate.now().minusDays(10),
                LocalDate.now(),
                0);

        var warehouse = new Warehouse();
        var shop = new Shop();
        var trash = new Trash();
        ControlQuality quality = new ControlQuality(List.of(warehouse, shop, trash));
        quality.distributeFood(apple);
        quality.distributeFood(beef);
        quality.distributeFood(not_fresh);

        quality.resortProducts();
        assertTrue(warehouse.getStoreProducts().contains(apple));
        assertEquals(1, warehouse.getStoreProducts().size());
        assertTrue(shop.getStoreProducts().contains(beef));
        assertEquals(1, shop.getStoreProducts().size());
        assertTrue(trash.getStoreProducts().contains(not_fresh));
        assertEquals(1, trash.getStoreProducts().size());
    }
}