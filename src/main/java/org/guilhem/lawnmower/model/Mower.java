package org.guilhem.lawnmower.model;

import org.guilhem.lawnmower.exception.UnknownOrientationException;
import org.guilhem.lawnmower.utils.Chars;

import java.util.StringJoiner;
import java.util.logging.Logger;

/**
 * Class describing a mower.
 */
public class Mower {
    private static final Logger LOGGER = Logger.getLogger(Mower.class.getName());

    /**
     * Array of possibles orientations.
     */
    private static final char[] ORIENTATION_ARRAY = {'N', 'E', 'S', 'W'};

    /**
     * Initial x position of the mower.
     */
    private final int INITIAL_X;

    /**
     * Initial y position of the mower.
     */
    private final int INITIAL_Y;

    /**
     * Initial orientation of the mower.
     */
    private final char INITIAL_ORIENTATION;

    /**
     * Array of instruction of the mower.
     * An instruction must be among the following value : D, G or A
     */
    private final char[] INSTRUCTIONS;

    public Mower(int INITIAL_X, int INITIAL_Y, char INITIAL_ORIENTATION, char[] INSTRUCTIONS) {
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

    public char getINITIAL_ORIENTATION() {
        return INITIAL_ORIENTATION;
    }

    public char[] getINSTRUCTIONS() {
        return INSTRUCTIONS;
    }

    /**
     * @return a string representation of the initial position of the mower. A position has an x value, an y value and an orientation.
     */
    public String getInitialPosition() {
        Position position =
                new Position(this.INITIAL_X, this.INITIAL_Y, Chars.indexOf(ORIENTATION_ARRAY, this.INITIAL_ORIENTATION));
        return constructPosition(position);
    }

    /**
     * Execute mowing instructions of the mower.
     * If an instruction is not in the array of known instructions, it is ignored and the method proceeds to the next one.
     *
     * @param maxX maximum x value of the field on which the mower is.
     * @param maxY maximum x value of the field on which the mower is.
     * @return a string representation of the final position of the mower. A position has an x value, an y value and an orientation.
     * @throws UnknownOrientationException if for some reason the resulting orientation after an instruction is not a known one.
     */
    public String maw(int maxX, int maxY) throws UnknownOrientationException{
        Position position =
                new Position(this.INITIAL_X, this.INITIAL_Y, Chars.indexOf(ORIENTATION_ARRAY, this.INITIAL_ORIENTATION));

        for (char instruction: INSTRUCTIONS) {
            switch (instruction){
                case 'D':
                    turnRight(position);
                    break;
                case 'G':
                    turnLeft(position);
                    break;
                case 'A':
                    move(position, maxX, maxY);
                    break;
                default:
                    LOGGER.warning("Unexpected instruction : instruction ignored and proceed to the next one.");
                    break;
            }
        }
        return constructPosition(position);
    }

    /**
     * @param position a position made of a x value, a y value and an orientation.
     * @return a string representation of the position. position's element are divided with a space character.
     */
    private String constructPosition(Position position) {
        StringJoiner finalPositionJoiner = new StringJoiner(" ");
        finalPositionJoiner.add(Integer.toString(position.getX()));
        finalPositionJoiner.add(Integer.toString(position.getY()));
        finalPositionJoiner.add(Character.toString(ORIENTATION_ARRAY[position.getOrientationIndex()]));
        return finalPositionJoiner.toString();
    }

    /**
     * Make mower turn on its right.
     * @param position position to modified
     */
    private void turnRight(Position position) {
        if(position.getOrientationIndex() != 3) {
            position.setOrientationIndex(position.getOrientationIndex()+1);
        } else {
            position.setOrientationIndex(0);
        }
    }

    /**
     * Make mower turn on its left.
     * @param position position to modified
     */
    private void turnLeft(Position position) {
        if(position.getOrientationIndex() != 0) {
            position.setOrientationIndex(position.getOrientationIndex()-1);
        } else {
            position.setOrientationIndex(3);
        }
    }

    /**
     * Make mower move from one case in its current orientation. If mower can't move without being out of the field, it does not move.
     * @param position position to modified
     * @param maxX maximum x value of the field
     * @param maxY aximum y value of the field
     */
    private void move(Position position, int maxX, int maxY) throws UnknownOrientationException {

        switch (position.getOrientationIndex()){
            case 0 :
                moveNorth(position, maxY);
                break;
            case 1 :
                moveEast(position, maxX);
                break;
            case 2 :
                moveSouth(position);
                break;
            case 3 :
                moveWest(position);
                break;
            default:
                throw new UnknownOrientationException("Unexpected orientation : can not resolve mower move");

        }
    }

    /**
     * Make mower move one case to the north.
     * @param position position to modified
     * @param maxY aximum y value of the field
     */
    private void moveNorth(Position position, int maxY) {
        if(position.getY() < maxY) {
            position.increaseY();
        }
    }

    /**
     * Make mower move one case to the east.
     * @param position position to modified
     * @param maxX maximum x value of the field
     */
    private void moveEast(Position position, int maxX) {
        if(position.getX() < maxX) {
            position.increaseX();
        }
    }

    /**
     * Make mower move one case to the south.
     * @param position position to modified
     */
    private void moveSouth(Position position) {
        if(position.getY() != 0) {
            position.decreaseY();
        }
    }

    /**
     * Make mower move one case to the west.
     * @param position position to modified
     */
    private void moveWest(Position position) {
        if(position.getX() != 0) {
            position.decreaseX();
        }
    }

    /**
     * Represent a position of the mower.
     */
    private class Position{

        private int x;

        private int y;

        /**
         * Index of the orientation in the ORIENTATION_ARRAY.
         */
        private int orientationIndex;

        Position(int x, int y, int orientationIndex) {
            this.x = x;
            this.y = y;
            this.orientationIndex = orientationIndex;
        }

        public int getX() {
            return x;
        }

        public void increaseX() {
            this.x++;
        }

        public void decreaseX() {
            this.x--;
        }

        public int getY() {
            return y;
        }

        public void increaseY() {
            this.y++;
        }

        public void decreaseY() {
            this.y--;
        }

        public int getOrientationIndex() {
            return orientationIndex;
        }

        public void setOrientationIndex(int orientationIndex) {
            this.orientationIndex = orientationIndex;
        }
    }
}
