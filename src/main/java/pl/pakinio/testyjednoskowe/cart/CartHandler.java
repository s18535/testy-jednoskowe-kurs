package pl.pakinio.testyjednoskowe.cart;

public interface CartHandler {
    boolean canHandleCart(Cart cart);
    void sendToPrepare(Cart cart);

    default boolean isDeliverFree(Cart cart){
        return cart.getOrders().size() > 2;
    }
}
