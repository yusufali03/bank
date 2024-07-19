import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class GUI extends JFrame {

    Transaction transferObject = new Transaction();
    StringBuilder sbAllData = new StringBuilder();
    LinkedList<Account> globalAccounts = new LinkedList<>();

    JButton showAllButton;
    JButton depositButton;
    JButton withdrawButton;
    JButton transferButton;

    JLabel showAllData;
    JLabel accDepositLabel;
    JLabel accWithdrawLabel;
    JLabel acc1TransferLabel;
    JLabel acc2TransferLabel;
    JLabel depositInputLabel;
    JLabel withdrawInputLabel;
    JLabel transferAmountLabel;

    JTextField accDeposit;
    JTextField accWithdraw;
    JTextField acc1Transfer;
    JTextField acc2Transfer;
    JTextField depositInput;
    JTextField withdrawInput;
    JTextField transferAmount;

    public GUI(LinkedList<Account> accounts ) {
        super("Banking System");
        setLayout(null);
        globalAccounts = accounts;

        showAllButton = new JButton("Show All Data");
        showAllButton.setBounds(10, 250, 150, 25);
        add(showAllButton);

        showAllData = new JLabel();
        showAllData.setBounds(50, 290, 800, 200);
        add(showAllData);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(30, 40, 100, 25);
        add(depositButton);

        accDepositLabel = new JLabel("Account Number:");
        accDepositLabel.setBounds(150, 40, 100, 25);
        add(accDepositLabel);

        accDeposit = new JTextField();
        accDeposit.setBounds(260, 40, 100, 25);
        add(accDeposit);

        depositInputLabel = new JLabel("Amount:");
        depositInputLabel.setBounds(380, 40, 100, 25);
        add(depositInputLabel);

        depositInput = new JTextField();
        depositInput.setBounds(440, 40, 100, 25);
        add(depositInput);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(30, 90, 100, 25);
        add(withdrawButton);

        accWithdrawLabel = new JLabel("Account Number:");
        accWithdrawLabel.setBounds(150, 90, 100, 25);
        add(accWithdrawLabel);

        accWithdraw = new JTextField();
        accWithdraw.setBounds(260, 90, 100, 25);
        add(accWithdraw);

        withdrawInputLabel = new JLabel("Amount:");
        withdrawInputLabel.setBounds(380, 90, 100, 25);
        add(withdrawInputLabel);

        withdrawInput = new JTextField();
        withdrawInput.setBounds(440, 90, 100, 25);
        add(withdrawInput);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(30, 170, 100, 25);
        add(transferButton);

        acc1TransferLabel = new JLabel("From Account:");
        acc1TransferLabel.setBounds(150, 170, 100, 25);
        add(acc1TransferLabel);

        acc1Transfer = new JTextField();
        acc1Transfer.setBounds(250, 170, 100, 25);
        add(acc1Transfer);

        acc2TransferLabel = new JLabel("To Account:");
        acc2TransferLabel.setBounds(360, 170, 100, 25);
        add(acc2TransferLabel);

        acc2Transfer = new JTextField();
        acc2Transfer.setBounds(440, 170, 100, 25);
        add(acc2Transfer);

        transferAmountLabel = new JLabel("Amount:");
        transferAmountLabel.setBounds(555, 170, 100, 25);
        add(transferAmountLabel);

        transferAmount = new JTextField();
        transferAmount.setBounds(630, 170, 100, 25);
        add(transferAmount);

        // Add action listeners
        HandlerClass handler = new HandlerClass();
        showAllButton.addActionListener(handler);
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);

//        setSize(700, 500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
    }

    private class HandlerClass implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == showAllButton) {
                showAllAccData();
//                showAllData.setText("<html>" + sbAllData.toString().replace("\n", "<br>") + "</html>");
            } else if (event.getSource() == depositButton) {
                int accountNum = Integer.parseInt(accDeposit.getText());
                int amount = Integer.parseInt(depositInput.getText());
                for (Account account : globalAccounts) {
                    if (account.getAccountNum() == accountNum) {
                        account.deposit(amount);
                        updateAccountData();
                        break;
                    }
                }
            } else if (event.getSource() == withdrawButton) {
                int accountNum = Integer.parseInt(accWithdraw.getText());
                int amount = Integer.parseInt(withdrawInput.getText());
                for (Account account : globalAccounts) {
                    if (account.getAccountNum() == accountNum) {
                        account.withdraw(amount);
                        updateAccountData();
                        break;
                    }
                }
            } else if (event.getSource() == transferButton) {
                int fromAccountNum = Integer.parseInt(acc1Transfer.getText());
                int toAccountNum = Integer.parseInt(acc2Transfer.getText());
                int amount = Integer.parseInt(transferAmount.getText());
                Account fromAccount = null, toAccount = null;
                for (Account account : globalAccounts) {
                    if (account.getAccountNum() == fromAccountNum) {
                        fromAccount = account;
                    }
                    if (account.getAccountNum() == toAccountNum) {
                        toAccount = account;
                    }
                }
                if (fromAccount != null && toAccount != null) {
                    Transaction t = new Transaction();
                    t.transfer(fromAccount, toAccount, amount);
                    updateAccountData();
                }
            }
        }

        private void showAllAccData() {
            StringBuilder accData = new StringBuilder();
            String file = "C:\\OOP\\Portfolio Exam\\src\\Accounts.csv";
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isFirstLine = true;
                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    String[] parts = line.split(",");
                    accData.append("Account Number: ").append(parts[2]).append("\n");
                    accData.append("First Name: ").append(parts[0]).append(", ");
                    accData.append("Last Name: ").append(parts[1]).append(", ");
                    accData.append("Balance: ").append(parts[3]).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            showAllData.setText("<html>" + accData.toString().replace("\n", "<br>") + "</html>");
        }



        private void updateAccountData() {
            sbAllData.setLength(0); // Clear existing data
            for (Account account : globalAccounts) {
                sbAllData.append("Account Number: ").append(account.getAccountNum())
                        .append(", First Name: ").append(account.getFirstName())
                        .append(", Last Name: ").append(account.getLastName())
                        .append(", Balance: ").append(account.getBalance())
                        .append("\n");

            }
            showAllData.setText("<html>" + sbAllData.toString().replace("\n", "<br>") + "</html>");
        }
    }
}


