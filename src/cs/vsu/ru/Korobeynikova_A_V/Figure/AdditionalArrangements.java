package cs.vsu.ru.Korobeynikova_A_V.Figure;


import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;

public class AdditionalArrangements implements Figure {
    private Coordinate position;
    private Figure.Status status;

//    public enum Status {
//        ACTIVATED,
//        NOT_ACTIVATED
//    }

    public AdditionalArrangements(Coordinate position, Figure.Status status) {
        super();
        this.position = position;
        this.status = status;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    @Override
    public Ship.Type getShipType() {
        return null;
    }

    @Override
    public void setShipType(Ship.Type shipType) {

    }

    @Override
    public Ship.Orientation getOrientation() {
        return null;
    }

    @Override
    public void setOrientation(Ship.Orientation orientation) {

    }

    public Figure.Status getStatus() {
        return status;
    }

    public void setStatus(Figure.Status status) {
        this.status = status;
    }
}
