package org.guilhem.lawnmower;

import org.guilhem.lawnmower.exception.UnknownOrientationException;
import org.guilhem.lawnmower.model.Mower;
import org.guilhem.lawnmower.model.MowingSetUp;
import org.guilhem.lawnmower.reader.MowingSetUpReader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {

        Path instructionFilePath = Paths.get(args[0]);

        MowingSetUp setUp = MowingSetUpReader.readInstruction(instructionFilePath);

        List<Mower> mowers = setUp.getMowers();

        mowers.forEach(mower -> {
            try {
                String mowerFinalPosition = mower.maw(setUp.getMaxX(), setUp.getMaxY());
                System.out.println(mowerFinalPosition);
            } catch (UnknownOrientationException e) {
                LOGGER.severe("Can not resolve instruction for mower, moving to the next one");
            }
        });
    }
}
