package battleship.model;

import battleship.rules.Rules;

public record EvenAndState(Rules rules, Game game, Event event) {
}
