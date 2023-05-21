/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metaland;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author aslinurtopcu
 */

public class OyunArayuzu {

    private final JFrame frame;
    private final JPanel durumPaneli;
    private final JPanel oyunPaneli;
    private final int gridBoyutu = 20; // Grid boyutunu istediğiniz gibi ayarlayabilirsiniz
    private final JButton[][] araziButonlari;
    private final JButton satinAl;
    private final ImageIcon cimIcon;
    private final ImageIcon digerResimIcon;
    private JPanel kullaniciBilgileriPaneli = new JPanel();


    
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public OyunArayuzu() {
        frame = new JFrame("Oyun Arayüzü");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        frame.setLayout(new BorderLayout());

        durumPaneli = new JPanel();
      //  durumPaneli.setPreferredSize(new Dimension(300, 1000));
        frame.add(durumPaneli, BorderLayout.EAST);
       // durumPaneli.setBackground(Color.CYAN);

        oyunPaneli = new JPanel();
        oyunPaneli.setLayout(new GridLayout(gridBoyutu, gridBoyutu));
        
 //oyuncu görünümleri*******
        
        // Veritabanı bağlantısını kur
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metaland", "root", "Qwertyu@123");
    } catch (SQLException e) {
        e.printStackTrace();
    }


kullaniciBilgileriPaneli.setPreferredSize(new Dimension(400, 1000));
frame.add(kullaniciBilgileriPaneli, BorderLayout.EAST);


JPanel kullaniciBilgiEtiketPaneli = new JPanel();

kullaniciBilgileriPaneli.add(kullaniciBilgiEtiketPaneli, BorderLayout.WEST);


JLabel kullaniciTakmaAdiValue = new JLabel("-");
JLabel adValue = new JLabel("-");
JLabel soyadValue = new JLabel("-");
JLabel paraValue = new JLabel("-");
JLabel esyaValue = new JLabel("-");
JLabel yemekValue = new JLabel("-");

updateKullaniciBilgileri(kullaniciTakmaAdiValue, adValue, soyadValue, paraValue, esyaValue, yemekValue);


//kullanılacak butonlar******

      satinAl = new JButton("Arazi Satın Al");
       satinAl.setPreferredSize(new Dimension(200,50));
       durumPaneli.add(satinAl);
       durumPaneli.setLayout(new BorderLayout()); 
       durumPaneli.add(satinAl, BorderLayout.SOUTH); 
        
       
//**********arazi düzeni*************************

        araziButonlari = new JButton[gridBoyutu][gridBoyutu];
        cimIcon = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/OyunArayuzu/src/images/grass.png");
        digerResimIcon = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/OyunArayuzu/src/images/grass1.png");

        for (int i = 0; i < gridBoyutu; i++) {
            for (int j = 0; j < gridBoyutu; j++) {
                araziButonlari[i][j] = new JButton();
                araziButonlari[i][j].setPreferredSize(new Dimension(25, 25));
                araziButonlari[i][j].setIcon(cimIcon);
                araziButonlari[i][j].setContentAreaFilled(false);
                araziButonlari[i][j].setBorderPainted(false);
                araziButonlari[i][j].addActionListener(new ButtonActive(i, j));
                oyunPaneli.add(araziButonlari[i][j]);
            }
        }

        frame.add(oyunPaneli, BorderLayout.CENTER);
       oyunPaneli.setBackground(Color.gray);
        frame.setVisible(true);
    }



    private class ButtonActive implements ActionListener {
        private final int satir;
        private final int sutun;
        private boolean digerResimGosteriliyor;

        public ButtonActive(int satir, int sutun) {
            this.satir = satir;
            this.sutun = sutun;
            this.digerResimGosteriliyor = false;
        }

        public void actionPerformed(ActionEvent e) {
            if (digerResimGosteriliyor) {
                araziButonlari[satir][sutun].setIcon(cimIcon);
            } else {
                araziButonlari[satir][sutun].setIcon(digerResimIcon);
            }

            digerResimGosteriliyor = !digerResimGosteriliyor;
        }
    }
    
                

      private void updateKullaniciBilgileri(JLabel kullaniciTakmaAdiValue, JLabel adValue, JLabel soyadValue,
        JLabel paraValue, JLabel esyaValue, JLabel yemekValue) {
    try {
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT kullanici_takma_adi, kullanici_adi, kullanici_soyadi, kullanici_para_miktari, kullanici_esya_miktari, kullanici_yemek_miktari FROM oyuncular");
        while (rs.next()) { 
            String kullaniciTakmaAdi = rs.getString("kullanici_takma_adi");
            String ad = rs.getString("kullanici_adi");
            String soyad = rs.getString("kullanici_soyadi");
            double para = rs.getDouble("kullanici_para_miktari");
            int esyaMiktari = rs.getInt("kullanici_esya_miktari");
            int yemekMiktari = rs.getInt("kullanici_yemek_miktari");

            kullaniciTakmaAdiValue.setText(kullaniciTakmaAdi);
            adValue.setText(ad);
            soyadValue.setText(soyad);
            paraValue.setText(String.valueOf(para));
            esyaValue.setText(String.valueOf(esyaMiktari));
            yemekValue.setText(String.valueOf(yemekMiktari));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


}
  
