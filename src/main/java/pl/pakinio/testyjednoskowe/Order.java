package pl.pakinio.testyjednoskowe;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Meal> meals = new ArrayList<>();

    void cancle(){
        this.meals.clear();
    }

    public void addMealOrder(Meal meal) {
        this.meals.add(meal);
    }

    public void removeMealFromOder(Meal meal) {
        this.meals.remove(meal);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
