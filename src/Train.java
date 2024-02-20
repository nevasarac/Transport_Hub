public class Train extends Vehicle {

    private String id;

    public Train(String id) {
        super("Tren");
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
        return 0.1 * distance; // Örnek bir hesaplama (0.1 TL/km)
    }

}