package cs.vsu.ru.Korobeynikova_A_V;

import java.util.Arrays;
import java.util.Scanner;

//создать фигуры, брать потом отсюда их
public class Figure {
    public int pos = 0;
//
//    public final char[] oneCell = makeFigureOne(new char[1]); //одноклеточный кораблю
//    public final char[][] twoCell = makeFigureTwoThreeFour(new char[2][2]); // двуклеточный корабль
//    public final char[][] threeCell = makeFigureTwoThreeFour(new char[3][3]); // трехклеточный корабль
//    public final char[][] fourCell = makeFigureTwoThreeFour(new char[4][4]); // четырехклеточных корабль

    private char[] makeFigureOne(char[] arr) {
        arr[0] = 1;
        return arr;
    }

    public char[][] makeFigureTwoThreeFour(char[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                if (pos == 0) {
                    if (c == 0) arr[r][c] = '1';
                    else arr[r][c] = '0';
                }
                else if (pos == 1) {
                    if (r == 0) arr[r][c] = '1';
                    else arr[r][c] = '0';
                }

            }
        }
        return arr;
    }

//    public char[][] placementOfFigures(char field[][]) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("4-х клеточный корабль вертикальный(0) или горизонтальный(1)?");
//        int pos = scanner.nextInt();
//        System.out.println("Выберите местоположение 4-х клеточного корабля: ");
//        System.out.println("По вертикали: ");
//        char vertical = (char) scanner.nextLine();
//        //int vertical = changeToInt((char) scanner.nextLine());
//    }
//
//    private int changeToInt(char symbol) { //меняем букву на число
//        int num = 0;
//        switch (symbol) {
//            case 'A':
//            case 'B': num = 1;
//            case 'C': num = 2;
//            case 'D': num = 3;
//            case 'E': num = 4;
//            case 'F': num = 5;
//            case 'G': num = 6;
//            case 'H': num = 7;
//            case 'I': num = 9;
//            case 'J': num = 9;
//        }
//        return num;
//    }
}
