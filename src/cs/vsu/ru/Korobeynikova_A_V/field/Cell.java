package cs.vsu.ru.Korobeynikova_A_V.field;

public class Cell {
    private char visual;
    private Status status;
    private Coordinate coordinate;

    public enum Status {
        SHIP,
        MARKED,
        EMPTY,
        UNKNOWN,
        MINE,
        MINESWEEPER,
        SUBMARINE
    }

    public Cell(Status status, Coordinate coordinate) {
        this.coordinate = coordinate;
        this.status = status;
        this.visual = setVisual(status);
    }

    public Cell(char visual) {
        this.visual = visual;
    }

    public char getVisual() {
        return visual;
    }

    public void setVisual(char visual) {
        this.visual = visual;
    }

    private char setVisual(Status status) {
        if (status == Status.SHIP) return '1';
        if (status == Status.EMPTY) return '0';
        if (status == Status.MARKED) return  '#';
        if (status == Status.UNKNOWN) return  '?';
        if (status == Status.MINE) return  '△';
        if (status == Status.MINESWEEPER) return '▲';
        if (status == Status.SUBMARINE) return '◊';
        return ' ';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.visual = setVisual(status);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
