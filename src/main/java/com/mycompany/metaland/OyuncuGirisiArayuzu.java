

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

public class OyuncuGirisiArayuzu extends JFrame {
    private JLabel adLabel, soyadLabel, takmaAdLabel, sifreLabel;
    private JTextField adField, soyadField, takmaAdField;
    private JPasswordField sifreField;
    private JButton kaydetButton, girisButton, geriButton;

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
        girisButton = new JButton("Giriş");
        geriButton = new JButton("Geri");

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
                       // new OyunArayuzu();
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
        add(kaydetButton, gbc);

        gbc.gridx = 1;
        add(girisButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(geriButton, gbc);

        setTitle("Oyuncu Girişi");
        setSize(400, 400);
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
                return false; // Kayıt başarısız, takma ad zaten kullanılıyor.
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
         sifre = "Qwertyu@123";

        try (Connection conn = DriverManager.getConnection(url, kullanici, sifre)) {
            String sql = "SELECT * FROM oyuncular WHERE kullanici_takma_adi = ? AND kullanici_sifresi = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, takmaAd);
            stmt.setString(2, sifre);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // Eşleşme varsa true, yoksa false döner

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Veritabanı hatası: " + e.getMessage());
        }
        return false; // Giriş başarısız
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
