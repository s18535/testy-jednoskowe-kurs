package pl.pakinio.testyjednoskowe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {

    @Test
    void newAccountSholudBeActiveAfterCrwation() {
        //given+when
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive(),"Check of new account is not active");
    }

    @Test
    void accountSholudBeActiveAfterActivation(){
        //given
        Account newAccount = new Account();
        assertFalse(newAccount.isActive());

        //when
        newAccount.activate();

        //then
        assertTrue(newAccount.isActive());
    }
}
