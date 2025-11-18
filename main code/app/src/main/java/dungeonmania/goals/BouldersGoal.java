package dungeonmania.goals;

import dungeonmania.Game;
import dungeonmania.entities.Switch;

/**
 * Concrete goal representing the requirement to activate all switches with boulders
 */
public class BouldersGoal implements Goal {
    @Override
    public boolean achieved(Game game) {
        if (game.getPlayer() == null) {
            return false;
        }

        return game.getMap().getEntities(Switch.class).stream().allMatch(Switch::isActivated);
    }

    @Override
    public String toString(Game game) {
        return achieved(game) ? "" : ":boulders";
    }
}
