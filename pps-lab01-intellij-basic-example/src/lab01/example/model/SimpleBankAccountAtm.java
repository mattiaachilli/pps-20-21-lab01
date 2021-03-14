package lab01.example.model;

/**
 * This class represent a particular instance of a SimpleBankAccountAtm.
 * In particular, a Simple Bank Account ATM allows always the deposit with 1$ fee
 */
public class SimpleBankAccountAtm extends AbstractBankAccount {

    public final static double TRANSACTION_FEE = 1;

    public SimpleBankAccountAtm(final AccountHolder accountHolder, final double balance) {
        super(accountHolder, balance);
    }

    @Override
    protected double getWithdrawFee() {
        return TRANSACTION_FEE;
    }

    @Override
    protected double getDepositFee() {
        return TRANSACTION_FEE;
    }
}
