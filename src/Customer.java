public class Customer extends User {
    public Customer(String username, String password) {
        super(username, password);
    }

    @Override
    public void login(String username, String password) {
        // Kullanıcı girişi kontrolü
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }
    }
}
