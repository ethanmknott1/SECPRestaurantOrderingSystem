package orderflow;

import foodobjects.FoodItem;
import foodobjects.Ingredient;

import java.util.ArrayList;

public class Order {
    private ArrayList<FoodItem> order = new ArrayList<>();

    public ArrayList<FoodItem> getOrder() {
        return order;
    }

    public void addFood(FoodItem food) {
        order.add(food);
    }

    public void deleteFoodByID(int ID) {
        for(int i = 0; i < order.size(); i++) {
            if(ID == order.get(i).getID()) {
                order.remove(i);
                return;
            }
        }
    }

    public String getItemNameByID(int ID) {
        for(int i = 0; i < order.size(); i++) {
            if(ID == order.get(i).getID()) {
                return order.get(i).getFoodName();
            }
        }
        return null;
    }

    public ArrayList<Ingredient> getIngredientsByFoodID(int ID) {
        for(int i = 0; i < order.size(); i++) {
            if(ID == order.get(i).getID()) {
                return order.get(i).getIngredientList();
            }
        }
        return null;
    }

    public void addIngredientByFoodID(int ID, Ingredient ingredient) {
        for(int i = 0; i < order.size(); i++) {
            if(ID == order.get(i).getID()) {
                order.get(i).addIngredient(ingredient);
            }
        }
    }

    public void removeIngredientByFoodIDAndIngredientName(String ingredient, int foodID) {
        for(int i = 0; i < order.size(); i++) {
            if(foodID == order.get(i).getID()) {
                order.get(i).removeIngredient(ingredient);
            }
        }
    }
}
