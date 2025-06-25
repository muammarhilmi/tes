/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingdokter1.utils;

/**
 *
 * @author Lenovo
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.bookingdokter1.model.Booking;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SerializationUtil {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();
    
    public static void serializeToJson(List<Booking> bookings, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(bookings, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void serializeToBinary(List<Booking> bookings, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(bookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Booking> deserializeFromBinary(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            return (List<Booking>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // LocalDateTime adapter for Gson
    private static class LocalDateTimeAdapter implements 
            com.google.gson.JsonSerializer<LocalDateTime>,
            com.google.gson.JsonDeserializer<LocalDateTime> {
        
        private static final DateTimeFormatter formatter = 
                DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        
        @Override
        public com.google.gson.JsonElement serialize(LocalDateTime src, 
                java.lang.reflect.Type typeOfSrc, 
                com.google.gson.JsonSerializationContext context) {
            return new com.google.gson.JsonPrimitive(formatter.format(src));
        }
        
        @Override
        public LocalDateTime deserialize(com.google.gson.JsonElement json, 
                java.lang.reflect.Type typeOfT, 
                com.google.gson.JsonDeserializationContext context) {
            return LocalDateTime.parse(json.getAsString(), formatter);
        }
    }
}
