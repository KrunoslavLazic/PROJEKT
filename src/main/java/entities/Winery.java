package entities;

import java.time.LocalDate;

public class Winery extends Entity{

    private String name;
    private Location location;
    private LocalDate dateOfEstablishment;

    public Winery(long id, String name, Location location, LocalDate dateOfEstablishment) {
        super(id);
        this.name = name;
        this.location = location;
        this.dateOfEstablishment = dateOfEstablishment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDateOfEstablishment() {
        return dateOfEstablishment;
    }

    public void setDateOfEstablishment(LocalDate dateOfEstablishment) {
        this.dateOfEstablishment = dateOfEstablishment;
    }

    @Override
    public String toString() {
        return name;
    }
}
