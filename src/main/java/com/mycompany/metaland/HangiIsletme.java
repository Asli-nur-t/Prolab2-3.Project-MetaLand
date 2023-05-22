/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metaland;

import static com.mycompany.metaland.OyunArayuzu.oyuncuBilgiButonu;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author aslinurtopcu
 */

public class HangiIsletme extends JFrame {
    
    
    static int secim=0;
    public HangiIsletme() {
        setTitle("İşletme Seçimi");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel hangisi = new JLabel("BİR İŞLETME TÜRÜ SEÇİN");
        Font myFont1 = new Font("Arial", Font.BOLD, 30);
        hangisi.setFont(myFont1);
        hangisi.setBounds(30, 50, 350, 50);
        panel.add(hangisi);
        

         JButton satinAl = new JButton("Satın Al");
        satinAl.setVisible(false);
        satinAl.setBounds(100, 360, 200, 50);
        satinAl.setBackground(Color.BLUE);
        satinAl.setForeground(Color.WHITE);
            satinAl.setOpaque(true);
            satinAl.setBorderPainted(false);
        satinAl.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Satın alma işlemi gerçekleşiyor...");
            dispose(); // Pencereyi kapat
        });
        
        panel.add(satinAl);
        
        
        JButton emlak = new JButton("Emlak");
        emlak.setBounds(100, 150, 200, 50);
         emlak.setForeground(Color.WHITE);
        emlak.setBackground(Color.pink);
        emlak.setOpaque(true);
        emlak.setBorderPainted(false);
        emlak.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Emlak seçildi");
            satinAl.setVisible(true);
            secim=1;
          
        });
        panel.add(emlak);

        JButton magaza = new JButton("Mağaza");
        magaza.setBounds(100, 220, 200, 50);
         magaza.setForeground(Color.WHITE);
        magaza.setBackground(Color.pink);
        magaza.setOpaque(true);
        magaza.setBorderPainted(false);
        magaza.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Mağaza seçildi");
            satinAl.setVisible(true);
            secim=2;
      
        });
        panel.add(magaza);

        JButton market = new JButton("Market");
        market.setBounds(100, 290, 200, 50);
        market.setForeground(Color.WHITE);
        market.setBackground(Color.pink);
        market.setOpaque(true);
        market.setBorderPainted(false);
        
        market.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Market seçildi");
            satinAl.setVisible(true);
            secim=3;
           
        });
        panel.add(market);

       
        
        JButton iptal = new JButton("Satın Almayı İptal Et");
        iptal.setBounds(100, 430, 200, 50);
        iptal.setForeground(Color.WHITE);
        iptal.setBackground(Color.red);
        iptal.setOpaque(true);
        iptal.setBorderPainted(false);
        
        iptal.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Satın alma iptal edildi");
            dispose(); // Pencereyi kapat
        });
        panel.add(iptal);

        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HangiIsletme();
    }
}

