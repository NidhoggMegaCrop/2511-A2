package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.battles.Battleable;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.entities.PotionListener;
import dungeonmania.entities.enemies.movement.MovementStrategy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public abstract class Enemy extends Entity implements Battleable {
    private BattleStatistics battleStatistics;
    private MovementStrategy movementStrategy;

    public Enemy(Position position, double health, double attack) {
        super(position.asLayer(Entity.CHARACTER_LAYER));
        battleStatistics = new BattleStatistics(health, attack, 0, BattleStatistics.DEFAULT_DAMAGE_MAGNIFIER,
                BattleStatistics.DEFAULT_ENEMY_DAMAGE_REDUCER);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return entity instanceof Player;
    }

    @Override
    public BattleStatistics getBattleStatistics() {
        return battleStatistics;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Player player) {
            map.getGame().battle(player, this);
        }
    }

    @Override
    public void onDestroy(GameMap map) {
        Game g = map.getGame();
        g.unsubscribe(getId());
        if (this instanceof PotionListener potionListener)
            map.getPlayer().removePotionListener(potionListener);
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        return;
    }

    /**
     * Movement logic for the enemy.
     *
     * When called, this enemy should move to a new position using its movement strategy.
     */
    public void move(Game game) {
        if (movementStrategy == null) {
            return; // No movement if strategy not set
        }

        Position nextPos = movementStrategy.getNextPosition(game, this);
        game.getMap().moveTo(this, nextPos);
    }

    /**
     * Set the movement strategy for this enemy
     * @param strategy The movement strategy to use
     */
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    /**
     * Get the current movement strategy
     * @return The current movement strategy
     */
    protected MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }
}
