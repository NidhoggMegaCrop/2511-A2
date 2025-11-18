package dungeonmania.goals;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Exit;
import dungeonmania.entities.Player;
import dungeonmania.util.Position;

/**
 * Concrete goal representing the requirement to reach an exit
 */
public class ExitGoal implements Goal {
    @Override
    public boolean achieved(Game game) {
        Player player = game.getPlayer();
        if (player == null) {
            return false;
        }

        Position playerPos = player.getPosition();
        List<Exit> exits = game.getMap().getEntities(Exit.class);

        if (exits == null || exits.isEmpty()) {
            return false;
        }

        return exits.stream().map(Entity::getPosition).anyMatch(playerPos::equals);
    }

    @Override
    public String toString(Game game) {
        return achieved(game) ? "" : ":exit";
    }
}
