package entities;

public class FoodType extends Entity{

    private String category;

    public FoodType(long id, String category) {
        super(id);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
