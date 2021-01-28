package pl.pakinio.testyjednoskowe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void newAccountShouldBeActiveAfterCrwation() {
        //given+when
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive(),"Check of new account is not active");
    }

    @Test
    void accountShouldBeActiveAfterActivation(){
        //given
        Account newAccount = new Account();
        assertFalse(newAccount.isActive());

        //when
        newAccount.activate();

        //then
        assertTrue(newAccount.isActive());
    }

    @Test
    void newCratedAccountShouldNotDefaultDeliveryAddressSet(){
        //given
        Account account=new Account();

        //when
        Address address = account.getDefaultDeliveyAddress();

        //then
        assertNull(address);
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){
        //given
        Address address = new Address("Krakowaska","67c");
        Account account=new Account();
        account.setDefaultDeliveyAddress(address);

        //when
        Address defaultAdress = account.getDefaultDeliveyAddress();

        //then
        assertNotNull(defaultAdress);
    }

}
