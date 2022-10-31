package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.field.RandomPlacements;

import java.util.Scanner;

public class Console{
    Scanner scanner = new Scanner(System.in);

    private int[] getCoordinates() {
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

        return new int[] {row - 1, col - 1};
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
        } else makeField(player);

        print(player.getField().toArray());
    }

    private void makeField(Player player) {
        int shipCells = 4; // кол-во типов кораблей
        int shipCellsCount = 1;
        while (shipCells > 0) {
            Ship ship = new Ship(new int[2], 0,Ship.Orientation.VERTICAL);
            for (int ships = shipCellsCount; ships > 0; ships--) {
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
                    while ((ship.getStartingPosition()[0] + ship.getShipType()) - 1 >= PlayingField.size) {
                        System.out.println("Невозможно поставить здесь корабль. Введите координаты заного. ");
                        ship.setStartingPosition(getCoordinates());
                    }
                } else {
                    while ((ship.getStartingPosition()[1] + ship.getShipType()) - 1 > PlayingField.size) {
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

                print(player.getField().toArray());
            }
            shipCells -= 1;
            shipCellsCount++;
        }
    }

    public void moveOnTheOpponent(int who, Player player, Player playerAttacked, PlayingField attacked, int sheepCount, PlayingField opponent) {
        PlayingField.Status cell = PlayingField.Status.UNKNOWN;

        while (cell != PlayingField.Status.EMPTY) {
            System.out.printf("Игрок %d делайте ход.", who);
            System.out.println();
            int[] point = getCoordinates();

            System.out.printf("Игрок %d походил по горизонтали на %d и по вертикали на %d.", who, point[1] + 1, point[0] + 1);
            System.out.println();

            switch (cell = attacked.getCellStatus(point[0], point[1])) {
                case SHIP -> {
                    attacked.setCellStatus(point[0], point[1], PlayingField.Status.MARKED);
                    opponent.setCellStatus(point[0], point[1], PlayingField.Status.MARKED);

                    if (player.hurtOrKill(playerAttacked.findShip(point[0], point[1]))) {
                        System.out.printf("Игрок %d ранил корабль другого игрока", who);
                        System.out.println();
                    } else {
                        System.out.printf("Игрок %d убил корабль другого игрока", who);
                        System.out.println();
                        sheepCount--;
                    }
                }
                case EMPTY -> {
                    System.out.println("Мимо.");
                    attacked.setCellStatus(point[0], point[1], PlayingField.Status.MARKED);
                    opponent.setCellStatus(point[0], point[1], PlayingField.Status.MARKED);
                }
                case MARKED -> System.out.println("Эта зона уже поражена.");
            }
            System.out.println("Поле противника: ");
            print(opponent.toArray());

            System.out.printf("Поле %d игрока: ", who);
            print(player.getField().toArray());
        }
    }

    public void finish(Player player1, Player player2) {
        if (player1.getCountShips() > player2.getCountShips()) System.out.println("Победил игрок 1");
        else System.out.println("Победил игрок 2");
    }


    private void print(char[][] arr) {
        System.out.println();
        for (char[] chars : arr) {
            System.out.println(chars);
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
