// Vehicle.java
public abstract class Vehicle {

    public final String type;

    public Vehicle(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    
    public String toString() {
        return type;
    }
    public abstract double calculateFuelCost(double distance);
}
