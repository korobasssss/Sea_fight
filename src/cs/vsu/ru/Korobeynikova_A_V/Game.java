package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.Figure.AdditionalArrangements;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.field.RandomPlacements;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private void attacks(Player player1, Player player2, GameUI gameUI) {
        int who = 1; // 1 - 1 игрок, 2 - 2 игрок

        while (shipsLifeStatus(player1.getShips()) && shipsLifeStatus(player2.getShips())) {
            if (who == 1) {
                moveOnTheOpponent(who, player1, player2, gameUI);
                who = 2;
            } else {
                moveOnTheOpponent(who, player2, player1, gameUI);
                who = 1;
            }
        }
    }

    public void placementOfFigures(Player player, GameUI gameUI) {
        String decision = gameUI.decisionOfPlacementFigures(player.getName());
        while (!isNumeric(decision) && !(decision.equals("0")) && !(decision.equals("1"))) {
            gameUI.messageOfWrongNumberOrLetter(player.getName());
            decision = gameUI.decisionOfPlacementFigures(player.getName());
        }

        if (decision.equals("0")) {
            gameUI.messageOfRandomPlacementFigures(player.getName());
            RandomPlacements.getRandomField(player);
            gameUI.print(player.getField().getField(), player.getNumber());

        } else {
            makeShips(player, gameUI);
            player.setMines(makeAddMineOrMinesweeper(player, player.getCountMines(), gameUI ));
            gameUI.print(player.getField().getField(), player.getNumber());
            player.setMinesweepers(makeAddMineOrMinesweeper(player, player.getCountMinesweepers(), gameUI));
            gameUI.print(player.getField().getField(), player.getNumber());
            player.setSubmarines(makeSubmarines(player, player.getCountSubmarines(), gameUI));
            gameUI.print(player.getField().getField(), player.getNumber());
        }
    }

    private List<AdditionalArrangements> makeAddMineOrMinesweeper(Player player, int count, GameUI gameUI) {
        List<AdditionalArrangements> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            gameUI.messageOfGetCoordinates(player.getName(), "минного поля");
            AdditionalArrangements smth = new AdditionalArrangements(gameUI.getCoordinates(player.getName()), AdditionalArrangements.Status.NOT_ACTIVATED);
            while (!player.canMakeMineOrMinesweeperOrNot(smth.getPosition())) {
                gameUI.messageOfWrongNumberOrLetter(player.getName());
                smth.setPosition(gameUI.getCoordinates(player.getName()));
            }
            list.add(smth);
        }
        return list;
    }

    private List<AdditionalArrangements> makeSubmarines(Player player, int count, GameUI gameUI) {
        List<AdditionalArrangements> list = new ArrayList<>();
        for (int i = 0; i < player.getCountSubmarines(); i++) {
            gameUI.messageOfGetCoordinates(player.getName(), "подлодки");
            AdditionalArrangements submarine = new AdditionalArrangements(gameUI.getCoordinates(player.getName()), AdditionalArrangements.Status.NOT_ACTIVATED);
            while (player.getField().getCellStatus(submarine.getPosition()) != Cell.Status.EMPTY) {
                gameUI.messageOfWrongNumberOrLetter(player.getName());
                gameUI.messageOfGetCoordinates(player.getName(), "подлодки");
                submarine.setPosition(gameUI.getCoordinates(player.getName()));
            }
            list.add(submarine);
            gameUI.print(player.getField().getField(), player.getNumber());
        }
        return list;
    }


    private void makeShips(Player player, GameUI gameUI) { // кол-во типов кораблей
        int shipCellsCount = Ship.Type.values().length;
        gameUI.messageOfGetCoordinates(player.getName(), "кораблей");
        for(Ship.Type type : Ship.Type.values()) {
            for (int ships = shipCellsCount; ships > 0; ships--) {
                Ship ship = new Ship(new Coordinate(0, 0), type, Ship.Orientation.VERTICAL, Ship.Status.ALIVE);
                ship.setShipType(type);
                String orientation;
                if (ship.getShipType() != Ship.Type.ONE_CELL) {
                    orientation = gameUI.decisionOfShipOrientation(player.getName(), type.getString());
                } else orientation = "0";
                while (!(orientation.equals("0")) && !(orientation.equals("1"))) {
                    gameUI.messageOfWrongNumberOrLetter(player.getName());
                    orientation = gameUI.decisionOfShipOrientation(player.getName(), type.getString());
                }
                if (orientation.equals("0")) {
                    ship.setOrientation(Ship.Orientation.VERTICAL);
                } else ship.setOrientation(Ship.Orientation.HORIZONTAL);
                String str = type.getString() + " клеточного корабля";
                gameUI.messageOfGetCoordinates(player.getName(), str);
                System.out.println();
                ship.setStartingPosition(gameUI.getCoordinates(player.getName()));
                if (ship.getOrientation() == Ship.Orientation.VERTICAL){
                    while ((ship.getStartingPosition().getVertical() + Integer.parseInt(ship.getShipType().getString())) - 1 >= PlayingField.getSize()) {
                        gameUI.messageOfWrongNumberOrLetter(player.getName());
                        ship.setStartingPosition(gameUI.getCoordinates(player.getName()));
                    }
                } else {
                    while ((ship.getStartingPosition().getHorizontal() + Integer.parseInt(ship.getShipType().getString())) - 1 >= PlayingField.getSize()) {
                        gameUI.messageOfWrongNumberOrLetter(player.getName());
                        ship.setStartingPosition(gameUI.getCoordinates(player.getName()));
                    }
                }
                while (!player.canMakeShipOrNot(ship)) {
                    gameUI.messageOfWrongNumberOrLetter(player.getName());
                    ship.setStartingPosition(gameUI.getCoordinates(player.getName()));
                }
                player.setShip(ship);

                gameUI.print(player.getField().getField(), player.getNumber());
            }
            shipCellsCount--;
        }
    }

    private boolean cellIsOpponentMine(List<AdditionalArrangements> oppMine, Coordinate coordinate) {
        for (AdditionalArrangements mine : oppMine) {
            if (mine.getPosition().equals(coordinate)) return true;
        }
        return false;
    }

    public void moveOnTheOpponent(int who, Player player, Player playerAttacked, GameUI gameUI) {
        Cell.Status cellStatus = Cell.Status.UNKNOWN;

        Coordinate point;
        while ((cellStatus == Cell.Status.UNKNOWN || cellStatus == Cell.Status.SHIP) && (shipsLifeStatus(player.getShips()) && shipsLifeStatus(playerAttacked.getShips()))) {
            gameUI.messageOfWhoseParty(player.getName());

            if (player.getOpponentShipCells().size() > 0) { // если известны координаты частей кораблей противника
                String decision = gameUI.decisionOfUsingOpponentsPartOfTheShip(player.getName());
                while (!(decision.equals("0")) && !(decision.equals("1"))) {
                    gameUI.messageOfWrongNumberOrLetter(player.getName());
                    decision = gameUI.decisionOfUsingOpponentsPartOfTheShip(player.getName());
                }
                if (decision.equals("1")) point = player.getOpponentShipCell();
                else point = gameUI.getCoordinates(player.getName());
            } else if (player.getShotFromASubmarineList().size() > 0) {
                point = player.getShotFromASubmarine();
            } else point = gameUI.getCoordinates(player.getName());

            if (player.getOpponentMines().size() > 0) { // если известно местоположение мин противника
                while (cellIsOpponentMine(player.getOpponentMines(), point)) {
                    gameUI.messageOfExistenceOpponentsMineOnThisCell(player.getName());
                    point = gameUI.getCoordinates(player.getName());
                }
            }

            gameUI.messageWhereDidMove(player.getName(), point);

            cellStatus = playerAttacked.getField().getCellStatus(point);
            statusAttacks(who, cellStatus, point, player, playerAttacked, gameUI);

            gameUI.messageOfWhoseField(playerAttacked.getName());
            gameUI.print(player.getOpponentsField().getField(), playerAttacked.getNumber() + ".1");

            gameUI.messageOfWhoseField(player.getName());
            gameUI.print(player.getField().getField(), player.getNumber());
        }
    }

    private void statusAttacks(int who, Cell.Status cellStatus, Coordinate coordinate, Player player, Player playerAttacked, GameUI gameUI) {
        switch (cellStatus) {
            case SHIP -> {
                player.getOpponentsField().setCellStatus(coordinate, Cell.Status.MARKED);
                playerAttacked.getField().setCellStatus(coordinate, Cell.Status.MARKED);

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
            }
            case MARKED -> gameUI.messageOfMarkedCell(player.getName());
            case MINE -> {
                for (int i = 0; i < playerAttacked.getMines().size(); i++) {
                    if (playerAttacked.getMines().get(i).getPosition().equals(coordinate)) {
                        playerAttacked.getMines().get(i).setStatus(AdditionalArrangements.Status.ACTIVATED); break;
                    }
                }
                player.getOpponentsField().setCellStatus(coordinate, Cell.Status.MARKED);
                playerAttacked.getField().setCellStatus(coordinate, Cell.Status.MARKED);

                gameUI.messageOfEntryOfOpponentsMineOrMinesweeper(player.getName(), "мину","корабля");
                Coordinate coordinates = gameUI.getCoordinates(player.getName());
                while (player.getField().getCellStatus(coordinates) != Cell.Status.SHIP) {
                    gameUI.messageThatYouAreALiar(player.getName(), "корабля");
                    coordinates = gameUI.getCoordinates(player.getName());
                }
                playerAttacked.setOpponentShipCells(coordinates);
            }
            case MINESWEEPER -> {
                for (int i = 0; i < playerAttacked.getMinesweepers().size(); i++) {
                    if (playerAttacked.getMinesweepers().get(i).getPosition().equals(coordinate)) {
                        playerAttacked.getMinesweepers().get(i).setStatus(AdditionalArrangements.Status.ACTIVATED); break;
                    }
                }
                player.getOpponentsField().setCellStatus(coordinate, Cell.Status.MARKED);
                playerAttacked.getField().setCellStatus(coordinate, Cell.Status.MARKED);

                gameUI.messageOfEntryOfOpponentsMineOrMinesweeper(player.getName(), "минного тральщика","мины");
                Coordinate coordinates = gameUI.getCoordinates(player.getName());
                while (player.getField().getCellStatus(coordinates) != Cell.Status.MINE) {
                    gameUI.messageThatYouAreALiar(player.getName(), "мины");
                    coordinates = gameUI.getCoordinates(player.getName());
                }
                for (AdditionalArrangements mine : player.getMines()) {
                    if (mine.getPosition().equals(coordinates)) playerAttacked.setOpponentMines(mine);
                }

            }
            case SUBMARINE -> {
                playerAttacked.getSubmarineList().get(0).setStatus(AdditionalArrangements.Status.ACTIVATED);

                player.getOpponentsField().setCellStatus(coordinate, Cell.Status.MARKED);
                playerAttacked.getField().setCellStatus(coordinate, Cell.Status.MARKED);;

                gameUI.messageOfEntryOfSubmarine(player.getName());
                playerAttacked.setShotFromASubmarine(coordinate);
            }
        }
    }

    public void finish(Player player1, Player player2, GameUI gameUI) {
        if (!shipsLifeStatus(player2.getShips())) gameUI.messageOfFinish(player1.getName());
        else if (!shipsLifeStatus(player1.getShips())) gameUI.messageOfFinish(player2.getName());
    }

    public boolean shipsLifeStatus(List<Ship> ships) {
        for (Ship ship : ships) {
            if (ship.getStatus() == Ship.Status.ALIVE) return true;
        }
        return false;
    }

    public void game(GameUI gameUI) {
        Player player1 = new Player("1", gameUI.setYourName("1"), new PlayingField(), new ArrayList<>(), new PlayingField());
        Player player2 = new Player("2", gameUI.setYourName("2"), new PlayingField(), new ArrayList<>(), new PlayingField());

        // расставляем фигуры на оба поля
        placementOfFigures(player1, gameUI);
        placementOfFigures(player2, gameUI);

        gameUI.messageOfPlayersReady();

        //ведем бой пока счетчик кол-ва кораблей одного из игроков не станет равным нулю
        attacks(player1, player2, gameUI);

        //конец
        finish(player1, player2, gameUI);
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
}
