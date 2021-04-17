package org.guilhem.lawnmower.reader;

import org.guilhem.lawnmower.model.Mower;
import org.guilhem.lawnmower.model.MowingSetUp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MowingSetUpReaderTest {
    private static final String INSTRUCTION_PATH = "D:\\Repo Git\\LawnMower\\src\\test\\resources\\instructions";

    private static final int EXPECTED_MAX_X = 5;

    private static final int EXPECTED_MAX_Y = 5;

    private static final int EXPECTED_MOWER_LIST_SIZE = 2;

    private static final int EXPECTED_MOWER1_INIT_X = 1;

    private static final int EXPECTED_MOWER1_INIT_Y = 2;

    private static final String EXPECTED_MOWER1_INIT_ORIENTATION = "N";

    private static final char[] EXPECTED_MOWER1_INSTRUCTIONS = {'G', 'A', 'G', 'A', 'G','A','G','A', 'A'};

    private static final int EXPECTED_MOWER2_INIT_X = 3;

    private static final int EXPECTED_MOWER2_INIT_Y = 3;

    private static final String EXPECTED_MOWER2_INIT_ORIENTATION = "E";

    private static final char[] EXPECTED_MOWER2_INSTRUCTIONS = {'A', 'A', 'D', 'A', 'A','D','A','D', 'D', 'A'};

    @Test
    public void shouldExtractMowingCondition() throws IOException {
        MowingSetUp setUp = MowingSetUpReader.readInstruction(INSTRUCTION_PATH);

        assertEquals(EXPECTED_MAX_X, setUp.getMaxX());
        assertEquals(EXPECTED_MAX_Y, setUp.getMaxY());
        assertEquals(EXPECTED_MOWER_LIST_SIZE, setUp.getMowers().size());

        Mower mower1 = setUp.getMowers().get(0);
        assertEquals(EXPECTED_MOWER1_INIT_X, mower1.getINITIAL_X());
        assertEquals(EXPECTED_MOWER1_INIT_Y, mower1.getINITIAL_Y());
        assertEquals(EXPECTED_MOWER1_INIT_ORIENTATION, mower1.getINITIAL_ORIENTATION());
        assertArrayEquals(EXPECTED_MOWER1_INSTRUCTIONS, mower1.getINSTRUCTIONS());

        Mower mower2 = setUp.getMowers().get(1);
        assertEquals(EXPECTED_MOWER2_INIT_X, mower2.getINITIAL_X());
        assertEquals(EXPECTED_MOWER2_INIT_Y, mower2.getINITIAL_Y());
        assertEquals(EXPECTED_MOWER2_INIT_ORIENTATION, mower2.getINITIAL_ORIENTATION());
        assertArrayEquals(EXPECTED_MOWER2_INSTRUCTIONS, mower2.getINSTRUCTIONS());
    }

    @Test(expected = NoSuchFileException.class)
    public void shouldThrowExceptionIfFileNotFound() throws IOException{
        MowingSetUpReader.readInstruction("not a true path");
    }
}
