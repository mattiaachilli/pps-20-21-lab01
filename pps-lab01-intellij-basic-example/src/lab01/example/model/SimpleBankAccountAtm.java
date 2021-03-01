package lab01.example.model;

/**
 * This class represent a particular instance of a SimpleBankAccountAtm.
 * In particular, a Simple Bank Account ATM allows always the deposit with 1$ fee
 */
public class SimpleBankAccountAtm extends SimpleBankAccount implements BankAccount {

    public final static int FEE = 1;

    public SimpleBankAccountAtm(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void deposit(int usrID, double amount) {
        super.deposit(usrID, amount - FEE);
    }

    @Override
    public void withdraw(int usrID, double amount) {
        super.withdraw(usrID, amount - FEE);
    }
}
