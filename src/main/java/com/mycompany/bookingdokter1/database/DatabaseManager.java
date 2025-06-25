/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingdokter1.database;

/**
 *
 * @author Lenovo
 */

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseManager {
    private static DatabaseManager instance;
    private MongoClient mongoClient;
    private MongoDatabase database;
    
    private DatabaseManager() {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("MONGODB_URI");
        String dbName = dotenv.get("DATABASE_NAME");
        
        mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase(dbName);
    }
    
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    public MongoDatabase getDatabase() { return database; }
    
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
