// GUI dependencies
import javax.swing.*; // Java GUI library

import foodobjects.FoodItem;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.Dimension;

// backend dependencies
import orderflow.GuiAPI;
import orderflow.Order;

public class GUI implements ActionListener {
    static Order order;

    // global GUI objects (so they may be defined in constructor and referenced via user action)
    JFrame mainFrame;
    
    JPanel mainPanel;
    JPanel headersPanel;
    JPanel centerPanel;
    JPanel mealsPanel;
    JPanel aLaCartePanel;
    JPanel summaryPanel;
    JPanel orderEditPanel;

    JPanel spacing0;
    JPanel spacing1;
    JPanel spacing2;
    JPanel spacing3;
    JPanel spacing4;

    public GUI(){
        JButton b1 = new JButton("<html>Single Cheeseburger<br />Side: Fries</html>");
        JButton b2 = new JButton("<html>Double Cheeseburger<br />Side: Fries</html>");
        JButton b3 = new JButton("<html>Crispy Chicken Sandwich<br />Side: Fries</html>");
        JButton b4 = new JButton("<html>Grilled Chicken Sandwich<br />Side: Fries</html>");
        JButton b5 = new JButton("<html>Fish Fillet Sandwich<br />Side: Tater Tots</html>");
        JButton b6 = new JButton("<html>10pc Chicken Nuggets<br />Side: Fries</html>");
        JButton b7 = new JButton("<html>Pepperoni Pizza<br />Side: Fries</html>");
        JButton b8 = new JButton("<html>Meat Lovers Pizza<br />Side: Fries</html>");
        JButton b9 = new JButton("French Fries");
        JButton b10 = new JButton("Tater Tots");
        JButton b11 = new JButton("Hashbrowns");
        JButton b12 = new JButton("Onion Rings");
        JButton b13 = new JButton("Water");
        JButton b14 = new JButton("Cola");
        JButton b15 = new JButton("Coffee");
        JButton b16 = new JButton("Juice");
        JButton b17 = new JButton("Item Options");
        JButton b18 = new JButton("Delete Item");
        JButton b19 = new JButton("Complete Order");

        // define GUI objects
        mainFrame = new JFrame();
        
        mainPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        layout.setHgap(100);
        layout.setVgap(50);
        //GridLayout layout = new GridLayout(1, 9);
        //layout.setHgap(10);
        //layout.setVgap(10);
        mainPanel.setLayout(layout);

        headersPanel = new JPanel();
        JLabel headers = new JLabel("<html><h1>Meals&emsp;&emsp;&emsp;A La Carte&emsp;&emsp;&emsp;Summary</h1></html>");
        headersPanel.add(headers);

        centerPanel = new JPanel();
        GridLayout centerLayout = new GridLayout(1, 0);
        centerLayout.setHgap(50);
        centerPanel.setLayout(centerLayout);

        mealsPanel = new JPanel();
        GridLayout mealsLayout = new GridLayout(0, 2);
        mealsLayout.setHgap(10);
        mealsLayout.setVgap(10);
        mealsPanel.setLayout(mealsLayout);
        mealsPanel.add(b1);
        mealsPanel.add(b2);
        mealsPanel.add(b3);
        mealsPanel.add(b4);
        mealsPanel.add(b5);
        mealsPanel.add(b6);
        mealsPanel.add(b7);
        mealsPanel.add(b8);

        aLaCartePanel = new JPanel();
        GridLayout aLaCarteLayout = new GridLayout(0, 2);
        aLaCarteLayout.setHgap(10);
        aLaCarteLayout.setVgap(10);
        aLaCartePanel.setLayout(aLaCarteLayout);
        aLaCartePanel.add(b9);
        aLaCartePanel.add(b10);
        aLaCartePanel.add(b11);
        aLaCartePanel.add(b12);
        aLaCartePanel.add(b13);
        aLaCartePanel.add(b14);
        aLaCartePanel.add(b15);
        aLaCartePanel.add(b16);

        summaryPanel = new JPanel();
        JList<FoodItem> summaryList = new JList<FoodItem>();
        summaryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        summaryList.setLayoutOrientation(JList.VERTICAL);
        summaryList.setVisibleRowCount(-1);
        summaryList.setPreferredSize(new Dimension(900, 900));
        JScrollPane listScroller = new JScrollPane(summaryList);
        listScroller.setPreferredSize(new Dimension(10, 10));
        summaryPanel.add(summaryList);

        orderEditPanel = new JPanel();
        GridLayout orderEditLayout = new GridLayout(3, 1);
        orderEditLayout.setHgap(10);
        orderEditLayout.setVgap(80); 
        orderEditPanel.setLayout(orderEditLayout);
        orderEditPanel.add(b17);
        orderEditPanel.add(b18);
        orderEditPanel.add(b19);

        centerPanel.add(mealsPanel);
        centerPanel.add(aLaCartePanel);
        centerPanel.add(summaryPanel);
        centerPanel.add(orderEditPanel);

        spacing0 = new JPanel();
        spacing0.setPreferredSize(new Dimension(10, 170));

        mainPanel.add(headersPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(spacing0, BorderLayout.SOUTH);
        
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Ordering System");
        
        mainFrame.pack();
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
    }

    /*
    public ItemOptionsMenu(){

    }

    public OrderCompletionMenu(){

    }*/

    public static void main(String [] args){
        order = new Order();
        new GUI();
    }

    public void actionPerformed(ActionEvent e){

    }
}
