package entities;

public sealed interface Autonomus permits Location {

    boolean isAutonomus(Region region);

}
