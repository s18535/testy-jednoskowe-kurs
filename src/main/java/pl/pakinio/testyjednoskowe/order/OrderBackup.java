package pl.pakinio.testyjednoskowe.order;

import java.io.*;

public class OrderBackup {
    private Writer writer;

    void createFile() throws FileNotFoundException {
        File file=new File("orderBackup.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter =new OutputStreamWriter(fileOutputStream);
        writer=new BufferedWriter(outputStreamWriter);
    }

    void  backupOrder(Order order) throws IOException {
        if (writer == null){
            throw new IOException("Backup file not created");
        }

        writer.append(order.toString());
    }
    void closeFile() throws IOException {
        writer.close();
    }

    public Writer getWriter() {
        return writer;
    }
}
