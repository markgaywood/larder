package net.amg.larder.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodItem {
    @JsonValue
    private final String name;

    public static FoodItem from(String name) {
        return new FoodItem(name);
    }
}
