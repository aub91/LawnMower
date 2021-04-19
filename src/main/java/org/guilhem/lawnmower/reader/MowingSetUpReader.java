package org.guilhem.lawnmower.reader;

import org.guilhem.lawnmower.exception.*;
import org.guilhem.lawnmower.model.Mower;
import org.guilhem.lawnmower.model.MowingSetUp;
import org.guilhem.lawnmower.utils.Chars;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class with methods to read mowing instruction from a file.
 */
public class MowingSetUpReader {

    private static final char[] ORIENTATION_ARRAY = {'N', 'E', 'S', 'W'};

    public static MowingSetUp readInstruction(Path instructionFilePath) throws MowingInstructionReadingException, IOException {

        MowingSetUp setUp = new MowingSetUp();

        try (BufferedReader reader = Files.newBufferedReader(instructionFilePath, StandardCharsets.UTF_8)) {
            extractFieldSize(setUp, reader.readLine());
            extractMowers(setUp, reader);
        }
        return setUp;
    }

    /**
     * Set MowingSetUp field information according to the given data extracted from instruction file
     * @param setUp the {@link MowingSetUp} to modify
     * @param fieldSizeData data about field size
     * @throws MowingInstructionReadingException  if some data in instruction file are wrong
     */
    private static void extractFieldSize(MowingSetUp setUp, String fieldSizeData) throws MowingInstructionReadingException {
        String[] fieldSizeDataSplit = fieldSizeData.split(" ");
        if(fieldSizeDataSplit.length != 2) {
            throw new WrongMowingInstructionFormat("Field size line does not have expected data");
        }

        try {
            int maxX = Integer.parseInt(fieldSizeDataSplit[0]);
            if(maxX >= 0) {
                setUp.setMaxX(maxX);
            } else {
                throw new OutOfRangeException("x's field size can't be a negative number");
            }
        } catch (NumberFormatException e) {
            throw new OutOfRangeException("Field size for x dimension can not be parsed as an integer.", e);
        }

        try {
            int maxY = Integer.parseInt(fieldSizeDataSplit[1]);
            if(maxY >= 0) {
                setUp.setMaxY(maxY);
            } else {
                throw new OutOfRangeException("y's field size can't be a negative number");
            }
        } catch (NumberFormatException e) {
            throw new WrongMowingInstructionFormat("Field size for y dimension can not be parsed as an integer.", e);
        }

    }

    /**
     * Set MowingSetUp field information according to the given data extracted from instruction file
     * @param setUp the {@link MowingSetUp} to modify
     * @param bufferedReader {@link BufferedReader} from which data are read
     * @throws MowingInstructionReadingException if some data in instruction file are wrong
     * @throws IOException if data can not be read from instruction file
     */
    private static void extractMowers(MowingSetUp setUp, BufferedReader bufferedReader) throws MowingInstructionReadingException, IOException {
        String mowerStartSetUp = bufferedReader.readLine();

        if(mowerStartSetUp != null) {
            do {
                String[] startSetUp = mowerStartSetUp.split(" ");
                if(startSetUp.length != 3) {
                    throw new WrongMowingInstructionFormat("Mower initial position line does not have expected data");
                }
                char[] instructions = bufferedReader.readLine().toCharArray();
                int initialX = checkMowerXValue(setUp, startSetUp[0]);
                int initialY = checkMowerYValue(setUp, startSetUp[1]);
                char initialOrientation = checkMowerOrientationValue(startSetUp[2]);
                Mower mower = new Mower(initialX, initialY, initialOrientation, instructions);

                setUp.addMower(mower);

                mowerStartSetUp = bufferedReader.readLine();
            } while (mowerStartSetUp != null);
        } else {
            throw new NoMowerException();
        }


    }

    private static int checkMowerXValue(MowingSetUp setUp, String xValue) throws OutOfRangeException {
        try {
            int xValueAsInteger = Integer.parseInt(xValue);
            if(xValueAsInteger <= setUp.getMaxX()) {
                return xValueAsInteger;
            } else {
                throw new OutOfRangeException("x initial value for the mower is superior to field max value for x");
            }

        } catch (NumberFormatException e) {
            throw new OutOfRangeException("Initial x position for the mower can not be parsed as an integer.", e);
        }
    }

    private static int checkMowerYValue(MowingSetUp setUp, String yValue) throws OutOfRangeException {
        try {
            int yValueAsInteger = Integer.parseInt(yValue);
            if(yValueAsInteger <= setUp.getMaxY()) {
                return yValueAsInteger;
            } else {
                throw new OutOfRangeException("y initial value for the mower is superior to field max value for y");
            }

        } catch (NumberFormatException e) {
            throw new OutOfRangeException("Initial y position for the mower can not be parsed as an integer.", e);
        }
    }

    private static char checkMowerOrientationValue(String orientationValue) throws UnknownOrientationException {
        char orientationValueChar = orientationValue.toCharArray()[0];
        if(Chars.indexOf(ORIENTATION_ARRAY, orientationValueChar) != -1) {
            return orientationValue.toCharArray()[0];
        } else {
            throw new UnknownOrientationException();
        }


    }
}
