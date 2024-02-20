import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

public class Admin extends User {

    private DefaultTableModel tableModel;
    private JTable table;
    private JFrame frame;
    public static final String DEFAULT_ADMIN_USERNAME = "admin";
    public static final String DEFAULT_ADMIN_PASSWORD = "admin";

    public Admin() {
        super(DEFAULT_ADMIN_USERNAME, DEFAULT_ADMIN_PASSWORD);
        
        initializeUI();
    }
    
    public void initializeUI() {
        // Önceki frame'i kapat
        if (frame != null) {
            frame.dispose();
        }
        frame = new JFrame("Admin Panel");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Butonları oluştur
        JButton displayCompaniesButton = new JButton("Firmaları Görüntüle");
        JButton addCompanyButton = new JButton("Yeni Firma Ekle");
        JButton deleteCompanyButton = new JButton("Firma Sil");

        // Butonlara tıklanma olaylarını ekle
        displayCompaniesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCompaniesInfo();
            }
        });

        addCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCompany();
            }
        });

        deleteCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCompany();
            }
        });

        // Butonları frame'e ekle
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(displayCompaniesButton);
        buttonPanel.add(addCompanyButton);
        buttonPanel.add(deleteCompanyButton);

        // Tabloyu oluştur
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        // Panel ve tabloyu frame'e ekle
        frame.add(buttonPanel, "North");
        frame.add(new JScrollPane(table), "Center");

        frame.setVisible(true);
    }

    // Firmaları görüntüleme işlemi
    public void displayCompaniesInfo() {
        // Başlıkları temizle
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        // Başlıkları ekle
        tableModel.addColumn("Firma Adı");
        tableModel.addColumn("Şifre");
        tableModel.addColumn("Araç Türü");
        tableModel.addColumn("Hizmet Ücreti");

        // Şirket bilgilerini tabloya ekle
        for (Company company : Company.predefinedCompanies.values()) {
            Vector<Object> row = new Vector<>();
            row.add(company.getCompanyName());
            row.add(company.getPassword());
            row.add(company.getVehicleType());
            row.add(company.getServiceFee());
            tableModel.addRow(row);
        }
    }

    // Yeni firma ekleme işlemi
    public void addCompany() {
        JDialog addDialog = new JDialog(frame, "Yeni Firma Ekle", true);

        JTextField companyNameField = new JTextField(20);
        JTextField passwordField = new JTextField(20);
        JTextField vehicleTypeField = new JTextField(20);
        JTextField serviceFeeField = new JTextField(20);

        JButton addButton = new JButton("Ekle");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Yeni firma eklemek için gerekli işlemleri buraya ekleyin
                String companyName = companyNameField.getText();
                String password = passwordField.getText();
                String vehicleType = vehicleTypeField.getText();
                double serviceFee = Double.parseDouble(serviceFeeField.getText());

                // Burada yeni firmayı ekleyebilirsiniz
                Company newCompany = new Company(companyName, password, vehicleType, serviceFee);
                Company.predefinedCompanies.put(companyName, newCompany);

                addDialog.dispose(); // Dialog'u kapat

                // Firmaları tekrar görüntüle
                displayCompaniesInfo();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Firma Adı:"));
        panel.add(companyNameField);
        panel.add(new JLabel("Şifre:"));
        panel.add(passwordField);
        panel.add(new JLabel("Araç Türü:"));
        panel.add(vehicleTypeField);
        panel.add(new JLabel("Hizmet Ücreti:"));
        panel.add(serviceFeeField);
        panel.add(addButton);

        addDialog.add(panel);
        addDialog.setSize(300, 200);
        addDialog.setVisible(true);
    }

    // Firma silme işlemi
    public void deleteCompany() {
        JDialog deleteDialog = new JDialog(frame, "Firma Sil", true);

        JComboBox<String> companyComboBox = new JComboBox<>();
        for (String companyName : Company.predefinedCompanies.keySet()) {
            companyComboBox.addItem(companyName);
        }

        JButton deleteButton = new JButton("Sil");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Firma silme işlemi için gerekli işlemleri buraya ekleyin
                String selectedCompanyName = (String) companyComboBox.getSelectedItem();
                Company.predefinedCompanies.remove(selectedCompanyName);

                deleteDialog.dispose(); // Dialog'u kapat

                // Firmaları tekrar görüntüle
                displayCompaniesInfo();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Silinecek Firma:"));
        panel.add(companyComboBox);
        panel.add(deleteButton);

        deleteDialog.add(panel);
        deleteDialog.setSize(300, 150);
        deleteDialog.setVisible(true);
        displayCompaniesInfo();
    }

    @Override
    public void login(String enteredUsername, String enteredPassword) {
        // Kullanıcı girişi kontrolü
        if (super.username.equals(enteredUsername) && super.password.equals(enteredPassword)) {
            JOptionPane.showMessageDialog(frame, "Admin girişi başarılı!");
            initializeUI(); // Admin girişi başarılıysa, yeni bir UI oluştur
        } else {
            JOptionPane.showMessageDialog(frame, "Admin girişi başarısız!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Admin();
            }
        });
    }
}
