import lab01.example.model.AccountHolder;
import lab01.example.model.SimpleBankAccountAtm;
import org.junit.jupiter.api.BeforeEach;

public class SimpleBankAccountWithAtmTest extends SimpleBankAccountTest {

    @BeforeEach
    void beforeEach(){
        final AccountHolder accountHolder = new AccountHolder("Mario", "Rossi", 1);
        super.setAccountHolder(accountHolder);
        super.setBankAccount(new SimpleBankAccountAtm(accountHolder, 0));

        super.setDepositValue(100);
        super.setWithdrawValue(70);
        super.setExceptedValue(100 - SimpleBankAccountAtm.FEE);
    }
}
