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

public class ParaKazanma {
    private String kullaniciAdi;
    
    public ParaKazanma(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }
    
    public void isYerindeCalis(String isletme, double paraMiktari) {

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/metaland?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "Qwertyu@123");
            
            //kullanıcının mevcut para miktarını sorgular
            double kullaniciPara = kullaniciParaAl(connection);
            
            // Kullanıcının para miktarını günceller
            kullaniciPara += paraMiktari;
            kullaniciParaGuncelle(connection, kullaniciPara);
            
            System.out.println("İşyerinde çalışarak para kazanıldı. Yeni bakiye: " + kullaniciPara);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           
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
       
        double kullaniciPara = 0;
        PreparedStatement statement = null;
        
        try {
            String query = "SELECT kullanici_para_miktari FROM oyuncular WHERE kullanici_takma_adi = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, kullaniciAdi);
            
        
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                kullaniciPara = resultSet.getDouble("kullanici_para_miktari");
            }
        } finally {
      
            if (statement != null) {
                statement.close();
            }
        }
        
        return kullaniciPara;
    }
    
    private void kullaniciParaGuncelle(Connection connection, double yeniPara) throws SQLException {
        //kullanıcının para miktarını günceller
        PreparedStatement statement = null;
        
        try {
            String query = "UPDATE oyuncular SET kullanici_para_miktari = ? WHERE kullanici_takma_adi = ?";
            statement = connection.prepareStatement(query);
            statement.setDouble(1, yeniPara);
            statement.setString(2, kullaniciAdi);
            
           
            statement.executeUpdate();
        } finally {
           
            if (statement != null) {
            statement.close();
            }
        }
    }

    
}


