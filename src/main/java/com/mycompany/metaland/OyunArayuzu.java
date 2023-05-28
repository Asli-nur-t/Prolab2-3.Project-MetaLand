 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metaland;

import static com.mycompany.metaland.HangiIsletme.secim;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author aslinurtopcu
 */


public class OyunArayuzu {
    ImageIcon backgroundImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/citybackG.jpeg");
    JLabel backgroundLabel = new JLabel(backgroundImage);
    
    ImageIcon asphaltImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/asphalt.jpeg");
    JLabel asphaltLabel = new JLabel(asphaltImage);
    
    ImageIcon asphaltImage2 = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/asphalt3.jpeg");
    JLabel asphaltLabel2 = new JLabel(asphaltImage2);
    
    ImageIcon shopImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/shop2.png");
    JLabel shopLabell = new JLabel(shopImage);
    
    ImageIcon emlakImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/emlak.png");
    JLabel emlakLabel = new JLabel(emlakImage);
    
    ImageIcon marketImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/shop.png");
    JLabel marketLabel = new JLabel(marketImage);
        
    
    private final JFrame frame;
    private final JPanel durumPaneli;
    private final JPanel oyunPaneli;
    private final JLabel saatLabel;
    private final int gridBoyutu = 20; // Grid boyutunu istediğiniz gibi ayarlayabilirsiniz
    private final JButton[][] araziButonlari;
    private final JButton satinAl;
    private final ImageIcon cimIcon;
    private final ImageIcon digerResimIcon;
    
    private int saat = 0;
    private int dakika = 0;
    static int sayac=0;
    private JPanel kullaniciBilgileriPaneli = new JPanel();
    
    private int[] kullaniciNumaralari;

    static final int yemekFiyati=100;//50 birim yemek 100 para seçim 4
    static final int esyaFiyati=200;//50 birim esya 200 para seçim 5
    static JButton oyuncuBilgiButonu;
    
    static String satinAlanKisi;
    static String kaybedenKullanici;
    
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
        durumPaneli.setPreferredSize(new Dimension(100, 50));
        durumPaneli.setBackground(Color.CYAN);

        frame.add(durumPaneli, BorderLayout.SOUTH);

        
     

        oyunPaneli = new JPanel();
        oyunPaneli.setLayout(new GridLayout(gridBoyutu, gridBoyutu));
        int[][] araziButonlariMatrisi = new int[gridBoyutu][gridBoyutu];

        
        //****** ön plandaki şehir düzeni resmi *********
       
        ImageIcon backgroundImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/citybackG.jpeg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 1000, 800);
        
        backgroundLabel.setVisible(true);
        frame.add(backgroundLabel);
        
         saatLabel = new JLabel("Saat: 0");
        durumPaneli.add(saatLabel);

        
        
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




updateKullaniciBilgileri();



JButton yatayYol = new JButton("yatay yol");
        yatayYol.setBounds(666, 500, 100, 50);
        yatayYol.setForeground(Color.WHITE);
        yatayYol.setBackground(Color.pink);
        yatayYol.setOpaque(true);
        yatayYol.setBorderPainted(false);
        yatayYol.addActionListener(e -> {
            //JOptionPane.showMessageDialog(null, "yatay yol seçildi");
            
            secim=4;
           
        });
       yatayYol.setBounds(450, 700, 400, 800);
        kullaniciBilgiEtiketPaneli.add(yatayYol);
        
        
        /* 
        JButton dikeyYol = new JButton("dikey yol");
        dikeyYol.setBounds(777, 500, 100, 50);
        dikeyYol.setForeground(Color.WHITE);
        dikeyYol.setBackground(Color.pink);
        dikeyYol.setOpaque(true);
        dikeyYol.setBorderPainted(false);
        
        dikeyYol.addActionListener(e -> {
            //JOptionPane.showMessageDialog(null, "dikey yol seçildi");
           

            secim=5;
           
        });
        kullaniciBilgiEtiketPaneli.add(dikeyYol);
        
*/

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
                
                HangiIsletme secimFrame= new HangiIsletme();
              //  updateKullaniciBilgileri();
                frame.add(backgroundLabel);
              
            }
        });
        
       
//**********  arka plandaki butonlu arazi düzeni  *************************

    araziButonlari = new JButton[gridBoyutu][gridBoyutu];
    cimIcon = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/OyunArayuzu/src/images/grass.png");
    digerResimIcon = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/grass1.png");


    oyunPaneli.add(backgroundLabel, BorderLayout.CENTER);
    for (int i = 0; i < gridBoyutu; i++) {
        for (int j = 0; j < gridBoyutu; j++) {
        
            //  araziButonlariMatrisi[i][j] = 0;
         
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
}   kullaniciBilgileriPaneli.add(saatLabel);
      //  saatSistemiBaslat();
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
                if(HangiIsletme.secim==1){
                    
                    araziButonlari[satir][sutun].setIcon( emlakImage);
                }
                else if(HangiIsletme.secim==2){
                    araziButonlari[satir][sutun].setIcon(shopImage);
                }
                else if(HangiIsletme.secim==3){
                    araziButonlari[satir][sutun].setIcon(marketImage);
                }
                else if(HangiIsletme.secim==4){
                    araziButonlari[satir][sutun].setIcon(asphaltImage2);
                }
                else if(HangiIsletme.secim==5){
                    araziButonlari[satir][sutun].setIcon(asphaltImage);
                }
               // araziButonlari[satir][sutun].setIcon(digerResimIcon);
            }

            digerResimGosteriliyor = !digerResimGosteriliyor;
        }
    }
    
                

      private void updateKullaniciBilgileri() {
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
                
                System.out.println("satın alma işlemi "+ kullaniciTakmaAdi+ " için yapılıyor");
                
                satinAlanKisi=kullaniciTakmaAdi;
                

            
                sayac++;
                if(sayac%2==1)
                 satinAl.setVisible(true);
                else satinAl.setVisible(false);
                
            }
        });
                    if (kullaniciTakmaAdi.equals(kaybedenKullanici)) {
                    kullaniciBilgiEtiketPaneli.remove(oyuncuBilgiButonu);
                    continue; //kaybeden kullanıcıyı atla ve diğer kullanıcıların bilgilerini güncelle
                        }
                saatSistemiBaslat();
                
                
                //****** Etiketleri panele ekleme ve konumlandırma******
                constraints.gridx = 0;
                constraints.gridy = 0;
                oyuncuBilgiButonu.add(new JLabel(""+kullaniciTakmaAdi), constraints);
                kullaniciBilgiEtiketPaneli.add(oyuncuBilgiButonu, constraints);

                /*
                constraints.gridx = 1;
                constraints.gridy = 0;
                kullaniciBilgiEtiketPaneli.add(kullaniciTakmaAdiValue, constraints);*/

                constraints.gridx = 0;
                constraints.gridy = 1;
                kullaniciBilgiEtiketPaneli.add(new JLabel("Adı:"+ad), constraints);

                /*constraints.gridx = 0;
                constraints.gridy = 1;
                kullaniciBilgiEtiketPaneli.add(adValue, constraints);*/

                constraints.gridx = 0;
                constraints.gridy = 2;
                kullaniciBilgiEtiketPaneli.add(new JLabel("Soyadı:"+soyad), constraints);

                

                constraints.gridx = 0;
                constraints.gridy = 3;
                kullaniciBilgiEtiketPaneli.add(new JLabel("Para:"+para), constraints);


                constraints.gridx = 0;
                constraints.gridy = 4;
                kullaniciBilgiEtiketPaneli.add(new JLabel("Eşya Miktarı:"+esyaMiktari), constraints);

                

                constraints.gridx = 0;
                constraints.gridy = 5;
                kullaniciBilgiEtiketPaneli.add(new JLabel("Yemek Miktarı:"+yemekMiktari), constraints);

             
                    
                    //Ölmeden önceki uyarılar
                    if(esyaMiktari==100){
                        JOptionPane.showMessageDialog(null, "Eşya miktarı 100 birimin altına düşmüştür eşya satın almazsanız"+kullaniciTakmaAdi+"ölebilir!!");
                    }
                    if(yemekMiktari==100){
                        JOptionPane.showMessageDialog(null, "Yemek miktarı 100 birimin altına düşmüştür lütfen eşya satın alın"+kullaniciTakmaAdi+"ölebilir!!");
                    }

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
      
    private void saatSistemiBaslat() {
    Timer timer = new Timer(1000, new ActionListener() {
        int saniye = 0;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            saniye++;
            if (saniye == 60) {
                saniye = 0;
                dakika++;
                if (dakika == 60) {
                    dakika = 0;
                    saat++;
                    if (saat == 13) {
                        saat = 1;
                    }
                }
            }
            saatLabel.setText("Saat: " + String.format("%02d", dakika) + ":" + String.format("%02d", saniye));
            System.out.println("saat:" + saat + " dakika:" + dakika + " saniye:" + saniye);
             yemekVeEsyaDegeriDusur(); // Her dakika geçtiğinde yemek ve eşya değerini düşür
        }
    });
    timer.start();
}

      private void yemekVeEsyaDegeriDusur() {
    try {
        stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE oyuncular SET kullanici_yemek_miktari = kullanici_yemek_miktari - 1, kullanici_esya_miktari = kullanici_esya_miktari - 0.2");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
}
      
      public void kaybedenKullanicilariGuncelle() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metaland", "root", "Qwertyu@123");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT kullanici_takma_adi FROM oyuncular WHERE kullanici_yemek_miktari = 0 AND kullanici_esya_miktari = 0");//yemek ve eşya değerleri 0 olduğunda kullanıcı kaybediyor
            while (rs.next()) {
                String kullaniciTakmaAdi = rs.getString("kullanici_takma_adi");
                System.out.println(kullaniciTakmaAdi + " açlıktan öldü!");
                kullaniciTakmaAdi=kaybedenKullanici;
                JOptionPane.showMessageDialog(null,kullaniciTakmaAdi +"kullanıcısı öldü ...");
                // Kullanıcıyı kaybedildi olarak işaretle
                String updateQuery = "UPDATE oyuncular SET kullanici_yasiyor_mu = false WHERE kullanici_takma_adi = '" + kullaniciTakmaAdi + "'";
                stmt.executeUpdate(updateQuery);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
      
      public void iflasEdenKullanicilariGuncelle() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metaland", "root", "Qwertyu@123");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT kullanici_takma_adi FROM oyuncular WHERE kullanici_para_miktari = 0 ");//para değeri 0 olduğunda kullanıcı iflas ederek kaybeder
            while (rs.next()) {
                String kullaniciTakmaAdi = rs.getString("kullanici_takma_adi");
                System.out.println(kullaniciTakmaAdi + " oyunu kaybetti!");
                JOptionPane.showMessageDialog(null,kullaniciTakmaAdi +"kullanıcısı iflas etti ...");
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        OyunArayuzu arayuz = new OyunArayuzu();
        arayuz.kaybedenKullanicilariGuncelle();
        arayuz.updateKullaniciBilgileri();
        arayuz.iflasEdenKullanicilariGuncelle();
    }
}







      
      
      
      
