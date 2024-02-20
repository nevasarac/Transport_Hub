public class Bus extends Vehicle {

    private String id;

    public Bus(String id) {
        super("Otobus");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public double calculateFuelCost(double distance) {
        // Trenin yakıt maliyeti hesaplama mantığı
        return 0.2 * distance; // Örnek bir hesaplama (0.1 TL/km)
    }

}