/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.manajemendropshipper.model;

/**
 *
 * @author USER
 */
// Kelas untuk merepresentasikan produk dalam sistem
public class Product {
    private int id; // ID unik untuk produk
    private String name; // Nama produk
    private double price; // Harga produk
    private int stock; // Jumlah stok
    private int supplierId; // ID dari supplier yang memasok produk

    // Konstruktor untuk menginisialisasi produk
    public Product(int id, String name, double price, int stock, int supplierId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.supplierId = supplierId;
    }

    // Getter dan Setter untuk mengakses atau mengubah atribut
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}

