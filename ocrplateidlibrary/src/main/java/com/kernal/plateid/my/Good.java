package com.kernal.plateid.my;

public class Good {
    private String name;
    private String warehouse;

    public Good(String name, String warehouse) {
        this.name = name;
        this.warehouse = warehouse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getName() {
        return name;
    }

    public String getWarehouse() {
        return warehouse;
    }
}
