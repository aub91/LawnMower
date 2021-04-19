package org.guilhem.lawnmower.reader;

import org.guilhem.lawnmower.exception.MowingInstructionReadingException;
import org.guilhem.lawnmower.exception.OutOfRangeException;
import org.guilhem.lawnmower.exception.UnknownOrientationException;
import org.guilhem.lawnmower.exception.WrongMowingInstructionFormat;
import org.guilhem.lawnmower.model.Mower;
import org.guilhem.lawnmower.model.MowingSetUp;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class MowingSetUpReaderTest {
    private static final String INSTRUCTION_PATH = "/instructions";

    private static final String INSTRUCTION_WRONG_FIELD_SIZE_PATH = "/instructionsWithWrongFieldSize";

    private static final String INSTRUCTION_WRONG_MOWER_POSITION_PATH = "/instructionsWithWrongMowerPosition";

    private static final String INSTRUCTION_WRONG_MOWER_ORIENTATION_PATH = "/instructionsWithWrongMowerOrientation";

    private static final String INSTRUCTION_WRONG_FIELD_DATA_SIZE_PATH = "/instructionsWithWrongFieldDataSize";

    private static final String INSTRUCTION_WRONG_MOWER_DATA_SIZE_PATH = "/instructionsWithWrongMowerDataSize";

    private static final int EXPECTED_MAX_X = 5;

    private static final int EXPECTED_MAX_Y = 5;

    private static final int EXPECTED_MOWER_LIST_SIZE = 2;

    private static final int EXPECTED_MOWER1_INIT_X = 1;

    private static final int EXPECTED_MOWER1_INIT_Y = 2;

    private static final char EXPECTED_MOWER1_INIT_ORIENTATION = 'N';

    private static final char[] EXPECTED_MOWER1_INSTRUCTIONS = {'G', 'A', 'G', 'A', 'G','A','G','A', 'A'};

    private static final int EXPECTED_MOWER2_INIT_X = 3;

    private static final int EXPECTED_MOWER2_INIT_Y = 3;

    private static final char EXPECTED_MOWER2_INIT_ORIENTATION = 'E';

    private static final char[] EXPECTED_MOWER2_INSTRUCTIONS = {'A', 'A', 'D', 'A', 'A','D','A','D', 'D', 'A'};

    @Test
    public void shouldExtractMowingCondition() throws MowingInstructionReadingException, IOException, URISyntaxException {
        URL instructionFileURL = this.getClass().getResource(INSTRUCTION_PATH);
        Path instructionFilePath = Paths.get(instructionFileURL.toURI());

        MowingSetUp setUp = MowingSetUpReader.readInstruction(instructionFilePath);

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

    @Test(expected = OutOfRangeException.class)
    public void shouldFailOnWrongFieldSize() throws MowingInstructionReadingException, IOException, URISyntaxException {
        URL instructionFileURL = this.getClass().getResource(INSTRUCTION_WRONG_FIELD_SIZE_PATH);
        Path instructionFilePath = Paths.get(instructionFileURL.toURI());

        MowingSetUpReader.readInstruction(instructionFilePath);
    }

    @Test(expected = OutOfRangeException.class)
    public void shouldFailOnWrongInitialMowerPosition() throws MowingInstructionReadingException, IOException, URISyntaxException {
        URL instructionFileURL = this.getClass().getResource(INSTRUCTION_WRONG_MOWER_POSITION_PATH);
        Path instructionFilePath = Paths.get(instructionFileURL.toURI());

        MowingSetUpReader.readInstruction(instructionFilePath);
    }

    @Test(expected = UnknownOrientationException.class)
    public void shouldFailOnWrongInitialMowerOrientation() throws MowingInstructionReadingException, IOException, URISyntaxException {
        URL instructionFileURL = this.getClass().getResource(INSTRUCTION_WRONG_MOWER_ORIENTATION_PATH);
        Path instructionFilePath = Paths.get(instructionFileURL.toURI());

        MowingSetUpReader.readInstruction(instructionFilePath);
    }

    @Test(expected = WrongMowingInstructionFormat.class)
    public void shouldFailOnWrongFieldDataSize() throws MowingInstructionReadingException, IOException, URISyntaxException {
        URL instructionFileURL = this.getClass().getResource(INSTRUCTION_WRONG_FIELD_DATA_SIZE_PATH);
        Path instructionFilePath = Paths.get(instructionFileURL.toURI());

        MowingSetUpReader.readInstruction(instructionFilePath);
    }

    @Test(expected = WrongMowingInstructionFormat.class)
    public void shouldFailOnWrongMowerDataSize() throws MowingInstructionReadingException, IOException, URISyntaxException {
        URL instructionFileURL = this.getClass().getResource(INSTRUCTION_WRONG_MOWER_DATA_SIZE_PATH);
        Path instructionFilePath = Paths.get(instructionFileURL.toURI());

        MowingSetUpReader.readInstruction(instructionFilePath);
    }
}
