package com.example.hibernate;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int power;
    private double mileage;
    private int year;

    public Car(int id, String brand, String model, int power, double mileage, int year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.mileage = mileage;
        this.year = year;
    }

    public Car(String brand, String model, int power, double mileage, int year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.mileage = mileage;
        this.year = year;
    }

    public Car(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString(){
        return id+" "+brand+" "+model+" "+power+" "+mileage+" "+year;
    }
}

