package pl.pakinio.testyjednoskowe.account;

import java.util.List;

public interface AccountRepository {

    List<Account> getAllAccounts();
    List<String> geyByName(String name);
}
