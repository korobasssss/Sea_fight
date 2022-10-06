package cs.vsu.ru.Korobeynikova_A_V.field;

public class PlayingField {
    private final char[][] field = new char[10][10];

    public boolean getCellStatus(char row, int col) { // не забыть переносить уже col - 1
        return field[changeToIng(row)][col] == '1';
    }

    public void setCellStatus(char row, int col, char status) {
        field[changeToIng(row)][col] = status;
    }

    public boolean checkShepFor9Cells(char row0, int col0) {
        for (int row = row0 - 1; row < row0 + 1; row++) {
            for (int col = col0 - 1; col < col0 + 1; col++) {
                if (field[row][col] == '1') return true;
            }
        }
        return false;
    }


    private int changeToIng(char symbol) { //меняем букву на число
        int num = 0;
        switch (symbol) {
            case 'A':
            case 'B': num = 1;
            case 'C': num = 2;
            case 'D': num = 3;
            case 'E': num = 4;
            case 'F': num = 5;
            case 'G': num = 6;
            case 'H': num = 7;
            case 'I': num = 9;
            case 'J': num = 9;
        }
        return num;
    }
}
