package cs.vsu.ru.Korobeynikova_A_V;

public class Ship {

    int[] startingPosition;
    int shipType;
    Orientation orientation;// todo сделать енумы

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    public Ship(int[] startingPosition, int shipType, Orientation orientation) {
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

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
