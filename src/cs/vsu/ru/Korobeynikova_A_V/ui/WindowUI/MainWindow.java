package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI;

import cs.vsu.ru.Korobeynikova_A_V.Game;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame { // todo не ебу как но переделать этот ебанный говнокод
    private JPanel panelMain;
    private JTable tableFirstPlayer;
    private JTable tableOpponentForFirstPlayer;
    private JTable tableSecondPlayer;

    private JTable tableOpponentForSecondPlayer;
    private JButton buttonStartGame;
    private JPanel panelForMessage;
    private JPanel panelForSetCoordinates;
    private JTextField textFieldGetCoordY;
    private JTextField textFieldGetCoordX;

    public static final int CELLS_COUNT_ON_ROW = 10;
    public static final int DEFAULT_CELL_SIZE = 36;

    private final List<JTable> listTableFields = new ArrayList<>();
    public MainWindow() {
        this.setTitle("МОРСКОЙ БОЙ");
        this.setContentPane(panelMain);
        this.setSize(new Dimension(900, 900));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);


        listTableFields.add(tableFirstPlayer);
        listTableFields.add(tableSecondPlayer);
        listTableFields.add(tableOpponentForFirstPlayer);
        listTableFields.add(tableOpponentForSecondPlayer);

        FieldUI fieldUI = new FieldUI();

        for (JTable table : listTableFields) {
            table.setRowHeight(DEFAULT_CELL_SIZE);
            Cell[][] cells = PlayingField.fillCell(new Cell[10][10]);
            fieldUI.setValuesToField(table, cells);
        }


        buttonStartGame.addActionListener(e -> {
            WindowUI windowUI = new WindowUI();
            Game game = new Game();
            game.game(windowUI);
        });

    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public JTable getTableFirstPlayer() {
        return tableFirstPlayer;
    }

    public JTable getTableOpponentForFirstPlayer() {
        return tableOpponentForFirstPlayer;
    }

    public JTable getTableSecondPlayer() {
        return tableSecondPlayer;
    }

    public JTable getTableOpponentForSecondPlayer() {
        return tableOpponentForSecondPlayer;
    }

    public JPanel getPanelForMessage() {
        return panelForMessage;
    }

    public JTextField getTextFieldGetCoordY() {
        return textFieldGetCoordY;
    }

    public JTextField getTextFieldGetCoordX() {
        return textFieldGetCoordX;
    }

    public List<JTable> getListTableFields() {
        return listTableFields;
    }
}
