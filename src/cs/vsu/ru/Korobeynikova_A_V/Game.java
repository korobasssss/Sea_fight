package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.field.RandomPlacements;

import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);

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
        int sheepCountPlayer1 = 7;
        int sheepCountPlayer2 = 7;
        boolean flag = true;
        while (sheepCountPlayer1 > 0 || sheepCountPlayer2 > 0) {
            if (flag) {

            }
        }
    }

    //private PlayingField



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
