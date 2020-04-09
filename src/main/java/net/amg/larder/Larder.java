package net.amg.larder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Larder {
    private final Map<FoodItem, Integer> stock;
    public static Larder from(Map<FoodItem, Integer> stock) {
        return new Larder(stock);
    }
    public List<String> retrieveLarderContents() {
        List<String> data = new ArrayList<>();
        data.add("------\nLarder generally stocks:");
        stock.forEach((key, value) -> data.add(key.getName()));
        return data;
    }
    public List<String> retrieveItemsInStock() {
        List<String> data = new ArrayList<>();
        data.add("------\nLarder  contain:");
        stock.forEach((key, value) -> {
            if (value > 0) {
                data.add(String.format("%3d: %s(s) of %s", value, key.getMeasurement(), key.getName()));
            }
        });
        return data;
    }
    public List<String> retrieveItemOutOfStock() {
        List<String> data = new ArrayList<>();
        data.add("------\nLarder does not contain:");
        stock.forEach((key, value) -> {
            if (value == 0) {
                data.add(String.format("currently has no %s", key.getName()));
            }
        });
        return data;
    }

}
