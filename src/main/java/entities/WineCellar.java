package entities;

import java.util.ArrayList;
import java.util.List;

public class WineCellar <T extends Wine> implements WineSorting{


    private Location location;
    private List<T> allWines;

    public WineCellar(Location location, List<T> allWines) {
        this.location = location;
    }

    public void addWine(T wine){
        this.allWines.add(wine);
    }

    public List<T> getAllWines(){
        return new ArrayList<>(allWines);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
