package pl.pakinio.testyjednoskowe;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pakinio.testyjednoskowe.extensions.IAExceptionIgnoreExtension;
import pl.pakinio.testyjednoskowe.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class MealTest {

    @Spy
    private Meal mealSpy;

    @Test
    void shouldReturnDiscountedPrince() {
        //given
        Meal meal = new Meal(35);

        //when
        int discountPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28, discountPrice);
        assertThat(discountPrice, equalTo(28));
    }

    @Test
    void referencesToTheSameObjectShouldBeEquel() {
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1, meal2);
        assertThat(meal1, sameInstance(meal2));
    }

    @Test
    void referencesToDiffrentObjectShouldNotBeEquel() {
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);

        //then
        assertNotSame(meal1, meal2);
        assertThat(meal1, not(sameInstance(meal2)));
    }

    @Test
    void twoMealsShouldBeEquelWhenPriceAndNameAreTheSame() {
        //given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");

        //then
        assertEquals(meal1, meal2, "Checking if two meals are equel");
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice() {
        //given
        Meal meal = new Meal(8, "Soup");

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(40));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 18})
    void mealPricesShouldBeLoverThan20(int price) {
        assertThat(price, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price) {
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice() {
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNameShouldEndWithCake(String name) {
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }

    private static Stream<String> createCakeNames() {
        List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitcake", "Cupcake");
        return cakeNames.stream();
    }

    @ExtendWith(IAExceptionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 8})
    void mealPricesShouldBeLoverThan10(int price) {
        if (price > 5) {
            throw new IllegalArgumentException();
        }
        assertThat(price, lessThan(20));
    }

   /* @TestFactory
    Collection<DynamicTest> dynamicTestCollection(){
        return Arrays.asList(
                dynamicTest("Dynamic test 1", () -> assertThat(5,lessThan(6))),
                dynamicTest("Dynamic test 2", () -> assertEquals(4,2*2))
        );
    }*/

    @Tag("fries")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices(){

        Order order=new Order();
        order.addMealToOrder(new Meal(10,2,"Hamburger"));
        order.addMealToOrder(new Meal(7,4,"Fries"));
        order.addMealToOrder(new Meal(22,3,"Pizza"));

        Collection<DynamicTest> dynamicTests=new ArrayList<>();

        for (int i = 0; i <order.getMeals().size() ; i++) {
            int price = order.getMeals().get(i).getPrize();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable =() ->{
                assertThat(calculatePrice(price,quantity),lessThan(67));
            };

            String name="Test name: "+i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name,executable);
            dynamicTests.add(dynamicTest);
        }

        return dynamicTests;
    }

    @Test
    void testMealsSumPrice(){

        //given
        Meal meal = mock(Meal.class);

        given(meal.getPrize()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);

        given(meal.sumPrice()).willCallRealMethod();

        //when
        int result = meal.sumPrice();

        //then
        assertThat(result,equalTo(45));
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    void testMealsSumPriceWithSpy(){

        //given
        /*Meal m=new Meal(14,4,"Burrito");
        Meal meal=spy(m);*/

        given(mealSpy.getPrize()).willReturn(15);
        given(mealSpy.getQuantity()).willReturn(3);

        //when
        int result = mealSpy.sumPrice();

        //then
        then(mealSpy).should().getPrize();
        then(mealSpy).should().getQuantity();
        assertThat(result,equalTo(45));
    }

    private int calculatePrice(int price, int quantity) {
        return price * quantity;
    }
}