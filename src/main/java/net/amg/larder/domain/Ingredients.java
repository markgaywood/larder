package net.amg.larder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Ingredients {
    @JsonProperty(value = "ingredients")
    private Map<FoodItem, Integer> foodList;
}
