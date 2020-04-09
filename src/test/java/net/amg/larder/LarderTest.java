package net.amg.larder;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsIterableContaining.hasItems;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class LarderTest {
    private Larder larder;

    @Before
    public void setup() {
        Map<FoodItem, Integer> stock = new HashMap<>();
        stock.putIfAbsent(FoodItem.from("egg", "dairy", "each"), 12);
        stock.putIfAbsent(FoodItem.from("bacon", "meat", "rasher"), 6);
        stock.putIfAbsent(FoodItem.from("mushroom", "veg", "each"), 45);
        stock.putIfAbsent(FoodItem.from("beans", "veg", "tin"), 3);
        stock.putIfAbsent(FoodItem.from("hash brown", "veg", "each"), 0);
        stock.putIfAbsent(FoodItem.from("sausage", "meat", "each"), 7);
        larder = Larder.from(stock);
    }

    @Test
    public void retrieveLarderContents() {
        List<String> data = larder.retrieveLarderContents();
        String larderContents = String.join(" ", data);
        System.out.println(larderContents);
        assertThat(larderContents, containsString("egg"));
        assertThat(larderContents, containsString("hash brown"));
    }

    @Test
    public void retrieveItemsInStock() {
        List<String> data = larder.retrieveItemsInStock();
        String inStock = String.join("\n\t", data);
        System.out.println(inStock);
        assertThat(inStock, containsString("egg"));
        assertThat(inStock, containsString("sausage"));
    }

    @Test
    public void retrieveItemsInStock_shouldNotContainHashBrowns() {
        List<String> data = larder.retrieveItemOutOfStock();
        assertThat(data, not(hasItems("hash brown")));
    }

    @Test
    public void retrieveItemsNotInStock() {
        List<String> data = larder.retrieveItemOutOfStock();
        String outOfStock = String.join("\n\t", data);
        System.out.println(outOfStock);
        assertThat(outOfStock, containsString("hash brown"));
    }

    @Test
    public void retrieveItemsNotInStock_shouldNotContainEggs() {
        List<String> data = larder.retrieveItemOutOfStock();
        assertThat(data, not(hasItems("eggs")));
    }

}