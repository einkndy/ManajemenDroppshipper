/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.manajemendropshipper.view;

/**
 *
 * @author USER
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Panel Dashboard untuk navigasi utama
public class DashboardPanel extends JPanel {
    public DashboardPanel(ActionListener listener) {
        setLayout(new GridLayout(3, 1)); // Tata letak grid dengan 3 baris

        // Tombol untuk Manajemen Produk
        JButton productButton = new JButton("Manajemen Produk");
        productButton.addActionListener(listener); // Menambahkan listener untuk event klik
        productButton.setActionCommand("PRODUCT"); // Perintah untuk navigasi ke ProductPanel

        // Tombol untuk Manajemen Pemasok
        JButton supplierButton = new JButton("Manajemen Pemasok");
        supplierButton.addActionListener(listener); // Listener untuk navigasi ke SupplierPanel
        supplierButton.setActionCommand("SUPPLIER");

        // Tambahkan komponen ke panel
        add(new JLabel("Selamat Datang di Sistem Manajemen", SwingConstants.CENTER));
        add(productButton);
        add(supplierButton);
    }
}
