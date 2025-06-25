/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingdokter1.gui;

/**
 *
 * @author Lenovo
 */


import com.mycompany.bookingdokter1.utils.CryptoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private ResourceBundle messages;
    
    public LoginFrame() {
        // Internationalization
        Locale locale = new Locale("id", "ID"); // Default Indonesian
//        messages = ResourceBundle.getBundle("messages", locale);
        messages = ResourceBundle.getBundle("i18n.messages", locale);

        
        initComponents();
        setupLayout();
    }
    
    private void initComponents() {
        setTitle(messages.getString("login.title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
    }
    
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Title
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel(messages.getString("app.title"));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, gbc);
        
        // Username
        gbc.gridwidth = 1; gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel(messages.getString("login.username")), gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);
        
        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel(messages.getString("login.password")), gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton(messages.getString("login.button"));
        JButton langButton = new JButton("EN/ID");
        
        loginButton.addActionListener(this::loginAction);
        langButton.addActionListener(this::changeLangAction);
        
        buttonPanel.add(loginButton);
        buttonPanel.add(langButton);
        add(buttonPanel, gbc);
    }
    
    private void loginAction(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        // Simple validation (demo purposes)
        if ("admin".equals(username) && "admin".equals(password)) {
            dispose();
            new MainFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, 
                messages.getString("login.error"));
        }
    }
    
    private void changeLangAction(ActionEvent e) {
        Locale currentLocale = messages.getLocale();
        Locale newLocale = currentLocale.getLanguage().equals("id") ? 
            Locale.ENGLISH : new Locale("id", "ID");
        
        messages = ResourceBundle.getBundle("messages", newLocale);
        refreshUI();
    }
    
    private void refreshUI() {
        dispose();
        new LoginFrame().setVisible(true);
    }
}
