package net.amg.larder.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuItem {
    @JsonValue
    private final String name;

    public static MenuItem from(String item) {
        return new MenuItem(item);
    }
}
