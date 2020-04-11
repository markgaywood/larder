package net.amg.larder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.amg.larder.utils.IncorrectJson;
import net.amg.larder.utils.JsonDeserialiser;

import java.io.IOException;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Menu {
    @JsonProperty
    private Map<String, Ingredients> recipes;

    public static Menu from(String json) throws IncorrectJson {
        return JsonDeserialiser.from(json, Menu.class);
    }

    public Ingredients getRecipe(String name) throws IOException {
        return recipes.entrySet().stream()
                .filter(recipe -> recipe.getKey().equals(name))
                .findFirst().orElseThrow(() -> new IOException("nothing")).getValue();
    }

    public int howManyCanIMake(Ingredients recipe) {
        return 0;
    }
}
