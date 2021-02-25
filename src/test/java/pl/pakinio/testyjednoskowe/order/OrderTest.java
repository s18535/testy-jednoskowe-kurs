package pl.pakinio.testyjednoskowe.order;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.pakinio.testyjednoskowe.Meal;
import pl.pakinio.testyjednoskowe.extensions.BeforeAfterExtension;
import pl.pakinio.testyjednoskowe.order.Order;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {

    private Order order;

    @BeforeEach
    void initializerOrder(){
        System.out.println("Before each");
        order=new Order();
    }

    @AfterEach
    void cleanUp(){
        System.out.println("After each");
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
        MatcherAssert.assertThat(order.getMeals(),emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize(){
        //given
        Meal meal=new Meal(15,"Burger");
        Meal m2=new Meal(5,"Sandwich");
        //Order order=new Order();

        //when
        order.addMealToOrder(meal);

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
        order.addMealToOrder(meal);
        order.removeMealFromOder(meal);

        //then
        assertThat(order.getMeals(),hasSize(0));
        assertThat(order.getMeals(),not(contains(meal)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {

        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals().get(0), is(meal1));
        assertThat(order.getMeals().get(1), is(meal2));

    }

//Order - czy w colekcji sa na swoim miejscu
    @Test
    void mealShouldBeInCorrectOrderAfterAddingThemOrder(){
        //given
        Meal meal1=new Meal(15,"Burger");
        Meal meal2=new Meal(5,"Sandwich");
        //Order order=new Order();

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

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

    //Range - test zakreu
    @Test
    void orderTotalPriceShouldNotExceedsMaxIntValue() {
        //given
        Meal meal1 = new Meal(Integer.MAX_VALUE, "Burger");
        Meal meal2 = new Meal(Integer.MAX_VALUE, "Sandwich");

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        assertThrows(IllegalStateException.class, () -> order.totalPrice());
    }

    //Existence - Sprawdzamy czy wartosc jest pusta, obrona aplikacji przed pustymi atrybutami(czy cos jest puste, jak zachowa sie dla 0,czy cos istnieje)
    @Test
    void emptyOrderTotalPriceShouldEqualZero() {
        //given
        //Order is created in BeforeEach

        //then
        assertThat(order.totalPrice(),is(0));
    }

    //Cardionality - czy lista ma 1 element lub wiele
    @Test
    void cancelingOrderShouldRemoveAllItemsFromMealsList() {
        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        order.cancle();

        //then
        assertThat(order.getMeals().size(), is(0));
    }
}