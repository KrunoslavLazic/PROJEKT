package entities;

import java.util.List;

public abstract class Wine extends Entity {

    private String grapeName;
    private Integer yearOfProduction;
    private Double alcoholPercentage;
    private FoodType suitableFoodTypes;
    private Winery winery;
    private SellingDetails sellingDetails;

    public Wine(long id, String grapeName, Integer yearOfProduction, Double alcoholPercentage, FoodType suitableFoodTypes, Winery winery, SellingDetails sellingDetails) {
        super(id);
        this.grapeName = grapeName;
        this.yearOfProduction = yearOfProduction;
        this.alcoholPercentage = alcoholPercentage;
        this.suitableFoodTypes = suitableFoodTypes;
        this.winery = winery;
        this.sellingDetails = sellingDetails;
    }
    public String getGrapeName() {
        return grapeName;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public Double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public FoodType getSuitableFoodTypes() {
        return suitableFoodTypes;
    }

    public Winery getWinery() {
        return winery;
    }

    public SellingDetails getSellingDetails() {
        return sellingDetails;
    }

    @Override
    public String toString() {
        return super.getId() + " " + grapeName + " " + yearOfProduction + " " +
                alcoholPercentage + " " + suitableFoodTypes + " " +
                winery + " " + sellingDetails;
    }
}
