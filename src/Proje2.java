import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Proje2 extends JFrame {
    private JButton adminButton;
    private JButton firmaButton;
    private JButton biletBulButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public Proje2() {
        // JFrame özelliklerini ayarla
        setTitle("Giriş Ekranı");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Layout manager kullanılmayacak

        // Admin butonu
        adminButton = new JButton("Admin");
        adminButton.setBounds(50, 50, 100, 30);
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginFields("Admin");
            }
        });
        add(adminButton);

        // Firma butonu
        firmaButton = new JButton("Firma");
        firmaButton.setBounds(180, 50, 100, 30);
        firmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginFields("Firma");
            }
        });
        add(firmaButton);

        // Bilet Bul butonu
        biletBulButton = new JButton("Bilet Bul");
        biletBulButton.setBounds(310, 50, 100, 30);
        biletBulButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                biletBulButtonClick();
            }
        });
        add(biletBulButton);

        // Kullanıcı adı ve şifre girişi için alanları oluştur (ilk başta gizli)
        usernameField = new JTextField();
        usernameField.setBounds(50, 100, 200, 30);
        usernameField.setVisible(false);
        add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 150, 200, 30);
        passwordField.setVisible(false);
        add(passwordField);

        // Kullanıcı adı ve şifre etiketleri
        usernameLabel = new JLabel("Kullanıcı Adı:");
        usernameLabel.setBounds(50, 80, 100, 20);
        usernameLabel.setVisible(false);
        add(usernameLabel);

        passwordLabel = new JLabel("Şifre:");
        passwordLabel.setBounds(50, 130, 100, 20);
        passwordLabel.setVisible(false);
        add(passwordLabel);

        // Giriş yap butonu
        JButton loginButton = new JButton("Giriş Yap");
        loginButton.setBounds(50, 200, 100, 30);
        loginButton.setVisible(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        add(loginButton);
    }

    private void showLoginFields(String userType) {
        // Kullanıcı adı ve şifre girişi için alanları görünür yap
        usernameField.setVisible(true);
        passwordField.setVisible(true);
        usernameLabel.setVisible(true);
        passwordLabel.setVisible(true);

        // Giriş yap butonunu görünür yap
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JButton && "Giriş Yap".equals(((JButton) component).getText())) {
                component.setVisible(true);
                break;
            }
        }

        // Yeniden boyutlandır ve güncelle
        setSize(600, 600);
        validate();
    }

    private void handleLogin() {
        // Kullanıcı adı ve şifre girişi için alanları gizle
        usernameField.setVisible(false);
        passwordField.setVisible(false);
        usernameLabel.setVisible(false);
        passwordLabel.setVisible(false);

        // Giriş yap butonunu gizle
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JButton && "Giriş Yap".equals(((JButton) component).getText())) {
                component.setVisible(false);
                break;
            }
        }

        // Kullanıcı adı ve şifreyi al
        String enteredUsername = usernameField.getText();
        char[] enteredPasswordChars = passwordField.getPassword();
        String enteredPassword = new String(enteredPasswordChars);
        
        Company company = Company.getPredefinedCompany(enteredUsername);
        // Kullanıcı adı ve şifreyi kontrol et
        if (enteredUsername.equals(Admin.DEFAULT_ADMIN_USERNAME) && enteredPassword.equals(Admin.DEFAULT_ADMIN_PASSWORD)) {
        // Admin girişi başarılı
        JOptionPane.showMessageDialog(this, "Admin girişi başarılı!");
        new Admin();
        
        }else if (company != null && company.getPassword().equals(enteredPassword)) {
        // Firma girişi başarılı
        JOptionPane.showMessageDialog(this, "Firma girişi başarılı!");
        company.showInterface();
        
    } else {
        // Giriş başarısız
        JOptionPane.showMessageDialog(this, "Giriş başarısız!");
    }

        // Alanları temizle
        usernameField.setText("");
        passwordField.setText("");
    }
    
    
    private void biletBulButtonClick() {
        // TODO: Bilet Bul butonuna tıklandığında yapılacak işlemleri buraya ekle
        // Örneğin, bilet bulma işlemlerinin yapılması
        JOptionPane.showMessageDialog(this, "Bilet bul işlemleri yapılacak!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Proje2().setVisible(true);
            }
        });
    }
}