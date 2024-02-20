public class Trip {
    private Vehicle vehicle;
    private Route route;
    private String time;
    private double price;

    public Trip(Vehicle vehicle, Route route, String time, double price) {
        this.vehicle = vehicle;
        this.route = route;
        this.time = time;
        this.price = price;
    }
}
