package com.atomicobject.games.rts;

import com.atomicobject.games.rts.state.Map;
import com.atomicobject.games.rts.state.Tile;
import com.atomicobject.games.rts.updates.Location;
import com.atomicobject.games.rts.updates.ResourceUpdate;
import com.atomicobject.games.rts.updates.TileUpdate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void buildNeighborLocationList() {
        var neighborsWithinOne = new ArrayList<Location>() {{
            add(new Location(-1, -1));
            add(new Location(0, -1));
            add(new Location(1, -1));
            add(new Location(-1, 0));
            add(new Location(1, 0));
            add(new Location(-1, 1));
            add(new Location(0, 1));
            add(new Location(1, 1));
        }};
        var map = new Map();
        var actualNeighbors = map.buildNeighborLocationList(1);
        assertThat(actualNeighbors, containsInAnyOrder(neighborsWithinOne.toArray()));
    }

    @Test
    void buildNeighborLocationList_With_Range() {
        var neighborsWithinTwo = new ArrayList<Location>() {{
            add(new Location(-2, -2));
            add(new Location(-1, -2));
            add(new Location(0, -2));
            add(new Location(1, -2));
            add(new Location(2, -2));
            add(new Location(-2, -1));
            add(new Location(-1, -1));
            add(new Location(0, -1));
            add(new Location(1, -1));
            add(new Location(2, -1));
            add(new Location(-2, 0));
            add(new Location(-1, 0));
            add(new Location(1, 0));
            add(new Location(2, 0));
            add(new Location(-2, 1));
            add(new Location(-1, 1));
            add(new Location(0, 1));
            add(new Location(1, 1));
            add(new Location(2, 1));
            add(new Location(-2, 2));
            add(new Location(-1, 2));
            add(new Location(0, 2));
            add(new Location(1, 2));
            add(new Location(2, 2));
        }};
        var map = new Map();
        var actualNeighbors = map.buildNeighborLocationList(2);
        assertThat(actualNeighbors, containsInAnyOrder(neighborsWithinTwo.toArray()));
    }

    @Test
    void neighbors() {
        var map = new Map();
        map.getTile(new Location(0, 0)).setTileUpdate(new TileUpdate() {{
            setVisible(true);
            setBlocked(false);
            setResources(new ResourceUpdate() {{ setValue(10); }});
        }});

        List<Tile> neighbors = map.neighbors(new Location(1, 1));
        Tile tile = neighbors.stream().filter(t -> t.getLocation().equals(new Location(0,0))).findFirst().get();
        assertTrue(tile.getTileUpdate().isVisible());
        assertFalse(tile.getTileUpdate().isBlocked());
        assertEquals(tile.getTileUpdate().getResources().getValue(), 10);
    }
}