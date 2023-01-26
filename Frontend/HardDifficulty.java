package Frontend;

import Backend.Account;
import Backend.AccountAlreadyExistsException;

public class HardDifficulty implements Difficulty{

    @Override
    public void initializeAccount() {
        int commision = 2;
        int balance = 50000;
        try {
            Account.makeAccount(balance, commision);
        } catch (AccountAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
