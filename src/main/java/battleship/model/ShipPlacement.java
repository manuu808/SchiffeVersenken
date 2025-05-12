package battleship.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class ShipPlacement extends Event {
    public ShipType type;
    Coordinate start;
    Direction direction;
    public Player player;

    public ShipPlacement(ShipType type, Coordinate start, Direction direction, Player player) {
        super();
        this.type = type;
        this.start = start;
        this.direction = direction;
        this.player = player;
    }

    @Override
    public boolean isShipPlacementEvent(Player player) {
        return this.player == player;
    }

    @Override
    public boolean isShotEvent(Player player) {
        return false;
    }

    public static Optional<Direction> toDirection(final ShipType type, final Coordinate start, final Coordinate end) {
        int distanceColumn = end.column() - start.column();
        int distanceRow = end.row() - start.row();
        int expectedDistance = type.getLength() - 1;

        if (distanceColumn == 0 && Math.abs(distanceRow) == expectedDistance) {
            return Optional.of(distanceRow < 0 ? Direction.SOUTH : Direction.NORTH);
        }
        if (distanceRow == 0 && Math.abs(distanceColumn) == expectedDistance) {
            return Optional.of(distanceColumn < 0 ? Direction.EAST : Direction.WEST);
        }
        return Optional.empty();
    }

    public Stream<Coordinate> toCoordinates() {
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        for (int i = 0; i < type.getLength(); i++) {
            coordinates.add(start.plus(i, direction));
        }
        return Arrays.stream(coordinates.toArray(new Coordinate[type.getLength()]));
    }
}