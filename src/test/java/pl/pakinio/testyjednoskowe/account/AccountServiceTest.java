package pl.pakinio.testyjednoskowe.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    @Test
    void getAllActiveAccounts() {
        //given
        List<Account> accounts=prepareAccountDate();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        when(accountRepository.getAllAccounts()).thenReturn(accounts);
        //given(accountRepository.getAllAccounts()).willReturn(accounts);   //zamiennik

        //when
        List<Account> accountList = accountService.getAllActiveAccount();

        //then
        assertThat(accountList, hasSize(2));
    }

    @Test
    void getNoActiveAccounts() {
        //given
        List<Account> accounts=prepareAccountDate();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        when(accountRepository.getAllAccounts()).thenReturn(Collections.emptyList());
        //given(accountRepository.getAllAccounts()).willReturn(accounts);   //zamiennik

        //when
        List<Account> accountList = accountService.getAllActiveAccount();

        //then
        assertThat(accountList, hasSize(0));
    }

    private List<Account> prepareAccountDate(){Address address1=new Address("Kwiatowa", "33/5");
        Account account1=new Account(address1);

        Account account2=new Account();

        Address address2=new Address("Piekarska","12b");
        Account account3=new Account(address2);

        return Arrays.asList(account1,account2,account3);}
}
