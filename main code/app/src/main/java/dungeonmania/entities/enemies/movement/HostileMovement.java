package dungeonmania.entities.enemies.movement;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

/**
 * Movement strategy for hostile enemies that pursue the player using pathfinding.
 */
public class HostileMovement implements MovementStrategy {
    @Override
    public Position getNextPosition(Game game, Enemy enemy) {
        GameMap map = game.getMap();
        return map.dijkstraPathFind(enemy.getPosition(), game.getPlayer().getPosition(), enemy);
    }
}
