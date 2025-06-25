/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingdokter1;
import com.mycompany.bookingdokter1.gui.LoginFrame2;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Lenovo
 */


public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new LoginFrame2().setVisible(true);
        });
    }
}

 