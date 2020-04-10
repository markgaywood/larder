package net.amg.larder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Larder {
    @JsonProperty
    private Map<FoodItem, Integer> stock;

    public static Larder from(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Larder.class);
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
                data.add(String.format("%3d portions of %s", value, key.getName()));
            }
        });
        return data;
    }

    public List<String> retrieveItemOutOfStock() {
        List<String> data = new ArrayList<>();
        stock.forEach((key, value) -> {
            if (value == 0) {
                data.add(String.format("currently have no %ss.", key.getName()));
            }
        });
        return data;
    }
}
