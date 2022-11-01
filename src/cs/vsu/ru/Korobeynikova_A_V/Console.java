package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.Figure.Mine;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.field.RandomPlacements;

import java.util.Scanner;

public class Console{
    Scanner scanner = new Scanner(System.in);

    private Coordinate getCoordinates() {
        System.out.println("Выберите координату :");
        System.out.println("По горизонтали: ");
        scanner.nextLine();
        int col = changeToInt(scanner.nextLine());
        while (col < 0 || col > PlayingField.size) {
            System.out.println("Вы ввели неверную цифру. По горизонтали: ");
            col = changeToInt(scanner.nextLine());
        }
        System.out.println("По вертикали: ");
        int row = scanner.nextInt();
        while (row < 0 || row > PlayingField.size) {
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
    }

    private void makeMines(Player player) {
        for (int i = 0; i < player.countMines; i++) {
            System.out.println("Введите координаты минного поля: ");
            Mine mine = new Mine(getCoordinates(), Mine.Status.NOT_ACTIVATED);
            player.setMines(mine);
            while (player.getField().getCellStatus(mine.getPosition().getVertical(), mine.getPosition().getHorizontal()) == Cell.Status.SHIP) {
                System.out.println("Вы не можете поставить сюда корабль! Введите координаты заново. ");
                mine.setPosition(getCoordinates());
                player.setMines(mine);
            }
            player.getField().setCellStatus(mine.getPosition().getVertical(), mine.getPosition().getHorizontal(), Cell.Status.MINE);
            print(player.getField().getField());
        }
    }

    private void makeShips(Player player) {
        int shipCells = 4; // кол-во типов кораблей
        int shipCellsCount = 1;
        while (shipCells > 0) {
            for (int ships = shipCellsCount; ships > 0; ships--) {
                Ship ship = new Ship(new Coordinate(0, 0), 0,Ship.Orientation.VERTICAL);
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
                    while ((ship.getStartingPosition().getVertical() + ship.getShipType()) - 1 >= PlayingField.size) {
                        System.out.println("Невозможно поставить здесь корабль. Введите координаты заного. ");
                        ship.setStartingPosition(getCoordinates());
                    }
                } else {
                    while ((ship.getStartingPosition().getHorizontal() + ship.getShipType()) - 1 > PlayingField.size) {
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

    public void moveOnTheOpponent(int who, Player player, Player playerAttacked, PlayingField attacked, PlayingField opponent) {
        Cell.Status cellStatus = Cell.Status.UNKNOWN;

        Coordinate point;
        while (cellStatus != Cell.Status.EMPTY && cellStatus != Cell.Status.MINE) {
            System.out.printf("Игрок %d делайте ход.", who);
            System.out.println();
            if (player.getOpponentShipCells().size() != 0) {
                System.out.println("Желаете ли вы воспользоваться координатами части корабля противника? (0 - нет, 1 - да");
                int decision = scanner.nextInt();
                if (decision == 1) point = player.getOpponentShipCell();
                else point = getCoordinates();
            } else point = getCoordinates();

            System.out.printf("Игрок %d походил по горизонтали на %d и по вертикали на %d.", who, point.getHorizontal() + 1, point.getVertical() + 1);
            System.out.println();

            switch (cellStatus = attacked.getCellStatus(point.getVertical(), point.getHorizontal())) {
                case SHIP -> {
                    attacked.setCellStatus(point.getVertical(), point.getHorizontal(), Cell.Status.MARKED);
                    opponent.setCellStatus(point.getVertical(), point.getHorizontal(), Cell.Status.MARKED);

                    if (!player.hurtOrKill(playerAttacked.getField(), playerAttacked.findShip(point.getVertical(), point.getHorizontal()))) {
                        System.out.printf("Игрок %d ранил корабль другого игрока", who);
                        System.out.println();
                    } else {
                        System.out.printf("Игрок %d убил корабль другого игрока", who);
                        System.out.println();
                        playerAttacked.countShips--;
                    }
                }
                case EMPTY -> {
                    System.out.println("Мимо.");
                    attacked.setCellStatus(point.getVertical(), point.getHorizontal(), Cell.Status.MARKED);
                    opponent.setCellStatus(point.getVertical(), point.getHorizontal(), Cell.Status.MARKED);
                }
                case MARKED -> System.out.println("Эта зона уже поражена.");
                case MINE -> {
                    for (int i = 0; i < playerAttacked.getMines().size(); i++) {
                        if (playerAttacked.getMines().get(i).getPosition().getVertical() == point.getVertical() && playerAttacked.getMines().get(i).getPosition().getHorizontal() == point.getHorizontal()) {
                            playerAttacked.getMines().get(i).setStatus(Mine.Status.NOT_ACTIVATED); break;
                        }
                    }
                    attacked.setCellStatus(point.getVertical(), point.getHorizontal(), Cell.Status.MARKED);
                    opponent.setCellStatus(point.getVertical(), point.getHorizontal(), Cell.Status.MARKED);

                    System.out.println("Вы попали на мину ! :( Введите координаты клетки одного из своих кораблей! ");
                    Coordinate coord = getCoordinates();
                    while (player.getField().getCellStatus(coord.getVertical(), coord.getHorizontal()) != Cell.Status.SHIP) {
                        System.out.println("Не обманывайте, там у вас нет корабля, введите координаты заново! ");
                        coord = getCoordinates();
                    }
                    playerAttacked.setOpponentShipCells(coord);
                }
            }
            System.out.println("Поле противника: ");
            print(opponent.getField());

            System.out.printf("Поле %d игрока: ", who);
            print(player.getField().getField());
        }
    }

    public void finish(Player player1, Player player2) {
        if (player1.getCountShips() > player2.getCountShips()) System.out.println("Победил игрок 1");
        else System.out.println("Победил игрок 2");
    }


    private void print(Cell[][] arr) {
        System.out.println();
        for (Cell[] cells : arr) {
            for (int col = 0; col < arr.length; col++) {
                System.out.print(cells[col].visual);
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
