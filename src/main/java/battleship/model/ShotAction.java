package battleship.model;

public class ShotAction extends TurnAction{

    public ShotAction(Player player) {
        super(player);
    }

    @Override
    public Boolean apply(EvenAndState evenAndState) {
        return evenAndState.rules().shot(evenAndState.game(), player, evenAndState.event());
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ShotAction shotAction) {
            return this.player == shotAction.player;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.player.hashCode();
    }
}
