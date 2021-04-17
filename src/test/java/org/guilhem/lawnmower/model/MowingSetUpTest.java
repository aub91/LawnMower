package org.guilhem.lawnmower.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MowingSetUpTest {
    private static final int EXPECTED_INIT_MAX_X = 0;

    private static final int EXPECTED_INIT_MAX_Y = 0;

    private static final int EXPECTED_INIT_MOWER_LIST_SIZE = 0;

    @Test
    public void shouldInitCorrectly() {
        MowingSetUp setUp = new MowingSetUp();

        assertEquals(EXPECTED_INIT_MAX_X, setUp.getMaxX());
        assertEquals(EXPECTED_INIT_MAX_Y, setUp.getMaxY());
        assertEquals(EXPECTED_INIT_MOWER_LIST_SIZE, setUp.getMowers().size());
    }

    @Test
    public void shouldAddMower() {
        MowingSetUp setUp = new MowingSetUp();

        Mower mower = new Mower(1, 1, 'N', new char[1]);
        setUp.addMower(mower);

        assertEquals(1, setUp.getMowers().size());
    }
}
