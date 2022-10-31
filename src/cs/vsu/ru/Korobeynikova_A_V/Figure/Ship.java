package cs.vsu.ru.Korobeynikova_A_V.Figure;

import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;

public class Ship {

    Coordinate startingPosition;
    int shipType;
    Orientation orientation;

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    public Ship(Coordinate startingPosition, int shipType, Orientation orientation) {
        this.startingPosition = startingPosition;
        this.shipType = shipType;
        this.orientation = orientation;
    }

    public Coordinate getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Coordinate startingPosition) {
        this.startingPosition = startingPosition;
    }

    public int getShipType() {
        return shipType;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
