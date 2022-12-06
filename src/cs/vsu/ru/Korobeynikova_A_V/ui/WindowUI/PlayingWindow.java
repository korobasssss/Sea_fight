package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI;

import cs.vsu.ru.Korobeynikova_A_V.Figure.AdditionalArrangements;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Figure;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.Game;
import cs.vsu.ru.Korobeynikova_A_V.LocalGame;
import cs.vsu.ru.Korobeynikova_A_V.Player;
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

public class PlayingWindow extends JFrame {
    private JPanel panelMain;
    private JTable tableFirstPlayer;
    private JTable tableOpponentForFirstPlayer;
    private JTable tableSecondPlayer;
    private JTable tableOpponentForSecondPlayer;

    private JTextArea areaForMessage;

    public static final int CELLS_COUNT_ON_ROW = 10;
    public static final int DEFAULT_CELL_SIZE = 36;

    Coordinate coordinate = new Coordinate(-1, -1);
    Game.Who whoseTurn = Game.Who.FIRST_PLAYER;

    public PlayingWindow(Game game, WindowUI gameUI, ArrayList<FieldTableModel> models) {
        this.setTitle("МОРСКОЙ БОЙ");
        this.setContentPane(panelMain);
        this.setSize(new Dimension(1000, 910));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        gameUI.setTextMessage(areaForMessage);
        gameUI.messageOfWhoseParty(game.getPlayer1().getName());

        tableFirstPlayer.setRowHeight(DEFAULT_CELL_SIZE);
        tableFirstPlayer.setModel(models.get(0));
        tableFirstPlayer.setDefaultRenderer(Cell.class, new CellRenderer());

        tableSecondPlayer.setRowHeight(DEFAULT_CELL_SIZE);
        tableSecondPlayer.setModel(models.get(1));
        tableSecondPlayer.setDefaultRenderer(Cell.class, new CellRenderer());

        tableOpponentForFirstPlayer.setRowHeight(DEFAULT_CELL_SIZE);
        FieldTableModel fieldTableModelOF = new FieldTableModel(game.getPlayer1().getOpponentsField().getField(), game);
        tableOpponentForFirstPlayer.setModel(fieldTableModelOF);
        tableOpponentForFirstPlayer.setDefaultRenderer(Cell.class, new CellRenderer());

        tableOpponentForFirstPlayer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                coordinate.setVertical(tableSecondPlayer.rowAtPoint(e.getPoint()));
                coordinate.setHorizontal(tableSecondPlayer.columnAtPoint(e.getPoint()));
                if (whoseTurn == Game.Who.FIRST_PLAYER) {
                    if (game.getFlagOfUsingOppCell()) game.setFlagOfUsingOppCell(false);
                    else if (game.getPlayer2().getField().getCellStatus(coordinate) != Cell.Status.SHIP) {
                        gameUI.messageOfWhoseParty(game.getPlayer2().getName());
                        whoseTurn = Game.Who.SECOND_PLAYER;
                    }
                    game.moveOnTheOpponent(game.getPlayer1(), game.getPlayer2(), coordinate);
                } else
                    gameUI.messageOfNotYourParty(game.getPlayer1().getName());

            }
        });

        tableOpponentForSecondPlayer.setRowHeight(DEFAULT_CELL_SIZE);
        FieldTableModel fieldTableModelOS = new FieldTableModel(game.getPlayer2().getOpponentsField().getField(), game);
        tableOpponentForSecondPlayer.setModel(fieldTableModelOS);
        tableOpponentForSecondPlayer.setDefaultRenderer(Cell.class, new CellRenderer());

        tableOpponentForSecondPlayer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                coordinate.setVertical(tableSecondPlayer.rowAtPoint(e.getPoint()));
                coordinate.setHorizontal(tableSecondPlayer.columnAtPoint(e.getPoint()));
                if (whoseTurn == Game.Who.SECOND_PLAYER) {
                    if (game.getFlagOfUsingOppCell()) game.setFlagOfUsingOppCell(false);
                    else if (game.getPlayer1().getField().getCellStatus(coordinate) != Cell.Status.SHIP) {
                        gameUI.messageOfWhoseParty(game.getPlayer1().getName());
                        whoseTurn = Game.Who.FIRST_PLAYER;
                    }
                    game.moveOnTheOpponent(game.getPlayer2(), game.getPlayer1(), coordinate);
                } else
                    gameUI.messageOfNotYourParty(game.getPlayer2().getName());
            }
        });
    }
}
