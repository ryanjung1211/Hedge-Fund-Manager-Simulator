package Frontend;

import Backend.Account;
import Backend.AccountAlreadyExistsException;

public class MediumDifficulty implements Difficulty{

    @Override
    public void initializeAccount() {
        int commision = 5;
        int balance = 75000;
        try {
            Account.makeAccount(balance, commision);
        } catch (AccountAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
