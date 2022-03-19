package GUI;

// GUI dependencies
import javax.swing.*; // Java GUI library
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.awt.event.*;
import java.io.IOException;

// backend dependencies
import orderflow.*;
import foodobjects.*;

import static java.awt.SystemColor.text;

public class GUI {
    static GuiAPI api;

    // global GUI objects (so they may be defined in constructor and referenced via ActionListener)
    JFrame mainFrame;

    JFrame itemOptionsFrame;

    // panel components for housing various layouts and other components (JButton, JList)
    JPanel mainPanel;
    JPanel headersPanel;
    JPanel centerPanel;
    JPanel mealsPanel;
    JPanel aLaCartePanel;
    JPanel summaryPanel;
    JPanel orderEditPanel;

    JPanel itemOptionsMainPanel;
    JPanel itemOptionsHeadingPanel;
    JPanel itemOptionsOptionPanel;
    JPanel itemOptionsButtonsPanel;

    // panel specifically used for creating visual spacing in the mainFrame BorderLayout
    JPanel spacing0;

    // JList for showing order breakdown
    JList<String> summaryList = new JList<>();

    // various JButtons for menu items and order options
    JButton btn_burger;
    JButton btn_double_burger;
    JButton btn_crispy_sandwich;
    JButton btn_grilled_sandwich;
    JButton btn_fish_sandwich;
    JButton btn_nuggets;
    JButton btn_pep_pizza;
    JButton btn_meat_pizza;
    JButton btn_fries;
    JButton btn_tots;
    JButton btn_mozz_stix;
    JButton btn_onion_rings;
    JButton btn_water;
    JButton btn_cola;
    JButton btn_coffee;
    JButton btn_juice;
    JButton btn_item_options;
    JButton btn_delete_item;
    JButton btn_complete_order;

    JButton btn_item_options_cancel;
    JButton btn_item_options_save;

    public GUI() {
        // create relevant buttons for the menu
        btn_burger = new JButton("<html>Single Cheeseburger<br />Side: Fries</html>");
        btn_double_burger = new JButton("<html>Double Cheeseburger<br />Side: Fries</html>");
        btn_crispy_sandwich = new JButton("<html>Crispy Chicken Sandwich<br />Side: Fries</html>");
        btn_grilled_sandwich = new JButton("<html>Grilled Chicken Sandwich<br />Side: Fries</html>");
        btn_fish_sandwich = new JButton("<html>Fish Fillet Sandwich<br />Side: Tater Tots</html>");
        btn_nuggets = new JButton("<html>10pc Chicken Nuggets<br />Side: Fries</html>");
        btn_pep_pizza = new JButton("<html>Pepperoni Pizza<br />Side: Mozzarella Sticks</html>");
        btn_meat_pizza = new JButton("<html>Meat Lovers Pizza<br />Side: Mozzarella Sticks</html>");
        btn_fries = new JButton("French Fries");
        btn_tots = new JButton("Tater Tots");
        btn_mozz_stix = new JButton("Mozzarella Sticks");
        btn_onion_rings = new JButton("Onion Rings");
        btn_water = new JButton("Water");
        btn_cola = new JButton("Cola");
        btn_coffee = new JButton("Coffee");
        btn_juice = new JButton("Juice");
        btn_item_options = new JButton("Item Options");
        btn_delete_item = new JButton("Delete Item");
        btn_complete_order = new JButton("Complete Order");

        // Main Frame and Panel
        mainFrame = new JFrame();

        mainPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        layout.setHgap(100);
        layout.setVgap(50);
        mainPanel.setLayout(layout);

        // Headers Panel
        headersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel headers = new JLabel(
                "<html><h1>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Meals&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;A La Carte&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Summary</h1></html>",
                SwingConstants.LEFT);
        headersPanel.add(headers);

        // Center Panel that houses mealsPanel, aLaCartePanel, summaryPanel, and orderEditPanel
        centerPanel = new JPanel();
        GridLayout centerLayout = new GridLayout(1, 0);
        centerLayout.setHgap(50);
        centerPanel.setLayout(centerLayout);

        // Meals Panel layout
        mealsPanel = new JPanel();
        GridLayout mealsLayout = new GridLayout(0, 2);
        mealsLayout.setHgap(10);
        mealsLayout.setVgap(10);
        mealsPanel.setLayout(mealsLayout);

        // add buttons and ActionListener to similarly behaving button
        btn_burger.addActionListener(new CustomActionListener());
        mealsPanel.add(btn_burger);
        btn_double_burger.addActionListener(new CustomActionListener());
        mealsPanel.add(btn_double_burger);
        btn_crispy_sandwich.addActionListener(new CustomActionListener());
        mealsPanel.add(btn_crispy_sandwich);
        btn_grilled_sandwich.addActionListener(new CustomActionListener());
        mealsPanel.add(btn_grilled_sandwich);
        btn_fish_sandwich.addActionListener(new CustomActionListener());
        mealsPanel.add(btn_fish_sandwich);
        btn_nuggets.addActionListener(new CustomActionListener());
        mealsPanel.add(btn_nuggets);
        btn_pep_pizza.addActionListener(new CustomActionListener());
        mealsPanel.add(btn_pep_pizza);
        btn_meat_pizza.addActionListener(new CustomActionListener());
        mealsPanel.add(btn_meat_pizza);

        // A La Carte Panel layout
        aLaCartePanel = new JPanel();
        GridLayout aLaCarteLayout = new GridLayout(0, 2);
        aLaCarteLayout.setHgap(10);
        aLaCarteLayout.setVgap(10);
        aLaCartePanel.setLayout(aLaCarteLayout);
        btn_fries.addActionListener(new CustomActionListener());
        aLaCartePanel.add(btn_fries);
        btn_tots.addActionListener(new CustomActionListener());
        aLaCartePanel.add(btn_tots);
        btn_mozz_stix.addActionListener(new CustomActionListener());
        aLaCartePanel.add(btn_mozz_stix);
        btn_onion_rings.addActionListener(new CustomActionListener());
        aLaCartePanel.add(btn_onion_rings);
        btn_water.addActionListener(new CustomActionListener());
        aLaCartePanel.add(btn_water);
        btn_cola.addActionListener(new CustomActionListener());
        aLaCartePanel.add(btn_cola);
        btn_coffee.addActionListener(new CustomActionListener());
        aLaCartePanel.add(btn_coffee);
        btn_juice.addActionListener(new CustomActionListener());
        aLaCartePanel.add(btn_juice);

        // Summary Panel layout
        summaryPanel = new JPanel();
        summaryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        summaryList.setLayoutOrientation(JList.VERTICAL);
        summaryList.setVisibleRowCount(-1);
        summaryList.setPreferredSize(new Dimension(350, 900));
        summaryPanel.add(summaryList);

        // Order Edit Panel layout
        orderEditPanel = new JPanel();
        GridLayout orderEditLayout = new GridLayout(3, 1);
        orderEditLayout.setHgap(10);
        orderEditLayout.setVgap(80);
        orderEditPanel.setLayout(orderEditLayout);
        btn_item_options.addActionListener(new CustomActionListener());
        orderEditPanel.add(btn_item_options);
        btn_delete_item.addActionListener(new CustomActionListener());
        orderEditPanel.add(btn_delete_item);
        btn_complete_order.addActionListener(new CustomActionListener());
        orderEditPanel.add(btn_complete_order);

        // Add panels to the Center Panel to create adequate horizontal spacing
        centerPanel.add(mealsPanel);
        centerPanel.add(aLaCartePanel);
        centerPanel.add(summaryPanel);
        centerPanel.add(orderEditPanel);

        // Spacing Panel
        spacing0 = new JPanel();
        spacing0.setPreferredSize(new Dimension(10, 170));

        // Add all relevant panels to the Main Panel
        mainPanel.add(headersPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(spacing0, BorderLayout.SOUTH);

        // Further define Main Frame layout and functionality
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Ordering System");
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void ItemOptionsMenu(int itemIndex) {

        //Button declaration
        btn_item_options_save = new JButton("Save");
        btn_item_options_cancel = new JButton("Cancel");

        //Main frame declaration
        itemOptionsFrame = new JFrame();

        // Set up the main panel for the item options screen
        itemOptionsMainPanel = new JPanel();
        BorderLayout itemOptionsLayout = new BorderLayout();
        itemOptionsLayout.setHgap(100);
        itemOptionsLayout.setVgap(50);
        itemOptionsMainPanel.setLayout(itemOptionsLayout);

        // Set up the heading section of the item options screen
        itemOptionsHeadingPanel = new JPanel();
        JLabel itemName = new JLabel(api.getItemNameByID(itemIndex));
        itemName.setBounds(0, 0, 100, 100);
        itemName.setFont(new Font(null, Font.BOLD, 20));
        itemOptionsHeadingPanel.add(itemName, BorderLayout.NORTH);

        // Set up the options section of the item options screen
        itemOptionsOptionPanel = new JPanel();
        GridLayout itemOptionsOptionLayout = new GridLayout(0, 1);
        itemOptionsOptionLayout.setHgap(10);
        itemOptionsOptionLayout.setVgap(10);
        itemOptionsOptionPanel.setLayout(itemOptionsOptionLayout);

        // Set up the radio buttons for the item options screen
        HashMap<String, Boolean> itemOptions = api.getIngredientsSelectedByFoodID(itemIndex);
        //Loop through the hashmap and add the radio buttons to the panel per ingredient
        for (HashMap.Entry<String, Boolean> entry : itemOptions.entrySet()) {
            JRadioButton option = new JRadioButton(entry.getKey());
            option.setSelected(entry.getValue());
            itemOptionsOptionPanel.add(option);
        }

        // Set up the button section of the item options screen
        itemOptionsButtonsPanel = new JPanel();
        GridLayout itemOptionsButtonLayout = new GridLayout(0, 2);
        itemOptionsButtonLayout.setHgap(10);
        itemOptionsButtonLayout.setVgap(10);
        itemOptionsButtonsPanel.setLayout(itemOptionsButtonLayout);
        btn_item_options_save.addActionListener(new CustomActionListener());
        itemOptionsButtonsPanel.add(btn_item_options_save);
        btn_item_options_cancel.addActionListener(new CustomActionListener());
        itemOptionsButtonsPanel.add(btn_item_options_cancel);

        // Add all the panels to the main panel
        itemOptionsMainPanel.add(itemOptionsHeadingPanel, BorderLayout.NORTH);
        itemOptionsMainPanel.add(itemOptionsOptionPanel, BorderLayout.CENTER);
        itemOptionsMainPanel.add(itemOptionsButtonsPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame and display it
        itemOptionsFrame.add(itemOptionsMainPanel, BorderLayout.CENTER);
        itemOptionsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        itemOptionsFrame.setLocation(960, 540);
        itemOptionsFrame.setTitle("Item Options");

        itemOptionsFrame.pack();
        itemOptionsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        api = new GuiAPI();
        new GUI();
    }

    class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btn_burger) {
                Meal burgerMeal = new Meal();
                burgerMeal.setMealName("Single Cheeseburger Meal");

                ArrayList<Ingredient> ingredientList = new ArrayList<>();
                ingredientList.add(new Ingredient("Buns"));
                ingredientList.add(new Ingredient("Beef Patty"));
                ingredientList.add(new Ingredient("Cheese"));
                ingredientList.add(new Ingredient("Lettuce"));
                ingredientList.add(new Ingredient("Tomato"));
                ingredientList.add(new Ingredient("Onion"));
                ingredientList.add(new Ingredient("Pickles"));
                ingredientList.add(new Ingredient("Ketchup"));
                ingredientList.add(new Ingredient("Mustard"));

                FoodItem burger = new FoodItem("Single Cheeseburger", ingredientList, 5.45);
                burgerMeal.addFood(burger);

                FoodItem fries = new FoodItem(IngredientlessItems.FRIES.getValue(), new ArrayList<>());
                burgerMeal.addFood(fries);

                FoodItem cola = new FoodItem(IngredientlessItems.COLA.getValue(), new ArrayList<>());
                burgerMeal.addFood(cola);

                api.addMealToOrder(burgerMeal.getFoodList());

            }

            else if (e.getSource() == btn_double_burger) {
                Meal doubleBurgerMeal = new Meal();
                doubleBurgerMeal.setMealName("Double Cheeseburger Meal");

                ArrayList<Ingredient> ingredientList = new ArrayList<>();
                ingredientList.add(new Ingredient("Buns"));
                ingredientList.add(new Ingredient("Beef Patty x2"));
                ingredientList.add(new Ingredient("Cheese"));
                ingredientList.add(new Ingredient("Lettuce"));
                ingredientList.add(new Ingredient("Tomato"));
                ingredientList.add(new Ingredient("Onion"));
                ingredientList.add(new Ingredient("Pickles"));
                ingredientList.add(new Ingredient("Ketchup"));
                ingredientList.add(new Ingredient("Mustard"));

                FoodItem burger = new FoodItem("Double Cheeseburger", ingredientList, 6.75);
                doubleBurgerMeal.addFood(burger);

                FoodItem fries = new FoodItem(IngredientlessItems.FRIES.getValue(), new ArrayList<>());
                doubleBurgerMeal.addFood(fries);

                FoodItem cola = new FoodItem(IngredientlessItems.COLA.getValue(), new ArrayList<>());
                doubleBurgerMeal.addFood(cola);

                api.addMealToOrder(doubleBurgerMeal.getFoodList());
            }

            else if (e.getSource() == btn_crispy_sandwich) {
                Meal crispySandwichMeal = new Meal();
                crispySandwichMeal.setMealName("Crispy Chicken Sandwich Meal");

                ArrayList<Ingredient> ingredientList = new ArrayList<>();
                ingredientList.add(new Ingredient("Buns"));
                ingredientList.add(new Ingredient("Fried Chicken Breast"));
                ingredientList.add(new Ingredient("Tomato"));
                ingredientList.add(new Ingredient("Pickles"));
                ingredientList.add(new Ingredient("Mayo"));

                FoodItem sandwich = new FoodItem("Crispy Chicken Sandwich", ingredientList, 5.25);
                crispySandwichMeal.addFood(sandwich);

                FoodItem fries = new FoodItem(IngredientlessItems.FRIES.getValue(), new ArrayList<>());
                crispySandwichMeal.addFood(fries);

                FoodItem cola = new FoodItem(IngredientlessItems.COLA.getValue(), new ArrayList<>());
                crispySandwichMeal.addFood(cola);

                api.addMealToOrder(crispySandwichMeal.getFoodList());
            }

            else if (e.getSource() == btn_grilled_sandwich) {
                Meal grilledSandwichMeal = new Meal();
                grilledSandwichMeal.setMealName("Grilled Chicken Sandwich Meal");

                ArrayList<Ingredient> ingredientList = new ArrayList<>();
                ingredientList.add(new Ingredient("Buns"));
                ingredientList.add(new Ingredient("Grilled Chicken Breast"));
                ingredientList.add(new Ingredient("Tomato"));
                ingredientList.add(new Ingredient("Pickles"));
                ingredientList.add(new Ingredient("Mayo"));

                FoodItem sandwich = new FoodItem("Grilled Chicken Sandwich", ingredientList, 6.24);
                grilledSandwichMeal.addFood(sandwich);

                FoodItem fries = new FoodItem(IngredientlessItems.FRIES.getValue(), new ArrayList<>());
                grilledSandwichMeal.addFood(fries);

                FoodItem cola = new FoodItem(IngredientlessItems.COLA.getValue(), new ArrayList<>());
                grilledSandwichMeal.addFood(cola);

                api.addMealToOrder(grilledSandwichMeal.getFoodList());
            }

            else if (e.getSource() == btn_fish_sandwich) {
                Meal fishSandwichMeal = new Meal();
                fishSandwichMeal.setMealName("Fish Fillet Sandwich Meal");

                ArrayList<Ingredient> ingredientList = new ArrayList<>();
                ingredientList.add(new Ingredient("Buns"));
                ingredientList.add(new Ingredient("Fish Fillet"));
                ingredientList.add(new Ingredient("Cheese"));
                ingredientList.add(new Ingredient("Lettuce"));
                ingredientList.add(new Ingredient("Pickles"));

                FoodItem sandwich = new FoodItem("Fish Fillet Sandwich", ingredientList, 4.25);
                fishSandwichMeal.addFood(sandwich);

                FoodItem tots = new FoodItem(IngredientlessItems.TOTS.getValue(), new ArrayList<>());
                fishSandwichMeal.addFood(tots);

                FoodItem cola = new FoodItem(IngredientlessItems.COLA.getValue(), new ArrayList<>());
                fishSandwichMeal.addFood(cola);

                api.addMealToOrder(fishSandwichMeal.getFoodList());
            }

            else if (e.getSource() == btn_nuggets) {
                Meal nuggetsMeal = new Meal();
                nuggetsMeal.setMealName("10pc Nuggets Meal");

                FoodItem nugs = new FoodItem(IngredientlessItems.NUGS.getValue(), new ArrayList<>(), 5.25);
                nuggetsMeal.addFood(nugs);

                FoodItem fries = new FoodItem(IngredientlessItems.FRIES.getValue(), new ArrayList<>());
                nuggetsMeal.addFood(fries);

                FoodItem cola = new FoodItem(IngredientlessItems.COLA.getValue(), new ArrayList<>());
                nuggetsMeal.addFood(cola);

                api.addMealToOrder(nuggetsMeal.getFoodList());
            }

            else if (e.getSource() == btn_pep_pizza) {
                Meal pepperoniPizzaMeal = new Meal();
                pepperoniPizzaMeal.setMealName("Pepperoni Pizza Meal");

                ArrayList<Ingredient> ingredientList = new ArrayList<>();
                ingredientList.add(new Ingredient("Dough"));
                ingredientList.add(new Ingredient("Tomato Sauce"));
                ingredientList.add(new Ingredient("Cheese"));
                ingredientList.add(new Ingredient("Pepperoni"));

                FoodItem pizza = new FoodItem("Pepperoni Pizza", ingredientList, 7.25);
                pepperoniPizzaMeal.addFood(pizza);

                FoodItem stix = new FoodItem(IngredientlessItems.MOZZ_STIX.getValue(), new ArrayList<>());
                pepperoniPizzaMeal.addFood(stix);

                FoodItem cola = new FoodItem(IngredientlessItems.COLA.getValue(), new ArrayList<>());
                pepperoniPizzaMeal.addFood(cola);

                api.addMealToOrder(pepperoniPizzaMeal.getFoodList());
            }

            else if (e.getSource() == btn_meat_pizza) {
                Meal meatPizzaMeal = new Meal();
                meatPizzaMeal.setMealName("Meat Lovers Pizza Meal");

                ArrayList<Ingredient> ingredientList = new ArrayList<>();
                ingredientList.add(new Ingredient("Dough"));
                ingredientList.add(new Ingredient("Tomato Sauce"));
                ingredientList.add(new Ingredient("Cheese"));
                ingredientList.add(new Ingredient("Pepperoni"));
                ingredientList.add(new Ingredient("Sausage"));
                ingredientList.add(new Ingredient("Ham"));
                ingredientList.add(new Ingredient("Bacon"));

                FoodItem pizza = new FoodItem("Meat Lovers Pizza", ingredientList, 8.25);
                meatPizzaMeal.addFood(pizza);

                FoodItem stix = new FoodItem(IngredientlessItems.MOZZ_STIX.getValue(), new ArrayList<>());
                meatPizzaMeal.addFood(stix);

                FoodItem cola = new FoodItem(IngredientlessItems.COLA.getValue(), new ArrayList<>());
                meatPizzaMeal.addFood(cola);

                api.addMealToOrder(meatPizzaMeal.getFoodList());
            }

            else if (e.getSource() == btn_fries) {
                FoodItem fries = new FoodItem(IngredientlessItems.FRIES.getValue(), new ArrayList<>());
                api.addItemToOrder(fries);
            }

            else if (e.getSource() == btn_tots) {
                FoodItem tots = new FoodItem(IngredientlessItems.TOTS.getValue(), new ArrayList<>());
                api.addItemToOrder(tots);
            }

            else if (e.getSource() == btn_mozz_stix) {
                FoodItem stix = new FoodItem(IngredientlessItems.MOZZ_STIX.getValue(), new ArrayList<>());
                api.addItemToOrder(stix);
            }

            else if (e.getSource() == btn_onion_rings) {
                FoodItem rings = new FoodItem(IngredientlessItems.ONION_RINGS.getValue(), new ArrayList<>());
                api.addItemToOrder(rings);
            }

            else if (e.getSource() == btn_water) {
                FoodItem water = new FoodItem(IngredientlessItems.WATER.getValue(), new ArrayList<>());
                api.addItemToOrder(water);
            }

            else if (e.getSource() == btn_cola) {
                FoodItem cola = new FoodItem(IngredientlessItems.COLA.getValue(), new ArrayList<>());
                api.addItemToOrder(cola);
            }

            else if (e.getSource() == btn_coffee) {
                FoodItem coffee = new FoodItem(IngredientlessItems.COFFEE.getValue(), new ArrayList<>());
                api.addItemToOrder(coffee);
            }

            else if (e.getSource() == btn_juice) {
                FoodItem juice = new FoodItem(IngredientlessItems.JUICE.getValue(), new ArrayList<>());
                api.addItemToOrder(juice);
            }

            else if (e.getSource() == btn_item_options) {
                int selectedIndex = summaryList.getSelectedIndex();
                if (selectedIndex != -1 && selectedIndex != summaryList.getModel().getSize() - 1) {
                    if (api.getIngredientsByFoodID(selectedIndex).size() > 0) {
                        ItemOptionsMenu(selectedIndex);
                    }
                }
            }

            else if (e.getSource() == btn_delete_item) {
                int selectedIndex = summaryList.getSelectedIndex();
                if (selectedIndex != -1 && selectedIndex != summaryList.getModel().getSize() - 1) {
                    api.removeItemFromOrder(selectedIndex);
                }
            }

            else if (e.getSource() == btn_complete_order) {
                try {
                    if (api.getOrder().getOrder().size() == 0)
                        return;
                    api.backupOrderToDatabase(api.getOrder());
                    api.clearOrder();
                } catch (IOException ex) {
                    System.out.println("exception");
                }
            }

            else if (e.getSource() == btn_item_options_save) {
                //Gets the label from the heading to then find the index of where the food item is in the order
                JLabel label = (JLabel) itemOptionsHeadingPanel.getComponent(0);
                //Gets all food items from the order so during loop there are not significantly more than it needs to be
                String[] foodItems = api.getFoodItemsFromOrder();
                int index = -1;
                //Finds the index of where the food item is in the order
                for (int i = 0; i < foodItems.length; i++) {
                    if (foodItems[i].equals(label.getText())) {
                        index = i;
                        break;
                    }
                }
                //Loops through all of the options and checks if it is selected
                //If it is, add the ingredient back to the food item
                //If it is not, remove the ingredient from the food item
                for (int i = 0; i < itemOptionsOptionPanel.getComponents().length; i++) {
                    JRadioButton option = (JRadioButton) itemOptionsOptionPanel.getComponents()[i];
                    if (option.isSelected()) {
                        api.addIngredientBackToItemByFoodID(index, option.getText());
                    } else {
                        api.removeIngredientByFoodIDAndIngredientNameForList(option.getText(), index);
                    }
                }
                // Dispose of the optoins frame as it is no longer needed
                itemOptionsFrame.dispose();
            }

            else if (e.getSource() == btn_item_options_cancel) {
                // Dispose of the optoins frame as it is no longer needed
                itemOptionsFrame.dispose();
            }

            summaryList.removeAll();
            summaryList.setListData(api.getFoodItemsAndPriceFromOrder());
        }
    }

}