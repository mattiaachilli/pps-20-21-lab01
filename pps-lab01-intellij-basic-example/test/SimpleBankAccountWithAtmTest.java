import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccountAtm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleBankAccountWithAtmTest extends SimpleBankAccountTest {

    private final static int DEPOSIT_VALUE = 200;
    private final static int WITHDRAW_VALUE = 100;
    private final static int EXCEPTED_VALUE = DEPOSIT_VALUE - SimpleBankAccountAtm.FEE;

    @BeforeEach
    void beforeEach(){
        final AccountHolder accountHolder = new AccountHolder("Mario", "Rossi", 1);
        super.setAccountHolder(accountHolder);
        super.setBankAccount(new SimpleBankAccountAtm(accountHolder, 0));

        super.setDepositValue(DEPOSIT_VALUE);
        super.setWithdrawValue(WITHDRAW_VALUE);
        super.setExceptedValue(EXCEPTED_VALUE);
    }

    @Test
    void testDepositSeveralTimes() {
        final AccountHolder accountHolder = super.getAccountHolder();
        final BankAccount bankAccount = super.getBankAccount();
        for (int i = 0; i < 10; i++) {
            bankAccount.deposit(accountHolder.getId(), DEPOSIT_VALUE);
        }
        assertEquals((DEPOSIT_VALUE - SimpleBankAccountAtm.FEE) * 10, bankAccount.getBalance());
    }

    @Test
    void testWithdrawSeveralTimes() {
        final AccountHolder accountHolder = super.getAccountHolder();
        final BankAccount bankAccount = super.getBankAccount();
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_VALUE * 5);
        for (int i = 0; i < 10; i++) {
            bankAccount.withdraw(accountHolder.getId(), WITHDRAW_VALUE);
        }
        assertEquals(SimpleBankAccountAtm.FEE * 9, bankAccount.getBalance());
    }

    @Test
    void testDepositAndWithdrawSeveralTimes() {
        final AccountHolder accountHolder = super.getAccountHolder();
        final BankAccount bankAccount = super.getBankAccount();
        for (int i = 0; i < 5; i++) {
            bankAccount.deposit(accountHolder.getId(), DEPOSIT_VALUE);
        }
        System.out.println(bankAccount.getBalance());
        for (int i = 0; i < 10; i++) {
            bankAccount.withdraw(accountHolder.getId(), WITHDRAW_VALUE);
        }
        assertEquals(SimpleBankAccountAtm.FEE * 5, bankAccount.getBalance());
    }
}
