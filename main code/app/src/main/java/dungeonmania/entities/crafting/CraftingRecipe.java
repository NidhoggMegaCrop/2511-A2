package dungeonmania.entities.crafting;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

/**
 * Interface for crafting recipes.
 * Each recipe encapsulates the logic for checking if it can be crafted
 * and actually crafting the item.
 */
public interface CraftingRecipe {
    /**
     * Check if this recipe can be crafted with the given inventory
     * @param inventory The player's inventory
     * @return true if the recipe can be crafted, false otherwise
     */
    boolean canCraft(Inventory inventory);

    /**
     * Craft the item, consuming the required materials from inventory
     * @param inventory The player's inventory
     * @param factory Factory to create the crafted item
     * @return The crafted item, or null if crafting failed
     */
    InventoryItem craft(Inventory inventory, EntityFactory factory);

    /**
     * Get the name of this craftable item
     * @return The item name (e.g., "bow", "shield")
     */
    String getName();
}
