package dungeonmania.entities.crafting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

/**
 * Manages all crafting recipes in the game.
 * Provides a central registry for recipes and coordinates crafting operations.
 */
public class CraftingManager {
    private Map<String, CraftingRecipe> recipes = new HashMap<>();

    /**
     * Constructor - registers all default recipes
     */
    public CraftingManager() {
        // Register default recipes
        registerRecipe(new BowRecipe());
        registerRecipe(new ShieldRecipe());
    }

    /**
     * Register a new recipe
     * @param recipe The recipe to register
     */
    public void registerRecipe(CraftingRecipe recipe) {
        recipes.put(recipe.getName(), recipe);
    }

    /**
     * Check if a specific item can be crafted
     * @param name Name of the item to craft
     * @param inventory Player's inventory
     * @return true if the item can be crafted, false otherwise
     */
    public boolean canCraft(String name, Inventory inventory) {
        CraftingRecipe recipe = recipes.get(name);
        return recipe != null && recipe.canCraft(inventory);
    }

    /**
     * Craft an item, consuming materials from inventory
     * @param name Name of the item to craft
     * @param inventory Player's inventory
     * @param factory Factory to create the crafted item
     * @return The crafted item, or null if crafting failed
     */
    public InventoryItem craft(String name, Inventory inventory, EntityFactory factory) {
        CraftingRecipe recipe = recipes.get(name);
        if (recipe == null) {
            return null;
        }
        return recipe.craft(inventory, factory);
    }

    /**
     * Get a list of all items that can currently be crafted
     * @param inventory Player's inventory
     * @return List of craftable item names
     */
    public List<String> getBuildables(Inventory inventory) {
        return recipes.values().stream().filter(recipe -> recipe.canCraft(inventory)).map(CraftingRecipe::getName)
                .collect(Collectors.toList());
    }
}
