package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.field.RandomPlacements;

import java.util.Scanner;

public class Console{
    Scanner scanner = new Scanner(System.in);


    private int[] getCoordinates() {
        System.out.println("Выберите координату :");
        System.out.println("По горизонтали: ");
        int col = changeToInt(scanner.nextLine());
        System.out.println("По вертикали: ");
        int row = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        return new int[] {row - 1, col - 1};
    }

    public char[][] placementOfFigures(int who, Player player) {
        System.out.printf("Игрок %d , Вы хотите случайную расстановку(0) или желаете самостоятельно расставить корабли(1)? ", who);
        int decision = scanner.nextInt();

        System.out.println("Случайная расстановка кораблей: ");
        if (decision == 0) {
            return RandomPlacements.getRandomField();
        }
        return makeField(player);
    }
    private char[][] makeField(Player player) {
        int sheepCells = 4; // кол-во типов кораблей
        while (sheepCells > 0) {
            Ship ship = new Ship(new int[2], 0,0);
            for (int ships = sheepCells; ships > 0; ships--) {
                ship.setShipType(sheepCells);
                System.out.printf("%d клеточный корабль вертикальный(0) или горизонтальный(1)? ", sheepCells);
                ship.setOrientation(scanner.nextInt());
                scanner.nextLine();
                System.out.printf("Выберите местоположение %d клеточного корабля: ", sheepCells);
                System.out.println();
                ship.setStartingPosition(getCoordinates());
                scanner.nextLine();
                while (!player.canMakeShipOrNot(ship)) {
                    System.out.println("Корабль расположен близко к другим, мы не можем его тут поставить, выберите другое местоположение.");
                    ship.setStartingPosition(getCoordinates());
                }

                player.makeSheep(ship);

                print(player.getField().toArray());
            }
            sheepCells -= 1;
        }

        return player.getField().toArray();
    }

    public void moveOnTheOpponent(int who, Player player, PlayingField attacked, int sheepCount, PlayingField opponent) {
        char cell = ' ';

        while (cell != '0') {
            System.out.printf("Игрок %d делайте ход.", who);
            System.out.println();
            int[] point = getCoordinates();

            System.out.printf("Игрок %d походил по вертикали на %d и по горизонтали на %d.", who, point[1] + 1, point[0] + 1);
            System.out.println();
            switch (cell = attacked.getCellStatus(point[0], point[1])) {
                case '1' -> {
                    if (player.hurtOrKill(player.findShip(point[0], point[1]))) {
                        System.out.printf("Игрок %d ранил корабль другого игрока", who);
                        System.out.println();
                    } else {
                        System.out.printf("Игрок %d убил корабль другого игрока", who);
                        System.out.println();
                        sheepCount--;
                    }
                    attacked.setCellStatus(point[0], point[1], '#');
                    opponent.setCellStatus(point[0], point[1], '#');
                }
                case '0' -> {
                    System.out.println("Мимо.");
                    attacked.setCellStatus(point[0], point[1], '#');
                    opponent.setCellStatus(point[0], point[1], '#');
                }
                case '#' -> System.out.println("Эта зона уже поражена.");
            }
            System.out.println("Поле противника: ");
            print(opponent.toArray());

            System.out.printf("Поле %d игрока: ", who);
            print(attacked.toArray());
        }
    }

    public void finish(Player player1, Player player2) {
        if (player1.getCountShips() > player2.getCountShips()) System.out.println("Победил игрок 1");
        else System.out.println("Победил игрок 2");
    }


    private void print(char[][] arr) {
        System.out.println();
        for (int row = 0; row < arr.length; row++) {
            System.out.println(arr[row]);
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
