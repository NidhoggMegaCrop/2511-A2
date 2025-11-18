package dungeonmania.entities.crafting;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.collectables.Arrow;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

/**
 * Recipe for crafting a bow.
 * Requirements: 1 wood + 3 arrows
 */
public class BowRecipe implements CraftingRecipe {
    @Override
    public boolean canCraft(Inventory inventory) {
        return inventory.count(Wood.class) >= 1 && inventory.count(Arrow.class) >= 3;
    }

    @Override
    public InventoryItem craft(Inventory inventory, EntityFactory factory) {
        if (!canCraft(inventory)) {
            return null;
        }

        // Consume materials
        inventory.remove(inventory.getFirst(Wood.class));
        inventory.remove(inventory.getFirst(Arrow.class));
        inventory.remove(inventory.getFirst(Arrow.class));
        inventory.remove(inventory.getFirst(Arrow.class));

        // Return the crafted bow
        return factory.buildBow();
    }

    @Override
    public String getName() {
        return "bow";
    }
}
