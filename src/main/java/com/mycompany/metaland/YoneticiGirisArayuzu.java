
package com.mycompany.metaland;

/**
 *
 * @author aslinurtopcu
 */

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YoneticiGirisArayuzu extends JFrame {
    private JTextField textFieldDatabase;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JTextArea textArea;

    public YoneticiGirisArayuzu() {
        setTitle("Yönetici Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Üst panel
        JPanel topPanel = new JPanel(new GridLayout(4, 2));
        topPanel.add(new JLabel("Veritabanı Adı:"));
        textFieldDatabase = new JTextField();
        topPanel.add(textFieldDatabase);
        topPanel.add(new JLabel("Kullanıcı Adı:"));
        textFieldUsername = new JTextField();
        topPanel.add(textFieldUsername);
        topPanel.add(new JLabel("Şifre:"));
        passwordField = new JPasswordField();
        topPanel.add(passwordField);
        JButton connectButton = new JButton("Bağlan");
        topPanel.add(connectButton);
        add(topPanel, BorderLayout.NORTH);

        // Orta panel
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Geri dönüş butonu
        JButton backButton = new JButton("Geri");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String database = textFieldDatabase.getText();
                String username = textFieldUsername.getText();
                String password = new String(passwordField.getPassword());

                try {
                    veritabaninaBaglan(database, username, password);
                } catch (SQLException ex) {
                    Logger.getLogger(YoneticiGirisArayuzu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 MetalanGirisSayfasi metalangirissayfasi = new MetalanGirisSayfasi();
        metalangirissayfasi.setVisible(true);
        dispose(); // Close the current window
            }
        });
    }
    
    private void veritabaninaBaglan(String database, String username, String password) throws SQLException {
    String url = "jdbc:mysql://localhost:3306/" + database + "?zeroDateTimeBehavior=CONVERT_TO_NULL";

    try (Connection conn = DriverManager.getConnection(url, username, password)) {
        // Tabloları görüntüle
        String[] tabloIsimleri = {"oyuncular", "digersiteler", "vs."};
        for (String tablo : tabloIsimleri) {
            textArea.append("Tablo: " + tablo + "\n");

            String sorgu = "SELECT * FROM " + tablo;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sorgu);

            ResultSetMetaData rsmd = rs.getMetaData();
            int sutunSayisi = rsmd.getColumnCount();
            for (int i = 1; i <= sutunSayisi; i++) {
                textArea.append(rsmd.getColumnName(i) + "\t");
            }
            textArea.append("\n");

            while (rs.next()) {
                for (int i = 1; i <= sutunSayisi; i++) {
                    textArea.append(rs.getString(i) + "\t");
                }
                textArea.append("\n");
            }

            rs.close();
            stmt.close();
        }
    }
    
}
    
}

    

