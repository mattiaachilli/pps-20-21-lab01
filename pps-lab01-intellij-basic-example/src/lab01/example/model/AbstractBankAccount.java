package lab01.example.model;

public abstract class AbstractBankAccount implements BankAccount {

    private double balance;
    private final AccountHolder accountHolder;

    public AbstractBankAccount(final AccountHolder holder, final double balance) {
        this.balance = balance;
        this.accountHolder = holder;
    }

    @Override
    public final AccountHolder getAccountHolder() {
        return this.accountHolder;
    }

    @Override
    public final double getBalance() {
        return this.balance;
    }

    protected abstract double getWithdrawFee();
    protected abstract double getDepositFee();

    @Override
    public final void deposit(int usrID, double amount) {
        if (checkUser(usrID)) {
            this.balance = this.balance + amount - getDepositFee();
        }
    }

    @Override
    public final void withdraw(int usrID, double amount) {
        if (checkUser(usrID) && isWithdrawAllowed(amount)) {
            this.balance = this.balance - amount - getWithdrawFee();
        }
    }

    private boolean checkUser(final int id) {
        return this.accountHolder.getId() == id;
    }

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount - getWithdrawFee();
    }
}
