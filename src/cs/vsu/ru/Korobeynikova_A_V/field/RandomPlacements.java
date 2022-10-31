package cs.vsu.ru.Korobeynikova_A_V.field;

import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlacements {
    private static final Random random = new Random();

    private static List<Ship> randomField1() {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(new int[] {0, 0}, 4, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {0, 3}, 3, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {4, 5}, 3, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {9, 0}, 2, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {7, 5}, 2, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {5, 2}, 2, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {0, 9}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {8, 8}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {0, 5}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {2, 5}, 1, Ship.Orientation.VERTICAL));
        return ships;
    }

    private static List<Ship> randomField2() {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(new int[] {7, 1}, 4, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {0, 2}, 3, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {0, 6}, 3, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {4, 0}, 2, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {2, 4}, 2, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {7, 6}, 2, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {6, 9}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {2, 9}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {9, 5}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {4, 4}, 1, Ship.Orientation.VERTICAL));
        return ships;
    }

    private static List<Ship> randomField3() {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(new int[] {9, 1}, 4, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {5, 7}, 3, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {1, 1}, 3, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {1, 8}, 2, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {9, 8}, 2, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {5, 2}, 2, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {2, 4}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {3, 9}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {7, 6}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {6, 4}, 1, Ship.Orientation.VERTICAL));
        return ships;
    }

    private static List<Ship> randomField4() {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(new int[] {1, 2}, 4, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {3, 0}, 3, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {1, 6}, 3, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {3, 4}, 2, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {8, 7}, 2, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {8, 9}, 2, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {4, 9}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {6, 8}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {9, 3}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {9, 0}, 1, Ship.Orientation.VERTICAL));
        return ships;
    }

    private static List<Ship> randomField5() {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship(new int[] {1, 5}, 4, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {9, 3}, 3, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {4, 7}, 3, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {0, 2}, 2, Ship.Orientation.HORIZONTAL));
        ships.add(new Ship(new int[] {3, 3}, 2, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {4, 5}, 2, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {1, 0}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {6, 1}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {9, 7}, 1, Ship.Orientation.VERTICAL));
        ships.add(new Ship(new int[] {4, 9}, 1, Ship.Orientation.VERTICAL));
        return ships;
    }

    public static List<Ship> getRandomField() {
        List<Ship> ships = new ArrayList<>();
        int rnd = random.nextInt(5);
        return switch (rnd) {
            case 0 -> randomField1();
            case 1 -> randomField2();
            case 2 -> randomField3();
            case 3 -> randomField4();
            case 4 -> randomField5();
            default -> ships;
        };
    }
}
