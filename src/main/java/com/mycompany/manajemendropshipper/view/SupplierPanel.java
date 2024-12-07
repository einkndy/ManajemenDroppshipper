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
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.List;

public class SupplierPanel extends JPanel {
    private JSONDatabase database;
    private JTable supplierTable;
    private DefaultTableModel tableModel;

    public SupplierPanel(JSONDatabase database, ActionListener backListener) {
        this.database = database;
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new FlowLayout());
        JButton backButton = new JButton("Kembali");
        backButton.addActionListener(backListener);
        backButton.setActionCommand("BACK");
        headerPanel.add(backButton);
        add(headerPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Nama"};
        tableModel = new DefaultTableModel(columnNames, 0);
        supplierTable = new JTable(tableModel);
        loadSuppliers();

        add(new JScrollPane(supplierTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Tambah Supplier");
        addButton.addActionListener(e -> addSupplier());
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadSuppliers() {
        List<Supplier> suppliers = database.getSuppliers();
        tableModel.setRowCount(0);
        for (Supplier supplier : suppliers) {
            tableModel.addRow(new Object[]{supplier.getId(), supplier.getName()});
        }
    }

    private void addSupplier() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Masukkan ID Supplier:"));
            String name = JOptionPane.showInputDialog(this, "Masukkan Nama Supplier:");

            Supplier supplier = new Supplier(id, name, null);
            database.addSupplier(supplier);
            loadSuppliers();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid. Silakan coba lagi.");
        }
    }
}
