public class Transaction {

    public boolean transfer(Account acc1, Account acc2,int amount) {
        int balanceAcc1 = acc1.getBalance();
        int balanceAcc2 = acc2.getBalance();
        if(balanceAcc1 < amount){
            System.out.println("Balance is less than amount, so transfer failed :(");
            return false;
        }
        acc1.setBalance(balanceAcc1 - amount);
        acc2.setBalance(balanceAcc2 + amount);


        return true;

    }
}
