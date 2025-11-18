package dungeonmania.entities.enemies.movement;

import java.util.List;
import java.util.Random;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

/**
 * Movement strategy for enemies that move in random directions.
 */
public class RandomMovement implements MovementStrategy {
    private Random randGen = new Random();

    @Override
    public Position getNextPosition(Game game, Enemy enemy) {
        GameMap map = game.getMap();
        List<Position> adjacentPositions = enemy.getPosition().getCardinallyAdjacentPositions();
        List<Position> validPositions = adjacentPositions.stream().filter(p -> map.canMoveTo(enemy, p)).toList();

        if (validPositions.isEmpty()) {
            return enemy.getPosition();
        }

        return validPositions.get(randGen.nextInt(validPositions.size()));
    }
}
