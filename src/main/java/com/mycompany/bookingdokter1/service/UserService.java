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
import com.mycompany.bookingdokter1.model.User;
import com.mycompany.bookingdokter1.utils.CryptoUtil;
import org.bson.Document;

public class UserService {
    private MongoCollection<Document> collection;
    
    public UserService() {
        this.collection = DatabaseManager.getInstance()
                .getDatabase().getCollection("users");
        
        // Create default admin user if not exists
        createDefaultUser();
    }
    
    private void createDefaultUser() {
        if (collection.countDocuments(Filters.eq("username", "admin")) == 0) {
            Document adminUser = new Document("username", "admin")
                    .append("password", CryptoUtil.hashPassword("admin"))
                    .append("role", "admin");
            collection.insertOne(adminUser);
        }
    }
    
    public boolean authenticate(String username, String password) {
        Document user = collection.find(Filters.eq("username", username)).first();
        if (user != null) {
            String hashedPassword = user.getString("password");
            return CryptoUtil.checkPassword(password, hashedPassword);
        }
        return false;
    }
    
    public void createUser(String username, String password, String role) {
        Document user = new Document("username", username)
                .append("password", CryptoUtil.hashPassword(password))
                .append("role", role);
        collection.insertOne(user);
    }
}
