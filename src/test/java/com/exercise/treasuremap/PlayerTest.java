package com.exercise.treasuremap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static com.exercise.treasuremap.Direction.*;
import static com.exercise.treasuremap.Orientation.*;

class PlayerTest {

    @Test
    void rotate() {
        Player player = new Player("Lara", new Tile(0, 0), N, "AA");
        // Testing clockwise rotation
        player.rotate(D);
        Assertions.assertEquals(E, player.getOrientation());
        player.rotate(D);
        Assertions.assertEquals(S,player.getOrientation());
        player.rotate(D);
        Assertions.assertEquals(O, player.getOrientation());
        player.rotate(D);
        Assertions.assertEquals(N, player.getOrientation());
        // Testing counter-clockwise rotation
        player.rotate(G);
        Assertions.assertEquals(O, player.getOrientation());
        player.rotate(G);
        Assertions.assertEquals(S, player.getOrientation());
        player.rotate(G);
        Assertions.assertEquals(E, player.getOrientation());
        player.rotate(G);
        Assertions.assertEquals(N, player.getOrientation());
    }
}