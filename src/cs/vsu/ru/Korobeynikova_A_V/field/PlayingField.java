package cs.vsu.ru.Korobeynikova_A_V.field;

import cs.vsu.ru.Korobeynikova_A_V.Ship;

import java.util.Arrays;

public class PlayingField {
    int size = 10;
    private char[][] field = fillCell(new char[size][size]);

    private char[][] fillCell(char[][] fieldFill) {
        for (int row = 0; row < fieldFill.length; row++) {
            Arrays.fill(fieldFill[row], '0');
        }
        return fieldFill;
    }

    public char getCellStatus(int row, int col) { // не забыть переносить уже col - 1
        return field[row][col];
    }

    public void setCellStatus(int row, int col, char status) {
        field[row][col] = status;
    }

    public void unknownField() {
        for (char[] chars : field) {
            Arrays.fill(chars, '?');
        }
    }

    public void addRndField(char[][] newField) {
        field = newField;
    }

    public int length() {
        return field.length;
    }

    public char[][] toArray() {
        return field;
    }

}
