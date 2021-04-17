package org.guilhem.lawnmower.model;

import org.guilhem.lawnmower.exception.UnknownOrientationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class MowerTest {
    @Test
    public void shouldGoNorth() throws UnknownOrientationException {
        char[] instructions = {'A'};
        Mower mower = new Mower(0, 0, 'N', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "0 1 N";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldGoSouth() throws UnknownOrientationException {
        char[] instructions = {'A'};
        Mower mower = new Mower(0, 1, 'S', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "0 0 S";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldGoEast() throws UnknownOrientationException {
        char[] instructions = {'A'};
        Mower mower = new Mower(0, 0, 'E', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "1 0 E";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldGoWest() throws UnknownOrientationException {
        char[] instructions = {'A'};
        Mower mower = new Mower(1, 0, 'W', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "0 0 W";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldNotGoNorth() throws UnknownOrientationException {
        char[] instructions = {'A'};
        Mower mower = new Mower(0, 2, 'N', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "0 2 N";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldNotGoSouth() throws UnknownOrientationException {
        char[] instructions = {'A'};
        Mower mower = new Mower(0, 0, 'S', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "0 0 S";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldNotGoEast() throws UnknownOrientationException {
        char[] instructions = {'A'};
        Mower mower = new Mower(2, 0, 'E', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "2 0 E";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldNotGoWest() throws UnknownOrientationException {
        char[] instructions = {'A'};
        Mower mower = new Mower(0, 0, 'W', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "0 0 W";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldTurnRight() throws UnknownOrientationException {
        char[] instructions = {'D'};
        Mower mower = new Mower(0, 0, 'N', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "0 0 E";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldTurnRightBorderCase() throws UnknownOrientationException {
        char[] instructions = {'D'};
        Mower mower = new Mower(0, 0, 'W', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "0 0 N";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldTurnLeft() throws UnknownOrientationException {
        char[] instructions = {'G'};
        Mower mower = new Mower(0, 0, 'E', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "0 0 N";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldTurnLeftBorderCase() throws UnknownOrientationException {
        char[] instructions = {'G'};
        Mower mower = new Mower(0, 0, 'N', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "0 0 W";

        assertEquals(expectedPosition, position);
    }

    @Test
    public void shouldIgnoreUnknownInstruction() throws UnknownOrientationException {
        char[] instructions = {'X'};
        Mower mower = new Mower(0, 0, 'N', instructions);

        String position = mower.maw(2, 2);

        String expectedPosition = "0 0 N";

        assertEquals(expectedPosition, position);
    }

    @Test(expected = UnknownOrientationException.class)
    public void shouldThrowExceptionWithUnknownOrientation() throws UnknownOrientationException {
        char[] instructions = {'A'};
        Mower mower = new Mower(0, 0, 'X', instructions);

        mower.maw(2, 2);
    }



}
