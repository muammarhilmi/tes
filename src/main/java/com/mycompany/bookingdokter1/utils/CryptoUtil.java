/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingdokter1.utils;

/**
 *
 * @author Lenovo
 */

import org.mindrot.jbcrypt.BCrypt;

public class CryptoUtil {
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
    
    public static String encryptData(String data) {
        // Simple encryption for demo
        StringBuilder encrypted = new StringBuilder();
        for (char c : data.toCharArray()) {
            encrypted.append((char) (c + 1));
        }
        return encrypted.toString();
    }
    
    public static String decryptData(String encryptedData) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : encryptedData.toCharArray()) {
            decrypted.append((char) (c - 1));
        }
        return decrypted.toString();
    }
}
