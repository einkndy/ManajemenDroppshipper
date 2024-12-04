/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.manajemendropshipper.model;

/**
 *
 * @author USER
 */
import java.util.List;

// Kelas untuk merepresentasikan supplier dalam sistem
public class Supplier {
    private int id; // ID unik untuk supplier
    private String name; // Nama supplier
    private List<Integer> productIds; // Daftar ID produk yang disuplai oleh supplier

    // Konstruktor untuk menginisialisasi supplier
    public Supplier(int id, String name, List<Integer> productIds) {
        this.id = id;
        this.name = name;
        this.productIds = productIds;
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

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }
}
