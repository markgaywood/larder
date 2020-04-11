package net.amg.larder.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import static net.amg.larder.utils.ResourceFromClasspath.contentsOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNull;

public class MenuTest {
    private static Menu menu;

    @BeforeClass
    public static void setupClass() throws Exception {
        menu = Menu.from(contentsOf("net/amg/larder/domain/english-breakfast.json"));
    }

    @Test
    public void isBaconIncludedWithTheEnglishBreakfast() throws Exception {
        Ingredients english = menu.getRecipe(MenuItem.from("english-breakfast"));
        assertThat(english.getFoodList().get(FoodItem.from("bacon")), equalTo(2));
    }

    @Test
    public void noBaconInTheVegetarian() throws Exception {
        Ingredients english = menu.getRecipe(MenuItem.from("vegetarian-breakfast"));
        assertNull(english.getFoodList().get(FoodItem.from("bacon")));
    }

    @Test(expected = Menu.MenuError.class)
    public void doYouDoEggsBenedict_no() throws Exception {
        menu.getRecipe(MenuItem.from("eggs benedict"));
    }
}