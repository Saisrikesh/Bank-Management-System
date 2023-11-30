import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {

    private String name;
    private int accNum;
    private double balance;

    public BankAccount(String n, int ac, double bal) {
        name = n;
        accNum = ac;
        balance = bal;
    }

    public String getName() {
        return name;
    }

    public int getAccNum() {
        return accNum;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance = balance + amount;
        System.out.println("\t\t------------------------------");
        System.out.println("\t\tDeposit Successful...");
        System.out.println("\t\t------------------------------");
        System.out.println("\t\tBalance in account: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("\t\t-----------------------");
            System.out.println("\t\t Withdraw Successful...");
            System.out.println("\t\t-----------------------");
            System.out.println("\t\tBalance left in account: " + balance);
        } else {
            System.out.println("\t\t-----------------------");
            System.out.println("\t\t Insufficient Balance...");
            System.out.println("\t\t-----------------------");
        }
    }
}

class BankManagement {

    private ArrayList<BankAccount> accounts;

    public BankManagement() {
        accounts = new ArrayList<>();
    }

    public void addAccount(String name, int accNum, double balance) {
        accounts.add(new BankAccount(name, accNum, balance));
    }

    public void showAccounts() {
        System.out.println("\t\t List of Account Holders");
        System.out.println("\t\t ----------------------");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println("\t\t " + (i + 1) + ". " + accounts.get(i).getName());
        }
        System.out.println("\t\t ----------------------");
    }

    public void searchAccount(int account) {
        int flag = 0;

        for (BankAccount acc : accounts) {
            if (acc.getAccNum() == account) {
                flag = 1;
                System.out.println("\t\t-----------------------------");
                System.out.println("\t\tAccount Found Successfully...");
                System.out.println("\t\t-----------------------------");
                System.out.println("\t\tAccount Holder Details");
                System.out.println("\t\tName: " + acc.getName());
                System.out.println("\t\tAccount Number: " + acc.getAccNum());
                System.out.println("\t\tBalance: " + acc.getBalance());
            }
        }
        if (flag == 0) {
            System.out.println("\t\t-----------------------------");
            System.out.println("\t\tAccount Not Found...");
            System.out.println("\t\t-----------------------------");
        }
    }

    public BankAccount findAccount(int accNum) {
        for (BankAccount acc : accounts) {
            if (acc.getAccNum() == accNum) {
                return acc;
            }
        }
        return null;
    }
}

public class Main {

    public static void main(String[] args) {
        BankManagement bank = new BankManagement();
        int choice;
        char option;

        do {
            System.out.println();
            System.out.println(" \t\t     Bank Management System      ");
            System.out.println("\t\t----------------------------------");
            System.out.println(" \t\t\t1.Create Account");
            System.out.println(" \t\t\t2.List of Account Holders");
            System.out.println(" \t\t\t3.Account Details");
            System.out.println(" \t\t\t4.Deposit Amount");
            System.out.println(" \t\t\t5.Withdraw Amount");
            System.out.println(" \t\t\t6.Exit");
            System.out.println("\t\t----------------------------------");
            System.out.println();
            System.out.print("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice) {

                case 1: {
                    String name;
                    int accNum;
                    double balance;
                    System.out.print(" \t\t Enter Account Holder Name: ");
                    name = scanner.next();
                    System.out.print(" \t\t Account Number: ");
                    accNum = scanner.nextInt();
                    System.out.print(" \t\t Enter Initial Amount: ");
                    balance = scanner.nextDouble();
                    if (balance < 100) {
                        System.out.print("\t\t Invalid - Please enter minimum of 100 rupees: ");
                        balance = scanner.nextDouble();
                    }
                    bank.addAccount(name, accNum, balance);
                    System.out.println("\t\t------------------------------");
                    System.out.println("\t\tAccount Created Successfully...");
                    System.out.println("\t\t------------------------------");
                    break;
                }

                case 2: {
                    bank.showAccounts();
                    break;
                }

                case 3: {
                    int accNum;
                    System.out.print("Enter Account Number: ");
                    accNum = scanner.nextInt();
                    bank.searchAccount(accNum);
                    break;
                }

                case 4: {
                    int accNum;
                    double amount;
                    System.out.print("\t\tEnter Account Number to Deposit Money: ");
                    accNum = scanner.nextInt();
                    BankAccount account = bank.findAccount(accNum);
                    if (account != null) {
                        System.out.print("\t\tEnter Amount to deposit:");
                        amount = scanner.nextDouble();
                        account.deposit(amount);
                    } else {
                        System.out.println("\t\t------------------------------");
                        System.out.println("\t\tAccount Not Found...");
                        System.out.println("\t\t------------------------------");
                    }
                    break;
                }

                case 5: {
                    int accNum;
                    double amount;
                    System.out.print("\t\tEnter Account Number to Withdraw Money: ");
                    accNum = scanner.nextInt();
                    BankAccount account = bank.findAccount(accNum);
                    if (account != null) {
                        System.out.print("\t\tEnter the Amount to withdraw: ");
                        amount = scanner.nextDouble();
                        account.withdraw(amount);
                    } else {
                        System.out.println("\t\t------------------------------");
                        System.out.println("\t\tAccount not Found...");
                        System.out.println("\t\t------------------------------");
                    }
                    break;
                }

                case 6: {
                    System.exit(1);
                    break;
                }
            }
            System.out.print("Do You Want to Continue or Exit[Y/N]: ");
            option = scanner.next().charAt(0);

        } while (option == 'y' || option == 'Y');
    }

    private static void clearConsole() {
        // The implementation of clearing console may vary based on the Java platform
        // This is a simple example and may not work in all environments
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
