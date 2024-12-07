/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.manajemendropshipper.view;

/**
 *
 * @author USER
 */
import com.mycompany.manajemendropshipper.database.JSONDatabase;
import com.mycompany.manajemendropshipper.model.Product;
import com.mycompany.manajemendropshipper.model.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.List;

// Panel untuk Manajemen Produk
// Panel untuk Manajemen Produk
public class ProductPanel extends JPanel {
    private JSONDatabase database; // Database JSON
    private JTable productTable; // Tabel untuk menampilkan produk
    private DefaultTableModel tableModel; // Model data tabel

    public ProductPanel(JSONDatabase database, boolean isSupplier, ActionListener backListener) {
        this.database = database;
        setLayout(new BorderLayout());

        // Header dengan tombol kembali
        JPanel headerPanel = new JPanel(new FlowLayout());
        JButton backButton = new JButton("Kembali");
        backButton.addActionListener(backListener);
        backButton.setActionCommand("BACK");
        headerPanel.add(backButton);
        add(headerPanel, BorderLayout.NORTH);

        // Tabel untuk menampilkan data produk
        String[] columnNames = {"ID", "Nama", "Harga", "Stok", "Supplier ID"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        loadProducts(); // Memuat data produk ke tabel

        add(new JScrollPane(productTable), BorderLayout.CENTER);

        // Panel tombol untuk Supplier
        if (isSupplier) {
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JButton addButton = new JButton("Tambah Produk");
            JButton editButton = new JButton("Edit Produk");
            JButton deleteButton = new JButton("Hapus Produk");

            addButton.addActionListener(e -> addProduct());
            editButton.addActionListener(e -> editProduct());
            deleteButton.addActionListener(e -> deleteProduct());

            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(deleteButton);
            add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    private void loadProducts() {
        List<Product> products = database.getProducts(); // Ambil daftar produk
        tableModel.setRowCount(0); // Hapus data sebelumnya
        for (Product product : products) {
            tableModel.addRow(new Object[]{
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock(),
                    product.getSupplierId()
            });
        }
    }

    private void addProduct() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Masukkan ID Produk:"));
            String name = JOptionPane.showInputDialog(this, "Masukkan Nama Produk:");
            double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Masukkan Harga Produk:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog(this, "Masukkan Stok Produk:"));
            int supplierId = Integer.parseInt(JOptionPane.showInputDialog(this, "Masukkan ID Supplier:"));

            Product product = new Product(id, name, price, stock, supplierId);
            database.addProduct(product); // Tambahkan produk ke database
            loadProducts(); // Refresh data tabel
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid. Silakan coba lagi.");
        }
    }

    private void editProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                String name = JOptionPane.showInputDialog(this, "Masukkan Nama Baru:", tableModel.getValueAt(selectedRow, 1));
                double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Masukkan Harga Baru:", tableModel.getValueAt(selectedRow, 2)));
                int stock = Integer.parseInt(JOptionPane.showInputDialog(this, "Masukkan Stok Baru:", tableModel.getValueAt(selectedRow, 3)));

                Product product = new Product(id, name, price, stock, (int) tableModel.getValueAt(selectedRow, 4));
                database.addProduct(product); // Perbarui produk di database
                loadProducts();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Input tidak valid. Silakan coba lagi.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih produk yang ingin diedit.");
        }
    }

    private void deleteProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus produk ini?");
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                database.getProducts().removeIf(product -> product.getId() == id); // Hapus produk dari database
                database.saveData(); // Simpan perubahan
                loadProducts();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih produk yang ingin dihapus.");
        }
    }

} // <-- Pastikan ada penutupan kurung di sini
