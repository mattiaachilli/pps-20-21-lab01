import lab01.example.model.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest extends AbstractBankAccountTest {

    @Override
    protected BankAccount getBankAccount(final AccountHolder accountHolder, final double balance) {
        return new SimpleBankAccount(accountHolder, balance);
    }

    @Override
    protected double getBalanceDeposit(final double initialBalance, final double depositedAmount) {
        return initialBalance + depositedAmount;
    }

    @Override
    protected double getBalanceWithdraw(double initialBalance, double withdrawAmount) {
        return initialBalance - withdrawAmount;
    }
}
