package pl.pakinio.testyjednoskowe.order;

import org.junit.jupiter.api.*;
import pl.pakinio.testyjednoskowe.Meal;
import pl.pakinio.testyjednoskowe.order.Order;
import pl.pakinio.testyjednoskowe.order.OrderBackup;

import java.io.IOException;

class OrderBackupTest {
    private static OrderBackup orderBackup;

    @BeforeAll
    static void setup() throws IOException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @BeforeEach
    void apppendAtTheStartOfTheLine() throws IOException {
        orderBackup.getWriter().append("New order: ");
    }

    @AfterEach
    void appendAtTheEndOfTheLine() throws IOException {
        orderBackup.getWriter().append(" backed up. ");
    }

    @Tag("fries")
    @Test
    void backupOrderWithOneMeal() throws IOException {
        //given
        Meal meal=new Meal(7,"Frise");
        pl.pakinio.testyjednoskowe.order.Order order = new Order();
        order.addMealToOrder(meal);

        //when
        orderBackup.backupOrder(order);

        //then
        System.out.println("Order: "+order.toString()+" backed up.");
    }

    @AfterAll
    static void tearDown() throws IOException {
        orderBackup.closeFile();
    }

}