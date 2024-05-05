package com.rmnk12k.utill;

import java.util.HashMap;

public enum ProductType {
    FLUIDS("Технические жидкости"),
    WHEELS("Шины и диски"),
    UNKNOWN;

    private final static HashMap<String, ProductType> nameToType = new HashMap<>();
    private String name;

    static {
        for (ProductType type : ProductType.values()) {
            if (type.name != null) {
                nameToType.put(type.name, type);
            }
        }
    }

    ProductType() {

    }

    ProductType(String name) {
        this.name = name;
    }

    public static ProductType getType(String name) {
        return nameToType.getOrDefault(name, ProductType.UNKNOWN);
    }
}
