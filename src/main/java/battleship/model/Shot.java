package battleship.model;

public class Shot extends Event {
    public final Coordinate coordinate;
    private final Player player;

    public Shot(Coordinate coordinate, Player player) {
        this.coordinate = coordinate;
        this.player = player;
    }

    @Override
    public boolean isShipPlacementEvent(Player player) {
        return false;
    }

    @Override
    public boolean isShotEvent(Player player) {
        return this.player == player;
    }
}
