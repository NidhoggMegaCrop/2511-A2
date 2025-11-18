package dungeonmania.entities.crafting;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

/**
 * Recipe for crafting a shield.
 * Requirements: 2 wood + (1 treasure OR 1 key)
 */
public class ShieldRecipe implements CraftingRecipe {
    @Override
    public boolean canCraft(Inventory inventory) {
        boolean hasWood = inventory.count(Wood.class) >= 2;
        boolean hasMaterial = inventory.count(Treasure.class) >= 1 || inventory.count(Key.class) >= 1;
        return hasWood && hasMaterial;
    }

    @Override
    public InventoryItem craft(Inventory inventory, EntityFactory factory) {
        if (!canCraft(inventory)) {
            return null;
        }

        // Consume 2 wood
        inventory.remove(inventory.getFirst(Wood.class));
        inventory.remove(inventory.getFirst(Wood.class));

        // Consume treasure or key (prefer treasure)
        if (inventory.count(Treasure.class) >= 1) {
            inventory.remove(inventory.getFirst(Treasure.class));
        } else {
            inventory.remove(inventory.getFirst(Key.class));
        }

        // Return the crafted shield
        return factory.buildShield();
    }

    @Override
    public String getName() {
        return "shield";
    }
}
