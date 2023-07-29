

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OyuncuGirisiArayuzu extends JFrame {
    private JLabel adLabel, soyadLabel, takmaAdLabel, sifreLabel;
    private JTextField adField, soyadField, takmaAdField;
    private JPasswordField sifreField;
    private JButton kaydetButton, girisButton, geriButton;

    public OyuncuGirisiArayuzu() {
        
            ImageIcon backgroundImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/citybackG.jpeg");
             JLabel backgroundLabel = new JLabel(backgroundImage);
        // Renkler
        Color background = new Color(215, 178, 209);
        Color foreground = new Color(44, 42, 43);
        Color buttonBackground = new Color(103, 172, 193);
        Color buttonForeground = Color.pink;
        Color buttonPanelC = new Color(255,232,160);
        Font yeniYaziTipi = new Font("Futura", Font.BOLD, 14);
      

       

        adLabel = new JLabel("AD:");
        soyadLabel = new JLabel("SOYAD:");
        takmaAdLabel = new JLabel("TAKMA AD:");
        sifreLabel = new JLabel("ŞİFRE:");
        
        adLabel.setFont(yeniYaziTipi);
        soyadLabel.setFont(yeniYaziTipi);
        takmaAdLabel.setFont(yeniYaziTipi);
        sifreLabel.setFont(yeniYaziTipi);
        
        adField = new JTextField(20);
        soyadField = new JTextField(20);
        takmaAdField = new JTextField(20);
        sifreField = new JPasswordField(20);
        kaydetButton = new JButton("Kaydet");
        girisButton = new JButton("Giriş");
        geriButton = new JButton("Geri");
        
        /*
        adField.setFont(yeniYaziTipi);
        soyadField.setFont(yeniYaziTipi);
        takmaAdField.setFont(yeniYaziTipi);
        sifreField.setFont(yeniYaziTipi);*/
        
        adField.setPreferredSize(new Dimension(200, 30));
        soyadField.setPreferredSize(new Dimension(200, 30));
        takmaAdField.setPreferredSize(new Dimension(200, 30));
        sifreField.setPreferredSize(new Dimension(200, 30));
        
        
        
         // Renkleri
        getContentPane().setBackground(background);
        adLabel.setForeground(foreground);
        soyadLabel.setForeground(foreground);
        takmaAdLabel.setForeground(foreground);
        sifreLabel.setForeground(foreground);
        
        
        kaydetButton.setBackground(buttonBackground);
        kaydetButton.setForeground(buttonForeground);
        kaydetButton.setFont(yeniYaziTipi);
        kaydetButton.setOpaque(true);
        kaydetButton.setBorderPainted(false);
       
        girisButton.setBackground(buttonBackground);
        girisButton.setForeground(buttonForeground);
        girisButton.setFont(yeniYaziTipi);
        girisButton.setOpaque(true);
        girisButton.setBorderPainted(false);
        
        geriButton.setBackground(buttonBackground);
        geriButton.setForeground(buttonForeground);
        geriButton.setFont(yeniYaziTipi);
        geriButton.setOpaque(true);
        geriButton.setBorderPainted(false);
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ad = adField.getText();
                String soyad = soyadField.getText();
                String takmaAd = takmaAdField.getText();
                String sifre = new String(sifreField.getPassword());

                if (ad.isEmpty() || soyad.isEmpty() || takmaAd.isEmpty() || sifre.isEmpty()) {
                    JOptionPane.showMessageDialog(OyuncuGirisiArayuzu.this, "Lütfen tüm alanları doldurun.");
                } else {
                    boolean kayitBasarili = veritabaninaOyuncuEkle(ad, soyad, takmaAd, sifre);
                    if (kayitBasarili) {
                        JOptionPane.showMessageDialog(OyuncuGirisiArayuzu.this, "Kayıt başarılı.");
                         adField.setText("");
                        soyadField.setText("");
                        takmaAdField.setText("");
                        sifreField.setText("");
                        
                         new OyunArayuzu();
                        dispose();
                        
                       
                    } else {
                        JOptionPane.showMessageDialog(OyuncuGirisiArayuzu.this, "Kayıt başarısız. Bu takma ad zaten kullanılıyor.");
                    }
                }
            }
        });

        girisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String takmaAd = takmaAdField.getText();
                String sifre = new String(sifreField.getPassword());

                if (takmaAd.isEmpty() || sifre.isEmpty()) {
                    JOptionPane.showMessageDialog(OyuncuGirisiArayuzu.this, "Lütfen tüm alanları doldurun.");
                } else {
                    boolean girisBasarili = kullaniciGirisiniKontrolEt(takmaAd, sifre);
                    if (girisBasarili) {
                        JOptionPane.showMessageDialog(OyuncuGirisiArayuzu.this, "Giriş başarılı.");
                        new OyunArayuzu();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(OyuncuGirisiArayuzu.this, "Giriş başarısız. Geçersiz takma ad veya şifre.");
                    }
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

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(adLabel, gbc);

        gbc.gridx = 1;
        panel.add(adField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(soyadLabel, gbc);

        gbc.gridx = 1;
        panel.add(soyadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(takmaAdLabel, gbc);

        gbc.gridx = 1;
        panel.add(takmaAdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(sifreLabel, gbc);

        gbc.gridx = 1;
        panel.add(sifreField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        buttonPanel.add(kaydetButton);
        buttonPanel.add(girisButton);
        buttonPanel.setBackground(buttonPanelC);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        gbc.gridy = 5;
        panel.add(geriButton, gbc);

        add(panel);
        panel.setBackground(background);

        setTitle("Oyuncu Girişi");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private boolean veritabaninaOyuncuEkle(String ad, String soyad, String takmaAd, String kullaniciSifre) {
        String url = "jdbc:mysql://localhost:3306/metaland";
        String kullanici = "root";
        String sifre = "Qwertyu@123";

        try (Connection conn = DriverManager.getConnection(url, kullanici, sifre)) {
            String sql = "SELECT * FROM oyuncular WHERE kullanici_takma_adi = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, takmaAd);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return false; // Kayıt başarısız,takma ad zaten kullanılıyor.
            } else {
                sql = "INSERT INTO oyuncular (kullanici_adi, kullanici_soyadi, kullanici_takma_adi, kullanici_sifresi) VALUES (?, ?, ?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, ad);
                stmt.setString(2, soyad);
                stmt.setString(3, takmaAd);
                stmt.setString(4, kullaniciSifre);
                stmt.executeUpdate();
                return true; // Kayıt başarılı
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + e.getMessage());
        }
        return false; // Kayıt başarısız
    }

   private boolean kullaniciGirisiniKontrolEt(String takmaAd, String sifre) {
    String url = "jdbc:mysql://localhost:3306/metaland";
    String kullanici = "root";
    String sifreDb = "Qwertyu@123";
    
    try (Connection conn = DriverManager.getConnection(url, kullanici, sifreDb)) {
        String sql = "SELECT COUNT(*) FROM oyuncular WHERE kullanici_takma_adi = '" + takmaAd + "' AND kullanici_sifresi = '" + sifre + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        int kayitSayisi = rs.getInt(1);
        return kayitSayisi > 0; // Eşleşme varsa başarılı giriş

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + e.getMessage());
    }
    
    System.out.println("Başarısız giriş");
    return false; // Başarısız giriş
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
