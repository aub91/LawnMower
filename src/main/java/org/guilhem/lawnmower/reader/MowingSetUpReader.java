package org.guilhem.lawnmower.reader;

import org.guilhem.lawnmower.model.Mower;
import org.guilhem.lawnmower.model.MowingSetUp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class MowingSetUpReader {
    private static final Logger LOGGER = Logger.getLogger(MowingSetUpReader.class.getName());

    public static MowingSetUp readInstruction(String instructionFilePath) throws IOException {

        MowingSetUp setUp = new MowingSetUp();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(instructionFilePath), StandardCharsets.UTF_8)){
            extractFieldSize(setUp, reader.readLine());
            extractMowers(setUp, reader);
        } catch (NoSuchFileException e) {
            LOGGER.severe("Can not find file with instruction");
            throw e;
        } catch (IOException e) {
            LOGGER.severe("Error while reading instruction file");
            throw e;
        }
        return setUp;
    }

    private static void extractFieldSize(MowingSetUp setUp, String fieldSizeData) {
        String[] fieldSizeDataSplit = fieldSizeData.split(" ");
        setUp.setMaxX(Integer.parseInt(fieldSizeDataSplit[0]));
        setUp.setMaxY(Integer.parseInt(fieldSizeDataSplit[1]));

    }

    private static void extractMowers(MowingSetUp setUp, BufferedReader bufferedReader) throws IOException {
        String mowerStartSetUp = bufferedReader.readLine();
        do {
            String[] startSetUp = mowerStartSetUp.split(" ");
            char[] instructions = bufferedReader.readLine().toCharArray();
            Mower mower = new Mower(Integer.parseInt(startSetUp[0]), Integer.parseInt(startSetUp[1]), startSetUp[2].toCharArray()[0], instructions);

            setUp.addMower(mower);

            mowerStartSetUp = bufferedReader.readLine();
        } while (mowerStartSetUp != null);

    }
}
