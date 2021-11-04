package com.examle.server;

import java.io.Serializable;

public class Car implements Serializable {
    private int Id;
    private String Brand;
    private String Model;
    private int Power;
    private int Year;
    private double Mileage;
    private String Color;

    public Car(String brand, String model, int power, double mileage, int year, String color) {
        Brand = brand;
        Model = model;
        Power = power;
        Year = year;
        Mileage = mileage;
        Color = color;
    }

    public Car(int id, String brand, String model, int power, double mileage, int year, String color) {
        Id = id;
        Brand = brand;
        Model = model;
        Power = power;
        Year = year;
        Mileage = mileage;
        Color = color;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setModel(String model) {
        Model = model;
    }

    public void setPower(int power) {
        Power = power;
    }

    public void setYear(int year) {
        Year = year;
    }

    public void setMileage(double mileage) {
        Mileage = mileage;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getId() {
        return Id;
    }

    public String getBrand() {
        return Brand;
    }

    public String getModel() {
        return Model;
    }

    public int getPower() {
        return Power;
    }

    public int getYear() {
        return Year;
    }

    public double getMileage() {
        return Mileage;
    }

    public String getColor() {
        return Color;
    }

    public String toString() {
        return Id+" "+Brand+" "+Model+" "+Power+" "+Year+" "+Mileage+" "+Color;
    }
}