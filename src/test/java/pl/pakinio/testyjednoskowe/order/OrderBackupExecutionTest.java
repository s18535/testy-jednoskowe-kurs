package pl.pakinio.testyjednoskowe.order;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionTest {


    //Time - test który sprawdza kolejnosc zdarzen, wątki
    @Test
    void callingBackupWithoutCreatingAFileFirstShouldThrowException() throws IOException {

        //given
        OrderBackup orderBackup = new OrderBackup();

        //then
        assertThrows(IOException.class, ()->orderBackup.backupOrder(new Order()));


    }
}
