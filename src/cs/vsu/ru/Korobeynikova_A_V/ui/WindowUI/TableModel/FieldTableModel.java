package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel;

import cs.vsu.ru.Korobeynikova_A_V.Game;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;

import javax.swing.table.AbstractTableModel;

public class FieldTableModel extends AbstractTableModel implements GameUpdateListener {

    private Cell[][] field;
    Game game;

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    private final String[] columnNames = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}; //todo добавить нумерацию строк

    private final Class columnClass = Cell.class;

    public FieldTableModel(Cell[][] field, Game game) {
        this.field = field;
        this.game = game;
        game.addGameUpdateListener(this);
    }

    @Override
    public int getRowCount() {
        return field.length;
    }

    @Override
    public int getColumnCount() {
        return field[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return field[rowIndex][columnIndex].getVisual();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setField(Cell[][] field) {
        this.field = field;
    }

    public void setValueAt(Cell value, int row, int col) { field[row][col] = value;}

    @Override
    public void gameUpdated(String toString) {
        fireTableDataChanged();
    }
}
