package battleship.rules;

import battleship.model.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public interface Rules {

    int getVerticalLength();

    int getHorizontalLength();

    default Set<Coordinate> getImpossibleCoordinatesAfterShot(final Player playerWhoShot, final Coordinate shot, final Game game) {
        Set<Coordinate> impossible = new HashSet<>();

        if (game.isHit(playerWhoShot, shot)) {
            return impossible;
        }

        int[] dx = new int[]{-1, 0, 1};
        int[] dy = new int[]{-1, 0, 1};

        for (int x : dx) {
            for (int y : dy) {
                Coordinate adjacent = new Coordinate(shot.column() + x, shot.row() + y);
                impossible.add(adjacent);
            }
        }
        return impossible;
    }

    Optional<Turn> getNextTurn(final Game game);

    Optional<Player> getWinner(final Game game);

    boolean placementConflict(final Coordinate first, final Coordinate second);

    default boolean shipPlacement(final Game game, final ShipType type, final Player player, final Event event) {
        if (event.isShipPlacementEvent(player)) {
            final ShipPlacement placement = (ShipPlacement)event;
            if (placement.type == type && this.validShipPlacement(placement, game.getShipCoordinates(placement.player))) {
                game.addEvent(event);
                return true;
            }
        }
        return false;
    }

    default boolean shot(final Game game, final Player player, final Event event) {
        if (event.isShotEvent(player)) {
            final Shot shot = (Shot)event;
            if (this.validCoordinate(shot.coordinate)) {
                game.addEvent(event);
                return true;
            }
        }
        return false;
    }

    default boolean validCoordinate(final Coordinate coordinate) {
        return Rules.isBetween(0, coordinate.column(), this.getHorizontalLength())
                && Rules.isBetween(0, coordinate.row(), this.getVerticalLength());
    }

    default boolean validShipPlacement(final ShipPlacement placement, final Collection<Coordinate> shipCoordinates) {
        return this.validPlacementCoordinates(placement) && this.noConflict(placement, shipCoordinates);
    }

    static boolean isBetween(final int lowerBoundInclusive, final int number, final int upperBoundExclusive) {
        return number >= lowerBoundInclusive && number < upperBoundExclusive;
    }

    private boolean noConflict(final ShipPlacement placement, final Collection<Coordinate> shipCoordinates) {
        for (final Coordinate existing : shipCoordinates) {
            if (placement
                            .toCoordinates()
                            .anyMatch(coordinate -> this.placementConflict(coordinate, existing))) {
                return false;
            }
        }
        return true;
    }

    private boolean validPlacementCoordinates(final ShipPlacement placement) {
        return placement.toCoordinates().allMatch(this::validCoordinate);
    }

}
