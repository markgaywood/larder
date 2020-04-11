package net.amg.larder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.amg.larder.utils.IncorrectJson;
import net.amg.larder.utils.JsonDeserialiser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Larder {
    @JsonProperty
    private Map<FoodItem, Integer> stock;

    public static Larder from(String json) throws IncorrectJson {
        return JsonDeserialiser.from(json, Larder.class);
    }

    public List<String> retrieveLarderContents() {
        List<String> data = new ArrayList<>();
        stock.forEach((key, value) -> data.add(key.getName()));
        return data;
    }

    public List<String> retrieveItemsInStock() {
        List<String> data = new ArrayList<>();
        stock.forEach((key, value) -> {
            if (value > 0) {
                data.add(format("%3d portions of %s", value, key.getName()));
            }
        });
        return data;
    }

    public List<String> retrieveItemOutOfStock() {
        List<String> data = new ArrayList<>();
        stock.forEach((key, value) -> {
            if (value == 0) {
                data.add(key.getName());
            }
        });
        return data;
    }

    public int howManyDoIHave(FoodItem item) {
        return stock.get(item);
    }

    public void addToStock(FoodItem item, int amount) {
        stock.computeIfAbsent(item, key -> 0);
        stock.computeIfPresent(item, (key, val) -> val + amount);
    }

    public void removeToStock(FoodItem item, int amount) throws StockError {
        if (stock.containsKey(item)) {
            if (stock.get(item) >= amount) {
                stock.computeIfPresent(item, (key, val) -> val - amount);
            } else {
                throw new StockError(format("Only have %d of %s", stock.get(item), item));
            }
        } else {
            throw new StockError(format("We do not have any %s", item));
        }
    }

    public static class StockError extends Exception {
        public StockError(String message) {
            super(message);
        }
    }
}
