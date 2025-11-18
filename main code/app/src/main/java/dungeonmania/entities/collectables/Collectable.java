package dungeonmania.entities.collectables;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

/**
 * Base class for simple collectable items that don't have durability or provide battle buffs.
 * These are items like wood, treasure, arrows, and keys that are simply collected and used as crafting materials.
 */
public abstract class Collectable extends InventoryItem {
    public Collectable(Position position) {
        super(position);
    }

    /**
     * Simple collectables don't provide any battle buffs.
     * Returns the origin statistics unchanged.
     */
    @Override
    public final BattleStatistics applyBuff(BattleStatistics origin) {
        return origin;
    }

    /**
     * Simple collectables don't have durability as they don't wear out.
     * This method is not applicable for collectables.
     */
    @Override
    public final int getDurability() {
        throw new UnsupportedOperationException("Simple collectables do not have durability");
    }
}
