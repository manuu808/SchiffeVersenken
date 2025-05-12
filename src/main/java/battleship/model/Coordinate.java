package battleship.model;

public record Coordinate(int column, int row) {

    public Coordinate plus(final int length, final Direction direction) {
        return switch (direction) {
            case NORTH -> this.plusRow(-length);
            case SOUTH -> this.plusRow(length);
            case WEST -> this.plusColumn(-length);
            case EAST -> this.plusColumn(length);
            default -> throw new IllegalStateException("Unknown direction!");
        };
    }

    public Coordinate plusColumn(final int column) {
        return new Coordinate(column() + column, row);
    }

    public Coordinate plusRow(final int row) {
        return new Coordinate(column, row() + row);
    }
}
