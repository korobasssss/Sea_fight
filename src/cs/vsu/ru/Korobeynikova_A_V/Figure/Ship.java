package cs.vsu.ru.Korobeynikova_A_V.Figure;

import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;

public class Ship {

    Coordinate startingPosition;
    int shipType;
    Orientation orientation;
    Status status;

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    } // todo слелать енум статуса жизни, и чтобы не считать кол-во кораблей, просто проходится по их жизни

    public enum Status {
        ALIVE,
        KILLED
    }

    public Ship(Coordinate startingPosition, int shipType, Orientation orientation, Status status) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
