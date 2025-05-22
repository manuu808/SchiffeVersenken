package battleship.rules;

import battleship.model.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class StandardRules implements Rules {

    public static final StandardRules INSTANCE = new StandardRules();

    private StandardRules() {}

    @Override
    public int getVerticalLength() {
        return 10;
    }

    @Override
    public int getHorizontalLength() {
        return 10;
    }

    private static Stream<Coordinate> toSurroundingCoordinates(final Coordinate coordinate) {
        final Stream.Builder<Coordinate> result = Stream.builder();
        result.accept(coordinate.plusColumn(1));
        result.accept(coordinate.plusColumn(-1));
        result.accept(coordinate.plusRow(1));
        result.accept(coordinate.plusRow(-1));
        result.accept(coordinate.plusColumn(1).plusRow(1));
        result.accept(coordinate.plusColumn(1).plusRow(-1));
        result.accept(coordinate.plusColumn(-1).plusRow(1));
        result.accept(coordinate.plusColumn(-1).plusRow(-1));
        return result.build();
    }

    @Override
    public Optional<Turn> getNextTurn(Game game) {
        if (getWinner(game).isPresent()) {
            return Optional.empty();
        }

        Player currentPlayer = currentPlayer(game);
        int numOfEvents = countEvents(game, currentPlayer);

        if (numOfEvents == 0) {
            TurnAction action = new ShipPlacementAction(currentPlayer, ShipType.CARRIER);
            return Optional.of(new Turn(action, "Place your ship"));
        }
        if (numOfEvents == 1) {
            TurnAction action = new ShipPlacementAction(currentPlayer, ShipType.BATTLESHIP);
            return Optional.of(new Turn(action, "Place your ship"));
        }
        if (numOfEvents == 2) {
            TurnAction action = new ShipPlacementAction(currentPlayer, ShipType.CRUISER);
            return Optional.of(new Turn(action, "Place your ship"));
        }
        if (numOfEvents == 3) {
            TurnAction action = new ShipPlacementAction(currentPlayer, ShipType.DESTROYER);
            return Optional.of(new Turn(action, "Place your ship"));
        }
        if (numOfEvents == 4) {
            TurnAction action = new ShipPlacementAction(currentPlayer, ShipType.CANNON_BOAT);
            return Optional.of(new Turn(action, "Place your ship"));
        }

        else {
            TurnAction action = new ShotAction(currentPlayer);
            return Optional.of(new Turn(action, "Take your shot"));
        }
    }

    public Player currentPlayer (Game game) {
        return game.getCurrentPlayer();
    }

    public int countEvents(Game game, Player player) {
        return (int) game.getEventsByPlayer(player)
                .count();
    }

    @Override
    public Optional<Player> getWinner(Game game) {
        for (Player player : Player.values()) {
            Set<Coordinate> ships = game.getShipCoordinates(player);

            boolean allHit = ships.stream().allMatch(coordinate -> game.isHit(player,coordinate));

            if (allHit) {
                return Optional.of(player.inverse());
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean placementConflict(Coordinate first, Coordinate second) {
        return first.equals(second);
    }
}
