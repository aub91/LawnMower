package org.guilhem.lawnmower;

import org.guilhem.lawnmower.exception.UnknownOrientationException;
import org.guilhem.lawnmower.model.Mower;
import org.guilhem.lawnmower.model.MowingSetUp;
import org.guilhem.lawnmower.reader.MowingSetUpReader;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {


        MowingSetUp setUp = MowingSetUpReader.readInstruction(args[0]);

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
