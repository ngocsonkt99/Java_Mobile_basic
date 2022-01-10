package com.example.btvn.models;

import java.util.ArrayList;


public class SaleManager {
    private ArrayList<Product> Products;

    public SaleManager() {
        Products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return Products;
    }

    public void setProducts(ArrayList<Product> products) {
        Products = products;
    }


    private static SaleManager saleManager;

    public static SaleManager get() {
        if (saleManager == null)
            saleManager = new SaleManager();
        return saleManager;
    }
}
