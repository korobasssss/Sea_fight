package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI;

import cs.vsu.ru.Korobeynikova_A_V.LocalGame;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.CellRenderer;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.FieldTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private JPanel panelMain;
    private JTable tableFirstPlayer;
    private JTable tableOpponentForFirstPlayer;
    private JTable tableSecondPlayer;
    private JTable tableOpponentForSecondPlayer;

    private JButton buttonStartGame;
    private JTextArea areaForMessage;

    public static final int CELLS_COUNT_ON_ROW = 10;
    public static final int DEFAULT_CELL_SIZE = 36;


    public MainWindow() {
        this.setTitle("МОРСКОЙ БОЙ");
        this.setContentPane(panelMain);
        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        WindowUI windowUI = new WindowUI(new ArrayList<>(), areaForMessage);
        Coordinate coordinate = new Coordinate(-1, -1);

        windowUI.setTable(tableFirstPlayer);
        windowUI.setTable(tableSecondPlayer);
        windowUI.setTable(tableOpponentForFirstPlayer);
        windowUI.setTable(tableOpponentForSecondPlayer);

//        for (JTable table : windowUI.getTables()) {
//            table.setRowHeight(DEFAULT_CELL_SIZE);
//            FieldTableModel fieldTableModelF = new FieldTableModel(PlayingField.fillCell(new Cell[10][10]));
//            table.setModel(fieldTableModelF);
//            table.setDefaultRenderer(Cell.class, new CellRenderer());
//            windowUI.setModels(fieldTableModelF);
//            table.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    System.out.println(table.rowAtPoint(e.getPoint()));;
//                    System.out.println(table.columnAtPoint(e.getPoint()));
//                }
//            });
//        }


        buttonStartGame.addActionListener(e -> {
            LocalGame game = new LocalGame(windowUI);

            tableFirstPlayer.setRowHeight(DEFAULT_CELL_SIZE);
            FieldTableModel fieldTableModelF = new FieldTableModel(game.getPlayer1().getField().getField(), game);
            tableFirstPlayer.setModel(fieldTableModelF);
            tableFirstPlayer.setDefaultRenderer(Cell.class, new CellRenderer());
            windowUI.setModels(fieldTableModelF);

            tableSecondPlayer.setRowHeight(DEFAULT_CELL_SIZE);
            FieldTableModel fieldTableModelS = new FieldTableModel(game.getPlayer2().getField().getField(), game);
            tableSecondPlayer.setModel(fieldTableModelS);
            tableSecondPlayer.setDefaultRenderer(Cell.class, new CellRenderer());
            windowUI.setModels(fieldTableModelS);

            tableOpponentForFirstPlayer.setRowHeight(DEFAULT_CELL_SIZE);
            FieldTableModel fieldTableModelOF = new FieldTableModel(game.getPlayer1().getOpponentsField().getField(), game);
            tableOpponentForFirstPlayer.setModel(fieldTableModelOF);
            tableOpponentForFirstPlayer.setDefaultRenderer(Cell.class, new CellRenderer());
            windowUI.setModels(fieldTableModelOF);

            tableOpponentForSecondPlayer.setRowHeight(DEFAULT_CELL_SIZE);
            FieldTableModel fieldTableModelOS = new FieldTableModel(game.getPlayer2().getOpponentsField().getField(), game);
            tableOpponentForSecondPlayer.setModel(fieldTableModelOS);
            tableOpponentForSecondPlayer.setDefaultRenderer(Cell.class, new CellRenderer());
            windowUI.setModels(fieldTableModelOS);

//            tableOpponentForFirstPlayer.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    System.out.println(tableOpponentForFirstPlayer.rowAtPoint(e.getPoint()));;
//                    System.out.println(tableOpponentForFirstPlayer.columnAtPoint(e.getPoint()));
//                    coordinate.setVertical(tableOpponentForFirstPlayer.rowAtPoint(e.getPoint()));
//                    coordinate.setHorizontal(tableOpponentForFirstPlayer.columnAtPoint(e.getPoint()));
//                }
//            });
//
//            tableOpponentForSecondPlayer.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    System.out.println(tableOpponentForSecondPlayer.rowAtPoint(e.getPoint()));;
//                    System.out.println(tableOpponentForSecondPlayer.columnAtPoint(e.getPoint()));
//                    coordinate.setVertical(tableOpponentForSecondPlayer.rowAtPoint(e.getPoint()));
//                    coordinate.setHorizontal(tableOpponentForSecondPlayer.columnAtPoint(e.getPoint()));
//                }
//            });

            game.game();
        });



    }
}
