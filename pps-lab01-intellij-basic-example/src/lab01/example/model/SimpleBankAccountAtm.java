package lab01.example.model;

/**
 * This class represent a particular instance of a SimpleBankAccountAtm.
 * In particular, a Simple Bank Account ATM allows always the deposit with 1$ fee
 */
public class SimpleBankAccountAtm extends SimpleBankAccount implements BankAccount {

    public SimpleBankAccountAtm(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void deposit(int usrID, double amount) {
        super.deposit(usrID, amount - 1);
    }

    @Override
    public void withdraw(int usrID, double amount) {
        super.withdraw(usrID, amount - 1);
    }
}
