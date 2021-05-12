import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by rakib
 * Date: 2021-05-12
 * Projekt: atm
 */
class MainTest {
    private ATM atm;

    @BeforeEach
    public void atmSetup() {
        atm = new ATM(2, 3, 5);
    }

    @Test
    public void ifWithdrawlsWereDifferent() {
        atm.withdraw(new ArrayList<>(List.of(2000,50,100,150,700,45,1200,800,23)));
        assertEquals(0, atm.getTotalFivehundredBills());
        assertEquals(0, atm.getTotalThousandBills());
        assertEquals(0, atm.getTotalHundredBills());
    }

    @Test
    public void ifSomeoneWantToTakeMoreMoneyThanAvailable() {
        atm.withdraw(new ArrayList<>(List.of(5000)));
        assertEquals(2, atm.getTotalThousandBills());
        assertEquals(3, atm.getTotalFivehundredBills());
        assertEquals(5, atm.getTotalHundredBills());
    }
}