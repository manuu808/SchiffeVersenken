package battleship.model;

public enum Player {
    FIRST,
    SECOND;

    public Player inverse() {
        if (this == FIRST) {
            return SECOND;
        } else {
            return FIRST;
        }
    }
}
