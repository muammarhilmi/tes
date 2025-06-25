/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingdokter1.service;

/**
 *
 * @author Lenovo
 */

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mycompany.bookingdokter1.database.DatabaseManager;
import com.mycompany.bookingdokter1.model.Booking;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingService<T extends Booking> {
    private MongoCollection<Document> collection;
    
    public BookingService() {
        this.collection = DatabaseManager.getInstance()
                .getDatabase().getCollection("bookings");
    }
    
    public void saveBooking(T booking) {
        Document doc = new Document("nama", booking.getNama())
                .append("nomorHp", booking.getNomorHp())
                .append("keluhan", booking.getKeluhan())
                .append("dokter", booking.getDokter())
                .append("waktuBooking", Date.from(booking.getWaktuBooking()
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .append("status", booking.getStatus());
        
        collection.insertOne(doc);
    }
    
    public List<T> getAllBookings() {
        List<T> bookings = new ArrayList<>();
        for (Document doc : collection.find()) {
            Booking booking = new Booking();
            booking.setId(doc.getObjectId("_id").toString());
            booking.setNama(doc.getString("nama"));
            booking.setNomorHp(doc.getString("nomorHp"));
            booking.setKeluhan(doc.getString("keluhan"));
            booking.setDokter(doc.getString("dokter"));
            
            Date date = doc.getDate("waktuBooking");
            if (date != null) {
                booking.setWaktuBooking(LocalDateTime.ofInstant(
                    date.toInstant(), ZoneId.systemDefault()));
            }
            booking.setStatus(doc.getString("status"));
            bookings.add((T) booking);
        }
        return bookings;
    }
    
    public void updateBookingStatus(String id, String status) {
        collection.updateOne(
            Filters.eq("_id", new ObjectId(id)),
            new Document("$set", new Document("status", status))
        );
    }
}
