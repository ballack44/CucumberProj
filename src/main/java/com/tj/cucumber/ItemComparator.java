package com.tj.cucumber;

import java.util.*;

public class ItemComparator {

    public static String compareLists(List<Item> list1, List<Item> list2) {
        Map<String, Item> map1 = buildMap(list1);
        Map<String, Item> map2 = buildMap(list2);
        StringBuilder logBuilder = new StringBuilder();

        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                String msg = "Missing item in second list: " + map1.get(key);
                logBuilder.append(msg).append("\n");
            } else {
                Item item1 = map1.get(key);
                Item item2 = map2.get(key);

                if (!item1.equals(item2)) {
                    logBuilder.append("Mismatch for item '").append(key).append("':\n");
                    if (item1.getPrice() != item2.getPrice()) {
                        String msg = String.format("  price differs (%s vs %s)", item1.getPrice(), item2.getPrice());
                        logBuilder.append(msg).append("\n");
                    }
                    if (!item1.getCategory().equals(item2.getCategory())) {
                        String msg = String.format("  category differs ('%s' vs '%s')", item1.getCategory(), item2.getCategory());
                        logBuilder.append(msg).append("\n");
                    }
                }
            }
        }

        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                String msg = "Extra item in second list: " + map2.get(key);
                logBuilder.append(msg).append("\n");
            }
        }

        return logBuilder.toString();
    }

    private static Map<String, Item> buildMap(List<Item> items) {
        Map<String, Item> map = new HashMap<>();
        for (Item item : items) {
            map.put(item.getName(), item);
        }
        return map;
    }
}
