package entities;

import java.util.ArrayList;
import java.util.List;

public enum Region{

    ISTRA("Croatia","Mediterranean"),
    SLAVONIJA("Croatia","Continental"),
    PLESIVICA("Croatia","Continental"),
    ZAGORJE("Croatia","Continental"),
    DALMACIJA("Croatia", "Mediterranean"),
    PELJESAC("Croatia","Mediterranean"),
    MENDOZA("Argentina","Continental"),
    BORDEAUX("France","Maritime"),
    CHAMPAGNE("France", "Maritime"),
    VENETO("Italy", "Mediterranean"),
    TUSCANY("Italy", "Mediterranean");

    private final String country;
    private final String climateType;

    Region(String country, String climateType) {
        this.country = country;
        this.climateType = climateType;
    }

    public String getCountry() {
        return country;
    }

    public String getclimateType() {
        return climateType;
    }
    public static List<Region> allRegions(){

        List<Region> regions = new ArrayList<>();
        regions.add(Region.ISTRA);
        regions.add(Region.SLAVONIJA);
        regions.add(Region.PLESIVICA);
        regions.add(Region.ZAGORJE);
        regions.add(Region.DALMACIJA);
        regions.add(Region.PELJESAC);
        regions.add(Region.MENDOZA);
        regions.add(Region.BORDEAUX);
        regions.add(Region.CHAMPAGNE);
        regions.add(Region.VENETO);
        regions.add(Region.TUSCANY);

        return regions;
    }

    @Override
    public String toString() {

        return regionToString(this) + " " + country + " " + climateType;
    }
    public static String regionToString(Region region){

        return switch (region){
            case ISTRA -> "Istra";
            case SLAVONIJA -> "Slavonija";
            case PLESIVICA -> "Plesivica";
            case ZAGORJE -> "Zagorje";
            case DALMACIJA -> "Dalmacija";
            case PELJESAC -> "Peljesac";
            case MENDOZA -> "Mendoza";
            case BORDEAUX -> "Bordeaux";
            case CHAMPAGNE -> "Champagne";
            case VENETO -> "Veneto";
            case TUSCANY -> "Tuscany";
        };
    }
    public static Region stringToRegion(String region){

        return switch (region.toUpperCase()){
            case "ISTRA" -> ISTRA;
            case "SLAVONIJA" -> SLAVONIJA;
            case "PLESIVICA" -> PLESIVICA;
            case "ZAGORJE" -> ZAGORJE;
            case "DALMACIJA" -> DALMACIJA;
            case "PELJESAC" -> PELJESAC;
            case "MENDOZA" -> MENDOZA;
            case "BORDEAUX" -> BORDEAUX;
            case "CHAMPANGE" -> CHAMPAGNE;
            case "VENETO" -> VENETO;
            case "TUSCANY" -> TUSCANY;
            default->throw new IllegalArgumentException("Unexpected value: " + region);
        };
    }
}
