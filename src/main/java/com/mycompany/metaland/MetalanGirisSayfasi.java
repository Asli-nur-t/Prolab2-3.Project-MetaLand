
package com.mycompany.metaland;

/**
 *
 * @author aslinurtopcu
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MetalanGirisSayfasi extends JFrame {
    private JButton kullaniciGirisiButton;
    private JButton yoneticiGirisiButton;
    private JLabel MetalandText;

    private OyuncuGirisiArayuzu oyuncuArayuzu;
    private YoneticiGirisArayuzu yoneticiArayuzu;

    public MetalanGirisSayfasi() {
        setTitle("Metaland Giriş Sayfası");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        Color buttonBackground = new Color(103, 172, 193);
        Color buttonBackgroundY = new Color(110,180, 59);
        
        ImageIcon backgroundImage = new ImageIcon("/Users/aslinurtopcu/NetBeansProjects/MetaLand/images/METALANDGiris.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 600, 400);
        add(backgroundLabel);

        MetalandText= new JLabel("METALAND");
        MetalandText.setSize(400, 400);
        MetalandText.setBounds(420, 200, 400, 50);
        Font myFont1 = new Font("Arial", Font.BOLD, 70);
        MetalandText.setFont(myFont1);
        MetalandText.setForeground(Color.white);
        backgroundLabel.add(MetalandText);
        
        kullaniciGirisiButton = new JButton("Kullanıcı Girişi");
        kullaniciGirisiButton.setBounds(400, 400, 400, 50);
        kullaniciGirisiButton.setBackground(buttonBackground);
        kullaniciGirisiButton.setOpaque(true);
        kullaniciGirisiButton.setForeground(Color.white);
        kullaniciGirisiButton.setFont(new Font("Arial", Font.BOLD, 18));
        kullaniciGirisiButton.setBorderPainted(false);
        kullaniciGirisiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                oyuncuArayuzu = new OyuncuGirisiArayuzu();
                oyuncuArayuzu.setVisible(true);
            }
        });
        backgroundLabel.add(kullaniciGirisiButton);

        yoneticiGirisiButton = new JButton("Yönetici Girişi");
        yoneticiGirisiButton.setBounds(400, 500, 400, 50);
        yoneticiGirisiButton.setBackground(buttonBackgroundY);
        yoneticiGirisiButton.setOpaque(true);
        yoneticiGirisiButton.setBorderPainted(false);
        yoneticiGirisiButton.setForeground(Color.white);
        yoneticiGirisiButton.setFont(new Font("Arial", Font.BOLD, 18));
        yoneticiGirisiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                yoneticiArayuzu = new YoneticiGirisArayuzu();
                yoneticiArayuzu.setVisible(true);
            }
        });
        backgroundLabel.add(yoneticiGirisiButton);

        setVisible(true);
    }

    public void showMetalandGirisSayfasi() {
        setVisible(true);
    }

    public void hideMetalandGirisSayfasi() {
        setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MetalanGirisSayfasi girisSayfasi = new MetalanGirisSayfasi();
            }
        });
    }
}
