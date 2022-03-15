package foodobjects;

public enum IngredientlessItems {
    FRIES("Fries"),
    TOTS("Tater Tots"),
    HASHBROWNS("Hashbrowns"),
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
