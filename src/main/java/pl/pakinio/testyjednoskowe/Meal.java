package pl.pakinio.testyjednoskowe;

import java.util.Objects;

public class Meal {
    private int prize;
    private String name;

    public Meal(int prize) {
        this.prize = prize;
    }

    public Meal(int prize, String name) {
        this.prize = prize;
        this.name = name;
    }

    public int getPrize() {
        return prize;
    }

    public int getDiscountedPrice(int discount){
        return this.prize-discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return prize == meal.prize && Objects.equals(name, meal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prize, name);
    }
}
