package net.amg.larder.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodItem {
    @JsonValue
    private final String name;

    public static FoodItem from(String item) {
        return new FoodItem(item);
    }

    @Override
    public String toString() {
        return name;
    }
}
