package cs.vsu.ru.Korobeynikova_A_V.field;

public class PlayingField {
    public static int size = 10;
    Cell[][] field = fillCell(new Cell[size][size]);


    private Cell[][] fillCell(Cell[][] fieldFill) {

        for (int row = 0; row < fieldFill.length; row++) {
            for (int col = 0; col < fieldFill[0].length; col++) {
                Cell cell = new Cell(Cell.Status.EMPTY);
                fieldFill[row][col] = cell;
            }

        }
        return fieldFill;
    }

    public void unknownField() {
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[0].length; col++) {
                Cell cell = new Cell(Cell.Status.UNKNOWN);
                field[row][col] = cell;
            }

        }
    }

    public void setCellStatus(int row, int col, Cell.Status status) {
        field[row][col].setStatus(status);
    }

    public Cell.Status getCellStatus(int row, int col) {
        return field[row][col].status;
    }

    public int length() {
        return field.length;
    }

    public Cell[][] getField() {
        return field;
    }

    public void setField(Cell[][] field) {
        this.field = field;
    }
}
