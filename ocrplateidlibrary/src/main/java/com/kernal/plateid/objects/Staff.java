package com.kernal.plateid.objects;

public class Staff extends SCItem{
    private String name;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    private String userName;
    private int id;

    public Staff( String userName,String name, int id) {
        this.name = name;
        this.userName = userName;
        this.id = id;
    }

    public Staff(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Staff(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
