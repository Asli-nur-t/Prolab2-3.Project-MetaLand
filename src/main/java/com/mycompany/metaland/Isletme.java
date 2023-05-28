/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metaland;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aslinurtopcu
 */
//*******IŞLETMELER HAKKINDA KARE ALAN BÜYÜKLÜKLERİ İÇİN 
      public class Isletme {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private int xEkseni;
    private int yEkseni;
    
    public int getXEkseni() {
        System.out.println("x ekseni: "+xEkseni);
        return xEkseni;
        
    }

    public void setXEkseni(int xEkseni) {
        this.xEkseni = xEkseni;
    }

    public int getYEkseni() {
        System.out.println("y ekseni: "+yEkseni);
        return yEkseni;
    }

    public void setYEkseni(int yEkseni) {
        this.yEkseni = yEkseni;
    }

    // Veri tabanından x ve y koordinatlarını okuyan metot
    public void veritabanindanKoordinatlariOku(int isletmeId) {
        try {
            
           
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metaland", "root", "Qwertyu@123");
            stmt = conn.createStatement();
            String query = "SELECT isletmenin_x_ekseni, isletmenin_y_ekseni FROM isletmeler WHERE isletme_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, isletmeId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.xEkseni = resultSet.getInt("isletmenin_x_ekseni");
                this.yEkseni = resultSet.getInt("isletmenin_y_ekseni");
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}

