/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookingdokter1.model;

/**
 *
 * @author Lenovo
 */

import java.io.Serializable;
import java.time.LocalDateTime;

public class Booking implements Serializable {
    private String id;
    private String nama;
    private String nomorHp;
    private String keluhan;
    private String dokter;
    private LocalDateTime waktuBooking;
    private String status;
    
    // Constructor
    public Booking() {}
    
    public Booking(String nama, String nomorHp, String keluhan, String dokter, LocalDateTime waktuBooking) {
        this.nama = nama;
        this.nomorHp = nomorHp;
        this.keluhan = keluhan;
        this.dokter = dokter;
        this.waktuBooking = waktuBooking;
        this.status = "Menunggu";
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public String getNomorHp() { return nomorHp; }
    public void setNomorHp(String nomorHp) { this.nomorHp = nomorHp; }
    
    public String getKeluhan() { return keluhan; }
    public void setKeluhan(String keluhan) { this.keluhan = keluhan; }
    
    public String getDokter() { return dokter; }
    public void setDokter(String dokter) { this.dokter = dokter; }
    
    public LocalDateTime getWaktuBooking() { return waktuBooking; }
    public void setWaktuBooking(LocalDateTime waktuBooking) { this.waktuBooking = waktuBooking; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
