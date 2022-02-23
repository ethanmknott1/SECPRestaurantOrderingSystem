package foodobjects;

import java.util.ArrayList;

public class FoodItem {
    private ArrayList<Ingredient> ingredientList = new ArrayList<>();
    private String foodName;
    private int ID; //This will be an easy way to tell what item we need to delete from an order.
    private int price;

    FoodItem(String foodName, int ID, ArrayList<Ingredient> ingredientList) {
        this.foodName = foodName;
        this.ID = ID;
        this.ingredientList = ingredientList;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    public void removeIngredient(String ingredientName) {
        for(int i = 0; i < ingredientList.size(); i++) {
            if(ingredientName.equalsIgnoreCase(ingredientList.get(i).getIngredient())) {
                ingredientList.remove(i);
                return;
            }
        }
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
}