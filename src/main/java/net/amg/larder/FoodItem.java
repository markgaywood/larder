package net.amg.larder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodItem {
    private final String name;
    private final String group;
    private final String measurement;

    public static FoodItem from(String name, String group, String measurement) {
        return new FoodItem(name, group, measurement);
    }
}
