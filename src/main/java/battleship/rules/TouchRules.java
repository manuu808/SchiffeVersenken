package battleship.rules;

import battleship.model.*;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unused")
public class TouchRules implements Rules {

    private static final TouchRules INSTANCE = new TouchRules();

    private TouchRules() {}

    @Override
    public int getVerticalLength() {
        return StandardRules.INSTANCE.getVerticalLength();
    }

    @Override
    public int getHorizontalLength() {
        return StandardRules.INSTANCE.getHorizontalLength();
    }

    @Override
    public Set<Coordinate> getImpossibleCoordinatesAfterShot(
            final Player playerWhoShot,
            final Coordinate shot,
            final Game game
    ) {
        return Collections.emptySet();
    }

    @Override
    public Optional<Turn> getNextTurn(final Game game) {
        return StandardRules.INSTANCE.getNextTurn(game);
    }

    @Override
    public Optional<Player> getWinner(final Game game) {
        return StandardRules.INSTANCE.getWinner(game);
    }

    @Override
    public boolean placementConflict(Coordinate first, Coordinate second) {
        return first.equals(second);
    }

}
