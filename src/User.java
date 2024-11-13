import java.util.ArrayList;

class User {
    String username;
    String password;
    ArrayList<Account> accounts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }
}