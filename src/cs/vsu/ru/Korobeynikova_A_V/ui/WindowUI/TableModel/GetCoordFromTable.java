package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel;

import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GetCoordFromTable extends MouseAdapter{

    Coordinate coordinate = null;
    JTable table;
    Boolean status = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        coordinate.setVertical(table.rowAtPoint(e.getPoint()));
        coordinate.setHorizontal(table.columnAtPoint(e.getPoint()));
        status = true;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}


//    Coordinate coordinate = new Coordinate(-1, -1);
//    JTable table;
//
//    public Coordinate getCoordinate(JTable table) {
//        setTable(table);
//        if (coordinate.getVertical() != -1) return coordinate;
//        return null;
//    }
//
//    public void setTable(JTable table) {
//        this.table = table;
//        this.table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                coordinate.setVertical(table.rowAtPoint(e.getPoint()));
//                coordinate.setHorizontal(table.columnAtPoint(e.getPoint()));
//            }
//        });
//    }
//
//    public Coordinate getCoordinate() {
//        return coordinate;
//    }
//}
