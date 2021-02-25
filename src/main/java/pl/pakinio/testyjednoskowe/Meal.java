package pl.pakinio.testyjednoskowe;

import java.util.Objects;

public class Meal {
    private int prize;
    private int quantity;
    private String name;

    public Meal(int prize) {
        this.prize = prize;
    }

    public Meal(int prize, String name) {
        this.prize = prize;
        this.name = name;
    }

    public Meal(int prize, int quantity, String name) {
        this.prize = prize;
        this.quantity = quantity;
        this.name = name;
    }

    public int getPrize() {
        return prize;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDiscountedPrice(int discount){
        if(discount > this.prize){
            throw new IllegalArgumentException("Discount canot be higher than the price!");
        }
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

    @Override
    public String toString() {
        return "Meal{" +
                "prize=" + prize +
                ", name='" + name + '\'' +
                '}';
    }
}
