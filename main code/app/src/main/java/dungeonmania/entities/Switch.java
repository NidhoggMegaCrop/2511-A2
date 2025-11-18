package dungeonmania.entities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.entities.collectables.Bomb;
import dungeonmania.entities.logical.Conductor;
import dungeonmania.entities.logical.LogicPropagator;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Switch extends Entity implements Conductor {
    /** Whether this switch is activated (ie a boulder has been pushed on top of it) */
    private boolean activated;
    private List<Bomb> bombs = new ArrayList<>();

    public Switch(Position position) {
        super(position.asLayer(Entity.ITEM_LAYER));
    }

    /** Subscribe to the state of this switch */
    public void subscribe(Bomb bomb, GameMap map) {
        bombs.add(bomb);
        if (activated) {
            activateBombs(map);
        }
    }

    public void unsubscribe(Bomb b) {
        bombs.remove(b);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activate(map, map.getGame().getTick());
        }
    }

    public void activateBombs(GameMap map) {
        for (Bomb b : bombs) {
            int x = b.getPosition().getX();
            int y = b.getPosition().getY();
            for (int i = x - b.getRadius(); i <= x + b.getRadius(); i++) {
                for (int j = y - b.getRadius(); j <= y + b.getRadius(); j++) {
                    map.destroyEntitiesOnPosition(i, j);
                }
            }
        }
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            deactivate(map);
        }
    }

    public boolean isActivated() {
        return activated;
    }

    @Override
    public void onDestroy(GameMap gameMap) {
        return;
    }

    @Override
    public void activate(GameMap map, int tick) {
        if (!activated) {
            activated = true;
            activateBombs(map);
            LogicPropagator.propagateActivation(map, this, tick);
        }
    }

    @Override
    public void deactivate(GameMap map) {
        if (activated) {
            activated = false;
            LogicPropagator.propagateDeactivation(map, this);
        }
    }
}
