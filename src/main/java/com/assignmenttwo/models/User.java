package com.assignmenttwo.models;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String name;
    private String password;
    private int size;
    private int height;
    private String hairColor;
    private double gpa;

    
    public User(){
    }
    
    
    public User(String name, String password, int size, int height, String hairColor, double gpa) {
        this.name = name;
        this.password = password;
        this.size = size;
        this.height = height;
        this.hairColor = hairColor;
        this.gpa = gpa;
    }

    // Getters and setters
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public String getHairColor() {
        return hairColor;
    }
    
    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    public int getUid() {
        return uid;
    }
    
    public void setUid(int uid) {
        this.uid = uid;
    }
}
