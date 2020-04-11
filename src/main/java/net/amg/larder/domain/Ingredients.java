package net.amg.larder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

import static java.lang.String.format;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Ingredients {
    @JsonProperty(value = "ingredients")
    private Map<FoodItem, Integer> foodList;

    public boolean doesItContain(FoodItem item){
        return foodList.containsKey(item);
    }

    public int howManyDoesItContain(FoodItem item) throws IngredientsError {
        if(!foodList.containsKey(item)){
            throw new IngredientsError(format("Does not contain %s in this recipe", item));
        }
        return foodList.get(item);
    }

    public static class IngredientsError extends Exception {
        public IngredientsError(String message) {
            super(message);
        }
    }

}
