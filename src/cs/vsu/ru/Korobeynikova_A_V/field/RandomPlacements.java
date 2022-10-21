package cs.vsu.ru.Korobeynikova_A_V.field;

import java.util.Random;

public class RandomPlacements {
    private static final Random random = new Random();

    private static char[][] randomField1 = {
            {'1', '0', '1', '0', '0', '1', '0', '0', '0', '1'},
            {'1', '0', '1', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '1', '0', '0', '1', '0', '0', '0', '0'},
            {'1', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '1', '1', '1', '0', '0'},
            {'0', '0', '1', '1', '0', '0', '0', '0', '0', '1'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '1'},
            {'0', '0', '0', '0', '0', '1', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '1', '0', '0', '1', '0'},
            {'1', '1', '0', '0', '0', '0', '0', '0', '0', '0'}};

    private static char[][] randomField2 = {
            {'0', '0', '1', '0', '0', '0', '1', '1', '1', '0'},
            {'0', '0', '1', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '1', '0', '1', '1', '0', '0', '0', '1'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '0', '1', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '1'},
            {'0', '1', '1', '1', '1', '0', '1', '1', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '1', '0', '0', '0', '0'}};
    private static char[][] randomField3 = {
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '1', '0', '0', '0', '0', '0', '0', '1', '1'},
            {'0', '1', '0', '0', '1', '0', '0', '0', '0', '0'},
            {'0', '1', '0', '0', '0', '0', '0', '0', '0', '1'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '1', '0', '0', '0', '0', '1', '1', '1'},
            {'0', '0', '1', '0', '1', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '1', '1', '1', '1', '0', '0', '0', '1', '1'}};
    private static char[][] randomField4 = {
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '1', '0', '0', '0', '1', '1', '1', '0'},
            {'0', '0', '1', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '1', '0', '1', '0', '0', '0', '0', '0'},
            {'1', '0', '1', '0', '1', '0', '0', '0', '0', '1'},
            {'1', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '1', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '1', '0', '0'},
            {'1', '0', '0', '1', '0', '0', '0', '1', '0', '0'}};
    private static char[][] randomField5 = {
            {'0', '0', '1', '1', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '0', '0', '1', '1', '1', '1', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '1', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '1', '0', '1', '0', '1', '0', '1'},
            {'0', '0', '0', '0', '0', '1', '0', '1', '0', '0'},
            {'0', '1', '0', '0', '0', '0', '0', '1', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '1', '1', '1', '0', '1', '0', '0'}};

    public static char[][] getRandomField() {
        int rnd = random.nextInt(5);
        return switch (rnd) {
            case 0 -> randomField1;
            case 1 -> randomField2;
            case 2 -> randomField3;
            case 3 -> randomField4;
            case 4 -> randomField5;
            default -> new char[2][2];
        };
    }
}
