package battleship.model;

public abstract class TurnAction implements java.util.function.Function<EvenAndState, Boolean> {
    final public Player player;

    public TurnAction(Player player) {
        this.player = player;
    }
}
