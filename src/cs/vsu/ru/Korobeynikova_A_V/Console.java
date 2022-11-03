package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.Figure.AdditionalArrangements;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.field.*;

import java.util.List;
import java.util.Scanner;

public class Console{
    Scanner scanner = new Scanner(System.in);

    private Coordinate getCoordinates() {
        System.out.println("Выберите координату :");
        System.out.println("По горизонтали: ");
        scanner.nextLine();
        int col = changeToInt(scanner.nextLine());
        while (col < 0 || col > PlayingField.getSize()) {
            System.out.println("Вы ввели неверную цифру. По горизонтали: ");
            col = changeToInt(scanner.nextLine());
        }
        System.out.println("По вертикали: ");
        int row = scanner.nextInt();
        while (row < 0 || row > PlayingField.getSize()) {
            System.out.println("Вы ввели неверную цифру. По вертикали: ");
            row = scanner.nextInt();
        }
        System.out.println();

        return new Coordinate(row - 1, col - 1);
    }

    public void placementOfFigures(int who, Player player) {
        System.out.printf("Игрок %d , Вы хотите случайную расстановку(0) или желаете самостоятельно расставить корабли(1)? ", who);
        int decision = scanner.nextInt();
        while (decision != 0 && decision != 1) {
            System.out.printf("Вы ввели неверную цифру. Игрок %d , Вы хотите случайную расстановку(0) или желаете самостоятельно расставить корабли(1)? ", who);
            decision = scanner.nextInt();
        }

        if (decision == 0) {
            System.out.println("Случайная расстановка кораблей: ");
            player.setShips(RandomPlacements.getRandomField());
            print(player.getField().getField());

        } else {
            makeShips(player);
        }
        makeMines(player);
        makeMineSweepers(player);
        makeSubmarines(player);
    }

    private void makeMines(Player player) {
        for (int i = 0; i < player.getCountMines(); i++) {
            System.out.println("Введите координаты минного поля: ");
            AdditionalArrangements mine = new AdditionalArrangements(getCoordinates(), AdditionalArrangements.Status.NOT_ACTIVATED);
            player.setMines(mine);
            while (!player.canMakeMineOrMinesweeperOrNot(mine.getPosition())) {
                System.out.println("Вы не можете поставить сюда мину! Введите координаты заново. ");
                mine.setPosition(getCoordinates());
                player.setMines(mine);
            }
            player.getField().setCellStatus(mine.getPosition(), Cell.Status.MINE);
            print(player.getField().getField());
        }
    }

    private void makeMineSweepers(Player player) {
        for (int i = 0; i < player.getCountMinesweepers(); i++) {
            System.out.println("Введите координаты минного тральщика: ");
            AdditionalArrangements minesweeper = new AdditionalArrangements(getCoordinates(), AdditionalArrangements.Status.NOT_ACTIVATED);
            player.setMinesweepers(minesweeper);
            while (!player.canMakeMineOrMinesweeperOrNot(minesweeper.getPosition())) {
                System.out.println("Вы не можете поставить сюда минного тральщика! Введите координаты заново. ");
                minesweeper.setPosition(getCoordinates());
                player.setMinesweepers(minesweeper);
            }
            player.getField().setCellStatus(minesweeper.getPosition(), Cell.Status.MINE);
            print(player.getField().getField());
        }
    }

    private void makeSubmarines(Player player) {
        for (int i = 0; i < player.getCountSubmarines(); i++) {
            System.out.println("Введите координаты подлодки: ");
            AdditionalArrangements submarine = new AdditionalArrangements(getCoordinates(), AdditionalArrangements.Status.NOT_ACTIVATED);
            player.setSubmarines(submarine);
            while (player.getField().getCellStatus(submarine.getPosition()) != Cell.Status.EMPTY) {
                System.out.println("Вы не можете поставить сюда подлодку! Введите координаты заново. ");
                submarine.setPosition(getCoordinates());
                player.setSubmarines(submarine);
            }
            player.getField().setCellStatus(submarine.getPosition(), Cell.Status.SUBMARINE);
            print(player.getField().getField());
        }
    }


    private void makeShips(Player player) {
        int shipCells = 4; // кол-во типов кораблей
        int shipCellsCount = 1;
        while (shipCells > 0) {
            for (int ships = shipCellsCount; ships > 0; ships--) {
                Ship ship = new Ship(new Coordinate(0, 0), 0, Ship.Orientation.VERTICAL, Ship.Status.ALIVE);
                ship.setShipType(shipCells);
                System.out.printf("%d клеточный корабль вертикальный(0) или горизонтальный(1)? ", shipCells);
                int orientation = scanner.nextInt();
                while (orientation != 0 && orientation != 1) {
                    System.out.printf("Вы ввели неверную цифру. %d клеточный корабль вертикальный(0) или горизонтальный(1)? ", shipCells);
                    orientation = scanner.nextInt();
                }
                if (orientation == 0) {
                    ship.setOrientation(Ship.Orientation.VERTICAL);
                } else ship.setOrientation(Ship.Orientation.HORIZONTAL);
                System.out.printf("Выберите местоположение %d клеточного корабля: ", shipCells);
                System.out.println();
                ship.setStartingPosition(getCoordinates());
                if (ship.getOrientation() == Ship.Orientation.VERTICAL){
                    while ((ship.getStartingPosition().getVertical() + ship.getShipType()) - 1 >= PlayingField.getSize()) {
                        System.out.println("Невозможно поставить здесь корабль. Введите координаты заного. ");
                        ship.setStartingPosition(getCoordinates());
                    }
                } else {
                    while ((ship.getStartingPosition().getHorizontal() + ship.getShipType()) - 1 > PlayingField.getSize()) {
                        System.out.println("Невозможно поставить здесь корабль. Введите координаты заного. ");
                        ship.setStartingPosition(getCoordinates());
                    }
                }
                while (!player.canMakeShipOrNot(ship)) {
                    System.out.println("Корабль расположен близко к другим, мы не можем его тут поставить, выберите другое местоположение.");
                    ship.setStartingPosition(getCoordinates());
                }
                player.setShips(ship);
                player.makeSheep(ship);

                print(player.getField().getField());
            }
            shipCells -= 1;
            shipCellsCount++;
        }
    }

    private boolean cellIsOpponentMine(List<AdditionalArrangements> oppMine, Coordinate coordinate) {
        for (AdditionalArrangements mine : oppMine) {
            if (mine.getPosition() == coordinate) return true;
        }
        return false;
    }

    public void moveOnTheOpponent(int who, Player player, Player playerAttacked, PlayingField attacked, PlayingField opponent) {
        Cell.Status cellStatus = Cell.Status.UNKNOWN;

        Coordinate point;
        while (cellStatus == Cell.Status.UNKNOWN || cellStatus == Cell.Status.SHIP) {
            System.out.printf("Игрок %d делайте ход.", who);
            System.out.println();

            if (player.getOpponentShipCells().size() > 0) { // если известны координаты частей кораблей противника
                System.out.println("Желаете ли вы воспользоваться координатами части корабля противника? (0 - нет, 1 - да");
                int decision = scanner.nextInt();
                if (decision == 1) point = player.getOpponentShipCell();
                else point = getCoordinates();
            } else if (player.getShotFromASubmarineList().size() > 0) {
                point = player.getShotFromASubmarine();
            } else point = getCoordinates();

            if (player.getOpponentMines().size() > 0) { // если известно местоположение мин противника
                while (cellIsOpponentMine(player.getOpponentMines(), point)) {
                    System.out.println("Вы знаете, что там стоит мина противника, выберите другую клетку: ");
                    point = getCoordinates();
                }
            }

            System.out.printf("Игрок %d походил по горизонтали на %d и по вертикали на %d.", who, point.getHorizontal() + 1, point.getVertical() + 1);
            System.out.println();

            cellStatus = attacked.getCellStatus(point);
            statusAttacks(who, cellStatus, point, player, playerAttacked, attacked, opponent);

            System.out.println("Поле противника: ");
            print(opponent.getField());

            System.out.printf("Поле %d игрока: ", who);
            print(player.getField().getField());
        }
    }

    private void statusAttacks(int who, Cell.Status cellStatus, Coordinate coordinate, Player player, Player playerAttacked, PlayingField attacked, PlayingField opponent) {
        switch (cellStatus) {
            case SHIP -> {
                attacked.setCellStatus(coordinate, Cell.Status.MARKED);
                opponent.setCellStatus(coordinate, Cell.Status.MARKED);

                if (!player.hurtOrKill(playerAttacked.getField(), playerAttacked.findShip(coordinate.getVertical(), coordinate.getHorizontal()))) {
                    System.out.printf("Игрок %d ранил корабль другого игрока", who);
                } else {
                    System.out.printf("Игрок %d убил корабль другого игрока", who);
                }
                System.out.println();
            }
            case EMPTY -> {
                System.out.println("Мимо.");
                attacked.setCellStatus(coordinate, Cell.Status.MARKED);
                opponent.setCellStatus(coordinate, Cell.Status.MARKED);
            }
            case MARKED -> System.out.println("Эта зона уже поражена.");
            case MINE -> {
                for (int i = 0; i < playerAttacked.getMines().size(); i++) {
                    if (playerAttacked.getMines().get(i).getPosition().getVertical() == coordinate.getVertical() && playerAttacked.getMines().get(i).getPosition().getHorizontal() == coordinate.getHorizontal()) {
                        playerAttacked.getMines().get(i).setStatus(AdditionalArrangements.Status.ACTIVATED); break;
                    }
                }
                attacked.setCellStatus(coordinate, Cell.Status.MARKED);
                opponent.setCellStatus(coordinate, Cell.Status.MARKED);

                System.out.println("Вы попали на мину ! :( Введите координаты клетки одного из своих кораблей! ");
                Coordinate coordinates = getCoordinates();
                while (player.getField().getCellStatus(coordinates) != Cell.Status.SHIP) {
                    System.out.println("Не обманывайте, там у вас нет корабля, введите координаты заново! ");
                    coordinates = getCoordinates();
                }
                playerAttacked.setOpponentShipCells(coordinates);
            }
            case MINESWEEPER -> {
                for (int i = 0; i < playerAttacked.getMinesweepers().size(); i++) {
                    if (playerAttacked.getMinesweepers().get(i).getPosition() == coordinate) {
                        playerAttacked.getMinesweepers().get(i).setStatus(AdditionalArrangements.Status.ACTIVATED); break;
                    }
                }
                attacked.setCellStatus(coordinate, Cell.Status.MARKED);
                opponent.setCellStatus(coordinate, Cell.Status.MARKED);

                System.out.println("Вы попали на минного тральщика ! :( Введите координаты клетки одной из своих мин! ");
                Coordinate coordinates = getCoordinates();
                while (player.getField().getCellStatus(coordinates) != Cell.Status.MINE) {
                    System.out.println("Не обманывайте, там у вас нет мины, введите координаты заново! ");
                    coordinates = getCoordinates();
                }
                playerAttacked.setOpponentShipCells(coordinates);
            }
            case SUBMARINE -> {
                playerAttacked.getSubmarineList().get(0).setStatus(AdditionalArrangements.Status.ACTIVATED);

                attacked.setCellStatus(coordinate, Cell.Status.MARKED);
                opponent.setCellStatus(coordinate, Cell.Status.MARKED);

                System.out.println("Вы попали в подводную лодку ! Ожидайте выстрела на ваше поле! ");
                playerAttacked.setShotFromASubmarine(coordinate);
            }
        }
    }

    public void finish(Player player1, Player player2) {
        if (!shipsLifeStatus(player2.getShips())) System.out.println("Победил игрок 1");
        else if (!shipsLifeStatus(player1.getShips())) System.out.println("Победил игрок 2");
    }

    public boolean shipsLifeStatus(List<Ship> ships) {
        for (Ship ship : ships) {
            if (ship.getStatus() == Ship.Status.ALIVE) return true;
        }
        return false;
    }


    private void print(Cell[][] arr) {
        System.out.println();
        for (Cell[] cells : arr) {
            for (int col = 0; col < arr.length; col++) {
                System.out.print(cells[col].getVisual());
            }
            System.out.println();
        }
        System.out.println();
    }

    private int changeToInt(String symbol) { //меняем букву на число
        if (symbol.length() == 1) {
            return symbol.charAt(0) - 65 + 1;
        }
        return -1;
    }
}
