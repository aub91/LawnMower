package org.guilhem.lawnmower.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MowingSetUp {

    private int maxX;

    private int maxY;

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
