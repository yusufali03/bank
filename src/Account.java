public class Account extends Customer {

    private  int balance;
    private int accountNumber;

    public  Account(String FName, String Lname, int accNum, int accBal){
        setFirstName(FName);
        setLastName(Lname);

        this.accountNumber = accNum;
        this.balance = accBal;

    }

    public int getBalance(){
        return this.balance;
    }

    public int getAccountNum(){
        return this.accountNumber;
    }

    public void deposit(int amount){
         this.balance += amount;
    }

    public void withdraw(int amount){
        this.balance -= amount;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

}
