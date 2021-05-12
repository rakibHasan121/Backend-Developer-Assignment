import java.util.ArrayList;
import java.util.List;

/**
 * Created by rakib
 * Date: 2021-05-11
 * Projekt: atm
 */
public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM(2, 3, 5);
        System.out.println("Total money in ATM: " + atm.atmTotal);
        List<Integer> listOfMoneyToWithdraw = new ArrayList<>(List.of(1500, 700, 400, 1100, 1000, 700, 300));
        atm.withdraw(listOfMoneyToWithdraw);
    }
}