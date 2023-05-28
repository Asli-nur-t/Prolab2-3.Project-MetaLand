/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metaland;

import static com.mycompany.metaland.OyunArayuzu.oyuncuBilgiButonu;
import static com.mycompany.metaland.OyunArayuzu.satinAlanKisi;
import static com.mycompany.metaland.OyunArayuzu.yemekFiyati;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
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
    
    ImageIcon backgroundImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/citybackG.jpeg");
    JLabel backgroundLabel = new JLabel(backgroundImage);
    
    ImageIcon asphaltImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/asphalt.jpeg");
    JLabel asphaltLabel = new JLabel(asphaltImage);
    
    ImageIcon asphaltImage2 = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/asphalt3.jpeg");
    JLabel asphaltLabel2 = new JLabel(asphaltImage2);
    
    ImageIcon esyaImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/furniture.png");
    JLabel esyaLabel = new JLabel(esyaImage);
    
    ImageIcon yemekImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/food.png");
    JLabel yemekLabel = new JLabel(yemekImage);
    
    
    ImageIcon shopImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/shop2.png");
    JLabel shopLabell = new JLabel(shopImage);
    
    ImageIcon emlakImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/emlak.png");
    JLabel emlakLabel = new JLabel(emlakImage);
    
    ImageIcon marketImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/shop.png");
    JLabel marketLabel = new JLabel(marketImage);
    
    int yemekFiyati=100;//50 birim yemek 100 para seçim 4
    int esyaFiyati=200;//50 birim esya 200 para seçim 5
    
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
        emlak.setIcon(emlakImage);
        emlak.setOpaque(true);
        emlak.setBorderPainted(false);
        emlak.addActionListener(e -> {
            //JOptionPane.showMessageDialog(null, "Emlak seçildi");
            satinAl.setVisible(true);
            secim=1;
          
        });
        panel.add(emlak);

        JButton magaza = new JButton("Mağaza");
        magaza.setBounds(100, 220, 200, 50);
        magaza.setIcon(shopImage);
         magaza.setForeground(Color.WHITE);
        magaza.setBackground(Color.pink);
        magaza.setOpaque(true);
        magaza.setBorderPainted(false);
        magaza.addActionListener(e -> {
           // JOptionPane.showMessageDialog(null, "Mağaza seçildi");
            satinAl.setVisible(true);
            secim=2;
      
        });
        panel.add(magaza);

        JButton market = new JButton("Market");
        market.setBounds(100, 290, 200, 50);
        market.setForeground(Color.WHITE);
        market.setBackground(Color.pink);
        market.setIcon(marketImage);
        market.setOpaque(true);
        market.setBorderPainted(false);
        
        market.addActionListener(e -> {
            //JOptionPane.showMessageDialog(null, "Market seçildi");
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
        
        
        JButton yemekAl = new JButton("Yemek satın al");
        yemekAl.setBounds(50, 500, 150, 50);
        yemekAl.setForeground(Color.WHITE);
        yemekAl.setBackground(Color.pink);
        yemekAl.setIcon(yemekImage);
        yemekAl.setOpaque(true);
        yemekAl.setBorderPainted(false);
        yemekAl.addActionListener(e -> {
           // JOptionPane.showMessageDialog(null, "Yemek satın alınıyor...");
            
                  System.out.println("yemek seçildi");
                 SatinAlma satinAlma = new SatinAlma(OyunArayuzu.satinAlanKisi, yemekFiyati);
                 satinAlma.satinAl();
                 System.out.println("yemek de kişi bu: "+OyunArayuzu.satinAlanKisi);
            satinAl.setVisible(true);
            secim=4;
           
        });
        panel.add(yemekAl);
        
        
         
        JButton esyaAl = new JButton("Eşya satın al");
        esyaAl.setBounds(200, 500, 150, 50);
        esyaAl.setForeground(Color.WHITE);
        esyaAl.setBackground(Color.pink);
        esyaAl.setOpaque(true);
        esyaAl.setIcon(esyaImage);
        esyaAl.setBorderPainted(false);
        
        esyaAl.addActionListener(e -> {
            //JOptionPane.showMessageDialog(null, "Eşya satın alınıyor...");
            System.out.println("eşya da kişi bu: "+OyunArayuzu.satinAlanKisi);
            SatinAlma satinAlma = new SatinAlma(OyunArayuzu.satinAlanKisi, esyaFiyati);
                 satinAlma.satinAl();
            satinAl.setVisible(true);

            secim=5;
           
        });
        panel.add(esyaAl);

        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HangiIsletme();
    }
}

