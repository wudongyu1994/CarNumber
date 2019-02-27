package com.kernal.plateid.my;

import com.kernal.plateid.objects.Project;
import com.kernal.plateid.objects.Staff;
import com.kernal.plateid.objects.Truck;
import com.kernal.plateid.objects.Warehouse;

import java.util.ArrayList;

public final class MyData {
    private static ArrayList<Good> goodsOut;
    private static ArrayList<Good> goodsDetail=new ArrayList<>();

    private static ArrayList<Good> goodsInstore;

    private static ArrayList<Project> projects;
    private static ArrayList<Warehouse> warehouses;
    private static ArrayList<Truck> trucks;
    private static ArrayList<Staff> staff;
    public static boolean hasTruck(String s){
        for(Truck truck:trucks){
            if(truck.getName().equals(s)){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Good> getGoodsDetail() {
//        if(goodsDetail.isEmpty() )
//            return null;
        return goodsDetail;
    }

    public static void addGoodsDetail(Good good){
        goodsDetail.add(good);
    }

    public static ArrayList<Good> getGoodsInStore() {
        goodsInstore =new ArrayList<>();
        goodsInstore.add(new Good("aaa","Ware1"));
        goodsInstore.add(new Good("bbb","Ware2"));
        goodsInstore.add(new Good("ccc","Ware3"));
        goodsInstore.add(new Good("ddd","Ware4"));
        goodsInstore.add(new Good("eee","Ware5"));
        goodsInstore.add(new Good("fff","Ware6"));
        goodsInstore.add(new Good("ggg","Ware7"));
        goodsInstore.add(new Good("hhh","Ware8"));
        goodsInstore.add(new Good("iii","Ware9"));
        goodsInstore.add(new Good("jjj","Ware10"));
        goodsInstore.add(new Good("kkk","Ware11"));
        goodsInstore.add(new Good("lll","Ware12"));
        goodsInstore.add(new Good("mmm","Ware13"));
        goodsInstore.add(new Good("nnn","Ware14"));
        goodsInstore.add(new Good("ooo","Ware15"));
        return goodsInstore;
    }

    public static ArrayList<Good> getGoodsOut() {
        return goodsOut;
    }

    public static void setGoodsOut(ArrayList<Good> goodsOut) {
        MyData.goodsOut = goodsOut;
    }

    public static ArrayList<Project> getProjects() {
        projects=new ArrayList<>();
        projects.add(new Project("SC1","project 1",1));
        projects.add(new Project("QY6","project 2",2));
        projects.add(new Project("DJ4","project 3",3));
        return projects;
    }

    public static ArrayList<Warehouse> getWarehouses() {
        warehouses=new ArrayList<>();
        warehouses.add(new Warehouse("浙江杭州","Ware 1",11));
        warehouses.add(new Warehouse("上海","Ware 2",22));
        warehouses.add(new Warehouse("江苏南京","Ware 3",333));
        warehouses.add(new Warehouse("江苏南京","Ware 3",334));
        warehouses.add(new Warehouse("江苏南京","Ware 3",335));
        warehouses.add(new Warehouse("江苏南京","Ware 3",336));
        warehouses.add(new Warehouse("江苏南京","Ware 3",337));
        warehouses.add(new Warehouse("江苏南京","Ware 3",338));
        warehouses.add(new Warehouse("江苏南京","Ware 3",339));
        warehouses.add(new Warehouse("江苏南京","Ware 3",340));
        warehouses.add(new Warehouse("江苏南京","Ware 3",341));
        warehouses.add(new Warehouse("江苏南京","Ware 3",342));
        warehouses.add(new Warehouse("江苏南京","Ware 3",343));
        warehouses.add(new Warehouse("江苏南京","Ware 3",344));
        warehouses.add(new Warehouse("江苏南京","Ware 3",345));
        warehouses.add(new Warehouse("江苏南京","Ware 3",346));
        warehouses.add(new Warehouse("江苏南京","Ware 3",347));
        warehouses.add(new Warehouse("江苏南京","Ware 3",348));
        warehouses.add(new Warehouse("江苏南京","Ware 3",349));
        warehouses.add(new Warehouse("江苏南京","Ware 3",350));
        warehouses.add(new Warehouse("江苏南京","Ware 3",351));
        warehouses.add(new Warehouse("江苏南京","Ware 3",352));
        warehouses.add(new Warehouse("江苏南京","Ware 3",353));
        warehouses.add(new Warehouse("江苏南京","Ware 3",354));
        warehouses.add(new Warehouse("江苏南京","Ware 3",355));
        warehouses.add(new Warehouse("江苏南京","Ware 3",356));
        return warehouses;
    }

    public static ArrayList<Truck> getTrucks() {
        trucks=new ArrayList<>();
        trucks.add(new Truck("浙A12345","truck 1",111));
        trucks.add(new Truck("川A43245","truck 2",222));
        trucks.add(new Truck("琼B12345","truck 3",333));
        return trucks;
    }

    public static ArrayList<Staff> getStaff() {
        staff =new ArrayList<>();
        staff.add(new Staff("admin1","John",1111));
        staff.add(new Staff("admin2","Tom",2222));
        staff.add(new Staff("superadmin","Mark",3333));
        return staff;
    }
}
