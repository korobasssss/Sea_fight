package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.field.RandomPlacements;

import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    private void placementOfFigures(PlayingField fieldFirstPlayer, PlayingField fieldSecondPlayer) { //расставляем фигуры на обоих полях
        //расставляем фигуры на поле для 1 игрока
        System.out.println("Игрок 1 , Вы хотите случаную расстановку(0) или желаете самостоятельно расставить корабли(1)? ");
        int decision = scanner.nextInt();
        if (decision == 0) {
            changeTheCells(fieldFirstPlayer, RandomPlacements.getRandomField());
            print(fieldFirstPlayer.toArray());
            System.out.println();
        } else {
            int sheepCount = 1;
            int sheepCells = 4; // кол-во типов кораблей
            Figure figure = new Figure();
            while (sheepCount <= 4) {
                for (int sheep = 0; sheep < sheepCount; sheep++) {
                    System.out.printf("%d клеточный корабль вертикальный(0) или горизонтальный(1)? ", sheepCells);
                    figure.pos = scanner.nextInt();
                    System.out.printf("Выберите местоположение %d клеточного корабля: ", sheepCells);
                    System.out.println("По вертикали: ");
                    int vertical = scanner.nextInt();
                    System.out.println("По горизонтали: ");
                    int horizontal = scanner.nextInt();
                    while (!checkOnRightPos(fieldFirstPlayer, vertical - 1 , horizontal - 1, sheepCells, figure.pos)) {
                        System.out.println("Корабль расположен близко к другим, мы не можем его тут поставить, выберите другое местоположение.");
                        System.out.println("По вертикали: ");
                        vertical = scanner.nextInt();
                        System.out.println("По горизонтали: ");
                        horizontal = scanner.nextInt();
                    }

                    if (sheepCells == 1) fieldFirstPlayer.setCellStatus(vertical - 1, horizontal - 1, '1');
                    else figure.makeSheep(fieldFirstPlayer, vertical - 1, horizontal - 1, sheepCells);

                    print(fieldFirstPlayer.toArray());
                    System.out.println();
                }
                sheepCells -= 1;
                sheepCount += 1;
            }
        }
        // для робота
        fieldSecondPlayer.addRndField(RandomPlacements.getRandomField());
    }

    private void attacks(PlayingField fieldFirstPlayer, PlayingField fieldSecondPlayer) {
        print(fieldSecondPlayer.toArray());
        int sheepCountPlayer1 = 10;
        int sheepCountPlayer2 = 10;
        PlayingField opponentOfTheFirstPlayer = new PlayingField(); opponentOfTheFirstPlayer.unknownField();
        PlayingField opponentOfTheSecondPlayer = new PlayingField(); opponentOfTheSecondPlayer.unknownField();
        int who = 1;

        while (sheepCountPlayer1 > 0 && sheepCountPlayer2 > 0) {
            if (who == 1) {
                sheepCountPlayer2 = moveOnTheOpponent(who, fieldSecondPlayer, sheepCountPlayer2, opponentOfTheSecondPlayer);
                who = 2;
            } else {
                sheepCountPlayer1 = moveOnTheOpponent(who, fieldFirstPlayer, sheepCountPlayer1, opponentOfTheFirstPlayer);
                who = 1;
            }
        }
        if (sheepCountPlayer1 < 0) System.out.println("Победил игрок 2");
        else if (sheepCountPlayer2 < 0) System.out.println("Победил игрок 1");
    }

    private int moveOnTheOpponent(int who, PlayingField attacked, int sheepCount, PlayingField opponent) {
        char cell = ' ';
        int vertical; int horizontal;
        while (cell != '0') {
            System.out.printf("Игрок %d делайте ход.", who);
            System.out.println();
            if (who == 1) {
                System.out.println("По вертикали: ");
                vertical = scanner.nextInt() - 1;
                System.out.println("По горизонтали: ");
                horizontal = scanner.nextInt() - 1;
            } else {
                vertical = random.nextInt(10);
                horizontal = random.nextInt(10);
            }

            System.out.printf("Игрок %d походил по вертикали на %d и по горизонтали на %d.", who, vertical + 1, horizontal + 1);
            System.out.println();
            cell = attacked.getCellStatus(vertical, horizontal);
            if (cell == '1') {
                if (attacked.checkShepFor9Cells(vertical, horizontal)) {
                    System.out.printf("Игрок %d ранил корабль другого игрока", who);
                    System.out.println();
                } else {
                    System.out.printf("Игрок %d убил корабль другого игрока", who);
                    System.out.println();
                    sheepCount--;
                }
                attacked.setCellStatus(vertical, horizontal, '#');
                opponent.setCellStatus(vertical, horizontal, '#');
            } else if (cell == '0') {
                System.out.println("Мимо.");
                attacked.setCellStatus(vertical, horizontal, '#');
                opponent.setCellStatus(vertical, horizontal, '#');
            } else if (cell == '#') {
                System.out.println("Эта зона уже поражена.");
            }
            if (who == 1) {
                System.out.println("Поле противника: ");
                print(opponent.toArray());
                System.out.println();
            } else {
                System.out.println("Поле 1 игрока: ");
                print(attacked.toArray());
            }
        }
        return sheepCount;
    }



    public void game() {
        PlayingField fieldFirstPlayer = new PlayingField();
        PlayingField fieldSecondPlayer = new PlayingField();

        // расставляем фигуры на оба поля
        placementOfFigures(fieldFirstPlayer, fieldSecondPlayer);

        System.out.println("Оба игрока готовы к бою.");

        //ведем бой пока счетчик одного из игроков не станет равным нулю
        attacks(fieldFirstPlayer, fieldSecondPlayer);

        //конец
    }

    private void print(char[][] arr) {
        for (int row = 0; row < arr.length; row++) {
            System.out.println(arr[row]);
        }
    }

    private PlayingField changeTheCells(PlayingField field, char[][] newField) {
        for (int row = 0; row < newField.length; row++) {
            for (int col = 0; col < newField[0].length; col++) {
                field.setCellStatus(row, col, newField[row][col]);
            }
        }
        return field;
    }

    private boolean checkOnRightPos(PlayingField field, int vertical, int horizontal, int shipType, int pos) {
        int rowFrom; int colFrom; int rowTo; int colTo;
        if (vertical == 0) rowFrom = 0;
        else rowFrom = vertical - 1;
        if (horizontal == 0) colFrom = 0;
        else colFrom = horizontal - 1;
        if (vertical == field.length()) rowTo = field.length() - 1;
        else if (pos == 0) {
            rowTo = vertical + shipType;
        } else rowTo = rowFrom + 3;
        if (horizontal == field.length()) colTo = field.length() - 1;
        else if (pos == 0) {
            colTo = colFrom + 3;
        } else colTo = horizontal + shipType;
        for (int r = rowFrom; r < rowTo; r++) {
            for (int c = colFrom; c < colTo; c++) {
                if (field.getCellStatus(r, c) == '1') return false;
            }
        }
        return true;
    }
}
