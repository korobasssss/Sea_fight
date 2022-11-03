package cs.vsu.ru.Korobeynikova_A_V.field;

public class PlayingField {
    private static final int size = 10;
    private Cell[][] field = fillCell(new Cell[size][size]);


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

    public void setCellStatus(Coordinate coordinate, Cell.Status status) {
        field[coordinate.getVertical()][coordinate.getHorizontal()].setStatus(status);
    }

    public Cell.Status getCellStatus(Coordinate coordinate) {
        return field[coordinate.getVertical()][coordinate.getHorizontal()].getStatus();
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

    public static int getSize() {
        return size;
    }
}
