package entities;

import java.util.List;

public class DessertWine extends Wine{

    private Integer calories;

    public DessertWine(long id, Integer calories, String grapeName, Integer yearOfProduction, Double alcoholPercentage, FoodType suitableFoodTypes, Winery winery, SellingDetails sellingDetails) {
        super(id, grapeName, yearOfProduction, alcoholPercentage, suitableFoodTypes, winery, sellingDetails);
        this.calories=calories;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}
