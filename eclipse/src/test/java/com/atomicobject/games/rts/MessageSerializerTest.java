package com.atomicobject.games.rts;

import com.atomicobject.games.rts.communication.AICommand;
import com.atomicobject.games.rts.communication.AICommandsMessage;
import com.atomicobject.games.rts.communication.MessageSerializer;
import com.atomicobject.games.rts.state.MapDirections;
import com.atomicobject.games.rts.state.Unit;
import com.atomicobject.games.rts.updates.GameUpdate;
import com.atomicobject.games.rts.updates.Location;
import com.atomicobject.games.rts.updates.UnitUpdate;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageSerializerTest {

    @Test
    void parseUpdate() throws IOException {
        var serverUpdate = "{\"unit_updates\":[{\"id\":5,\"player_id\":0,\"x\":0,\"y\":0,\"status\":\"idle\",\"type\":\"base\",\"resource\":750,\"health\":300},{\"id\":6,\"player_id\":0,\"x\":0,\"y\":0,\"status\":\"idle\",\"type\":\"worker\",\"health\":10,\"can_attack\":true,\"range\":2,\"speed\":5,\"resource\":0,\"attack_damage\":2,\"attack_cooldown_duration\":30,\"attack_cooldown\":0,\"attack_type\":\"melee\"},{\"id\":7,\"player_id\":0,\"x\":0,\"y\":0,\"status\":\"idle\",\"type\":\"worker\",\"health\":10,\"can_attack\":true,\"range\":2,\"speed\":5,\"resource\":0,\"attack_damage\":2,\"attack_cooldown_duration\":30,\"attack_cooldown\":0,\"attack_type\":\"melee\"},{\"id\":8,\"player_id\":0,\"x\":0,\"y\":0,\"status\":\"idle\",\"type\":\"worker\",\"health\":10,\"can_attack\":true,\"range\":2,\"speed\":5,\"resource\":0,\"attack_damage\":2,\"attack_cooldown_duration\":30,\"attack_cooldown\":0,\"attack_type\":\"melee\"},{\"id\":9,\"player_id\":0,\"x\":0,\"y\":0,\"status\":\"idle\",\"type\":\"worker\",\"health\":10,\"can_attack\":true,\"range\":2,\"speed\":5,\"resource\":0,\"attack_damage\":2,\"attack_cooldown_duration\":30,\"attack_cooldown\":0,\"attack_type\":\"melee\"},{\"id\":10,\"player_id\":0,\"x\":0,\"y\":0,\"status\":\"idle\",\"type\":\"worker\",\"health\":10,\"can_attack\":true,\"range\":2,\"speed\":5,\"resource\":0,\"attack_damage\":2,\"attack_cooldown_duration\":30,\"attack_cooldown\":0,\"attack_type\":\"melee\"},{\"id\":11,\"player_id\":0,\"x\":0,\"y\":0,\"status\":\"idle\",\"type\":\"worker\",\"health\":10,\"can_attack\":true,\"range\":2,\"speed\":5,\"resource\":0,\"attack_damage\":2,\"attack_cooldown_duration\":30,\"attack_cooldown\":0,\"attack_type\":\"melee\"}],\"tile_updates\":[{\"visible\":true,\"x\":-2,\"y\":-2,\"blocked\":true,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":-2,\"y\":-1,\"blocked\":true,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":-2,\"y\":0,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":-2,\"y\":1,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":-2,\"y\":2,\"blocked\":true,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":-1,\"y\":-2,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":-1,\"y\":-1,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":-1,\"y\":0,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":-1,\"y\":1,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":-1,\"y\":2,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":0,\"y\":-2,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":0,\"y\":-1,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":0,\"y\":0,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":0,\"y\":1,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":0,\"y\":2,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":1,\"y\":-2,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":1,\"y\":-1,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":1,\"y\":0,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":1,\"y\":1,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":1,\"y\":2,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":2,\"y\":-2,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":2,\"y\":-1,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":2,\"y\":0,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":2,\"y\":1,\"blocked\":false,\"resources\":null,\"units\":[]},{\"visible\":true,\"x\":2,\"y\":2,\"blocked\":false,\"resources\":null,\"units\":[]}],\"game_info\":{\"map_width\":32,\"map_height\":32,\"game_duration\":300000,\"turn_duration\":200,\"unit_info\":{\"base\":{\"hp\":300,\"range\":2},\"worker\":{\"cost\":100,\"hp\":10,\"range\":2,\"speed\":5,\"attack_damage\":2,\"attack_type\":\"melee\",\"attack_cooldown_duration\":30,\"can_carry\":true,\"create_time\":50},\"scout\":{\"cost\":130,\"hp\":5,\"range\":5,\"speed\":3,\"attack_damage\":1,\"attack_type\":\"melee\",\"attack_cooldown_duration\":30,\"create_time\":100},\"tank\":{\"cost\":150,\"hp\":30,\"range\":2,\"speed\":10,\"attack_damage\":4,\"attack_type\":\"ranged\",\"attack_cooldown_duration\":70,\"create_time\":150}}},\"player\":1,\"turn\":2,\"time\":20}";

        MessageSerializer serializer = new MessageSerializer();
        GameUpdate update = serializer.parseUpdate(serverUpdate);

        assertEquals(update.getPlayer(), 1);
        assertEquals(update.getTurn(), 2);
        assertEquals(update.getTime(), 20);

        assertNotNull(update.getGameInfo());
        assertNotNull(update.getUnitUpdates());
        assertNotNull(update.getTileUpdates());

        assertEquals(update.getGameInfo().getUnitInfo().get("tank").getCost(), 150);
        assertEquals(update.getUnitUpdates().get(0).getHealth(), 300);
        assertEquals(update.getTileUpdates().get(0).getLocation(), new Location(-2, -2));
    }

    @Test
    void serializeAICommandsMessage() throws JsonProcessingException {
        Unit unit = new Unit() {{
            setUnitUpdate(new UnitUpdate() {{
                setId(2);
            }});
        }};
        AICommand command1 = AICommand.buildMoveCommand(unit, MapDirections.Direction.NORTH);
        List<AICommand> commands = new ArrayList<AICommand>() {{
            add(command1);
        }};
        var message = new AICommandsMessage(commands);
        var serialized = new MessageSerializer().serializeAICommandsMessage(message);
        var expected = "{\"commands\":[{\"command\":\"MOVE\",\"unit\":2,\"dir\":\"N\",\"type\":null,\"dx\":0,\"dy\":0,\"target\":0}]}\n";
        assertEquals(serialized, expected);
    }
}