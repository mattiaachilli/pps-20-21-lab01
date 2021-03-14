package lab01.example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdraw is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount extends AbstractBankAccount {

    public SimpleBankAccount(final AccountHolder accountHolder, final double balance) {
        super(accountHolder, balance);
    }

    @Override
    protected double getWithdrawFee() {
        return 0;
    }

    @Override
    protected double getDepositFee() {
        return 0;
    }
}
