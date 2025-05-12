package battleship.model;

import java.util.UUID;

public abstract class Event implements Comparable<Event> {
    UUID id;
    long timestamp;

    @Override
    public int compareTo(final Event other) {
        final int result = Long.compare(this.timestamp, other.timestamp);
        if (result == 0) {
            return this.id.compareTo(other.id);
        }
        return result;
    }

    public Event() {
        this.id = UUID.randomUUID();
        this.timestamp = System.currentTimeMillis();
    }

    public Event(UUID id, long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Event) {
            return this.id.equals(((Event)o).id);
        }
        return false;
    }

    public abstract boolean isShipPlacementEvent(Player player);

    public abstract boolean isShotEvent(Player player);
}
