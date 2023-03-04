package com.exercise.treasuremap;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.exercise.treasuremap.Direction.*;
import static com.exercise.treasuremap.Orientation.*;

class MovementTest {

    @Test
    void rotate() {
        Movement movement = new Movement();
        Assertions.assertEquals(S, movement.rotate(E, D));
        Assertions.assertEquals(N, movement.rotate(E, G));
        Assertions.assertEquals(E, movement.rotate(E, A));
        Assertions.assertEquals(N, movement.rotate(O, D));
        Assertions.assertEquals(S, movement.rotate(O, G));
        Assertions.assertEquals(O, movement.rotate(O, A));
        Assertions.assertEquals(E, movement.rotate(N, D));
        Assertions.assertEquals(O, movement.rotate(N, G));
        Assertions.assertEquals(N, movement.rotate(N, A));
        Assertions.assertEquals(O, movement.rotate(S, D));
        Assertions.assertEquals(E, movement.rotate(S, G));
        Assertions.assertEquals(S, movement.rotate(S, A));


    }

    @Test
    void getNextTile() {
        Movement movement = new Movement();
        Tile currentTile = new Tile(1, 2);

        Assertions.assertEquals(1, movement.getNextTile(S, currentTile).getPosX());
        Assertions.assertEquals(2, movement.getNextTile(E, currentTile).getPosX());
        Assertions.assertEquals(0, movement.getNextTile(O, currentTile).getPosX());
        Assertions.assertEquals(1, movement.getNextTile(N, currentTile).getPosX());


        Assertions.assertEquals(3, movement.getNextTile(S, currentTile).getPosY());
        Assertions.assertEquals(2, movement.getNextTile(E, currentTile).getPosY());
        Assertions.assertEquals(2, movement.getNextTile(O, currentTile).getPosY());
        Assertions.assertEquals(1, movement.getNextTile(N, currentTile).getPosY());

    }

    @Test
    void canMove() {
        Movement movement = new Movement();
        TreasureMap map = new TreasureMap(3, 4);
        Tile nextTile1 = new Tile(1, 2);
        nextTile1.setMountain(false);
        Tile nextTile2 = new Tile(3, 5);
        nextTile1.setMountain(false);

        Assertions.assertTrue(movement.canMove(nextTile1, map));
        Assertions.assertFalse(movement.canMove(nextTile2, map));
    }

    @Test
    void updateTreasuresInTile() {
//        Movement movement = new Movement();
//        Tile nextTile1 = new Tile(1, 2);
//        nextTile1.setNbrOfTreasure(2);
//        movement.updateTreasuresInTile(nextTile1);
//        Assertions.assertEquals(1, nextTile1.getNbrOfTreasure());
    }

    @Test
    void startGame() {

    }
}