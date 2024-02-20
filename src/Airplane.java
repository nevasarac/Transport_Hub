public class Airplane extends Vehicle {

    private String id;

    public Airplane(String id) {
        super("Uçak");
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
        // Uçağın yakıt maliyeti hesaplama mantığı
        return 0.5 * distance; // Örnek bir hesaplama (0.5 TL/km)
    }
}
