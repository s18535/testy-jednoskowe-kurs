package pl.pakinio.testyjednoskowe;

import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
        Order order = new Order();
        order.addMealOrder(meal);

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