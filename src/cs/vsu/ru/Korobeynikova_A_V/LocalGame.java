package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.Figure.Figure;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.RandomPlacements;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.GameUpdateListener;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.WindowUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LocalGame implements Game {
    GameUI gameUI;

    Player player1;
    Player player2;

    Boolean flagOfUsingOppCell = false;

    private final List<GameUpdateListener> listeners = new ArrayList<>();

    public LocalGame(Player player1, Player player2, GameUI gameUI) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameUI = gameUI;
    }

    @Override
    public void addGameUpdateListener(GameUpdateListener listener) {
        listeners.add(listener);
    }

    @Override
    public void placementOfFigures(Player player, Figure figure) {
        if (player.getShips().size() < player.getField().getCountShips()) {
            makeShip(player, figure);
            if (player.canMakeShipOrNot(figure)) {
                player.setShip(figure);
                listeners.forEach(l -> l.gameUpdated(player.toString()));
                gameUI.print(player);
            } else {
                gameUI.messageOfWrongNumberOrLetter(player.getName());
            }
        } else if (player.getMines().size() < player.getField().getCountMines()) {
            if (player.canMakeMineOrMinesweeperOrNot(figure.getPosition())) {
                player.setMine(figure);
                listeners.forEach(l -> l.gameUpdated(player.toString()));
                gameUI.print(player);
            } else {
                gameUI.messageOfWrongNumberOrLetter(player.getName());
            }
        }
        else if (player.getMinesweepers().size() < player.getField().getCountMinesweepers()) {
            if (player.canMakeMineOrMinesweeperOrNot(figure.getPosition())) {
                player.setMinesweeper(figure);
                listeners.forEach(l -> l.gameUpdated(player.toString()));
                gameUI.print(player);
            } else {
                gameUI.messageOfWrongNumberOrLetter(player.getName());
            }
        }
        else if (player.getSubmarineList().size() < player.getCountSubmarines()) {
            if (player.getField().getCellStatus(figure.getPosition()) == Cell.Status.EMPTY) {
                player.setSubmarine(figure);
                listeners.forEach(l -> l.gameUpdated(player.toString()));
                gameUI.print(player);
            } else {
                gameUI.messageOfWrongNumberOrLetter(player.getName());
            }
        }

        gameUI.messageOfGetCoordinates(player);
        if (player1.getSubmarineList().size() > 0 && player2.getSubmarineList().size() > 0) gameUI.messageOfPlayersReady();
    }

    private void makeShip(Player player, Figure ship) {
        if (player.getShips().size() < player.getField().getOneCellShip()) {

        } else if (player.getShips().size() < player.getField().getOneCellShip()
                + player.getField().getTwoCellShip()) {
            ship.setShipType(Ship.Type.TWO_CELLS);
        } else if (player.getShips().size() < player.getField().getOneCellShip()
                + player.getField().getTwoCellShip()
                + player.getField().getThreeCellShip()) {
            ship.setShipType(Ship.Type.THREE_CELLS);
        } else if (player.getShips().size() < player.getField().getOneCellShip()
                + player.getField().getTwoCellShip()
                + player.getField().getThreeCellShip()
                + player.getField().getFourCellShip()) {
            ship.setShipType(Ship.Type.FOURTH_CELLS);
        }
    }

    @Override
    public void randomPlacement(Player player) {
        gameUI.messageOfRandomPlacementFigures(player.getName());
        RandomPlacements.getRandomField(player);
        listeners.forEach(l -> l.gameUpdated(player.toString()));
    }

    public boolean cellIsOpponentMine(List<Figure> oppMine, Coordinate coordinate) {
        for (Figure mine : oppMine) {
            if (mine.getPosition().equals(coordinate)) return true;
        }
        return false;
    }

    public void moveOnTheOpponent(Player player, Player playerAttacked, Coordinate coordinate) {
        if (player.getShotFromASubmarineList().size() > 0) { // если противник попал в подлодку
            coordinate = player.getShotFromASubmarine();
        } else {
            if (player.getOpponentShipCells().size() != 0) { // если известны координаты корабля противника
                if (gameUI.decisionOfUsingOpponentsPartOfTheShip(player.getName()) == 1) {
                    coordinate = player.getOpponentShipCell();
                    flagOfUsingOppCell = true;
                }
            }
        }

        if (player.getOpponentMines().size() > 0 && cellIsOpponentMine(player.getOpponentMines(), coordinate))
            gameUI.messageOfExistenceOpponentsMineOnThisCell(player.getName());

        gameUI.messageWhereDidMove(player.getName(), coordinate);
        statusAttacks(playerAttacked.getField().getCellStatus(coordinate), coordinate, player, playerAttacked);

        finish(player1, player2);
    }

    private void statusAttacks(Cell.Status cellStatus, Coordinate coordinate, Player player, Player playerAttacked) {
        switch (cellStatus) {
            case SHIP -> {
                player.getOpponentsField().setCellStatus(coordinate, Cell.Status.MARKED);
                playerAttacked.getField().setCellStatus(coordinate, Cell.Status.MARKED);

                listeners.forEach(l -> l.gameUpdated(player.toString()));

                if (!player.hurtOrKill(playerAttacked.getField(), playerAttacked.findShip(coordinate.getVertical(), coordinate.getHorizontal()))) {
                    gameUI.messageOfShipState(player.getName(), "ранил");
                } else {
                    gameUI.messageOfShipState(player.getName(), "убил");
                }
            }
            case EMPTY -> {
                gameUI.messageOfEmptyCell(player.getName());
                player.getOpponentsField().setCellStatus(coordinate, Cell.Status.MARKED);
                playerAttacked.getField().setCellStatus(coordinate, Cell.Status.MARKED);

                listeners.forEach(l -> l.gameUpdated(player.toString()));
            }
            case MARKED -> gameUI.messageOfMarkedCell(player.getName());
            case MINE -> {
                boolean flag = false;
                for (int i = 0; i < player.getOpponentMines().size(); i++) {
                    if (player.getOpponentMines().get(i).getPosition().equals(coordinate)) {
                        flag = true;
                        gameUI.messageOfExistenceOpponentsMineOnThisCell(player.getName());
                        break;
                    }
                }
                if (!flag) {
                    for (int i = 0; i < playerAttacked.getMines().size(); i++) {
                        if (playerAttacked.getMines().get(i).getPosition().equals(coordinate)) {
                            playerAttacked.getMines().get(i).setStatus(Figure.Status.KILLED); break;
                        }
                    }
                    player.getOpponentsField().setCellStatus(coordinate, Cell.Status.MARKED);
                    playerAttacked.getField().setCellStatus(coordinate, Cell.Status.MARKED);

                    listeners.forEach(l -> l.gameUpdated(player.toString()));

                    gameUI.messageOfEntryOfOpponentsMineOrMinesweeper(player.getName(), "мину","корабля");
                    Coordinate coordinates = gameUI.getCoordinates(player);
                    while (player.getField().getCellStatus(coordinates) != Cell.Status.SHIP) {
                        gameUI.messageThatYouAreALiar(player.getName(), "корабля");
                        coordinates = gameUI.getCoordinates(player);
                    }
                    playerAttacked.setOpponentShipCells(coordinates);
                }

            }
            case MINESWEEPER -> {
                for (int i = 0; i < playerAttacked.getMinesweepers().size(); i++) {
                    if (playerAttacked.getMinesweepers().get(i).getPosition().equals(coordinate)) {
                        playerAttacked.getMinesweepers().get(i).setStatus(Figure.Status.KILLED); break;
                    }
                }
                player.getOpponentsField().setCellStatus(coordinate, Cell.Status.MARKED);
                playerAttacked.getField().setCellStatus(coordinate, Cell.Status.MARKED);

                listeners.forEach(l -> l.gameUpdated(player.toString()));

                gameUI.messageOfEntryOfOpponentsMineOrMinesweeper(player.getName(), "минного тральщика","мины");
                Coordinate coordinates = gameUI.getCoordinates(player);
                while (player.getField().getCellStatus(coordinates) != Cell.Status.MINE) {
                    gameUI.messageThatYouAreALiar(player.getName(), "мины");
                    coordinates = gameUI.getCoordinates(player);
                }
                for (Figure mine : player.getMines()) {
                    if (mine.getPosition().equals(coordinates)) playerAttacked.setOpponentMines(mine);
                }

            }
            case SUBMARINE -> {
                playerAttacked.getSubmarineList().get(0).setStatus(Figure.Status.KILLED);

                player.getOpponentsField().setCellStatus(coordinate, Cell.Status.MARKED);
                playerAttacked.getField().setCellStatus(coordinate, Cell.Status.MARKED);

                listeners.forEach(l -> l.gameUpdated(player.toString()));

                gameUI.messageOfEntryOfSubmarine(player.getName());
                playerAttacked.setShotFromASubmarine(coordinate);
            }
        }
    }

    public void finish(Player player1, Player player2) {
        if (!shipsLifeStatus(player2.getShips())) gameUI.messageOfFinish(player1.getName());
        else if (!shipsLifeStatus(player1.getShips())) gameUI.messageOfFinish(player2.getName());
    }

    public boolean shipsLifeStatus(List<Figure> ships) {
        for (Figure ship : ships) {
            if (ship.getStatus() == Ship.Status.ALIVE) return true;
        }
        return false;
    }

    private boolean isNumeric(String str) {
        if (str.length() != 1) return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public List<GameUpdateListener> getListeners() {
        return listeners;
    }

    public Boolean getFlagOfUsingOppCell() {
        return flagOfUsingOppCell;
    }

    public void setFlagOfUsingOppCell(Boolean flagOfUsingOppCell) {
        this.flagOfUsingOppCell = flagOfUsingOppCell;
    }
}
