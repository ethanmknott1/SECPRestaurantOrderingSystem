package orderflow;

import foodobjects.FoodItem;
import foodobjects.Ingredient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GuiAPI {
    private Order order = new Order();
    private DatabaseOutput databaseOutputObject = new DatabaseOutput();
    private int counter = 0; // contains IDs that are set to FoodItems

    public void backupOrderToDatabase(Order order) throws IOException {
        databaseOutputObject.backupOrderToTXTFile(order);
    }

    public Order getOrder() {
        return order;
    }

    public void addMealToOrder(ArrayList<FoodItem> foodItems) {
        for (int i = 0; i < foodItems.size(); i++) {
            foodItems.get(i).setID(this.counter);
            this.counter++;
            order.addFood(foodItems.get(i));
        }
    }

    public void addItemToOrder(FoodItem food) {
        food.setID(this.counter);
        this.counter++;
        order.addFood(food);
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for (int i = 0; i < order.getOrder().size(); i++) {
            totalPrice += order.getOrder().get(i).getPrice();
        }

        return totalPrice;
    }

    public void removeItemFromOrder(int ID) {
        order.deleteFoodByID(ID);
        this.counter--;
    }

    public String getItemNameByID(int ID) {
        return order.getItemNameByID(ID);
    }

    public ArrayList<Ingredient> getIngredientsByFoodID(int ID) {
        return order.getIngredientsByFoodID(ID);
    }

    public HashMap<String, Boolean> getIngredientsSelectedByFoodID(int ID) {
        return order.getIngredientsSelectedByFoodID(ID);
    }

    public void addIngredientBackToItemByFoodID(int ID, String ingredientName) {
        order.addIngredientBackToItemByFoodID(ID, ingredientName);
    }

    public void removeIngredientByFoodIDAndIngredientNameForList(String ingredient, int foodID) {
        order.removeIngredientFromItemByFoodIDAndIngredientName(ingredient, foodID);
    }

    public String[] getFoodItemsFromOrder() {
        ArrayList<FoodItem> items = order.getOrder();
        String[] ret = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            ret[i] = items.get(i).getFoodName();
        }

        return ret;
    }

    public void clearOrder() {
        this.order = new Order();
        this.databaseOutputObject = new DatabaseOutput();
        this.counter = 0;
    }

}