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
    ImageIcon backgroundImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/citybackG.jpeg");
    JLabel backgroundLabel = new JLabel(backgroundImage);
        
    private final JFrame frame;
    private final JPanel durumPaneli;
    private final JPanel oyunPaneli;
    private final int gridBoyutu = 20; // Grid boyutunu istediğiniz gibi ayarlayabilirsiniz
    private final JButton[][] araziButonlari;
    private final JButton satinAl;
    private final ImageIcon cimIcon;
    private final ImageIcon digerResimIcon;
    private JPanel kullaniciBilgileriPaneli = new JPanel();

    static JButton oyuncuBilgiButonu;
    
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    static int sayac=0;
    public OyunArayuzu() {
        
        
        frame = new JFrame("Oyun Arayüzü");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        frame.setLayout(new BorderLayout());

       durumPaneli = new JPanel();
        durumPaneli.setPreferredSize(new Dimension(100, 50));
        durumPaneli.setBackground(Color.CYAN);

        frame.add(durumPaneli, BorderLayout.SOUTH);

        
     

        oyunPaneli = new JPanel();
        oyunPaneli.setLayout(new GridLayout(gridBoyutu, gridBoyutu));
        
        //****** ön plandaki şehir düzeni resmi *********
       
        ImageIcon backgroundImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/citybackG.jpeg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 1000, 800);
        
        backgroundLabel.setVisible(true);
        frame.add(backgroundLabel);

        
        
        // ********* Veritabanı bağlantısı ******
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metaland", "root", "Qwertyu@123");
    } catch (SQLException e) {
        e.printStackTrace();
    }


kullaniciBilgileriPaneli.setPreferredSize(new Dimension(400, 600));
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




      satinAl = new JButton("Arazi Satın Al");
       satinAl.setPreferredSize(new Dimension(200,50));
       durumPaneli.add(satinAl);
       durumPaneli.setLayout(new BorderLayout()); 
       durumPaneli.add(satinAl, BorderLayout.SOUTH); 
       //herhangi bir kullanıcı takma adına basılana kadar görünmez
       satinAl.setVisible(false);
       satinAl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                 new HangiIsletme();
                  if(HangiIsletme.secim==1)System.out.println("emlak seçildi");
                  else if(HangiIsletme.secim==2)System.out.println("mağaza seçildi");
                  else if(HangiIsletme.secim==3)System.out.println("market seçildi");
              
            }
        });
        
       
//**********  arka plandaki butonlu arazi düzeni  *************************

       araziButonlari = new JButton[gridBoyutu][gridBoyutu];
cimIcon = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/OyunArayuzu/src/images/grass.png");
digerResimIcon = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/grass1.png");


oyunPaneli.add(backgroundLabel, BorderLayout.CENTER);
for (int i = 0; i < gridBoyutu; i++) {
    for (int j = 0; j < gridBoyutu; j++) {
        araziButonlari[i][j] = new JButton();
        araziButonlari[i][j].setPreferredSize(new Dimension(25, 25));
        araziButonlari[i][j].setIcon(cimIcon);
        araziButonlari[i][j].setContentAreaFilled(false);
        araziButonlari[i][j].setBorderPainted(false);
        araziButonlari[i][j].setOpaque(false);
        araziButonlari[i][j].setForeground(new Color(255, 255, 255, 128));
        araziButonlari[i][j].addActionListener(new ButtonActive(i, j));
        oyunPaneli.add(araziButonlari[i][j]);
        backgroundLabel.setVisible(true);
    }
}
        
        oyunPaneli.add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.setBounds(0, 0, 1000, 800);
        backgroundLabel.setVisible(true);
        frame.add(backgroundLabel);
        frame.add(oyunPaneli, BorderLayout.CENTER);
        backgroundLabel.setVisible(true);
        
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
            
            oyunPaneli.add(backgroundLabel, BorderLayout.CENTER);
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
        while (rs.next()) { // Tüm kayıtları döngüyle getirebilmek için
            String kullaniciTakmaAdi = rs.getString("kullanici_takma_adi");
            String ad = rs.getString("kullanici_adi");
            String soyad = rs.getString("kullanici_soyadi");
            double para = rs.getDouble("kullanici_para_miktari");
            int esyaMiktari = rs.getInt("kullanici_esya_miktari");
            int yemekMiktari = rs.getInt("kullanici_yemek_miktari");


         // Kullanıcı bilgi etiket paneli
            JPanel kullaniciBilgiEtiketPaneli = new JPanel();
            kullaniciBilgiEtiketPaneli.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets.bottom = 5;

            oyuncuBilgiButonu= new JButton();
            oyuncuBilgiButonu.setBackground(Color.cyan);
            oyuncuBilgiButonu.setOpaque(true);
            oyuncuBilgiButonu.setBorderPainted(false);
            
            oyuncuBilgiButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Oyuncubilgibutonuna basılması arazi satın alma butonunun görünmesini tetiklemeli bu şekilde mal varlığı güncellenecek
                sayac++;
                if(sayac%2==1)
                 satinAl.setVisible(true);
                else satinAl.setVisible(false);
                
            }
        });

                // Etiketleri panele ekleme ve konumlandırma
                constraints.gridx = 0;
                constraints.gridy = 0;
                oyuncuBilgiButonu.add(new JLabel(""+kullaniciTakmaAdi), constraints);
                kullaniciBilgiEtiketPaneli.add(oyuncuBilgiButonu, constraints);

                constraints.gridx = 1;
                constraints.gridy = 0;
                kullaniciBilgiEtiketPaneli.add(kullaniciTakmaAdiValue, constraints);

                constraints.gridx = 0;
                constraints.gridy = 1;
                kullaniciBilgiEtiketPaneli.add(new JLabel("Adı:"+ad), constraints);

                constraints.gridx = 0;
                constraints.gridy = 1;
                kullaniciBilgiEtiketPaneli.add(adValue, constraints);

                constraints.gridx = 0;
                constraints.gridy = 2;
                kullaniciBilgiEtiketPaneli.add(new JLabel("Soyadı:"+soyad), constraints);

                constraints.gridx = 0;
                constraints.gridy = 2;
                kullaniciBilgiEtiketPaneli.add(soyadValue, constraints);

                constraints.gridx = 0;
                constraints.gridy = 3;
                kullaniciBilgiEtiketPaneli.add(new JLabel("Para:"+para), constraints);

                constraints.gridx = 0;
                constraints.gridy = 3;
                kullaniciBilgiEtiketPaneli.add(paraValue, constraints);

                constraints.gridx = 0;
                constraints.gridy = 4;
                kullaniciBilgiEtiketPaneli.add(new JLabel("Eşya Miktarı:"+esyaMiktari), constraints);

                constraints.gridx = 0;
                constraints.gridy = 4;
                kullaniciBilgiEtiketPaneli.add(esyaValue, constraints);

                constraints.gridx = 0;
                constraints.gridy = 5;
                kullaniciBilgiEtiketPaneli.add(new JLabel("Yemek Miktarı:"+yemekMiktari), constraints);

                constraints.gridx = 0;
                constraints.gridy = 5;
                kullaniciBilgiEtiketPaneli.add(yemekValue, constraints);

                    // Kullanıcı bilgileri paneline etiket panelini ekleyin

                kullaniciBilgileriPaneli.add(kullaniciBilgiEtiketPaneli);

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