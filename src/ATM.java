import java.util.List;

/**
 * Created by rakib
 * Date: 2021-05-11
 * Projekt: atm
 */
public class ATM {
    protected int totalThousandBills;
    protected int totalFivehundredBills;
    protected int totalHundredBills;
    protected int atmTotal;

    /**
     * This constructor will create the hypothetical ATM when the programme starts. With each bills then it will calculate the total amount of money ATM holds
     *
     * @param thousandBills    total number of 1000 bills the ATM shall have
     * @param fivehundredbills total number of 500 bills the ATM shall have
     * @param hundredBills     total number of 100 bills the ATM shall have
     */
    public ATM(int thousandBills, int fivehundredbills, int hundredBills) {
        this.totalThousandBills = thousandBills;
        this.totalFivehundredBills = fivehundredbills;
        this.totalHundredBills = hundredBills;
        atmTotal = thousandBills * 1000 + fivehundredbills * 500 + hundredBills * 100;
    }

    /**
     * Add bills to the ATM system
     *
     * @param thousandBills    total number of remaining 1000 bills
     * @param fivehundredbills total number of remaining 500 bills
     * @param hundredBills     total number of remaining 100 bills
     */
    public void addBills(int thousandBills, int fivehundredbills, int hundredBills) {
        totalThousandBills += thousandBills;
        totalFivehundredBills += fivehundredbills;
        totalHundredBills += hundredBills;
    }

    /**
     * Remove bills from ATM system
     *
     * @param thousandBills    total number of 1000 bills given to user after successful transaction
     * @param fivehundredbills total number of 500 bills given to user after successful transaction
     * @param hundredBills     total number of 100 bills given to user after successful transaction
     */
    public void removeBills(int thousandBills, int fivehundredbills, int hundredBills) {
        totalThousandBills -= thousandBills;
        totalFivehundredBills -= fivehundredbills;
        totalHundredBills -= hundredBills;
    }

    /**
     * Deduct inputted amount from ATM total after successful transaction
     * @param removeAmount User given amount
     */
    public void removeAmount(int removeAmount) {
        atmTotal -= removeAmount;
    }

    /**
     * Shows how much of each bill user will get after a successful transaction
     * @param withdrawnthousandBills expected 1000 bill
     * @param withdrawnFivehundredbills expected 500 bill
     * @param withdrawnHundredBills expected 100 bill
     * @param withdrawalAmount amount to be withdrwan
     */
    public void successMessage(int withdrawnthousandBills, int withdrawnFivehundredbills, int withdrawnHundredBills, int withdrawalAmount) {
        String successOnThousand = withdrawnthousandBills > 0 ? String.format("%d x 1000", withdrawnthousandBills) : "0 x 1000";
        String successOnFiveHundred = withdrawnFivehundredbills > 0 ? String.format("%d x 500", withdrawnFivehundredbills) : "0 x 500";
        String successOnHundred = withdrawnHundredBills > 0 ? String.format("%d x 100", withdrawnHundredBills) : "0 x 100";
        removeAmount(withdrawalAmount);
        String message = String.format("For %d you will get %s, %s, %s Bill", withdrawalAmount, successOnThousand, successOnFiveHundred, successOnHundred);
        System.out.println(message);
    }

    /**
     * If user ask for a amount that ATM can't give out when remaining bills don’t add up to
     * @param thousandBills    total number of 1000 bills the ATM shall have
     * @param fivehundredbills total number of 500 bills the ATM shall have
     * @param hundredBills     total number of 100 bills the ATM shall have
     * @param withdraw user input amount
     */
    public void failMessage(int thousandBills, int fivehundredbills, int hundredBills, int withdraw) {
        String message = String.format("Not enough bills in ATM to make %d", withdraw);
        System.out.println(message);
        addBills(thousandBills, fivehundredbills, hundredBills);
    }

    /**
     * If user asks for money that is more than ATM starts with or if its more than after ATM's all transactionn
     * @param withdrawal money user wishes to withdra
     * @return Message sent to user
     */
    public String NotEnoughMoneyInAtmMessage(int withdrawal) {
        return String.format("There is not enough money in ATM to withdraw %d!", withdrawal);
    }

    /**
     * This calculates whether ATM can give any return or not. If it can then displays also amount of each unit bill.
     * @param listOfAmount list of withdrawal amount
     */
    public void withdraw(List<Integer> listOfAmount) {
        for (int withdrawl : listOfAmount) {
            int orginalAmaount = withdrawl;
            if (withdrawl > getAtmTotal()) {
                System.out.println(NotEnoughMoneyInAtmMessage(withdrawl));
                continue;
            }
            int usedThousandBills = 0;
            int usedFivehundredBills = 0;
            int usedHundredBills = 0;
            while (withdrawl != 0) {
                if (totalThousandBills != 0 && withdrawl >= 1000) {
                    removeBills(1, 0, 0);
                    usedThousandBills++;
                    withdrawl -= 1000;
                } else if (totalFivehundredBills != 0 && withdrawl >= 500) {
                    removeBills(0, 1, 0);
                    usedFivehundredBills++;
                    withdrawl -= 500;
                } else if (totalHundredBills != 0 && withdrawl >= 100) {
                    removeBills(0, 0, 1);
                    usedHundredBills++;
                    withdrawl -= 100;
                } else {
                    failMessage(usedThousandBills, usedFivehundredBills, usedHundredBills, orginalAmaount);
                    break;
                }
                if (withdrawl == 0) {
                    successMessage(usedThousandBills, usedFivehundredBills, usedHundredBills, orginalAmaount);
                }
            }
        }
        System.out.println("Available money in ATM: " + getAtmTotal());
    }


    public int getAtmTotal() {
        return atmTotal;
    }

    public int getTotalThousandBills() {
        return totalThousandBills;
    }

    public int getTotalFivehundredBills() {
        return totalFivehundredBills;
    }

    public int getTotalHundredBills() {
        return totalHundredBills;
    }
}
