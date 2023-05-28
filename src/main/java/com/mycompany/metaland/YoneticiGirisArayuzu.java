
package com.mycompany.metaland;

/**
 *
 * @author aslinurtopcu
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class YoneticiGirisArayuzu extends JFrame {
    private JTextField textFieldDatabase;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JTextArea textArea;
    private JComboBox<String> comboBoxTable;
    private JComboBox<String> comboBoxColumn;
    private JTextField textFieldValue;
    private JComboBox<Integer> comboBoxRows;

    public YoneticiGirisArayuzu() {
        setTitle("Yönetici Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1800, 800);
        setLayout(new BorderLayout());

        
        JPanel topPanel = new JPanel(new GridLayout(4, 2));// Üstteki panel
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

        
        textArea = new JTextArea();// Ortadaki panel
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        
        JButton editButton = new JButton("Düzenle");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

       
        JButton tabloGosterButou = new JButton("Güncel Tabloyu Görüntüle");
        buttonPanel.add(tabloGosterButou);

        
        JButton geributonu = new JButton("Geri");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(geributonu);
        add(bottomPanel, BorderLayout.EAST);
        
        
        
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addPanel.add(new JLabel("Tablo Seç:"));
        comboBoxTable = new JComboBox<>(new String[]{"oyuncular", "oyun_verileri"}); //***tablo adlarını başta manuel ekledim ama bunun bir önemi yok*****
        addPanel.add(comboBoxTable);
        addPanel.add(new JLabel("Sütun Seç:"));
        comboBoxColumn = new JComboBox<>();
        addPanel.add(comboBoxColumn);
        addPanel.add(new JLabel("Değer:"));
        textFieldValue = new JTextField(10);
        addPanel.add(textFieldValue);
        JButton ekleButonu = new JButton("Ekle");
        addPanel.add(ekleButonu);
        
        addPanel.add(new JLabel("Satır Seç:"));
        comboBoxRows = new JComboBox<>();
        addPanel.add(comboBoxRows);
        add(addPanel, BorderLayout.WEST);

        

        geributonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MetalanGirisSayfasi metalangirissayfasi = new MetalanGirisSayfasi();
                metalangirissayfasi.setVisible(true);
                dispose(); 
            }
        });

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Bağlantı işlevi
                String database = textFieldDatabase.getText();
                String username = textFieldUsername.getText();
                String password = new String(passwordField.getPassword());

                String url = "jdbc:mysql://localhost/" + database;

                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    textArea.setText("Bağlantı başarılı!\n");

                    
                    updateComboBoxes(connection);
                    

                    
                    
                    
                    //*******buton işlemleri**********
                    
                    
                    comboBoxTable.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedTable = (String) comboBoxTable.getSelectedItem();
        try {
            updateColumnComboBox(connection, selectedTable);
            updateRowsComboBox(connection, selectedTable);
        } catch (SQLException ex) {
            textArea.append("Sütun ve satır güncelleme hatası: " + ex.getMessage() + "\n");
            Logger.getLogger(YoneticiGirisArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});




                    
                    
                   editButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedTable = (String) comboBoxTable.getSelectedItem();
                        String selectedColumn = (String) comboBoxColumn.getSelectedItem();
                        String newValue = textFieldValue.getText();
                        int selectedRow = (int) comboBoxRows.getSelectedItem();

                        Guncelle(connection, selectedTable, selectedColumn, newValue, selectedRow);
                    }
                });

                    
                    
                    //****özellikle işletme bilgilerine ulaşmalıyım
                    //
                    
                    ekleButonu.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selectedTable = (String) comboBoxTable.getSelectedItem();
                            String selectedColumn = (String) comboBoxColumn.getSelectedItem();
                            String newValue = textFieldValue.getText();

                            DatayiAl(connection, selectedTable, selectedColumn, newValue);
                        }
                    });

                   
                    tabloGosterButou.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JFrame frame = new JFrame("Güncel Tablo");
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setSize(800, 600);

                            try {
                                Statement statement = connection.createStatement();
                                String selectedTable = (String) comboBoxTable.getSelectedItem();
                                String query = "SELECT * FROM " + selectedTable;
                                ResultSet resultSet = statement.executeQuery(query);

                                JTable table = new JTable(buildTableModel(resultSet));
                                JScrollPane scrollPane = new JScrollPane(table);
                                frame.add(scrollPane);
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Veritabanı hatası: " + ex.getMessage());
                                Logger.getLogger(YoneticiGirisArayuzu.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            frame.setVisible(true);
                        }
                    });

                } catch (SQLException ex) {
                    textArea.setText("Bağlantı hatası: " + ex.getMessage());
                    Logger.getLogger(YoneticiGirisArayuzu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        setVisible(true);
    }

    
    
    private void updateComboBoxes(Connection connection) throws SQLException {
    // Tablo seçeneklerini güncelle
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
    ArrayList<String> tableNames = new ArrayList<String>();

    while (tables.next()) {
        String tableName = tables.getString("TABLE_NAME");
        tableNames.add(tableName);
    }
    comboBoxTable.setModel(new DefaultComboBoxModel<>(tableNames.toArray(new String[0])));

    // Sütun seçeneklerini güncelle
    String selectedTable = (String) comboBoxTable.getSelectedItem();
    updateColumnComboBox(connection, selectedTable);
}

    
    private void updateColumnComboBox(Connection connection, String selectedTable) throws SQLException {
    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet columns = metaData.getColumns(null, null, selectedTable, null);
    ArrayList<String> columnNames = new ArrayList<>();
    while (columns.next()) {
        String columnName = columns.getString("COLUMN_NAME");
        columnNames.add(columnName);
    }
    comboBoxColumn.setModel(new DefaultComboBoxModel<>(columnNames.toArray(new String[0])));
}


    
    private void updateRowsComboBox(Connection connection, String selectedTable) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + selectedTable;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        int rowCount = resultSet.getInt(1);

        Integer[] rows = new Integer[rowCount];
        for (int i = 0; i < rowCount; i++) {
            rows[i] = i + 1;
        }

        comboBoxRows.setModel(new DefaultComboBoxModel<>(rows));
    }

    private void Guncelle(Connection connection, String table, String column, String newValue, int selectedRow) {
        try {
            Statement statement = connection.createStatement();
            String updateQuery = "UPDATE " + table + " SET " + column + " = '" + newValue + "' WHERE " + getPrimaryKeyColumn(table) + " = " + selectedRow;
            int rowsAffected = statement.executeUpdate(updateQuery);
            textArea.append(rowsAffected + " satır güncellendi.\n");
        } catch (SQLException ex) {
            textArea.append("Güncelleme hatası: " + ex.getMessage() + "\n");
            Logger.getLogger(YoneticiGirisArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getPrimaryKeyColumn(String table) {
        switch (table) {
            case "isletmeler":
                return "isletme_id";
            case "alanlar":
                return "alan_id";
            case "oyunlar":
                return "oyun_id";
            case "emlaklar":
                return "emlak_id";
            case "oyuncular":
                return "kullanici_no";
            default:
                return "";
        }
    }

    private void DatayiAl(Connection connection, String table, String column, String value) {
        try {
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO " + table + " (" + column + ") VALUES ('" + value + "')";
            int rowsAffected = statement.executeUpdate(insertQuery);
            textArea.append(rowsAffected + " satır eklendi.\n");
        } catch (SQLException ex) {
            textArea.append("Ekleme hatası: " + ex.getMessage() + "\n");
            Logger.getLogger(YoneticiGirisArayuzu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        // Sütun başlıklarını al
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int column = 1; column <= columnCount; column++) {
            columnNames[column - 1] = metaData.getColumnName(column);
        }

        // Verileri al
        Object[][] data = new Object[100][columnCount]; // Örnek olarak maksimum 100 satır alıyoruz
        int row = 0;
        while (resultSet.next() && row < 100) {
            for (int column = 1; column <= columnCount; column++) {
                data[row][column - 1] = resultSet.getObject(column);
            }
            row++;
        }

        return new DefaultTableModel(data, columnNames);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new YoneticiGirisArayuzu();
            }
        });
    }
}




