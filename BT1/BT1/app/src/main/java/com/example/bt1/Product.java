package com.example.bt1;

public class Product {

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Product(int id, String name, String email, String address) {
        Id = id;
        Name = name;
        Email = email;
        Address = address;
    }
    public Product() {
        this.Id = 0;
        this.Name = null;
        this.Address = null;
        this.Email = null;
    }
    public int Id;
    public String Name;
    public String Email;
    public String Address;

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }
}
