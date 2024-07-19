import javax.swing.*;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create a LinkedList of accounts
        LinkedList<Account> accounts = new LinkedList<>();

        // The file path to the CSV file
        String file = "C:\\OOP\\Portfolio Exam\\src\\Accounts.csv";

        try {
            // Step 2: Create an object called readAccounts from the ReadAccounts class
            Read_Accounts readAccounts = new Read_Accounts(file);

            // Step 3: Create a LinkedList of First Names
            LinkedList<String> firstNames = readAccounts.getFirstNames();

            // Step 4: Create a LinkedList of Last Names
            LinkedList<String> lastNames = readAccounts.getLastNames();

            // Step 5: Create a LinkedList of accounts
            LinkedList<Integer> accountList = readAccounts.getAccounts();

            // Step 6: Create a LinkedList of balances
            LinkedList<Integer> balanceList = readAccounts.getBalances();

            // Step 7: Populate the accounts LinkedList using a for loop
            for (int i = 0; i < firstNames.size(); i++) {
                String firstName = firstNames.get(i);
                String lastName = lastNames.get(i);
                int accountNum = accountList.get(i);
                int balance = balanceList.get(i);
                accounts.add(new Account(firstName, lastName, accountNum, balance));
            }

            // Step 8: Testing the created accounts
            // Print all accounts to verify
            for (Account account : accounts) {
                System.out.println("Account: " + account.getFirstName() + " " + account.getLastName() + ", Balance: " + account.getBalance());
            }

            // Perform some transactions
            Transaction transaction = new Transaction();
            if (accounts.size() >= 2) {
                Account account1 = accounts.get(0);
                Account account2 = accounts.get(1);
                Account account3 = accounts.get(2);
                Account account4 = accounts.get(3);

                // Deposit, withdraw, and transfer as examples
                account1.deposit(0);
                account2.withdraw(0);
                account3.withdraw(500);
                account4.deposit(10000);
                transaction.transfer(account1, account2, 250);
                transaction.transfer(account4, account3, 2000);

                System.out.println("Balance account 1: " + account1.getBalance());
                System.out.println("Balance account 2: " + account2.getBalance());
                System.out.println("Balance account 3: " + account3.getBalance());
                System.out.println("Balance account 4: " + account4.getBalance());
            }

            GUI gui = new GUI(accounts);
            gui.setSize(1000, 1000);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gui.setVisible(true);



        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}