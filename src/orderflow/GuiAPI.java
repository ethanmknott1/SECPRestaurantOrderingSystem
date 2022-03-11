package orderflow;

import foodobjects.FoodItem;
import foodobjects.Ingredient;

import java.io.IOException;
import java.util.ArrayList;

public class GuiAPI {
    private Order order = new Order();
    private DatabaseOutput databaseOutputObject = new DatabaseOutput();

    public void backupOrderToDatabase(Order order) throws IOException {
        databaseOutputObject.backupOrderToTXTFile(order);
    }

    public void addMealToOrder(ArrayList<FoodItem> foodItems) {
        for(int i = 0; i < foodItems.size(); i++) {
            order.addFood(foodItems.get(i));
        }
    }

    public void addItemToOrder(FoodItem food) {
        order.addFood(food);
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for(int i = 0; i < order.getOrder().size(); i++) {
            totalPrice += order.getOrder().get(i).getPrice();
        }

        return totalPrice;
    }

    public void removeItemFromOrder(int ID) {
        order.deleteFoodByID(ID);
    }

    public String getItemNameByID(int ID) {
        return order.getItemNameByID(ID);
    }

    public ArrayList<Ingredient> getIngredientsByFoodID(int ID) {
        return order.getIngredientsByFoodID(ID);
    }

    public void addIngredientByFoodID(int ID, Ingredient ingredient) {
        order.addIngredientByFoodID(ID, ingredient);
    }

    public void removeIngredientByFoodIDAndIngredientName(String ingredient, int foodID) {
        order.removeIngredientByFoodIDAndIngredientName(ingredient, foodID);
    }

}