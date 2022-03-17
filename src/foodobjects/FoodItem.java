package foodobjects;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodItem {
    private ArrayList<Ingredient> ingredientList = new ArrayList<>();
    private HashMap<String, Boolean> ingredientSelected = new HashMap<>();
    private String foodName;
    private int ID; //This will be an easy way to tell what item we need to delete from an order.
    private int price;

    public FoodItem(String foodName, ArrayList<Ingredient> ingredientList) {
        this.foodName = foodName;
        this.ingredientList = ingredientList;

        for(int i = 0; i < ingredientList.size(); i++) {
            ingredientSelected.put(ingredientList.get(i).getIngredient(), Boolean.TRUE);
        }
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void addIngredientBackToItem(String ingredient) {
        ingredientSelected.put(ingredient, Boolean.TRUE);
    }

    public void removeIngredientFromFoodItem(String ingredientName) {
        ingredientSelected.replace(ingredientName, Boolean.FALSE);
    }

    public ArrayList<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public HashMap<String, Boolean> getIngredientSelected() {
        return ingredientSelected;
    }
}