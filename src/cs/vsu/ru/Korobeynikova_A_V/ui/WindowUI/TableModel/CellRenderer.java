package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel;

import cs.vsu.ru.Korobeynikova_A_V.field.Cell;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellRenderer extends JLabel implements TableCellRenderer { // todo не цвета, а картинки

    public CellRenderer()
    {
        super.setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String strV = String.valueOf(value);
        switch (strV) {
            case "1" -> super.setBackground(Color.GREEN);
            case "0" ->super.setBackground(Color.WHITE);
            case "#" -> super.setBackground(Color.ORANGE);
            case "?" -> super.setBackground(Color.LIGHT_GRAY);
            case "△" -> super.setBackground(Color.blue);
            case "▲" -> super.setBackground(Color.MAGENTA);
            case "◊" -> super.setBackground(Color.CYAN);
        }
        return this;
    }
}
