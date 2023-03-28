package entities;

import java.util.List;

public class RoseWine extends Wine{

    private Integer flavorIntensity;

    public RoseWine(long id,Integer flavorIntensity, String grapeName, Integer yearOfProduction, Double alcoholPercentage, FoodType suitableFoodTypes, Winery winery, SellingDetails sellingDetails ) {
        super(id, grapeName, yearOfProduction, alcoholPercentage, suitableFoodTypes, winery, sellingDetails);
        this.flavorIntensity = flavorIntensity;
    }

    public Integer getFlavorProfile() {
        return flavorIntensity;
    }

    public void setFlavorProfile(Integer flavorIntensity) {
        this.flavorIntensity = flavorIntensity;
    }
    @Override
    public String toString() {
        return "White " + super.toString() + " " + flavorIntensity;
    }
}
