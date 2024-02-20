import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company extends User {
    private JFrame frame;
    private String companyName;
    private String vehicleType; 
    private double serviceFee;  

    public static Map<String, Company> predefinedCompanies;

    static {
        // Önceden belirlenmiş firmaları ekleyelim
        predefinedCompanies = new HashMap<>();
        addPredefinedCompany("A", "A", "Otobus", 1000);
        addPredefinedCompany("B", "B", "Otobus", 1200);
        addPredefinedCompany("C", "C", "Otobus,Uçak", 1500);
        addPredefinedCompany("D", "D", "Tren", 900);
        addPredefinedCompany("F", "F", "Uçak", 1800);
    }
    // Araç ve sefer listelerini tutacak değişkenler
    private List<String> vehicles;
    private List<String> routes;

    // Tablo modeli
    private DefaultTableModel tableModel;

    public Company(String companyName, String password, String vehicleType, double serviceFee) {
        super(companyName, password);
        this.companyName = companyName;
        this.vehicleType = vehicleType;
        this.serviceFee = serviceFee;
        this.vehicles = new ArrayList<>();
        this.routes = new ArrayList<>();

        // Tablo modelini oluştur
        String[] columnNames = {"Araçlar", "Seferler"};
        tableModel = new DefaultTableModel(null, columnNames);

        // Arayüzü göster
        
    }

    private static void addPredefinedCompany(String companyName, String password, String vehicleType, double serviceFee) {
        Company company = new Company(companyName, password, vehicleType, serviceFee);
        predefinedCompanies.put(companyName, company);
    }

    // Önceden belirlenmiş firmaları getiren metot
    public static Company getPredefinedCompany(String companyName) {
        return predefinedCompanies.get(companyName);
    }

    // Araç ekleme
    public void addVehicle(String vehicle) {
        vehicles.add(vehicle);
        updateTable();
    }

    // Araç çıkarma
    public void removeVehicle(String vehicle) {
        vehicles.remove(vehicle);
        updateTable();
    }

    public void addRoute(String routeKey) {
        String predefinedRoute = Route.getRoute(routeKey);
        String addedRoute = Route.getAddedRoutes().get(routeKey);

        if (predefinedRoute != null) {
            routes.add(predefinedRoute);
            updateTable();
        } else if (addedRoute != null) {
            routes.add(addedRoute);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(null, "Belirtilen sefer numarası sistemde tanımlı değil.");
        }
    }

    public void removeRoute(String route) {
        routes.remove(route);
        updateTable();
    }



    // Günlük kar hesabı yapma
    public double calculateDailyProfit(double passengerFare, double personnelCost, double fuelCost) {
        // Günlük kar hesabını yapmak için gerekli faktörleri kullanarak hesaplama yap
        double totalIncome = passengerFare * routes.size(); // Her seferde elde edilen toplam gelir
        double totalCost = serviceFee * routes.size() + personnelCost * routes.size() + fuelCost * routes.size(); // Toplam maliyet

        return totalIncome - totalCost;
    }

    // Kullanıcı girişi
    @Override
    public void login(String enteredUsername, String enteredPassword) {
        // Kullanıcı girişi kontrolü
        if (super.username.equals(enteredUsername) && super.password.equals(enteredPassword)) {
            JOptionPane.showMessageDialog(frame, "Admin girişi başarılı!");

            // Admin girişi başarılıysa arayüzü göster
            
        } else {
            JOptionPane.showMessageDialog(frame, "Admin girişi başarısız!");
        }
    }

    // Arayüzü gösterme
    public void showInterface() {
        // Önceki frame'i kapat
        if (frame != null) {
            frame.dispose();
        }

        frame = new JFrame(getCompanyName() + " Arayüzü");
        frame.setSize(1000, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // Tabloyu oluştur
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Alt paneli oluştur
        JPanel bottomPanel = new JPanel();

        // Label'lar ve Butonlar
        JLabel nameLabel = new JLabel("Firma Adı: " + getCompanyName());
        JLabel vehicleTypeLabel = new JLabel("Araç Türü: " + getVehicleType());
        JLabel serviceFeeLabel = new JLabel("Hizmet Bedeli: " + getServiceFee());

        JButton addVehicleButton = new JButton("Araç Ekle");
        JButton removeVehicleButton = new JButton("Araç Çıkar");
        JButton addRouteButton = new JButton("Sefer Ekle");
        JButton removeRouteButton = new JButton("Sefer Çıkar");
        JButton calculateProfitButton = new JButton("Günlük Kar Hesapla");

        // Araç Ekleme Butonu
        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newVehicle = JOptionPane.showInputDialog("Eklemek istediğiniz aracın id'sini girin:");
                if (newVehicle != null && !newVehicle.isEmpty()) {
                    addVehicle(newVehicle);
                    JOptionPane.showMessageDialog(null, "Araç başarıyla eklendi!");
                }
            }
        });

        // Araç Çıkarma Butonu
        removeVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vehicles.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Şirketin hiç aracı bulunmuyor.");
                    return;
                }

                String[] vehicleArray = vehicles.toArray(new String[0]);
                String selectedVehicle = (String) JOptionPane.showInputDialog(null, "Çıkarmak istediğiniz aracı seçin:",
                        "Araç Çıkarma", JOptionPane.QUESTION_MESSAGE, null, vehicleArray, vehicleArray[0]);

                if (selectedVehicle != null) {
                    removeVehicle(selectedVehicle);
                    JOptionPane.showMessageDialog(null, "Araç başarıyla çıkarıldı!");
                }
            }
        });

        // Sefer Ekleme Butonu
addRouteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Araç tipine göre uygun seferleri al
        List<String> availableRoutes = getAvailableRoutes();

        if (!availableRoutes.isEmpty()) {
            String[] routeArray = availableRoutes.toArray(new String[0]);

            // Kullanıcıya seçenekleri göster
            String selectedRoute = (String) JOptionPane.showInputDialog(
                    null, "Lütfen eklemek istediğiniz seferi seçin:",
                    "Sefer Ekleme", JOptionPane.QUESTION_MESSAGE,
                    null, routeArray, routeArray[0]
            );

            // Kullanıcı bir seçenek yaparsa, seçilen seferi ekleyin
            if (selectedRoute != null) {
                addRoute(selectedRoute);
                JOptionPane.showMessageDialog(null, "Sefer başarıyla eklendi!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Araç tipine uygun sefer bulunmuyor.");
        }
    }
});


        // Sefer Çıkarma Butonu
        removeRouteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (routes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Şirketin hiç seferi bulunmuyor.");
            return;
        }

        String[] routeArray = routes.toArray(new String[0]);
        String selectedRoute = (String) JOptionPane.showInputDialog(null, "Çıkarmak istediğiniz seferi seçin:",
                "Sefer Çıkarma", JOptionPane.QUESTION_MESSAGE, null, routeArray, routeArray[0]);

        if (selectedRoute != null) {
            removeRoute(selectedRoute);
            JOptionPane.showMessageDialog(null, "Sefer başarıyla çıkarıldı!");
        }
    }
});


        // Günlük Kar Hesaplama Butonu
        calculateProfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Örnek değerler
                double passengerFare = 50.0;    // Örnek bir yolcu ücreti
                double personnelCost = 500.0;   // Örnek bir personel maliyeti
                double fuelCost = 300.0;        // Örnek bir yakıt maliyeti

                double dailyProfit = calculateDailyProfit(passengerFare, personnelCost, fuelCost);

                JOptionPane.showMessageDialog(null, "Günlük Kar: " + dailyProfit);
            }
        });

        // Alt paneldeki bileşenleri ekle
        bottomPanel.add(addVehicleButton);
        bottomPanel.add(removeVehicleButton);
        bottomPanel.add(addRouteButton);
        bottomPanel.add(removeRouteButton);
        bottomPanel.add(calculateProfitButton);

        // Ana paneldeki bileşenleri konumlandır
        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(vehicleTypeLabel, BorderLayout.CENTER);
        panel.add(serviceFeeLabel, BorderLayout.SOUTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Frame'i görünür yap
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    // Diğer getter metotları ve main metodu
    public String getCompanyName() {
        return companyName;
    }

    public String getPassword() {
        return super.password;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public List<String> getAvailableRoutes() {
    List<String> availableRoutes = new ArrayList<>();
    
    // Araç tipini virgülle ayırarak listeye ekle
    String[] vehicleTypes = getVehicleType().split(",");
    
    for (String vehicleType : vehicleTypes) {
        switch (vehicleType.trim()) {
            case "Otobus":
                availableRoutes.add("3.sefer");
                availableRoutes.add("4.sefer");
                break;
            case "Uçak":
                availableRoutes.add("5.sefer");
                availableRoutes.add("6.sefer");
                break;
            case "Tren":
                availableRoutes.add("1.sefer");
                availableRoutes.add("2.sefer");
                break;
            // Ek araç tipleri için gerekli durumları buraya ekleyebilirsiniz
            // case "YeniAraçTipi":
            //     availableRoutes.add("YeniSefer");
            //     break;
            default:
                System.out.println("Bilinmeyen araç tipi: " + vehicleType.trim());
                break;
        }
    }

    return availableRoutes;
}

    private void updateTable() {
        // Tabloyu temizle
        tableModel.setRowCount(0);

        // Araçları ve seferleri tabloya ekleyerek sıralı bir şekilde güncelle
        int maxSize = Math.max(vehicles.size(), routes.size());
        for (int i = 0; i < maxSize; i++) {
            String vehicle = (i < vehicles.size()) ? vehicles.get(i) : "";
            String route = (i < routes.size()) ? routes.get(i) : "";

            tableModel.addRow(new Object[]{vehicle, route});
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Company("Firma Adı", "Şifre", "Araç Tipi", 1500.0);
            }
        });
    }
}
