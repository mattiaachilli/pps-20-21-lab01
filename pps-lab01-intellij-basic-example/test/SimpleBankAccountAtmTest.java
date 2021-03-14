import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccountAtm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleBankAccountAtmTest extends AbstractBankAccountTest {

    private final static double N_WITHDRAW_DEPOSIT = 10;

    @Override
    protected BankAccount getBankAccount(final AccountHolder accountHolder, final double balance) {
        return new SimpleBankAccountAtm(accountHolder, balance);
    }

    @Override
    protected double getBalanceDeposit(double initialBalance, double depositedAmount) {
        return initialBalance + depositedAmount - SimpleBankAccountAtm.TRANSACTION_FEE;
    }

    @Override
    protected double getBalanceWithdraw(double initialBalance, double withdrawAmount) {
        return initialBalance - withdrawAmount - SimpleBankAccountAtm.TRANSACTION_FEE;
    }

    @Test
    void testDepositSeveralTimes() {
        for (int i = 0; i < N_WITHDRAW_DEPOSIT; i++) {
            bankAccount.deposit(accountHolder.getId(), DEPOSIT_VALUE);
        }
        assertEquals(INITIAL_BALANCE + (DEPOSIT_VALUE - SimpleBankAccountAtm.TRANSACTION_FEE) * N_WITHDRAW_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWithdrawSeveralTimes() {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_VALUE * 4);
        for (int i = 0; i < N_WITHDRAW_DEPOSIT; i++) {
            bankAccount.withdraw(accountHolder.getId(), WITHDRAW_VALUE);
        }
        assertEquals(WITHDRAW_VALUE - (N_WITHDRAW_DEPOSIT - 1), bankAccount.getBalance());
    }
}
