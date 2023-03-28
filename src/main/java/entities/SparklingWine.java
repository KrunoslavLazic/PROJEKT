package entities;
import java.util.List;

public class SparklingWine extends Wine{

    private Integer gramsOfCO2;

    public SparklingWine(long id, Integer gramsOfCO2, String grapeName, Integer yearOfProduction, Double alcoholPercentage, FoodType suitableFoodTypes, Winery winery, SellingDetails sellingDetails) {
        super(id, grapeName, yearOfProduction, alcoholPercentage, suitableFoodTypes, winery, sellingDetails);
        if (gramsOfCO2 < 6)this.gramsOfCO2=6;
        if (gramsOfCO2 > 13)this.gramsOfCO2=13;
    }

    public Integer getGramsOfCO2() {
        return gramsOfCO2;
    }

    public void setGramsOfCO2(Integer gramsOfCO2) {
        this.gramsOfCO2 = gramsOfCO2;
    }
    @Override
    public String toString() {
        return "Sparkling " + super.toString() + " " + gramsOfCO2;
    }
}
