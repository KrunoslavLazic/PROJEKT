package entities;

public final class Location implements Autonomus{

    private final long postalCode;
    private final String town;
    private final Region region;

    private Location(long postalCode, String town, Region region) {
        this.postalCode = postalCode;
        this.town = town;
        this.region = region;
    }

    public static class Builder{

        private long postalCode;
        private String town;
        private Region region;

        public Builder withPostalCode(long postalCode){
            this.postalCode = postalCode;
            return this;
        }
        public Builder withTown(String town){
            this.town = town;
            return this;
        }
        public Builder withRegion(Region region){
            this.region = region;
            return this;
        }
        public Location build(){
            return new Location(postalCode, town,region);
        }
    }

    public Region getRegion() {
        return region;
    }

    public String getTown() {
        return town;
    }

    public long getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return postalCode + " " + town + " " + region;
    }

    @Override
    public boolean isAutonomus(Region region) {
        return switch (region){
            case BORDEAUX, CHAMPAGNE, VENETO -> true;
            default -> false;
        };
    }
}
