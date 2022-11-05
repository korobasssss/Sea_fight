//package cs.vsu.ru.Korobeynikova_A_V;
//
//import cs.vsu.ru.Korobeynikova_A_V.Figure.AdditionalArrangements;
//import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
//import cs.vsu.ru.Korobeynikova_A_V.field.*;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class Console{
//    Scanner scanner = new Scanner(System.in);
//
//    private Coordinate getCoordinates() {
//        System.out.println("По горизонтали: ");
//        scanner.nextLine();
//        int col = changeToInt(scanner.nextLine());
//        while (col < 0 || col > PlayingField.getSize()) {
//            System.out.println("Вы ввели неверную цифру. По горизонтали: ");
//            col = changeToInt(scanner.nextLine());
//        }
//        System.out.println("По вертикали: ");
//        String row = scanner.nextLine();
//        while (!(row < 0 || row > PlayingField.getSize())) {
//            System.out.println("Вы ввели неверную цифру. По вертикали: ");
//            row = scanner.nextInt();
//        }
//        System.out.println();
//
//        return new Coordinate(row - 1, col - 1);
//    }
//
//
//    private void print(Cell[][] arr) {
//        System.out.println();
//        for (Cell[] cells : arr) {
//            for (int col = 0; col < arr.length; col++) {
//                System.out.print(cells[col].getVisual());
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
//
//    private int changeToInt(String symbol) { //меняем букву на число
//        if (symbol.length() == 1) {
//            return symbol.charAt(0) - 65 + 1;
//        }
//        return -1;
//    }
//}
