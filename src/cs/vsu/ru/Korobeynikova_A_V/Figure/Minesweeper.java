package cs.vsu.ru.Korobeynikova_A_V.Figure;

import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;

public class Minesweeper {

    Coordinate position;
    Status status;

    public enum Status {
        ACTIVATED,
        NOT_ACTIVATED
    }

    public Minesweeper(Coordinate position, Status status) {
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
