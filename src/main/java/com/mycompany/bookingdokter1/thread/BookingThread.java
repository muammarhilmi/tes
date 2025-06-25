/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingdokter1.thread;

/**
 *
 * @author Lenovo
 */

import com.mycompany.bookingdokter1.model.Booking;
import com.mycompany.bookingdokter1.service.BookingService;

import javax.swing.SwingUtilities;
import javax.swing.JLabel;

public class BookingThread extends Thread {
    private Booking booking;
    private BookingService<Booking> service;
    private JLabel statusLabel;
    
    public BookingThread(Booking booking, BookingService<Booking> service, JLabel statusLabel) {
        this.booking = booking;
        this.service = service;
        this.statusLabel = statusLabel;
    }
    
    @Override
    public void run() {
        try {
            SwingUtilities.invokeLater(() -> 
                statusLabel.setText("Menyimpan booking..."));
            
            // Simulate processing time
            Thread.sleep(2000);
            
            service.saveBooking(booking);
            
            SwingUtilities.invokeLater(() -> 
                statusLabel.setText("Booking berhasil disimpan!"));
            
            Thread.sleep(2000);
            
            SwingUtilities.invokeLater(() -> 
                statusLabel.setText("Siap"));
                
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> 
                statusLabel.setText("Error: " + e.getMessage()));
        }
    }
}
