import java.util.Date;

class Transaction {
    static int nextTransactionId = 1;
    int transactionId;
    String type;
    double amount;
    Date date;

    public Transaction(String type, double amount) {
        this.transactionId = nextTransactionId++;
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "ID: " + transactionId + ", Type: " + type + ", Amount: " + amount + ", Date: " + date;
    }
}