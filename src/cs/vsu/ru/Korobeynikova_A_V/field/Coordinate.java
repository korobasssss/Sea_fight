package cs.vsu.ru.Korobeynikova_A_V.field;

import java.util.Objects;

public class Coordinate {
    private int vertical;
    private int horizontal;

    public Coordinate(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return vertical == that.vertical && horizontal == that.horizontal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertical, horizontal);
    }
}
