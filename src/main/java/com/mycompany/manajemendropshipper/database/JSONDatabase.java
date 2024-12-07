/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.manajemendropshipper.database;

/**
 *
 * @author USER
 */
import com.mycompany.manajemendropshipper.model.Product;
import com.mycompany.manajemendropshipper.model.Supplier;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Fi le;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

// Kelas untuk mengelola data JSON sebagai database lokal
public class JSONDatabase {
    private final String filePath = "resources/database.json"; // Lokasi file database
    private JSONObject database; // Objek JSON untuk menyimpan data produk dan supplier

    // Konstruktor yang memuat data dari file JSON
    public JSONDatabase() {
        loadData();
    }

    // Method untuk membaca data dari file JSON
    private void loadData() {
        try (FileReader reader = new FileReader(new File(filePath))) {
            StringBuilder content = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                content.append((char) i); // Membaca file JSON karakter demi karakter
            }
            database = new JSONObject(content.toString()); // Menginisialisasi objek JSON
        } catch (Exception e) {
            // Jika file tidak ditemukan, buat struktur JSON baru
            database = new JSONObject();
            database.put("products", new JSONArray());
            database.put("suppliers", new JSONArray());
        }
    }

    // Method untuk menyimpan data kembali ke file JSON
    public void saveData() {
        try (FileWriter writer = new FileWriter(new File(filePath))) {
            writer.write(database.toString(4)); // Menyimpan data dengan format yang rapi
        } catch (Exception e) {
            e.printStackTrace(); // Menangkap error saat penyimpanan
        }
    }

    // Method untuk mengambil daftar produk
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        JSONArray productsArray = database.getJSONArray("products");
        for (int i = 0; i < productsArray.length(); i++) {
            JSONObject obj = productsArray.getJSONObject(i);
            // Membuat objek Product dari data JSON
            products.add(new Product(
                    obj.getInt("id"),
                    obj.getString("name"),
                    obj.getDouble("price"),
                    obj.getInt("stock"),
                    obj.getInt("supplierId")
            ));
        }
        return products;
    }

    // Method untuk mengambil daftar supplier
    public List<Supplier> getSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        JSONArray suppliersArray = database.getJSONArray("suppliers");
        for (int i = 0; i < suppliersArray.length(); i++) {
            JSONObject obj = suppliersArray.getJSONObject(i);
            suppliers.add(new Supplier(
                    obj.getInt("id"),
                    obj.getString("name"),
                    null // Daftar produk tidak langsung di-load
            ));
        }
        return suppliers;
    }

    // Method untuk menambahkan produk ke database
    public void addProduct(Product product) {
        JSONObject obj = new JSONObject();
        obj.put("id", product.getId());
        obj.put("name", product.getName());
        obj.put("price", product.getPrice());
        obj.put("stock", product.getStock());
        obj.put("supplierId", product.getSupplierId());
        database.getJSONArray("products").put(obj); // Menambahkan data ke JSON
        saveData();
    }

    // Method untuk menambahkan supplier ke database
    public void addSupplier(Supplier supplier) {
        JSONObject obj = new JSONObject();
        obj.put("id", supplier.getId());
        obj.put("name", supplier.getName());
        database.getJSONArray("suppliers").put(obj); // Menambahkan data ke JSON
        saveData();
    }
}

