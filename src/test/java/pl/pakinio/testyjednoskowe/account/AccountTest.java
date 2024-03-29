package pl.pakinio.testyjednoskowe.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pl.pakinio.testyjednoskowe.account.Account;
import pl.pakinio.testyjednoskowe.account.Address;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {

    @Test
    void newAccountShouldBeActiveAfterCrwation() {
        //given+when
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive(),"Check of new account is not active");
        assertThat(newAccount.isActive(),equalTo(false));
        assertThat(newAccount.isActive(),is(false));
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
        assertThat(newAccount.isActive(),equalTo(true));
    }

    @Test
    void newCratedAccountShouldNotDefaultDeliveryAddressSet(){
        //given
        Account account=new Account();

        //when
        Address address = account.getDefaultDeliveyAddress();

        //then
        assertNull(address);
        assertThat(address,nullValue());
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
        assertThat(defaultAdress,is(notNullValue()));
    }
    @RepeatedTest(2)
    void newAccountWithNotNullAddressShouldBeActive(){

        //given
        Address address=new Address("Puławska", "46/6");

        //when
        Account account =new Account(address);

        //then
        assumingThat(address != null,() ->{
            assertTrue(account.isActive());
        });
    }
    @Test
    void invalidEmailShouldThrowException() {

        //given
        Account account = new Account();

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> account.setEmail("wrongEmail"));

    }

    @Test
    void validEmailShouldBeSet() {
        //given
        Account account = new Account();

        //when
        account.setEmail("kontakt@devfoundry.pl");

        //then
        assertThat(account.getEmail(), is("kontakt@devfoundry.pl"));
    }

}
