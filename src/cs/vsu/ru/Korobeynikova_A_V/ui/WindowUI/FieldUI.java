package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI;

import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class FieldUI extends JFrame { // todo переместить в класс tableModel

    public void setValuesToField(JTable table, Cell[][] cells) {
        DefaultTableModel defaultTableModel = new DefaultTableModel(MainWindow.CELLS_COUNT_ON_ROW, MainWindow.CELLS_COUNT_ON_ROW);
        change(defaultTableModel, cells);

        table.setModel(defaultTableModel);
    }

    private void change (DefaultTableModel defaultTableModel, Cell[][] cells) {

        Object[][] arr = new Object[cells.length][cells[0].length];
        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells[0].length; c++) {
                arr[r][c] = cells[r][c].getVisual();
                defaultTableModel.setValueAt(arr[r][c], r, c);
            }

        }
    }



}
