package dungeonmania.entities.logical;

import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

/**
 * Wire entity that conducts current between switches and logical entities.
 * Wires themselves do not follow logical rules but pass current through.
 */
public class Wire extends Entity implements Conductor {
    private boolean isActivated = false;

    public Wire(Position position) {
        super(position.asLayer(Entity.ITEM_LAYER));
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        // Wires don't interact with overlapping entities
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        // No action needed
    }

    @Override
    public void onDestroy(GameMap gameMap) {
        // No cleanup needed
    }

    @Override
    public boolean isActivated() {
        return isActivated;
    }

    @Override
    public void activate(GameMap map, int tick) {
        if (!isActivated) {
            isActivated = true;
            // Propagate current to adjacent conductors and logical entities
            LogicPropagator.propagateActivation(map, this, tick);
        }
    }

    @Override
    public void deactivate(GameMap map) {
        if (isActivated) {
            isActivated = false;
        }
    }
}
