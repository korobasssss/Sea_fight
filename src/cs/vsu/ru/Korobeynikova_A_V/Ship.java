package cs.vsu.ru.Korobeynikova_A_V;

public class Ship {

    int[] startingPosition;
    int shipType;
    int orientation;

    public Ship(int[] startingPosition, int shipType, int orientation) {
        this.startingPosition = startingPosition;
        this.shipType = shipType;
        this.orientation = orientation;
    }

    public int[] getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(int[] startingPosition) {
        this.startingPosition = startingPosition;
    }

    public int getShipType() {
        return shipType;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
