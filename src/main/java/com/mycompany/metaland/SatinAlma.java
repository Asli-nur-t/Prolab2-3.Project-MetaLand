/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metaland;

/**
 *
 * @author aslinurtopcu
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SatinAlma {
    private String kullaniciAdi;
    private double urunFiyati;
    
    public SatinAlma(String kullaniciAdi, double urunFiyati) {
        this.kullaniciAdi = kullaniciAdi;
        this.urunFiyati = urunFiyati;
    }
    
    public void satinAl() {
        // Veritabanına bağlanma işlemleri
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metaland?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "Qwertyu@123");
            
            // Kullanıcının mevcut para miktarını sorgulama
            double kullaniciPara = kullaniciParaAl(connection);
            
            if(HangiIsletme.secim==4){
                kullaniciYemekMiktariArtir( connection,kullaniciAdi);
            }
            if(HangiIsletme.secim==5){
                kullaniciEsyaMiktariArtir( connection,kullaniciAdi);
            }
            
            
            // Eğer kullanıcının yeterli parası varsa ürünü satın alma işlemi
            if (kullaniciPara >= urunFiyati) {
                kullaniciPara -= urunFiyati;
                kullaniciParaGuncelle(connection, kullaniciPara);
                System.out.println("Ürün satın alındı. Yeni bakiye: " + kullaniciPara);
             
            } else {
                System.out.println("Yetersiz bakiye. Ürün satın alınamadı.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Kapatma işlemleri
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private double kullaniciParaAl(Connection connection) throws SQLException {
        // Kullanıcının para miktarını veritabanından sorgulama işlemi
        double kullaniciPara = 0;
        PreparedStatement statement = null;
        
        try {
            String query = "SELECT kullanici_para_miktari FROM oyuncular WHERE kullanici_takma_adi = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, kullaniciAdi);
            
            // Sorguyu çalıştırma
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                kullaniciPara = resultSet.getDouble("kullanici_para_miktari");
            }
        } finally {
            // Kapatma işlemleri
            if (statement != null) {
                statement.close();
            }
        }
        
        return kullaniciPara;
    }
    
    private void kullaniciParaGuncelle(Connection connection, double yeniPara) throws SQLException {
        // Kullanıcının para miktarını güncelleme işlemi
        PreparedStatement statement = null;
        
        try {
            String query = "UPDATE oyuncular SET kullanici_para_miktari = ? WHERE kullanici_takma_adi = ?";
            statement = connection.prepareStatement(query);
            statement.setDouble(1, yeniPara);
            statement.setString(2, kullaniciAdi);
            
            // Sorguyu çalıştırma
            statement.executeUpdate();
        } finally {
            // Kapatma işlemleri
            if (statement != null) {
                statement.close();
            }
        }
    }
    
       private void kullaniciYemekMiktariArtir(Connection connection, String kullaniciAdi) throws SQLException {
    // Kullanıcının yemek miktarını güncelleme işlemi
    PreparedStatement statement = null;
    
    try {
        String query = "UPDATE oyuncular SET kullanici_yemek_miktari = kullanici_yemek_miktari + 100 WHERE kullanici_takma_adi = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, kullaniciAdi);
        
        // Sorguyu çalıştırma
        statement.executeUpdate();
    } finally {
        // Kapatma işlemleri
        if (statement != null) {
            statement.close();
        }
    }
}
       
       private void kullaniciEsyaMiktariArtir(Connection connection, String kullaniciAdi) throws SQLException {
    // Kullanıcının eşya miktarını güncelleme işlemi
    PreparedStatement statement = null;
    
    try {
        String query = "UPDATE oyuncular SET kullanici_esya_miktari = kullanici_esya_miktari + 50 WHERE kullanici_takma_adi = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, kullaniciAdi);
        
        // Sorguyu çalıştırma
        statement.executeUpdate();
    } finally {
        // Kapatma işlemleri
        if (statement != null) {
            statement.close();
        }
    }
}

    
    
}

