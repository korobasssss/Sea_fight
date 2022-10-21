package cs.vsu.ru.Korobeynikova_A_V.field;

import java.util.Arrays;

public class PlayingField {
    int size = 10;
    char[][] field = fillCell(new char[size][size]);

    private char[][] fillCell(char[][] fieldFill) {
        for (int row = 0; row < fieldFill.length; row++) {
            Arrays.fill(fieldFill[row], '0');
        }
        return fieldFill;
    }

    public void unknownField() {
        for (char[] chars : field) {
            Arrays.fill(chars, '?');
        }
    }

    public void addRndField(char[][] newField) {
        field = newField;
    }

    public char getCellStatus(int row, int col) { // не забыть переносить уже col - 1
        return field[row][col];
    }

    public void setCellStatus(int row, int col, char status) {
        field[row][col] = status;
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
