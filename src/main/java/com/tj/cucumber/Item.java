package com.tj.cucumber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String name;
    private double price;
    private String category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Double.compare(item.price, price) == 0 &&
                name.equals(item.name) &&
                category.equals(item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, category);
    }

    @Override
    public String toString() {
        return String.format("Item{name='%s', price=%.2f, category='%s'}", name, price, category);
    }
}