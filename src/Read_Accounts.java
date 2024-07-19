import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Read_Accounts {
    private String url;

    public  Read_Accounts( String URL){
         this.url = URL;
    }

    private LinkedList<String[]> readAccounts() throws IOException {
        LinkedList<String[]> accountList = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.url))) {
            String line;
            boolean isFirstLine = true; // To skip the header line
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip the first line
                    continue;
                }
                String[] accountData = line.split(",");
                accountList.add(accountData);
            }
        }
        return accountList;
    }

    public LinkedList<String> getFirstNames() throws IOException {
        LinkedList<String[]> accounts = readAccounts();
        LinkedList<String> firstNames = new LinkedList<>();
        for (String[] account : accounts) {
            firstNames.add(account[0]); // Assuming the first name is in the first column
        }
        return firstNames;
    }

    public LinkedList<String> getLastNames() throws IOException {
        LinkedList<String[]> accounts = readAccounts();
        LinkedList<String> lastNames = new LinkedList<>();
        for (String[] account : accounts) {
            lastNames.add(account[1]); // Assuming the last name is in the second column
        }
        return lastNames;
    }

    public LinkedList<Integer> getAccounts() throws IOException {
        LinkedList<String[]> accounts = readAccounts();
        LinkedList<Integer> accountNumbers = new LinkedList<>();
        for (String[] account : accounts) {
            accountNumbers.add(Integer.parseInt(account[2])); // Assuming the account number is in the third column
        }
        return accountNumbers;
    }

    public LinkedList<Integer> getBalances() throws IOException {
        LinkedList<String[]> accounts = readAccounts();
        LinkedList<Integer> balances = new LinkedList<>();
        for (String[] account : accounts) {
            balances.add(Integer.parseInt(account[3])); // Assuming the balance is in the fourth column
        }
        return balances;
    }
}