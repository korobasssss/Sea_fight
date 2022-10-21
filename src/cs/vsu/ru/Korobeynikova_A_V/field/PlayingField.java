package cs.vsu.ru.Korobeynikova_A_V.field;

import java.util.Arrays;

public class PlayingField {
    int size = 10;
    private final char[][] field = fillCell(new char[size][size]);

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

    public boolean checkShepFor9Cells(int row0, int col0) {
        int row = row0 - 1;
        int col = col0 - 1;

        while(row <= row0 + 1) {
            if (row  >= 0 && row < field.length) {
                col = col0 - 1;
                while (col <= col0 + 1) {
                    if (col  >= 0 && col < field.length) {
                        if (row == row0 && col == col0) {}
                        else if (field[row][col] == '1') return true;
                    }
                    col++;
                }
            }
            row++;
        }
        return false;
    }

    public void addRndField(char[][] newField) {
        for (int row = 0; row < field.length; row++) {
            field[row] = newField[row];
        }
    }

    public void unknownField() {
        for (int row = 0; row < field.length; row++) {
            Arrays.fill(field[row], '?');
        }
    }

    public int length() {
        return field.length;
    }

    public char[][] toArray() {
        return field;
    }

}
