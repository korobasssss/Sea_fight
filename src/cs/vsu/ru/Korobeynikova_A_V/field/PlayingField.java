package cs.vsu.ru.Korobeynikova_A_V.field;

import java.util.Arrays;

public class PlayingField {
    public static int size = 10;
    char[][] field = fillCell(new char[size][size]);

    public enum Status {
        SHIP,
        MARKED,
        EMPTY,
        UNKNOWN
    }

    private char[][] fillCell(char[][] fieldFill) {
        for (char[] chars : fieldFill) {
            Arrays.fill(chars, '0');
        }
        return fieldFill;
    }

    public void unknownField() {
        for (char[] chars : field) {
            Arrays.fill(chars, '?');
        }
    }

    public Status getCellStatus(int row, int col) { // не забыть переносить уже col - 1
        return enumStatus(row, col);
    }

    private Status enumStatus(int row, int col) {
        if (field[row][col] == '0') return Status.EMPTY;
        if (field[row][col] == '1') return Status.SHIP;
        if (field[row][col] == '#') return Status.MARKED;

        return Status.EMPTY;
    }

    public void setCellStatus(int row, int col, Status status) {
        field[row][col] = charStatus(status);
    }

    private char charStatus(Status status) {
        if (status == Status.EMPTY) return '0';
        if (status == Status.SHIP) return '1';
        if (status == Status.MARKED) return '#';

        return '0';
    }

    public int length() {
        return field.length;
    }

    public char[][] toArray() {
        return field;
    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }
}
