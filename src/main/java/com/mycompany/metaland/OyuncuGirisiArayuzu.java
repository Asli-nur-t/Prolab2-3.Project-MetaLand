/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metaland;

/**
 *
 * @author aslinurtopcu
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OyuncuGirisiArayuzu extends JFrame {
    private JLabel adLabel, soyadLabel, takmaAdLabel, sifreLabel;
    private JTextField adField, soyadField, takmaAdField;
    private JPasswordField sifreField;
    private JButton kaydetButton, geriButton;

    public OyuncuGirisiArayuzu() {
        adLabel = new JLabel("Ad:");
        soyadLabel = new JLabel("Soyad:");
        takmaAdLabel = new JLabel("Takma Ad:");
        sifreLabel = new JLabel("Şifre:");
        adField = new JTextField(20);
        soyadField = new JTextField(20);
        takmaAdField = new JTextField(20);
        sifreField = new JPasswordField(20);
        kaydetButton = new JButton("Kaydet");
        geriButton = new JButton("Geri");
        

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ad = adField.getText();
                String soyad = soyadField.getText();
                String takmaAd = takmaAdField.getText();
                String sifre = new String(sifreField.getPassword());
                
                 if (ad.isEmpty() || soyad.isEmpty() || takmaAd.isEmpty()|| sifre.isEmpty()) {
            JOptionPane.showMessageDialog(OyuncuGirisiArayuzu.this, "Lütfen tüm alanları doldurun.");
                     System.out.println("alanlar doldurulmadı hata");
        } else{
            veritabaninaOyuncuEkle(ad, soyad, takmaAd, sifre);    
                 }

            }
        });

        geriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MetalanGirisSayfasi girisSayfasi = new MetalanGirisSayfasi();
                girisSayfasi.setVisible(true);
                dispose();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(adLabel, gbc);

        gbc.gridx = 1;
        add(adField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(soyadLabel, gbc);

        gbc.gridx = 1;
        add(soyadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(takmaAdLabel, gbc);

        gbc.gridx = 1;
        add(takmaAdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(sifreLabel, gbc);

        gbc.gridx = 1;
        add(sifreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(kaydetButton, gbc);

        gbc.gridy = 5;
        add(geriButton, gbc);

        setTitle("Oyuncu Girişi");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

   private void veritabaninaOyuncuEkle(String ad, String soyad, String takmaAd, String kullaniciSifre) {
    String url = "jdbc:mysql://localhost:3306/metaland";
    String kullanici = "root";
    String sifre = "Qwertyu@123";

    try (Connection conn = DriverManager.getConnection(url, kullanici, sifre)) {
        String sql = "INSERT INTO oyuncular (kullanici_adi, kullanici_soyadi, kullanici_takma_adi, kullanici_sifresi) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, ad);
        stmt.setString(2, soyad);
        stmt.setString(3, takmaAd);
        stmt.setString(4, kullaniciSifre);
        stmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Oyuncu başarıyla eklendi.");

        MetalanGirisSayfasi girisSayfasi = new MetalanGirisSayfasi();
        girisSayfasi.setVisible(true);
        dispose();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + e.getMessage());
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                OyuncuGirisiArayuzu arayuz = new OyuncuGirisiArayuzu();
                arayuz.setVisible(true);
            }
        });
    }
}


