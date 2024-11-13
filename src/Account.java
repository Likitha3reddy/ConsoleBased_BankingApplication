import java.util.ArrayList;

class Account {
    static int nextAccountNumber = 1001;
    String accountNumber;
    String accountHolder;
    String accountType; // Savings or Checking
    double balance;
    ArrayList<Transaction> transactions;

    public Account(String accountHolder, String accountType, double initialDeposit) {
        this.accountNumber = "ACC" + nextAccountNumber++;
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            return true;
        } else {
            System.out.println("Insufficient funds for withdrawal.");
            return false;
        }
    }

    public void printStatement() {
        System.out.println("Statement for Account: " + accountNumber);
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public void calculateMonthlyInterest(double interestRate) {
        if (accountType.equalsIgnoreCase("Savings")) {
            double interest = balance * interestRate / 100;
            deposit(interest);
            System.out.println("Interest added: " + interest);
        }
    }
}