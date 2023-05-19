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
    private JLabel adLabel, soyadLabel;
    private JTextField adField, soyadField;
    private JButton kaydetButton;

    public OyuncuGirisiArayuzu() {
        adLabel = new JLabel("Ad:");
        soyadLabel = new JLabel("Soyad:");
        adField = new JTextField(20);
        soyadField = new JTextField(20);
        kaydetButton = new JButton("Kaydet");

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ad = adField.getText();
                String soyad = soyadField.getText();

                veritabaninaOyuncuEkle(ad, soyad);
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
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(kaydetButton, gbc);

        setTitle("Oyuncu Girişi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void veritabaninaOyuncuEkle(String ad, String soyad) {
        String url = "jdbc:mysql://localhost:3306/metaland";
        String kullanici = "root";
        String sifre = "Qwertyu@123";

        try (Connection conn = DriverManager.getConnection(url, kullanici, sifre)) {
            String sql = "INSERT INTO oyuncular (kullanici_adi, kullanici_soyadi) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ad);
            stmt.setString(2, soyad);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Oyuncu başarıyla eklendi.");
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
