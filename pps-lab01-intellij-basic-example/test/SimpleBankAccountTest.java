import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;

import lab01.example.model.SimpleBankAccountAtm;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {


    private final static int DEPOSIT_VALUE = 100;
    private final static int WITHDRAW_VALUE = 70;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;
    private int depositValue;
    private int withdrawValue;
    private int exceptedValue;

    @BeforeEach
    void beforeEach(){
        setAccountHolder(new AccountHolder("Mario", "Rossi", 1));
        setBankAccount(new SimpleBankAccount(accountHolder, 0));
        this.setDepositValue(DEPOSIT_VALUE);
        this.setWithdrawValue(WITHDRAW_VALUE);
        this.setExceptedValue(DEPOSIT_VALUE);
    }

    protected final void setBankAccount(final BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    protected final void setAccountHolder(final AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    protected final void setDepositValue(final int depositValue) {
        this.depositValue = depositValue;
    }

    protected final void setWithdrawValue(final int withdrawValue) {
        this.withdrawValue = withdrawValue;
    }

    protected final void setExceptedValue(final int exceptedValue) {
        this.exceptedValue = exceptedValue;
    }

    protected final BankAccount getBankAccount() {
        return this.bankAccount;
    }

    protected final AccountHolder getAccountHolder() {
        return this.accountHolder;
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), depositValue);
        assertEquals(exceptedValue, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), depositValue);
        bankAccount.deposit(2, depositValue);
        assertEquals(exceptedValue, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), depositValue);
        bankAccount.withdraw(accountHolder.getId(), withdrawValue);
        assertEquals(depositValue - withdrawValue, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), depositValue);
        bankAccount.withdraw(2, withdrawValue);
        assertEquals(exceptedValue, bankAccount.getBalance());
    }
}
