package entities;
import java.util.List;

public class RedWine extends Wine{

    private Integer bambusCompatibility;

    public RedWine(long id, Integer bambusCompatibility, String grapeName, Integer yearOfProduction, Double alcoholPercentage, FoodType suitableFoodTypes, Winery winery, SellingDetails sellingDetails) {
        super(id, grapeName, yearOfProduction, alcoholPercentage, suitableFoodTypes, winery, sellingDetails);
        if (bambusCompatibility > 11) this.bambusCompatibility = 10;
        if (bambusCompatibility < 0) this.bambusCompatibility = 0;
    }

    public Integer getBambusCompatibility() {
        return bambusCompatibility;
    }

    public void setBambusCompatibility(Integer bambusCompatibility) {
        this.bambusCompatibility = bambusCompatibility;
    }
    @Override
    public String toString() {
        return "Red " + super.toString() + " " + bambusCompatibility;
    }
}
