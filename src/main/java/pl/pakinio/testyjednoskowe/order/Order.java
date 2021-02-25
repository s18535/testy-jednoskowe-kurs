package pl.pakinio.testyjednoskowe.order;

import pl.pakinio.testyjednoskowe.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private OrderStatus orderStatus;
    private List<Meal> meals = new ArrayList<>();

    void cancle() {
        this.meals.clear();
    }

    public void addMealToOrder(Meal meal) {
        this.meals.add(meal);
    }

    public void removeMealFromOder(Meal meal) {
        this.meals.remove(meal);
    }

    public void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus=orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    int totalPrice() {

        int sum = this.meals.stream().mapToInt(meal -> meal.getPrize()).sum();

        if (sum<0){
            throw new IllegalStateException("Price limit exceeded");
        }else {
            return sum;
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
