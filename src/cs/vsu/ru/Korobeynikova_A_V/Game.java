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
            int sheepCells = 4;
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

                    switch (sheepCells) {
                        case 1 : fieldFirstPlayer.setCellStatus(vertical - 1, horizontal - 1, '1'); break;
                        case 2 : makeSheep(fieldFirstPlayer, figure.makeFigureTwoThreeFour(new char[2][2]), vertical - 1, horizontal - 1); break;
                        case 3 : makeSheep(fieldFirstPlayer, figure.makeFigureTwoThreeFour(new char[3][3]), vertical - 1, horizontal - 1); break;
                        case 4 : makeSheep(fieldFirstPlayer, figure.makeFigureTwoThreeFour(new char[4][4]), vertical - 1, horizontal - 1); break;
                    }
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
        int sheepCountPlayer1 = 10;
        int sheepCountPlayer2 = 10;
        PlayingField opponentOfTheFirstPlayer = new PlayingField(); opponentOfTheFirstPlayer.unknownField();
        PlayingField opponentOfTheSecondPlayer = new PlayingField(); opponentOfTheSecondPlayer.unknownField();
        int who = 1;

        while (sheepCountPlayer1 > 0 && sheepCountPlayer2 > 0) {
            if (who == 1) {
                sheepCountPlayer2 = moveOnTheOpponent(who, fieldSecondPlayer, sheepCountPlayer2, opponentOfTheSecondPlayer);
                System.out.println("Поле 1 игрока: ");
                print(fieldFirstPlayer.toArray());
                who = 2;
            } else {
                sheepCountPlayer1 = moveOnTheOpponent(who, fieldFirstPlayer, sheepCountPlayer1, opponentOfTheFirstPlayer);
                who = 1;
            }
        }
        if (sheepCountPlayer1 == 0) System.out.println("Победил игрок 2");
        else if (sheepCountPlayer2 == 0) System.out.println("Победил игрок 1");
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

    private PlayingField makeSheep(PlayingField field, char[][] shep, int vert, int hor) {
        for (int row = 0; row < shep.length; row++) {
            for (int col = 0; col < shep[0].length; col++) {
                if (shep[row][col] == '1') field.setCellStatus(row + vert, col + hor, shep[row][col]);
            }
        }
        return field;
    }

    private PlayingField changeTheCells(PlayingField field, char[][] newField) {
        for (int row = 0; row < newField.length; row++) {
            for (int col = 0; col < newField[0].length; col++) {
                field.setCellStatus(row, col, newField[row][col]);
            }
        }
        return field;
    }
}
