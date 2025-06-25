/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bookingdokter1;

import com.mycompany.bookingdokter1.gui.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Lenovo
 */
public class Bookingdokter1 {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        LoginFrame.main(args);
        
        SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }
}
