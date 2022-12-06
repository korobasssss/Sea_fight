package cs.vsu.ru.Korobeynikova_A_V.Figure;

import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;

public class Ship implements Figure{

    private Coordinate position;
    private Type shipType;
    private Orientation orientation;
    private Figure.Status status;

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

//    public enum Status {
//        ALIVE,
//        KILLED
//    }

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

    public Ship(Coordinate startingPosition, Type shipType, Orientation orientation, Figure.Status status) {
        this.position = startingPosition;
        this.shipType = shipType;
        this.orientation = orientation;
        this.status = status;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
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

    public Figure.Status getStatus() {
        return status;
    }

    public void setStatus(Figure.Status status) {
        this.status = status;
    }
}
