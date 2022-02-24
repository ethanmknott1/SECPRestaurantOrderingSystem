package foodobjects;

import java.util.ArrayList;

public class Meal {
    private ArrayList<FoodItem> foodList = new ArrayList<>();
    private String mealName;

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public ArrayList<FoodItem> getFoodList() {
        return foodList;
    }

    public void addFood(FoodItem foodItem) {
        foodList.add(foodItem);
    }
}