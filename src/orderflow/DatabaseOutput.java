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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();

        //Adapted from https://www.youtube.com/watch?v=k3K9KHPYZFc
        new File("database/").mkdirs();
        File file = new File("database/", dtf.format(now) + ".txt");
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(dtf.format(now) + " ORDER");
        pw.println("");
        pw.println("ITEMS");

        double total = 0;

        for(int i = 0; i < order.getOrder().size(); i++) {
            total += order.getOrder().get(i).getPrice();
            pw.println(order.getOrder().get(i).getFoodName() + String.format(" $%.2f", order.getOrder().get(i).getPrice()));
            for(int j = 0; j < order.getOrder().get(i).getIngredientList().size(); j++) {
                //This is where the output decides if an ingredient was selected or not
                if (order.getOrder().get(i).getIngredientSelected().get(order.getOrder().get(i).getIngredientList().get(j).getIngredient())) {
                    pw.println("    " + order.getOrder().get(i).getIngredientList().get(j).getIngredient());
                }
            }
            pw.println("");
        }

        pw.println("TOTAL");
        pw.println(String.format("$%.2f", total));
        pw.println("");

        pw.close();
        fw.close();
    }
}
