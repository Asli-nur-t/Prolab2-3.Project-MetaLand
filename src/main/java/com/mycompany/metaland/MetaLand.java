/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.metaland;

import java.sql.SQLException;
import javax.swing.SwingUtilities;

import java.sql.*;
/**
 *
 * @author aslinurtopcu
 */
public class MetaLand {

     public static void main(String[] args) {
           
       
           String url = "jdbc:mysql://localhost:3306/metaland?zeroDateTimeBehavior=CONVERT_TO_NULL";

        String kullaniciAdi = "root"; // Veritabanı kullanıcı adı
        String sifre = "Qwertyu@123"; // Veritabanı şifresi
        
        
        try {
            // Veritabanı bağlantısını kurma
            Connection conn = DriverManager.getConnection(url, kullaniciAdi, sifre);

            // SQL sorgusu
            String sql = "SELECT * FROM oyuncular";

            // SQL sorgusunu çalıştırma
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("Giriş başarılı");
           
            // Sonuçları işleme
            while (resultSet.next()) {
                
                String ad = resultSet.getString("kullanici_adi");
                 String soyad = resultSet.getString("kullanici_soyadi");
                
                System.out.println(" Ad: " + ad+"Soyad: "+soyad);
            }

            // Bağlantıyı kapatma
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              // MetalanGirisSayfasi Basla= new MetalanGirisSayfasi();
                new OyunArayuzu();
               // new HangiIsletme();
            }
        });
    }
}
