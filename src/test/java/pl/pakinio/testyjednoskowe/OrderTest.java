package pl.pakinio.testyjednoskowe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void initializerOrder(){
        order=new Order();
    }

    @AfterEach
    void cleanUp(){
        order.cancle();
    }

    @Test
    void testAssertArrayEquals(){
        //given
        int[] ints1 = {1,2,3};
        int[] ints2 = {1,2,3};

        //then
        assertArrayEquals(ints1,ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder(){
        //given
        //Order order=new Order();

        //then
        assertThat(order.getMeals(),empty());
        assertThat(order.getMeals().size(),equalTo(0));
        assertThat(order.getMeals(),hasSize(0));
        assertThat(order.getMeals(),emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize(){
        //given
        Meal meal=new Meal(15,"Burger");
        Meal m2=new Meal(5,"Sandwich");
        //Order order=new Order();

        //when
        order.addMealOrder(meal);

        //then
        assertThat(order.getMeals(),hasSize(1));
        assertThat(order.getMeals(),contains(meal));
        assertThat(order.getMeals(),hasItem(meal));
        assertThat(order.getMeals().get(0).getPrize(),equalTo(15));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){
        //given
        Meal meal=new Meal(15,"Burger");
        //Order order=new Order();

        //when
        order.addMealOrder(meal);
        order.removeMealFromOder(meal);

        //then
        assertThat(order.getMeals(),hasSize(0));
        assertThat(order.getMeals(),not(contains(meal)));
    }

    @Test
    void mealShouldBeInCorrectOrderAfterAddingThemOrder(){
        //given
        Meal meal1=new Meal(15,"Burger");
        Meal meal2=new Meal(5,"Sandwich");
        //Order order=new Order();

        //when
        order.addMealOrder(meal1);
        order.addMealOrder(meal2);

        //then
        assertThat(order.getMeals(),contains(meal1,meal2));
        assertThat(order.getMeals(),containsInAnyOrder(meal2,meal1));
    }

    @Test
    void testIfTwoMealListsAreTheSame(){
        //given
        Meal meal1=new Meal(15,"Burger");
        Meal meal2=new Meal(5,"Sandwich");
        Meal meal3=new Meal(11,"Kebab");

        List<Meal> meals1= Arrays.asList(meal1,meal2);
        List<Meal> meals2= Arrays.asList(meal1,meal2);

        //then
        assertThat(meals1,is(meals2));
    }
}