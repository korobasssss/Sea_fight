package cs.vsu.ru.Korobeynikova_A_V.field;

public class Submarine {
    Coordinate position;
    Status status;

    public enum Status {
        ACTIVATED,
        NOT_ACTIVATED
    }

    public Submarine(Coordinate coordinate, Status status) {
        this.position = coordinate;
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
