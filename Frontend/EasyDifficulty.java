package Frontend;

import Backend.Account;
import Backend.AccountAlreadyExistsException;

public class EasyDifficulty implements Difficulty{

    @Override
    public void initializeAccount() {
        int commision = 10;
        int balance = 100000;
        try {
            Account.makeAccount(balance, commision);
        } catch (AccountAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
