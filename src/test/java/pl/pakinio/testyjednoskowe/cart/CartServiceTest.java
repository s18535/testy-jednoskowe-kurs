package pl.pakinio.testyjednoskowe.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.pakinio.testyjednoskowe.order.Order;
import pl.pakinio.testyjednoskowe.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;

//@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @InjectMocks
    private CartService cartService;
    @Mock
    private CartHandler cartHandler;
    @Captor
    private ArgumentCaptor<Cart> argumentCaptor;

    @Test
    void processCartShouldSentToPrepare() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

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

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler,never()).sendToPrepare(cart);
        //then(cartHandler).should(never()).sendToPrepare(cart);  //zamienik

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void processCartShouldNotSentToPrepareWithArgumentMatchers() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler, never()).sendToPrepare(any(Cart.class));
        then(cartHandler).should(never()).sendToPrepare(any(Cart.class));  //zamienik

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void canHandleCartShouldReturnMultipleValues() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(true,false,false,true);


        //then
        assertThat(cartHandler.canHandleCart(cart),equalTo(true));
        assertThat(cartHandler.canHandleCart(cart),equalTo(false));
        assertThat(cartHandler.canHandleCart(cart),equalTo(false));
        assertThat(cartHandler.canHandleCart(cart),equalTo(true));


    }

    //dzieki argThat() moZEMY PODAWAC ARGUMENTY KTÓRE MAJA ZWROCOC BOOLEAN
    @Test
    void processCartShouldSentToPrepareWithLambdas() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(argThat(c -> c.getOrders().size() > 0))).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void canHandleCartShouldThrowException() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willThrow(IllegalStateException.class);

        //when
        //then
        assertThrows(IllegalStateException.class,()->cartService.processCart(cart));
    }

    //dla kazdego parametru tyzreba zrobic caprora zebu go przetestować
    @Test
    void processCartShouldSentToPrepareWithArgumentCaptor() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        //verify(cartHandler).sendToPrepare(argumentCaptor.capture()); /zamieniik
        then(cartHandler).should().sendToPrepare(argumentCaptor.capture());  //zamienik

        assertThat(argumentCaptor.getValue().getOrders().size(),equalTo(1));

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void shouldDoNothingWhenProcessCart() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //doNothing().when(cartHandler).sendToPrepare(cart);
        //willDoNothing().given(cartHandler).sendToPrepare(cart); //sprawdzamy czy metoda typu void nic nie robi
        willDoNothing().willThrow(IllegalStateException.class).given(cartHandler).sendToPrepare(cart);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPrepare(cart);  //zamienik

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void shouldAnswerWhenProcessCart() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);;

      /*doAnswer(invocationOnMock -> {
          Cart argumentCart =invocationOnMock.getArgument(0);
          argumentCart.clearCart();
          return true;
      }).when(cartHandler).canHandleCart(cart);*/

      /*when(cartHandler.canHandleCart(cart)).then(i->{
          Cart argumentCart=i.getArgument(0);
          argumentCart.clearCart();
          return true;
      });//zamiennik*/

        willAnswer(invocationOnMock -> {
            Cart argumentCart =invocationOnMock.getArgument(0);
            argumentCart.clearCart();
            return true;
        }).given(cartHandler).canHandleCart(cart);

        given(cartHandler.canHandleCart(cart)).will(i->{
            Cart argumentCart=i.getArgument(0);
            argumentCart.clearCart();
            return true;
        });//zamiennik

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPrepare(cart);  //zamienik
        assertThat(resultCart.getOrders().size(),equalTo(0));
    }

    @Test
    void deliveryShouldBeFree(){

        //given
        Cart cart=new Cart();
        cart.addOrderToCart(new Order());
        cart.addOrderToCart(new Order());
        cart.addOrderToCart(new Order());

        doCallRealMethod().when(cartHandler).isDeliverFree(cart);//zamiennik
        given(cartHandler.isDeliverFree(cart)).willCallRealMethod();

        //when
        boolean isDeliverFree=cartHandler.isDeliverFree(cart);

        //then
        assertTrue(isDeliverFree);
    }
}