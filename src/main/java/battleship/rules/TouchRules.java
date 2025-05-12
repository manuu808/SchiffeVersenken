package battleship.rules;

import battleship.model.*;

import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unused")
public class TouchRules implements Rules {

    @Override
    public int getVerticalLength() {
        return 10;
    }

    @Override
    public int getHorizontalLength() {
        return 10;
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

    public Player currentPlayer (Game game) {
        return game.getCurrentPlayer();
    }

    public int countEvents(Game game, Player player) {
        return (int) game.getEventsByPlayer(player)
                .count();
    }
}
