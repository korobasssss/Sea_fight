package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel;

import cs.vsu.ru.Korobeynikova_A_V.field.Cell;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CellRenderer extends JLabel implements TableCellRenderer {

    public CellRenderer()
    {
        super.setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String strV = String.valueOf(value);
        switch (strV) {
            case "1" -> super.setBackground(Color.GREEN); // корабль
            case "0" ->super.setBackground(Color.WHITE); // пусто
            case "#" -> super.setBackground(Color.ORANGE); // помечено
            case "?" -> super.setBackground(Color.LIGHT_GRAY); // неизвестно
            case "△" -> super.setBackground(Color.blue); // мина
            case "▲" -> super.setBackground(Color.MAGENTA); // минный тральщик
            case "◊" -> super.setBackground(Color.CYAN); // подлодкаминный тральщик
        }
        return this;
    }
}
