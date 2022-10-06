package cs.vsu.ru.Korobeynikova_A_V.foreground;

import java.util.Arrays;

//создать фигуры, брать потом отсюда их
public class Figure {

    public final int[] oneCell = makeFigureOne(new int[1]); //одноклеточный кораблю
    public final int[][] twoCell = makeFigureTwoThreeFour(0, new int[2][2]); // двуклеточный корабль
    public final int[][] threeCell = makeFigureTwoThreeFour(0, new int[3][3]); // трехклеточный корабль
    public final int[][] fourCell = makeFigureTwoThreeFour(0, new int[4][4]); // четырехклеточных корабль

    private int[] makeFigureOne(int[] arr) {
        arr[0] = 1;
        return arr;
    }

    private int[][] makeFigureTwoThreeFour(int pos, int[][] arr) {
        if (pos == 1) {
            for (int r = 0; r < arr[0].length; r++) {
                arr[r][0] = 1;
            }
        }
        if (pos == 2) {
            Arrays.fill(arr[0], 1);
        }
        return arr;
    }

}
