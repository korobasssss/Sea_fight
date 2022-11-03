package cs.vsu.ru.Korobeynikova_A_V.Figure;

import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;

public class Ship {

    private Coordinate startingPosition;
    private Type shipType;
    private Orientation orientation;
    private Status status;

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    public enum Status {
        ALIVE,
        KILLED
    }

    public enum Type {
        ONE_CELL("1"),
        TWO_CELLS("2"),
        THREE_CELLS("3"),
        FOURTH_CELLS("4");

        private final String str;

        Type(String str) {
            this.str = str;
        }

        public String getString() { return this.str;}
    }

    public Ship(Coordinate startingPosition, Type shipType, Orientation orientation, Status status) {
        this.startingPosition = startingPosition;
        this.shipType = shipType;
        this.orientation = orientation;
        this.status = status;
    }

    public Coordinate getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Coordinate startingPosition) {
        this.startingPosition = startingPosition;
    }

    public Type getShipType() {
        return shipType;
    }

    public void setShipType(Type shipType) {
        this.shipType = shipType;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
