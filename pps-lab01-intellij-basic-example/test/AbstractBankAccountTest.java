import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractBankAccountTest {

    protected final static double INITIAL_BALANCE = 100;
    protected final static double DEPOSIT_VALUE = 200;
    protected final static double WITHDRAW_VALUE = 100;
    private final static double NOT_ALLOWED_WITHDRAW_VALUE = 500;
    private final static int HOLDER_ID = 1;

    protected BankAccount bankAccount;
    protected AccountHolder accountHolder;

    @BeforeEach
    void beforeEach(){
        this.accountHolder = new AccountHolder("Mario", "Rossi", HOLDER_ID);
        this.bankAccount = getBankAccount(accountHolder, INITIAL_BALANCE);
    }

    protected abstract BankAccount getBankAccount(final AccountHolder accountHolder, final double balance);

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    protected abstract double getBalanceDeposit(final double initialBalance, final double depositedAmount);

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_VALUE);
        assertEquals(getBalanceDeposit(INITIAL_BALANCE, DEPOSIT_VALUE), bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_VALUE);
        bankAccount.deposit(HOLDER_ID + 1, DEPOSIT_VALUE);
        assertEquals(getBalanceDeposit(INITIAL_BALANCE, DEPOSIT_VALUE), bankAccount.getBalance());
    }

    protected abstract double getBalanceWithdraw(final double initialBalance, final double withdrawAmount);

    @Test
    void testWithdraw() {
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW_VALUE);
        assertEquals(getBalanceWithdraw(INITIAL_BALANCE, WITHDRAW_VALUE), bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.withdraw(HOLDER_ID + 1, WITHDRAW_VALUE);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testWithdrawNotAllowed() {
        bankAccount.withdraw(HOLDER_ID, NOT_ALLOWED_WITHDRAW_VALUE);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }
}
