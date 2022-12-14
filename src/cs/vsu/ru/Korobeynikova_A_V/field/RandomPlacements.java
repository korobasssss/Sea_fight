package cs.vsu.ru.Korobeynikova_A_V.field;

import cs.vsu.ru.Korobeynikova_A_V.Figure.AdditionalArrangements;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Figure;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPlacements {
    private static final Random random = new Random();

    private static void randomField1(Player player) {
        List<Figure> ships = new ArrayList<>();
        ships.add(new Ship(new Coordinate(0, 0), Ship.Type.FOURTH_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(0, 3), Ship.Type.THREE_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(4, 5), Ship.Type.THREE_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(9, 0), Ship.Type.TWO_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(7, 5), Ship.Type.TWO_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(5, 2), Ship.Type.TWO_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(0, 9), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(8, 8), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(0, 5), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(2, 5), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        player.setShips(ships);

        List<Figure> mines = new ArrayList<>();
        mines.add(new AdditionalArrangements(new Coordinate(3, 9), Figure.Status.ALIVE));
        mines.add(new AdditionalArrangements(new Coordinate(7, 0), Figure.Status.ALIVE));
        player.setMines(mines);

        List<Figure> minesweepers = new ArrayList<>();
        minesweepers.add(new AdditionalArrangements(new Coordinate(8, 3), Figure.Status.ALIVE));
        player.setMinesweepers(minesweepers);

        List<Figure> submarines = new ArrayList<>();
        submarines.add(new AdditionalArrangements(new Coordinate(1, 7), Figure.Status.ALIVE));
        player.setSubmarines(submarines);
    }

    private static void randomField2(Player player) {
        List<Figure> ships = new ArrayList<>();
        ships.add(new Ship(new Coordinate(7, 1), Ship.Type.FOURTH_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(0, 2), Ship.Type.THREE_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(0, 6), Ship.Type.THREE_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(4, 0), Ship.Type.TWO_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(2, 4), Ship.Type.TWO_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(7, 6), Ship.Type.TWO_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(6, 9), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(2, 9), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(9, 5), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(4, 4), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        player.setShips(ships);

        List<Figure> mines = new ArrayList<>();
        mines.add(new AdditionalArrangements(new Coordinate(9, 9), Figure.Status.ALIVE));
        mines.add(new AdditionalArrangements(new Coordinate(0, 0), Figure.Status.ALIVE));
        player.setMines(mines);

        List<Figure> minesweepers = new ArrayList<>();
        minesweepers.add(new AdditionalArrangements(new Coordinate(9, 1), Figure.Status.ALIVE));
        player.setMinesweepers(minesweepers);

        List<Figure> submarines = new ArrayList<>();
        submarines.add(new AdditionalArrangements(new Coordinate(4, 7), Figure.Status.ALIVE));
        player.setSubmarines(submarines);
    }

    private static void randomField3(Player player) {
        List<Figure> ships = new ArrayList<>();
        ships.add(new Ship(new Coordinate(9, 1), Ship.Type.FOURTH_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(5, 7), Ship.Type.THREE_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(1, 1), Ship.Type.THREE_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(1, 8), Ship.Type.TWO_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(9, 8), Ship.Type.TWO_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(5, 2), Ship.Type.TWO_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(2, 4), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(3, 9), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(7, 6), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(6, 4), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        player.setShips(ships);

        List<Figure> mines = new ArrayList<>();
        mines.add(new AdditionalArrangements(new Coordinate(0, 5), Figure.Status.ALIVE));
        mines.add(new AdditionalArrangements(new Coordinate(7, 9), Figure.Status.ALIVE));
        player.setMines(mines);

        List<Figure> minesweepers = new ArrayList<>();
        minesweepers.add(new AdditionalArrangements(new Coordinate(6, 0), Figure.Status.ALIVE));
        player.setMinesweepers(minesweepers);

        List<Figure> submarines = new ArrayList<>();
        submarines.add(new AdditionalArrangements(new Coordinate(3, 6), Figure.Status.ALIVE));
        player.setSubmarines(submarines);
    }

    private static void randomField4(Player player) {
        List<Figure> ships = new ArrayList<>();
        ships.add(new Ship(new Coordinate(1, 2), Ship.Type.FOURTH_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(3, 0), Ship.Type.THREE_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(1, 6), Ship.Type.THREE_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(3, 4), Ship.Type.TWO_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(8, 7), Ship.Type.TWO_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(8, 9), Ship.Type.TWO_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(4, 9), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(6, 8), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(9, 3), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(9, 0), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        player.setShips(ships);

        List<Figure> mines = new ArrayList<>();
        mines.add(new AdditionalArrangements(new Coordinate(7, 1), Figure.Status.ALIVE));
        mines.add(new AdditionalArrangements(new Coordinate(3, 7), Figure.Status.ALIVE));
        player.setMines(mines);

        List<Figure> minesweepers = new ArrayList<>();
        minesweepers.add(new AdditionalArrangements(new Coordinate(1, 4), Figure.Status.ALIVE));
        player.setMinesweepers(minesweepers);

        List<Figure> submarines = new ArrayList<>();
        submarines.add(new AdditionalArrangements(new Coordinate(8, 5), Figure.Status.ALIVE));
        player.setSubmarines(submarines);
    }

    private static void randomField5(Player player) {
        List<Figure> ships = new ArrayList<>();
        ships.add(new Ship(new Coordinate(1, 5), Ship.Type.FOURTH_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(9, 3), Ship.Type.THREE_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(4, 7), Ship.Type.THREE_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(0, 2), Ship.Type.TWO_CELLS, Ship.Orientation.HORIZONTAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(3, 3), Ship.Type.TWO_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(4, 5), Ship.Type.TWO_CELLS, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(1, 0), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(6, 1), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(9, 7), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        ships.add(new Ship(new Coordinate(4, 9), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE));
        player.setShips(ships);

        List<Figure> mines = new ArrayList<>();
        mines.add(new AdditionalArrangements(new Coordinate(7, 4), Figure.Status.ALIVE));
        mines.add(new AdditionalArrangements(new Coordinate(6, 9), Figure.Status.ALIVE));
        player.setMines(mines);

        List<Figure> minesweepers = new ArrayList<>();
        minesweepers.add(new AdditionalArrangements(new Coordinate(3, 1), Figure.Status.ALIVE));
        player.setMinesweepers(minesweepers);

        List<Figure> submarines = new ArrayList<>();
        submarines.add(new AdditionalArrangements(new Coordinate(9, 1), Figure.Status.ALIVE));
        player.setSubmarines(submarines);
    }

    public static void getRandomField(Player player) {
        int rnd = random.nextInt(5);
        switch (rnd) {
            case 0 -> randomField1(player);
            case 1 -> randomField2(player);
            case 2 -> randomField3(player);
            case 3 -> randomField4(player);
            case 4 -> randomField5(player);
        };
    }
}
