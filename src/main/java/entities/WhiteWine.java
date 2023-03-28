package entities;

import java.util.List;

public class WhiteWine extends Wine{

    private Integer gemistCompatibility;

    public WhiteWine(long id, String grapeName, Integer yearOfProduction, Double alcoholPercentage, FoodType suitableFoodTypes, Winery winery, SellingDetails sellingDetails, Integer gemistCompatibility) {
        super(id, grapeName, yearOfProduction, alcoholPercentage, suitableFoodTypes, winery, sellingDetails);
        if (gemistCompatibility<0){
            this.gemistCompatibility=0;
        }
        else if(gemistCompatibility>10){
            this.gemistCompatibility=10;
        }
        else{
            this.gemistCompatibility = gemistCompatibility;
        }

    }

    public Integer getGemistCompatibility() {
        return gemistCompatibility;
    }

    public void setGemistCompatibility(Integer gemistCompatibility) {
        this.gemistCompatibility = gemistCompatibility;
    }

    @Override
    public String toString() {
        return "White " + super.toString() + " " + gemistCompatibility;
    }
}
