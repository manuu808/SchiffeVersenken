package battleship.model;

public enum ShipType {
    BATTLESHIP("Schlachtschiff", 4),
    CANNON_BOAT("Kanonenboot", 1),
    CARRIER("Flugzeugträger", 5),
    CRUISER("Kreuzer", 3),
    DESTROYER("Zerstörer", 2),
    SUBMARINE("U-Boot", 3);

    private final String name;
    private final int length;

    ShipType(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

}
