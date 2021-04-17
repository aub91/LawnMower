package org.guilhem.lawnmower.reader;

import org.guilhem.lawnmower.model.MowingSetUp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MowingSetUpReaderTest {
    private static final String INSTRUCTION_PATH = "D:\\Repo Git\\LawnMower\\src\\test\\resources\\instructions";

    private static final int EXPECTED_MAX_X = 5;

    private static final int EXPECTED_MAX_Y = 5;

    private static final int EXPECTED_MOWER_LIST_SIZE = 2;

    @Test
    public void shouldExtractMowingCondition() {
        MowingSetUp setUp = MowingSetUpReader.readInstruction(INSTRUCTION_PATH);

        assertEquals(EXPECTED_MAX_X, setUp.getMaxX());
        assertEquals(EXPECTED_MAX_Y, setUp.getMaxY());
        assertEquals(EXPECTED_MOWER_LIST_SIZE, setUp.getMowers().size());
    }
}
