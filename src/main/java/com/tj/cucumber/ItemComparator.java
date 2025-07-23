package com.tj.cucumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ItemComparator {
    private static final Logger logger = LoggerFactory.getLogger(ItemComparator.class);

    public static boolean compareLists(List<Item> list1, List<Item> list2) {
        boolean equal = true;
        Map<String, Item> map1 = buildMap(list1);
        Map<String, Item> map2 = buildMap(list2);

        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                logger.error("Missing item in second list: {}", map1.get(key));
                equal = false;
            } else {
                Item item1 = map1.get(key);
                Item item2 = map2.get(key);

                if (!item1.equals(item2)) {
                    logger.error("Mismatch for item '{}':", key);
                    if (item1.getPrice() != item2.getPrice())
                        logger.error("  price differs ({} vs {})", item1.getPrice(), item2.getPrice());
                    if (!item1.getCategory().equals(item2.getCategory()))
                        logger.error("  category differs ('{}' vs '{}')", item1.getCategory(), item2.getCategory());
                    equal = false;
                }
            }
        }

        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                logger.error("Extra item in second list: {}", map2.get(key));
                equal = false;
            }
        }

        return equal;
    }

    private static Map<String, Item> buildMap(List<Item> items) {
        Map<String, Item> map = new HashMap<>();
        for (Item item : items) {
            map.put(item.getName(), item);
        }
        return map;
    }
}
