package battleship.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private final List<Event> allEvents;

    public Game() {
        this.allEvents = new LinkedList<Event>();
    }


    public boolean isHit(Player shooter, Coordinate target) {
            Player hitPlayer = shooter.inverse();
            Set<Coordinate> shipCoords = getShipCoordinates(hitPlayer);
            return shipCoords.contains(target);
    }

    public void addEvent(final Event event) {
        this.allEvents.add(event);
    }

    public Set<Coordinate> getActualShotCoordinates(final Player hitPlayer) {
        return this.allEvents.stream()
                .filter(event -> event.isShotEvent(hitPlayer.inverse()))
                .map(event -> ((Shot)event).coordinate)
                .collect(Collectors.toSet());
    }

    public Stream<Event> getEvents() {
        return allEvents.stream();
    }

    public Stream<Event> getEventsByPlayer(final Player player) {
        return allEvents.stream()
                .filter(event -> event.isShipPlacementEvent(player) || event.isShotEvent(player));
    }

    public Set<Coordinate> getShipCoordinates(final Player player) {
        return this.allEvents.stream()
                .filter(event -> event.isShipPlacementEvent(player))
                .flatMap(event -> ((ShipPlacement)event).toCoordinates())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Player getCurrentPlayer() {
        long shotCount = allEvents.stream()
                .filter(event -> event.isShotEvent(Player.FIRST) || event.isShotEvent(Player.SECOND))
                .count();
        return shotCount % 2 == 0 ? Player.FIRST : Player.SECOND;
    }
}
