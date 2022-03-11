package orderflow;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseOutput {

    public void backupOrderToTXTFile(Order order) throws IOException {
        //Block adapted from https://www.javatpoint.com/java-get-current-date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        //Adapted from https://www.youtube.com/watch?v=k3K9KHPYZFc
        File directoryFile = new File("database/");
        File file = new File(directoryFile, dtf.format(now));
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(dtf.format(now) + " ORDER");
        pw.println("");
        pw.println("ITEMS");

        for(int i = 0; i < order.getOrder().size(); i++) {
            pw.println(order.getOrder().get(i).getFoodName());
            for(int j = 0; j < order.getOrder().get(i).getIngredientList().size(); j++) {
                pw.println("    " + order.getOrder().get(i).getIngredientList().get(j).getIngredient());
            }
            pw.println("");
        }

        pw.close();
        fw.close();
    }
}
