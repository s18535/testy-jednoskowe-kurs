package pl.pakinio.testyjednoskowe.cart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.pakinio.testyjednoskowe.Meal;
import pl.pakinio.testyjednoskowe.cart.Cart;
import pl.pakinio.testyjednoskowe.order.Order;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    //@Disabled
    @DisplayName("Cart is able to process 1000 orders in 100 ms")
    @Test
    void simulateLargeOrder() {
        //given
        Cart cart =new Cart();

        //when
        //then
        assertTimeout(Duration.ofMillis(10), cart::simulateLargeOrder);
    }
    @Test
    void cartShouldBeEmptyAfterAddingOrderToCart(){
        //given
        Order order=new Order();
        Cart cart=new Cart();

        //when
        cart.addOrderToCart(order);

        //then
        assertThat(cart.getOrders(),anyOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertThat(cart.getOrders(),allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertAll("This is a group of assertion Cart",
                ()->assertThat(cart.getOrders(),notNullValue()),
                ()->assertThat(cart.getOrders(),hasSize(1)),
                ()->assertThat(cart.getOrders(),is(not(empty()))),
                ()->assertThat(cart.getOrders(), is(not(emptyCollectionOf(Order.class)))),
                ()-> {
                    List<Meal> mealList=cart.getOrders().get(0).getMeals();
                    assertThat(mealList, empty());
                }
        );
    }
}