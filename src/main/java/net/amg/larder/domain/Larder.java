package net.amg.larder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.amg.larder.utils.IncorrectJson;
import net.amg.larder.utils.JsonDeserialiser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return stock.keySet()
                .stream().parallel()
                .map(FoodItem::getName)
                .collect(Collectors.toList());
    }

    public List<String> retrieveItemsInStock() {
        return stock.entrySet()
                .stream().parallel()
                .filter(stockItem -> stockItem.getValue() > 0)
                .map(stockItem -> String.format("%3d portions of %s", stockItem.getValue(), stockItem.getKey().getName()))
                .collect(Collectors.toList());
    }

    public List<String> retrieveItemOutOfStock() {
        return stock.entrySet()
                .stream().parallel()
                .filter(stockItem -> stockItem.getValue() == 0)
                .map(stockItem -> stockItem.getKey().getName())
                .collect(Collectors.toList());
    }

    public int howManyDoIHave(FoodItem item) {
        return stock.get(item);
    }

    public void addToStock(FoodItem item, int amount) {
        stock.computeIfAbsent(item, key -> 0);
        stock.computeIfPresent(item, (key, val) -> val + amount);
    }

    public void removeFromStock(FoodItem item, int amount) throws StockError {
        if (!stock.containsKey(item)) {
            throw new StockError(format("We do not have any %s", item));
        }
        if (stock.get(item) < amount) {
            throw new StockError(format("Only have %d of %s", stock.get(item), item));
        }
        stock.computeIfPresent(item, (key, val) -> val - amount);
    }

    public static class StockError extends Exception {
        public StockError(String message) {
            super(message);
        }
    }
}
