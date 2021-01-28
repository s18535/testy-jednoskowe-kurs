package pl.pakinio.testyjednoskowe;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrince() {
        //given
        Meal meal=new Meal(35);

        //when
        int discountPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28,discountPrice);
        assertThat(discountPrice,equalTo(28));
    }

    @Test
    void referencesToTheSameObjectShouldBeEquel(){
        //given
        Meal meal1=new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1,meal2);
        assertThat(meal1,sameInstance(meal2));
    }
    @Test
    void referencesToDiffrentObjectShouldNotBeEquel(){
        //given
        Meal meal1=new Meal(10);
        Meal meal2 = new Meal(20);

        //then
        assertNotSame(meal1,meal2);
        assertThat(meal1, not(sameInstance(meal2)));
    }

    @Test
    void twoMealsShouldBeEquelWhenPriceAndNameAreTheSame(){
        //given
        Meal meal1=new Meal(10,"Pizza");
        Meal meal2=new Meal(10,"Pizza");

        //then
        assertEquals(meal1,meal2,"Checking if two meals are equel");
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice(){
        //given
        Meal meal=new Meal(8,"Soup");

        //when
        //then
        assertThrows(IllegalArgumentException.class,()->meal.getDiscountedPrice(40));
    }
}