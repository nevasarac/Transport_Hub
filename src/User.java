public abstract class User implements ILoginable {
    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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