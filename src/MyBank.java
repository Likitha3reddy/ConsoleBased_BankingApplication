import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MyBank {
    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User loggedInUser = null;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Banking Application ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Open Account");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. View Statement");
            System.out.println("8. Calculate Interest");
            System.out.println("9. Check Balance");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> loginUser();
                case 3 -> logoutUser();
                case 4 -> openAccount();
                case 5 -> deposit();
                case 6 -> withdraw();
                case 7 -> viewStatement();
                case 8 -> calculateInterest();
                case 9 -> checkBalance();
                case 0 -> System.out.println("Exiting application.");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.add(new User(username, password));
        System.out.println("User registered successfully.");
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful.");
                return;
            }
        }
        System.out.println("Invalid username or password.");
    }

    private static void logoutUser() {
        loggedInUser = null;
        System.out.println("Logged out successfully.");
    }

    private static void openAccount() {
        if (loggedInUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        System.out.print("Enter account holder's name: ");
        String accountHolder = scanner.nextLine();
        System.out.print("Enter account type (Savings/Checking): ");
        String accountType = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        Account account = new Account(accountHolder, accountType, initialDeposit);
        loggedInUser.accounts.add(account);
        System.out.println("Account created successfully. Account Number: " + account.accountNumber);
    }

    private static void deposit() {
        if (loggedInUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        Account account = selectAccount();
        if (account == null) return;

        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful. New Balance: " + account.balance);
    }

    private static void withdraw() {
        if (loggedInUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        Account account = selectAccount();
        if (account == null) return;

        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New Balance: " + account.balance);
        }
    }

    private static void viewStatement() {
        if (loggedInUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        Account account = selectAccount();
        if (account == null) return;

        account.printStatement();
    }

    private static void calculateInterest() {
        if (loggedInUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        Account account = selectAccount();
        if (account == null) return;

        System.out.print("Enter monthly interest rate (%): ");
        double interestRate = scanner.nextDouble();
        account.calculateMonthlyInterest(interestRate);
    }

    private static void checkBalance() {
        if (loggedInUser == null) {
            System.out.println("Please log in first.");
            return;
        }

        Account account = selectAccount();
        if (account == null) return;

        System.out.println("Current Balance: " + account.balance);
    }

    private static Account selectAccount() {
        if (loggedInUser.accounts.isEmpty()) {
            System.out.println("No accounts found. Please open an account first.");
            return null;
        }

        System.out.println("Select account by number:");
        for (int i = 0; i < loggedInUser.accounts.size(); i++) {
            System.out.println((i + 1) + ". " + loggedInUser.accounts.get(i).accountNumber);
        }

        int choice = scanner.nextInt();
        if (choice < 1 || choice > loggedInUser.accounts.size()) {
            System.out.println("Invalid selection.");
            return null;
        }
        return loggedInUser.accounts.get(choice - 1);
    }
}
