package pl.pakinio.testyjednoskowe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"Fabryczna, 10", "Armii Krajowej, 57/11","'Romka, Tomka, Atomka', 40"})
    void givenAddressesShouldNotBeEmptyAndHaveProperNames(String street, String number){
        assertThat(street,notNullValue());
        assertThat(street,containsString("a"));
        assertThat(number,notNullValue());
        assertThat(number.length(),lessThan(8));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void addressesFromFileShouldNotBeEmptyAndHaveProperNames(String street, String number){
        assertThat(street,notNullValue());
        assertThat(street,containsString("a"));
        assertThat(number,notNullValue());
        assertThat(number.length(),lessThan(8));
    }
}