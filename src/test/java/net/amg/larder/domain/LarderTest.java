package net.amg.larder.domain;

import net.amg.larder.utils.ResourceFromClasspath;
import org.junit.Before;
import org.junit.Test;

import static java.lang.String.join;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsIterableContaining.hasItems;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class LarderTest {
    private Larder larder;

    @Before
    public void setup() throws Exception {
        larder = Larder.from(ResourceFromClasspath.contentsOf("net/amg/larder/domain/stock-001.json"));
    }

    @Test
    public void retrieveLarderContents() {
        String larderContents = join(", ", larder.retrieveLarderContents());
        assertThat(larderContents, containsString("egg"));
        assertThat(larderContents, containsString("hash brown"));
    }

    @Test
    public void retrieveItemsInStock() {
        String inStock = join("\n\t", larder.retrieveItemsInStock());
        assertThat(inStock, containsString("egg"));
        assertThat(inStock, containsString("sausage"));
    }

    @Test
    public void retrieveItemsInStock_shouldNotContainHashBrowns() {
        assertThat(larder.retrieveItemOutOfStock(), hasItems("hash brown"));
    }

    @Test
    public void retrieveItemsNotInStock() {
        assertThat(join("\n\t", larder.retrieveItemOutOfStock()), containsString("hash brown"));
    }

    @Test
    public void retrieveItemsNotInStock_shouldNotContainEggs() {
        assertThat(larder.retrieveItemOutOfStock(), not(hasItems("eggs")));
    }

    @Test
    public void add12MoreEggs_shouldBe24Now() {
        FoodItem egg = FoodItem.from("egg");
        larder.addToStock(egg, 12);
        assertThat(larder.getStock().get(egg), equalTo(24));
    }

    @Test
    public void add6Tomatoes() {
        FoodItem tomato = FoodItem.from("tomato");
        larder.addToStock(tomato, 6);
        assertThat(larder.getStock().get(tomato), equalTo(6));
    }

    @Test
    public void use7Eggs_stockReducedTo5() throws Exception {
        FoodItem egg = FoodItem.from("egg");
        larder.removeFromStock(egg, 7);
        assertThat(larder.howManyDoIHave(egg), equalTo(5));
    }

    @Test(expected = Larder.StockError.class)
    public void remove6Tomatoes_throwsStockError() throws Exception {
        FoodItem tomato = FoodItem.from("tomato");
        larder.removeFromStock(tomato, 6);
    }

    @Test(expected = Larder.StockError.class)
    public void useMoreBaconThanWeHave_throwsStockError() throws Exception {
        FoodItem bacon = FoodItem.from("bacon");
        larder.removeFromStock(bacon, 10);
    }
}