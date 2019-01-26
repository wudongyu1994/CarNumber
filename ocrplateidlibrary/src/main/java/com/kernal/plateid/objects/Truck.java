package com.kernal.plateid.objects;

public class Truck extends SCItem {
    private String carNumber,name;
    private int id;

    public Truck(String name) {
        this.name = name;
    }

    public Truck(String carNumber, String name) {
        this.carNumber = carNumber;
        this.name = name;
    }

    public Truck(String carNumber, String name, int id) {
        this.carNumber = carNumber;
        this.name = name;
        this.id = id;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public int getId() {
        return id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
