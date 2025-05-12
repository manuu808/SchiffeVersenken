package battleship.model;

public class ShipPlacementAction extends TurnAction {
    public final ShipType shipType;

    public ShipPlacementAction(Player player, ShipType shipType) {
        super(player);
        this.shipType = shipType;
    }

    @Override
    public Boolean apply(EvenAndState evenAndState) {
        evenAndState.rules().shipPlacement()
        return evenAndState.rules().shipPlacement(evenAndState.game(), shipType, player, evenAndState.event());
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ShipPlacementAction shipPlacementAction) {
            return this.player == shipPlacementAction.player && this.shipType == shipPlacementAction.shipType;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.player.hashCode() + this.shipType.hashCode();
    }
}
