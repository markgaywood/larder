package net.amg.larder.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import static net.amg.larder.utils.ResourceFromClasspath.contentsOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;

public class MenuTest {
    private static Menu menu;

    @BeforeClass
    public static void setupClass() throws Exception {
        menu = Menu.from(contentsOf("net/amg/larder/domain/breakfast-menu.json"));
    }

    @Test
    public void isBaconIncludedWithTheEnglishBreakfast() throws Exception {
        Ingredients english = menu.getRecipe(MenuItem.from("english-breakfast"));
        assertThat(english.howManyDoesItContain(FoodItem.from("bacon")), equalTo(2));
    }

    @Test
    public void isBaconIncludedWithTheVegetarianBreakfast() throws Menu.MenuError {
        Ingredients vegetarian = menu.getRecipe(MenuItem.from("vegetarian-breakfast"));
        try {
            vegetarian.howManyDoesItContain(FoodItem.from("sausage"));
        } catch (Ingredients.IngredientsError error) {
            assertThat(error.getMessage(), equalTo("Does not contain sausage in this recipe"));
        }
    }

    @Test
    public void noBaconInTheVegetarian() throws Exception {
        Ingredients vegetarian = menu.getRecipe(MenuItem.from("vegetarian-breakfast"));
        assertFalse(vegetarian.doesItContain(FoodItem.from("bacon")));
    }

    @Test(expected = Menu.MenuError.class)
    public void doYouDoEggsBenedict_no() throws Exception {
        menu.getRecipe(MenuItem.from("eggs benedict"));
    }
}