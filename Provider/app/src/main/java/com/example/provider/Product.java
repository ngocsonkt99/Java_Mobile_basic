package com.example.provider;

public class Product {

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMadein() {
        return Madein;
    }

    public void setMadein(String madein) {
        Madein = madein;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(boolean unit) {
        Unit = unit;
    }

    public Product(int id, String name, String madein, String unit) {
        Id = id;
        Name = name;
        Unit = unit;
        Madein = madein;
    }
    public Product() {
        this.Id = 0;
        this.Name = null;
        this.Unit = null;
        this.Madein = null;
    }
    public int Id;
    public String Name;
    public String Unit;
    public String Madein;

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Unit='" + Unit + '\'' +
                ", Madein='" + Madein + '\'' +
                '}';
    }
}
