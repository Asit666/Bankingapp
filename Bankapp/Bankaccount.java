package Bankapp;

public class Bankaccount {
    private String accountnumber;
    private String pin;
    private double balance;

    public Bankaccount(String accountnumber, String pin, double balance) {
        this.accountnumber = accountnumber;
        this.pin = pin;
        this.balance = balance;
    }

    public double getbalance() {
        return balance;
    }

    public String getaccountnumber() {
        return accountnumber;
    }

    public boolean checkpin(String pin) {
        return this.pin.equals(pin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Depsited: " + amount);
        } else {
            System.out.println("Invalid amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdraw: " + amount);
        } else {
            System.out.println("Invalid or insufficient balance.");
        }
    }
}
