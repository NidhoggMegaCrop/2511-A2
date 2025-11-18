package dungeonmania.entities.enemies.movement;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

/**
 * Movement strategy for enemies that flee from the player when invincible potion is active.
 * This extracts the duplicated flee logic from Mercenary and ZombieToast.
 */
public class FleeMovement implements MovementStrategy {
    @Override
    public Position getNextPosition(Game game, Enemy enemy) {
        GameMap map = game.getMap();

        Position playerDiff = Position.calculatePositionBetween(map.getPlayer().getPosition(), enemy.getPosition());

        Position moveX = (playerDiff.getX() >= 0) ? Position.translateBy(enemy.getPosition(), Direction.RIGHT)
                : Position.translateBy(enemy.getPosition(), Direction.LEFT);

        Position moveY = (playerDiff.getY() >= 0) ? Position.translateBy(enemy.getPosition(), Direction.DOWN)
                : Position.translateBy(enemy.getPosition(), Direction.UP);

        Position offset = enemy.getPosition();

        if (playerDiff.getY() == 0 && map.canMoveTo(enemy, moveX)) {
            offset = moveX;
        } else if (playerDiff.getX() == 0 && map.canMoveTo(enemy, moveY)) {
            offset = moveY;
        } else if (Math.abs(playerDiff.getX()) >= Math.abs(playerDiff.getY())) {
            if (map.canMoveTo(enemy, moveY)) {
                offset = moveY;
            } else if (map.canMoveTo(enemy, moveX)) {
                offset = moveX;
            } else {
                offset = enemy.getPosition();
            }
        } else {
            if (map.canMoveTo(enemy, moveX)) {
                offset = moveX;
            } else if (map.canMoveTo(enemy, moveY)) {
                offset = moveY;
            } else {
                offset = enemy.getPosition();
            }
        }

        return offset;
    }
}
