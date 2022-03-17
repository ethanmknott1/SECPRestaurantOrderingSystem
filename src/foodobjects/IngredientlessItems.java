package foodobjects;

public enum IngredientlessItems {
    NUGS("Chicken Nuggets"),
    FRIES("Fries"),
    TOTS("Tater Tots"),
    MOZZ_STIX("Mozzarella Sticks"),
    ONION_RINGS("Onion Rings"),
    WATER("Water"),
    COLA("Cola"),
    COFFEE("Coffee"),
    JUICE("Juice");
    
    private String value;

    IngredientlessItems(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
