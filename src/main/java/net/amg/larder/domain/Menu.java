package net.amg.larder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.amg.larder.utils.IncorrectJson;
import net.amg.larder.utils.JsonDeserialiser;

import java.util.Map;

import static java.lang.String.format;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Menu {
    @JsonProperty
    private Map<MenuItem, Ingredients> recipes;

    public static Menu from(String json) throws IncorrectJson {
        return JsonDeserialiser.from(json, Menu.class);
    }

    public Ingredients getRecipe(MenuItem name) throws MenuError {
        return recipes.entrySet().stream()
                .filter(recipe -> recipe.getKey().equals(name))
                .findFirst()
                .orElseThrow(() -> new MenuError(format("We do not have %s on the menu.", name))).getValue();
    }

    public static class MenuError extends Exception {
        public MenuError(String message) {
            super(message);
        }
    }
}
