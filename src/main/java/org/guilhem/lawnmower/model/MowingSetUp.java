package org.guilhem.lawnmower.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent the set up of the mowing.
 * It is usually read from an instruction file.
 */
public class MowingSetUp {

    /**
     * Maximum x value of the field to maw.
     */
    private int maxX;

    /**
     * Maximum y value of the field to maw.
     */
    private int maxY;

    /**
     * List of the mowers which have mowing instructions for the field.
     */
    private final List<Mower> mowers;

    public MowingSetUp() {
        mowers = new ArrayList<>();
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void addMower(Mower mower) {
        mowers.add(mower);
    }

    public List<Mower> getMowers() {
        return mowers;
    }
}
