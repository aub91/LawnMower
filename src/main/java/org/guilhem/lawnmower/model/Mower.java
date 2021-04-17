package org.guilhem.lawnmower.model;

public class Mower {
    private final int INITIAL_X;

    private final int INITIAL_Y;

    private final String INITIAL_ORIENTATION;

    private int x;

    private int y;

    private String orientation;

    private final char[] INSTRUCTIONS;

    public Mower(int INITIAL_X, int INITIAL_Y, String INITIAL_ORIENTATION, char[] INSTRUCTIONS) {
        this.INITIAL_X = INITIAL_X;
        this.INITIAL_Y = INITIAL_Y;
        this.INITIAL_ORIENTATION = INITIAL_ORIENTATION;
        this.INSTRUCTIONS = INSTRUCTIONS;
    }

    public int getINITIAL_X() {
        return INITIAL_X;
    }

    public int getINITIAL_Y() {
        return INITIAL_Y;
    }

    public String getINITIAL_ORIENTATION() {
        return INITIAL_ORIENTATION;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getOrientation() {
        return orientation;
    }

    public char[] getINSTRUCTIONS() {
        return INSTRUCTIONS;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
