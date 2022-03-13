package foodobjects;

public enum IngredientlessItems {
    RANCH("Ranch"),
    KETCHUP("Ketchup"),
    MUSTARD("Mustard"),
    MAYO("Mayo"),
    SALT("Salt");

    private String value;

    IngredientlessItems(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
