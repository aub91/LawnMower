package org.guilhem.lawnmower.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MowingSetUp {

    private final Map<String, Integer> fieldSizeMap;

    private final List<Mower> mowers;

    public MowingSetUp() {
        fieldSizeMap = new HashMap<>();
        fieldSizeMap.put("x", 0);
        fieldSizeMap.put("y", 0);

        mowers = new ArrayList<>();
    }

    public void setMaxX(int maxX) {
        fieldSizeMap.put("x", maxX);
    }

    public void setMaxY(int maxY) {
        fieldSizeMap.put("y", maxY);
    }

    public int getMaxX() {
        return fieldSizeMap.get("x");
    }

    public int getMaxY() {
        return fieldSizeMap.get("y");
    }

    public void addMower(Mower mower) {
        mowers.add(mower);
    }

    public Map<String, Integer> getFieldSizeMap() {
        return fieldSizeMap;
    }

    public List<Mower> getMowers() {
        return mowers;
    }
}
