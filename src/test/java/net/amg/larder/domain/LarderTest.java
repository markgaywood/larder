package net.amg.larder.domain;

import net.amg.larder.utils.ResourceFromClasspath;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.core.IsIterableContaining.hasItems;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class LarderTest {
    private static Larder larder;

    @BeforeClass
    public static void setup() throws Exception {
        larder = Larder.from(ResourceFromClasspath.contentsOf("net/amg/larder/domain/stock-001.json"));
    }

    @Test
    public void retrieveLarderContents() {
        String larderContents = String.join(", ", larder.retrieveLarderContents());
        System.out.println(format("------\nLarder generally stocks:\n\t%s", larderContents));
        assertThat(larderContents, containsString("egg"));
        assertThat(larderContents, containsString("hash brown"));
    }

    @Test
    public void retrieveItemsInStock() {
        String inStock = String.join("\n\t", larder.retrieveItemsInStock());
        System.out.println(format("------\nLarder  contain:%s", inStock));
        assertThat(inStock, containsString("egg"));
        assertThat(inStock, containsString("sausage"));
    }

    @Test
    public void retrieveItemsInStock_shouldNotContainHashBrowns() {
        assertThat(larder.retrieveItemOutOfStock(), not(hasItems("hash brown")));
    }

    @Test
    public void retrieveItemsNotInStock() {
        String outOfStock = String.join("\n\t", larder.retrieveItemOutOfStock());
        System.out.println(format("------\nLarder does not contain:%s", outOfStock));
        assertThat(outOfStock, containsString("hash brown"));
    }

    @Test
    public void retrieveItemsNotInStock_shouldNotContainEggs() {
        List<String> data = larder.retrieveItemOutOfStock();
        assertThat(data, not(hasItems("eggs")));
    }

}