package entities;

public class Meal extends Entity{

    private String name;
    private FoodType foodType;
    private MealTime mealtime;
    private Integer calories;

    public Meal(long id, String name, FoodType foodType, MealTime mealtime, Integer calories) {
        super(id);
        this.name = name;
        this.foodType = foodType;
        this.mealtime = mealtime;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public MealTime getMealtime() {
        return mealtime;
    }

    public void setMealtime(MealTime mealtime) {
        this.mealtime = mealtime;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}
