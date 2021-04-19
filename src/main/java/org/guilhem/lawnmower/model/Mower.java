package org.guilhem.lawnmower.model;

import org.guilhem.lawnmower.exception.UnknownOrientationException;
import org.guilhem.lawnmower.utils.Chars;

import java.util.StringJoiner;
import java.util.logging.Logger;

public class Mower {
    private static final Logger LOGGER = Logger.getLogger(Mower.class.getName());

    private static final char[] ORIENTATION_ARRAY = {'N', 'E', 'S', 'W'};

    private final int INITIAL_X;

    private final int INITIAL_Y;

    private final char INITIAL_ORIENTATION;

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

    public String getInitialPosition() {
        Position position =
                new Position(this.INITIAL_X, this.INITIAL_Y, Chars.indexOf(ORIENTATION_ARRAY, this.INITIAL_ORIENTATION));
        return constructPosition(position);
    }

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

    private String constructPosition(Position position) {
        StringJoiner finalPositionJoiner = new StringJoiner(" ");
        finalPositionJoiner.add(Integer.toString(position.getX()));
        finalPositionJoiner.add(Integer.toString(position.getY()));
        finalPositionJoiner.add(Character.toString(ORIENTATION_ARRAY[position.getOrientationIndex()]));
        return finalPositionJoiner.toString();
    }

    private Position turnRight(Position position) {
        if(position.getOrientationIndex() != 3) {
            position.setOrientationIndex(position.getOrientationIndex()+1);
        } else {
            position.setOrientationIndex(0);
        }

        return position;
    }

    private Position turnLeft(Position position) {
        if(position.getOrientationIndex() != 0) {
            position.setOrientationIndex(position.getOrientationIndex()-1);
        } else {
            position.setOrientationIndex(3);
        }
        return position;
    }

    private Position move(Position position, int maxX, int maxY) throws UnknownOrientationException {

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
                LOGGER.severe("Unexpected orientation : can not resolve mower move");
                throw new UnknownOrientationException();

        }

        return position;
    }

    private Position moveNorth(Position position, int maxY) {
        Position newPosition = position;
        if(position.getY() < maxY) {
            position.increaseY();
        }
        return newPosition;
    }

    private void moveEast(Position position, int maxX) {
        if(position.getX() < maxX) {
            position.increaseX();
        }
    }

    private void moveSouth(Position position) {
        if(position.getY() != 0) {
            position.decreaseY();
        }
    }

    private void moveWest(Position position) {
        if(position.getX() != 0) {
            position.decreaseX();
        }
    }

    private class Position{
        private int x;

        private int y;

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
