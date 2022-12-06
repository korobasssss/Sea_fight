package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI;

import cs.vsu.ru.Korobeynikova_A_V.Figure.AdditionalArrangements;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Figure;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.Game;
import cs.vsu.ru.Korobeynikova_A_V.Player;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.CellRenderer;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.FieldTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StartWindow extends JFrame{
    private JPanel panelMain;
    private JTable tableFirstPlayer;
    private JTable tableSecondPlayer;
    private JButton buttonRandomForPlayer1;
    private JButton buttonForPlacementForPlayer1;
    private JButton buttonRandomForPlayer2;
    private JButton buttonForPlacementForPlayer2;
    private JButton buttonStart;
    private JTextArea textAreaForMessage;

    public static final int CELLS_COUNT_ON_ROW = 10;
    public static final int DEFAULT_CELL_SIZE = 36;

    boolean flag1 = false;
    boolean flag2 = false;

    ArrayList<FieldTableModel> models = new ArrayList<>();

    public StartWindow(Player player1, Player player2, Game game, WindowUI windowUI) {
        this.setTitle("МОРСКОЙ БОЙ");
        this.setContentPane(panelMain);
        this.setSize(new Dimension(800, 880));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        tableFirstPlayer.setRowHeight(DEFAULT_CELL_SIZE);
        tableFirstPlayer.setSize(200, 200);
        FieldTableModel fieldTableModelF = new FieldTableModel(player1.getField().getField(), game);
        tableFirstPlayer.setModel(fieldTableModelF);
        tableFirstPlayer.setDefaultRenderer(Cell.class, new CellRenderer());
        models.add(fieldTableModelF);

        tableSecondPlayer.setRowHeight(DEFAULT_CELL_SIZE);
        FieldTableModel fieldTableModelS = new FieldTableModel(player2.getField().getField(), game);
        tableSecondPlayer.setModel(fieldTableModelS);
        tableSecondPlayer.setDefaultRenderer(Cell.class, new CellRenderer());
        models.add(fieldTableModelS);

        windowUI.setTextMessage(textAreaForMessage);

        buttonRandomForPlayer1.addActionListener(e -> {
            models.get(0).setField(PlayingField.fillCell(player1.getField().getField()));
            game.randomPlacement(player1);
        });

        buttonRandomForPlayer2.addActionListener(e -> {
            models.get(1).setField(PlayingField.fillCell(player2.getField().getField()));
            tableSecondPlayer.clearSelection();
            game.randomPlacement(player2);
        });

        buttonForPlacementForPlayer1.addActionListener(e -> {
            windowUI.messageOfGetCoordinates(player1);
            tableFirstPlayer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Coordinate coordinate = new Coordinate(
                            tableSecondPlayer.rowAtPoint(e.getPoint()),
                            tableSecondPlayer.columnAtPoint(e.getPoint()));

                    actionMouseClick(game, windowUI, player1, coordinate, e);
                }
            });
        });

        buttonForPlacementForPlayer2.addActionListener(e -> {
            windowUI.messageOfGetCoordinates(player2);
            tableSecondPlayer.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Coordinate coordinate = new Coordinate(
                            tableSecondPlayer.rowAtPoint(e.getPoint()),
                            tableSecondPlayer.columnAtPoint(e.getPoint()));

                    actionMouseClick(game, windowUI, player2, coordinate, e);
                }
            });
        });


        buttonStart.addActionListener(e -> {
            if (player1.getSubmarineList().size() > 0 && player2.getSubmarineList().size() > 0) {
                PlayingWindow playingWindow = new PlayingWindow(game, windowUI, models);
                playingWindow.setVisible(true);
            } else JOptionPane.showMessageDialog(null, "Вы расставили не все фигуры!!!", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void actionMouseClick(Game game, WindowUI windowUI,  Player player, Coordinate coordinate, MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (player.getSubmarineList().size() > 0) {
                JOptionPane.showMessageDialog(null, "Игрок %s, вы уже расставили все свои фигуры!".formatted(player.getName()), "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else {
                coordinate.setVertical(tableFirstPlayer.rowAtPoint(e.getPoint()));
                coordinate.setHorizontal(tableFirstPlayer.columnAtPoint(e.getPoint()));
                if (player.getShips().size() < player.getField().getCountShips()) {
                    Figure ship = new Ship(coordinate, Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE);
                    game.placementOfFigures(player, ship);
                } else {
                    Figure addArg = new AdditionalArrangements(coordinate, Figure.Status.ALIVE);
                    game.placementOfFigures(player, addArg);
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            coordinate.setVertical(tableSecondPlayer.rowAtPoint(e.getPoint()));
            coordinate.setHorizontal(tableSecondPlayer.columnAtPoint(e.getPoint()));

            if (player.getField().getCellStatus(coordinate) == Cell.Status.SHIP) {
                //player.findShip(tableFirstPlayer.rowAtPoint(e.getPoint()), tableFirstPlayer.columnAtPoint(e.getPoint()));
                Figure ship;
                switch (player.findShip(tableFirstPlayer.rowAtPoint(e.getPoint()), tableFirstPlayer.columnAtPoint(e.getPoint())).getOrientation()) {
                    case VERTICAL -> {
                        ship = new Ship(coordinate,
                                player.findShip(tableFirstPlayer.rowAtPoint(e.getPoint()), tableFirstPlayer.columnAtPoint(e.getPoint())).getShipType(),
                                Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE);
                    }
                    case HORIZONTAL -> {
                        ship = new Ship(coordinate,
                                player.findShip(tableFirstPlayer.rowAtPoint(e.getPoint()), tableFirstPlayer.columnAtPoint(e.getPoint())).getShipType(),
                                Ship.Orientation.VERTICAL, Figure.Status.ALIVE);
                    }
                    default -> ship = null;
                }
                player.deleteFigure(player.findShip(tableFirstPlayer.rowAtPoint(e.getPoint()), tableFirstPlayer.columnAtPoint(e.getPoint())));
                if (player.canMakeShipOrNot(ship)) {
                    player.setShip(ship);
                    game.getListeners().forEach(l -> l.gameUpdated(player.toString()));
                }
                else {
                    Figure ship1 = new Ship(ship.getPosition(), ship.getShipType(), ship.getOrientation(), ship.getStatus());
                    switch (ship1.getOrientation()) {
                        case VERTICAL -> {
                            ship1 = new Ship(coordinate,
                                    ship.getShipType(),
                                    Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE);
                        }
                        case HORIZONTAL -> {
                            ship1 = new Ship(coordinate,
                                    ship.getShipType(),
                                    Ship.Orientation.VERTICAL, Figure.Status.ALIVE);
                        }
                        default -> ship1 = null;
                    }
                    player.setShip(ship1);
                    game.getListeners().forEach(l -> l.gameUpdated(player.toString()));
                    windowUI.messageOfWrongNumberOrLetter(player.getName());
                }
            }
        }
    }
}
