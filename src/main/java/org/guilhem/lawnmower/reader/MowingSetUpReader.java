package org.guilhem.lawnmower.reader;

import org.guilhem.lawnmower.exception.MowingInstructionReadingException;
import org.guilhem.lawnmower.exception.NoMowerException;
import org.guilhem.lawnmower.exception.OutOfRangeException;
import org.guilhem.lawnmower.exception.UnknownOrientationException;
import org.guilhem.lawnmower.model.Mower;
import org.guilhem.lawnmower.model.MowingSetUp;
import org.guilhem.lawnmower.utils.Chars;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.logging.Logger;

public class MowingSetUpReader {
    private static final Logger LOGGER = Logger.getLogger(MowingSetUpReader.class.getName());

    private static final char[] ORIENTATION_ARRAY = {'N', 'E', 'S', 'W'};

    public static MowingSetUp readInstruction(Path instructionFilePath) throws MowingInstructionReadingException, IOException {

        MowingSetUp setUp = new MowingSetUp();

        try (BufferedReader reader = Files.newBufferedReader(instructionFilePath, StandardCharsets.UTF_8)) {
            extractFieldSize(setUp, reader.readLine());
            extractMowers(setUp, reader);
        }
        return setUp;
    }

    private static void extractFieldSize(MowingSetUp setUp, String fieldSizeData) throws OutOfRangeException {
        String[] fieldSizeDataSplit = fieldSizeData.split(" ");
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
                throw new OutOfRangeException("x's field size can't be a negative number");
            }
        } catch (NumberFormatException e) {
            LOGGER.severe("Field size for x dimension can not be parsed as an integer.");
            throw e;
        }

    }

    private static void extractMowers(MowingSetUp setUp, BufferedReader bufferedReader) throws MowingInstructionReadingException, IOException {
        String mowerStartSetUp = bufferedReader.readLine();
        if(mowerStartSetUp != null) {
            do {
                String[] startSetUp = mowerStartSetUp.split(" ");
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
