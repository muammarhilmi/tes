package com.mycompany.bookingdokter1.gui;

import com.mycompany.bookingdokter1.model.Booking;
import com.mycompany.bookingdokter1.service.BookingService;
import com.mycompany.bookingdokter1.thread.BookingThread;
import com.mycompany.bookingdokter1.utils.NetworkUtil;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class BookingFrame extends JDialog {  // Changed from JFrame to JDialog
    private JTextField namaField, nomorHpField, keluhanField, dokterField;
    private JSpinner waktuSpinner;
    private JLabel statusLabel;
    private MainFrame parent;
    private BookingService<Booking> bookingService;
    
    public BookingFrame(MainFrame parent) {
        super(parent, "New Booking", true);  // Modal dialog
        this.parent = parent;
        this.bookingService = new BookingService<>();
        
        initComponents();
        setupLayout();
    }
    
    private void initComponents() {
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        namaField = new JTextField(20);
        nomorHpField = new JTextField(20);
        keluhanField = new JTextField(20);
        dokterField = new JTextField(20);
        
        // DateTime spinner
        waktuSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(waktuSpinner, "dd/MM/yyyy HH:mm");
        waktuSpinner.setEditor(editor);
        
        statusLabel = new JLabel("Ready");
    }
    
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Form fields
        addField("Nama:", namaField, gbc, 0);
        addField("Nomor HP:", nomorHpField, gbc, 1);
        addField("Keluhan:", keluhanField, gbc, 2);
        addField("Dokter:", dokterField, gbc, 3);
        addField("Waktu:", waktuSpinner, gbc, 4);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        JButton checkNetworkButton = new JButton("Check Network");
        
        saveButton.addActionListener(e -> saveBooking());
        cancelButton.addActionListener(e -> dispose());
        checkNetworkButton.addActionListener(e -> checkNetwork());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(checkNetworkButton);
        add(buttonPanel, gbc);
        
        // Status
        gbc.gridy = 6;
        add(statusLabel, gbc);
    }
    
    private void addField(String label, JComponent field, GridBagConstraints gbc, int row) {
        gbc.gridwidth = 1; gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        add(field, gbc);
    }
    
    private void saveBooking() {
        try {
            // Validation
            if (namaField.getText().trim().isEmpty() ||
                nomorHpField.getText().trim().isEmpty() ||
                keluhanField.getText().trim().isEmpty() ||
                dokterField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                return;
            }
            
            String nama = namaField.getText().trim();
            String nomorHp = nomorHpField.getText().trim();
            String keluhan = keluhanField.getText().trim();
            String dokter = dokterField.getText().trim();
            
            java.util.Date date = (java.util.Date) waktuSpinner.getValue();
            LocalDateTime waktu = LocalDateTime.ofInstant(
                date.toInstant(), java.time.ZoneId.systemDefault());
            
            Booking booking = new Booking(nama, nomorHp, keluhan, dokter, waktu);
            
            // Use thread for saving
            BookingThread bookingThread = new BookingThread(booking, bookingService, statusLabel);
            bookingThread.start();
            
            // Wait for thread to complete then refresh parent
            new Thread(() -> {
                try {
                    bookingThread.join();
                    SwingUtilities.invokeLater(() -> {
                        parent.loadBookings();
                        dispose();
                    });
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void checkNetwork() {
        // Network utility demonstration
        boolean connected = NetworkUtil.isInternetAvailable();
        statusLabel.setText(connected ? "Network: Connected" : "Network: Disconnected");
    }
    
    // Optional: Add sound notification when booking is saved
    private void playNotificationSound() {
        try {
            // Simple beep sound using system default
            Toolkit.getDefaultToolkit().beep();
        } catch (Exception e) {
            System.out.println("Could not play sound: " + e.getMessage());
        }
    }
}