package dungeonmania.entities.enemies.movement;

import dungeonmania.Game;
import dungeonmania.entities.Player;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

/**
 * Movement strategy for allied mercenaries that follow the player.
 * Allies move to the player's previous position when adjacent.
 */
public class AlliedMovement implements MovementStrategy {
    private boolean wasAdjacentToPlayer = false;

    @Override
    public Position getNextPosition(Game game, Enemy enemy) {
        GameMap map = game.getMap();
        Player player = game.getPlayer();

        boolean isAdjacentToPlayer = Position.isAdjacent(player.getPosition(), enemy.getPosition());

        Position nextPos;

        if (wasAdjacentToPlayer && !isAdjacentToPlayer) {
            // Follow to player's previous distinct position
            nextPos = player.getPreviousDistinctPosition();
        } else {
            // If currently still adjacent, wait in place. Else pursue the player.
            nextPos = isAdjacentToPlayer ? enemy.getPosition()
                    : map.dijkstraPathFind(enemy.getPosition(), player.getPosition(), enemy);

            wasAdjacentToPlayer = Position.isAdjacent(player.getPosition(), nextPos);
        }

        return nextPos;
    }
}
