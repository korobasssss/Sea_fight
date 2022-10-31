package cs.vsu.ru.Korobeynikova_A_V.Figure;


import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;

public class Mine {
    Coordinate position;
    Status status;

    public enum Status {
        ACTIVATED,
        NOT_ACTIVATED
    }

    public Mine(Coordinate position, Status status) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
