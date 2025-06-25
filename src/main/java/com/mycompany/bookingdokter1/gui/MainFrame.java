/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingdokter1.gui;

/**
 *
 * @author Lenovo
 */

import com.mycompany.bookingdokter1.model.Booking;
import com.mycompany.bookingdokter1.service.BookingService;
import com.mycompany.bookingdokter1.utils.SerializationUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ResourceBundle;

public class MainFrame extends JFrame {
    private JTable bookingTable;
    private DefaultTableModel tableModel;
    private BookingService<Booking> bookingService;
    private ResourceBundle messages;
    
    public MainFrame() {
        messages = ResourceBundle.getBundle("messages");
        bookingService = new BookingService<>();
        
        initComponents();
        setupLayout();
        loadBookings();
    }
    
    private void initComponents() {
        setTitle(messages.getString("main.title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        String[] columns = {"ID", "Nama", "No. HP", "Keluhan", "Dokter", "Waktu", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        bookingTable = new JTable(tableModel);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newBookingItem = new JMenuItem("New Booking");
        JMenuItem exportItem = new JMenuItem("Export");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        newBookingItem.addActionListener(e -> openBookingDialog());
        exportItem.addActionListener(e -> exportBookings());
        exitItem.addActionListener(e -> System.exit(0));
        
        fileMenu.add(newBookingItem);
        fileMenu.add(exportItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        // Table
        add(new JScrollPane(bookingTable), BorderLayout.CENTER);
        
        // Status bar
        JLabel statusLabel = new JLabel("Ready");
        add(statusLabel, BorderLayout.SOUTH);
    }
    
    private void openBookingDialog() {
        new BookingFrame(this).setVisible(true);
    }
    
    private void exportBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        SerializationUtil.serializeToJson(bookings, "bookings.json");
        SerializationUtil.serializeToBinary(bookings, "bookings.ser");
        JOptionPane.showMessageDialog(this, "Bookings exported successfully!");
    }
    
    public void loadBookings() {
        tableModel.setRowCount(0);
        List<Booking> bookings = bookingService.getAllBookings();
        
        for (Booking booking : bookings) {
            Object[] row = {
                booking.getId(),
                booking.getNama(),
                booking.getNomorHp(),
                booking.getKeluhan(),
                booking.getDokter(),
                booking.getWaktuBooking(),
                booking.getStatus()
            };
            tableModel.addRow(row);
        }
    }
}
