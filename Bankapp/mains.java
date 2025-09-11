package Bankapp;

import java.util.ArrayList;

import java.util.Scanner;

public class mains {
    private static String opt2;
    private static String recipientaccountn;
    private static boolean vo;

    public static void main(String[] args) {
        Scanner A = new Scanner(System.in);
        ArrayList<Bankaccount> accounts = new ArrayList<>();

        accounts.add(new Bankaccount("12345", "1111", 1000));
        accounts.add(new Bankaccount("56789", "0111", 2000));
        accounts.add(new Bankaccount("23456", "1011", 3000));
        accounts.add(new Bankaccount("34567", "1101", 4000));
        accounts.add(new Bankaccount("45678", "1110", 5000));

        System.out.println("Welcome------>");
        System.err.print("Enter your account number: ");
        String accNumber = A.nextLine().trim();

        System.out.print("Enter your account PIN: ");
        String pin = A.nextLine().trim();

        Bankaccount loggedin = null;
        for (Bankaccount acc : accounts) {
            if (acc.getaccountnumber().equals(accNumber) && acc.checkpin(pin)) {
                loggedin = acc;
                break;
            }
        }

        if (loggedin != null) {
            System.out.println("Login successful");
            vo = true;

            while (vo) {
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer Money");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                opt2 = A.nextLine().trim();

                if (opt2.equals("1") || opt2.equals("2") || opt2.equals("3") || opt2.equals("4") || opt2.equals("5")) {
                    if (opt2.equals("5")) {
                        break;
                    } else if (opt2.equals("1")) {
                        System.out.println("Balance: " + loggedin.getbalance());
                        vo = true;
                        continue;
                    } else if (opt2.equals("2")) {
                        System.out.print("Enter the amount to deposit: ");
                        double adding = A.nextDouble();
                        A.nextLine();
                        loggedin.deposit(adding);
                        System.out.println("Amount added: " + adding);
                        System.out.println("Total account balance: " + loggedin.getbalance());
                        vo = true;
                        continue;
                    } else if (opt2.equals("3")) {
                        System.out.print("Enter the amount to withdraw: ");
                        double withdrawing = A.nextDouble();
                        A.nextLine();
                        loggedin.withdraw(withdrawing);
                        System.out.println("Withdrawn amount: " + withdrawing);
                        System.out.println("Total account balance: " + loggedin.getbalance());
                        vo = true;
                        continue;
                    } else if (opt2.equals("4")) {
                        recipientaccountn = null;
                        while (true) {
                            System.out.print("Enter the recipient's account number: ");
                            recipientaccountn = A.nextLine().trim();
                            Bankaccount recipient = null;
                            for (Bankaccount acc : accounts) {
                                if (acc.getaccountnumber().equals(recipientaccountn)) {
                                    recipient = acc;
                                    break;
                                }
                            }
                            if (recipient == null) {
                                System.out.println("Account not found, try again");
                                continue;
                            }
                            if (recipientaccountn.equals(loggedin.getaccountnumber())) {
                                System.out.println("You cannot transfer money to your own account.");
                                continue;
                            }
                            System.out.println("Account found");
                            System.out.print("Enter the amount to transfer: ");
                            String amountStr = A.nextLine().trim();
                            double amount;
                            try {
                                amount = Double.parseDouble(amountStr);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid amount, try again");
                                continue;
                            }
                            if (amount <= 0) {
                                System.out.println("Invalid amount, try again");
                                continue;
                            }
                            if (loggedin.getbalance() < amount) {
                                System.out.println("Insufficient balance, try again");
                                continue;
                            }
                            loggedin.withdraw(amount);
                            recipient.deposit(amount);
                            System.out.println("Transfer successful, your account balance is: " + loggedin.getbalance());
                            break;
                            }
                        }
                        } else {
                            System.out.println("Account not found, try again");
                            vo = true;
                            continue;
                        }
                        System.out.println("Error, try again");
                        vo = true;
                        continue;
                    }
                } else {
                    System.out.println("Invalid account or PIN.");
                }
                System.out.println("TS");A.close(); 
            } 
        }  