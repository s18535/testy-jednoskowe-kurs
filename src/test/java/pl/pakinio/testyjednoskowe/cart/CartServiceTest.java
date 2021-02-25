package pl.pakinio.testyjednoskowe.cart;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import pl.pakinio.testyjednoskowe.order.Order;
import pl.pakinio.testyjednoskowe.order.OrderStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Test
    void processCartShouldSentToPrepare() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler).sendToPrepare(cart);
        //then(cartHandler).should().sendToPrepare(cart);  //zamienik
        verify(cartHandler,times(1)).sendToPrepare(cart);
        verify(cartHandler, atLeastOnce()).sendToPrepare(cart);

        InOrder inOrder=inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void processCartShouldNotSentToPrepare() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler,never()).sendToPrepare(cart);
        //then(cartHandler).should(never()).sendToPrepare(cart);  //zamienik

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }
}