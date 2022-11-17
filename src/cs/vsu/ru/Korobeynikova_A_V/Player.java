package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.Figure.AdditionalArrangements;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Player {
    private final String number;
    private final String name;
    private PlayingField field;
    private PlayingField opponentsField;
    private List<Ship> ships;
    private List<AdditionalArrangements> mines;
    private List<AdditionalArrangements> minesweepers;
    private List<AdditionalArrangements> submarines;
    private Stack<Coordinate> opponentShipCells;
    private List<AdditionalArrangements> opponentMines;
    private Stack<Coordinate> shotFromASubmarine;
    private final int countMines = 2;
    private final int countMinesweepers = 1;
    private final int countSubmarines = 1;

    public Player(String number, String name, PlayingField field, List<Ship> ships, PlayingField opponentsField) {
        opponentsField.unknownField();

        this.number = number;
        this.name = name;
        this.field = field;
        this.ships = ships;
        this.mines = new ArrayList<>();
        this.minesweepers = new ArrayList<>();
        this.submarines = new ArrayList<>();
        this.opponentsField = opponentsField;
        this.opponentShipCells = new Stack<>();
        this.opponentMines = new ArrayList<>();
        this.shotFromASubmarine = new Stack<>();
    }

    private void makeSheep(Ship ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) makeVerticalShip(ship);
        else makeHorizontalShip(ship);
    }

    private void makeVerticalShip(Ship ship) {
        Coordinate coordinate = new Coordinate(0, ship.getStartingPosition().getHorizontal());
        for (int row = ship.getStartingPosition().getVertical(); row < ship.getStartingPosition().getVertical() + Integer.parseInt(ship.getShipType().getString()); row++) {
            coordinate.setVertical(row);
            field.setCellStatus(coordinate, Cell.Status.SHIP);
        }
    }

    private void makeHorizontalShip(Ship ship) {
        Coordinate coordinate = new Coordinate(ship.getStartingPosition().getVertical(), 0);
        for (int col = ship.getStartingPosition().getHorizontal(); col < ship.getStartingPosition().getHorizontal() + Integer.parseInt(ship.getShipType().getString()); col++) {
            coordinate.setHorizontal(col);
            field.setCellStatus(coordinate, Cell.Status.SHIP);
        }
    }

    public boolean hurtOrKill(PlayingField opponentsField, Ship ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) {
            Coordinate coordinate = new Coordinate(0, ship.getStartingPosition().getHorizontal());
            for (int row = ship.getStartingPosition().getVertical(); row < ship.getStartingPosition().getVertical() + Integer.parseInt(ship.getShipType().getString()); row++) {
                coordinate.setVertical(row);
                if (opponentsField.getCellStatus(coordinate) == Cell.Status.SHIP) return false; //ранил
            }
            ship.setStatus(Ship.Status.KILLED);
            return true; //убил
        } else if (ship.getOrientation() == Ship.Orientation.HORIZONTAL) {
            Coordinate coordinate = new Coordinate(ship.getStartingPosition().getVertical(), 0);
            for (int col = ship.getStartingPosition().getHorizontal(); col < ship.getStartingPosition().getHorizontal() + Integer.parseInt(ship.getShipType().getString()); col++) {
                coordinate.setHorizontal(col);
                if (opponentsField.getCellStatus(coordinate) == Cell.Status.SHIP) return false; //ранил
            }
            ship.setStatus(Ship.Status.KILLED);
            return true; //убил
        }
        return false;
    }

    public Ship findShip(int row, int col) {
        for (Ship ship : ships) {
            if (ship.getOrientation() == Ship.Orientation.VERTICAL) {
                for (int r = ship.getStartingPosition().getVertical(); r <= ship.getStartingPosition().getVertical() + Integer.parseInt(ship.getShipType().getString()); r++) {
                    if (r == row && col == ship.getStartingPosition().getHorizontal()) {
                        return ship;
                    }
                }
            } else {
                for (int c = ship.getStartingPosition().getHorizontal(); c <= ship.getStartingPosition().getHorizontal() + Integer.parseInt(ship.getShipType().getString()); c++) {
                    if (c == col && row == ship.getStartingPosition().getVertical()) {
                        return ship;
                    }
                }
            }
        }
        return new Ship(new Coordinate(0, 0), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Ship.Status.KILLED);
    }

    public boolean canMakeShipOrNot(Ship ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) return canVerticalMakeShipOrNot(ship);
        else return canMakeHorizontalShipOrNot(ship);
    }

    public boolean canVerticalMakeShipOrNot(Ship ship) {
        int row = ship.getStartingPosition().getVertical() - 1;

        Coordinate coordinate = new Coordinate(0, 0);
        while(row <= ship.getStartingPosition().getVertical() + Integer.parseInt(ship.getShipType().getString())) {
            if (row  >= 0 && row < field.length()) {
                int col = ship.getStartingPosition().getHorizontal() - 1;
                while (col <= ship.getStartingPosition().getHorizontal() + 1) {
                    if (col  >= 0 && col < field.length()) {
                        coordinate.setVertical(row); coordinate.setHorizontal(col);
                        if (field.getCellStatus(coordinate) == Cell.Status.SHIP) return false;
                    }
                    col++;
                }
            }
            row++;
        }
        return true;
    }

    public boolean canMakeHorizontalShipOrNot(Ship ship) {
        int row = ship.getStartingPosition().getVertical() - 1;

        Coordinate coordinate = new Coordinate(0, 0);
        while(row <= ship.getStartingPosition().getVertical() + 1) {
            if (row  >= 0 && row < field.length()) {
                int col = ship.getStartingPosition().getHorizontal() - 1;
                while (col <= ship.getStartingPosition().getHorizontal() + Integer.parseInt(ship.getShipType().getString())) {
                    if (col  >= 0 && col < field.length()) {
                        coordinate.setVertical(row); coordinate.setHorizontal(col);
                        if (field.getCellStatus(coordinate) == Cell.Status.SHIP || field.getCellStatus(coordinate) == Cell.Status.MINE || field.getCellStatus(coordinate) == Cell.Status.MINESWEEPER || field.getCellStatus(coordinate) == Cell.Status.SUBMARINE) return false;
                    }
                    col++;
                }
            }
            row++;
        }
        return true;
    }

    public boolean canMakeMineOrMinesweeperOrNot(Coordinate coordinate1) {
        int row = coordinate1.getVertical() - 1;

        Coordinate coordinate = new Coordinate(0, 0);
        while(row <= coordinate1.getVertical() + 1) {
            if (row  >= 0 && row < field.length()) {
                int col = coordinate1.getHorizontal() - 1;
                while (col <= coordinate1.getHorizontal() + 1) {
                    if (col  >= 0 && col < field.length()) {
                        coordinate.setVertical(row); coordinate.setHorizontal(col);
                        if (field.getCellStatus(coordinate) == Cell.Status.SHIP || field.getCellStatus(coordinate) == Cell.Status.MINE || field.getCellStatus(coordinate) == Cell.Status.MINESWEEPER || field.getCellStatus(coordinate) == Cell.Status.SUBMARINE) return false;
                    }
                    col++;
                }
            }
            row++;
        }
        return true;
    }


    public PlayingField getField() {
        return field;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
        for (Ship ship : ships) {
            makeSheep(ship);
        }
    }

    public void setShip(Ship ship) {
        this.ships.add(ship);
        makeSheep(ship);
    }

    public Coordinate getOpponentShipCell() {
        return opponentShipCells.pop();
    }

    public Stack<Coordinate> getOpponentShipCells() {
        return opponentShipCells;
    }

    public void setOpponentShipCells(Coordinate coordinate) {
        opponentShipCells.push(coordinate);
    }

    public List<AdditionalArrangements> getMines() {
        return mines;
    }

    public void setMines(AdditionalArrangements mine) {
        this.mines.add(mine);
        field.setCellStatus(mine.getPosition(), Cell.Status.MINE);
    }

    public void setMines(List<AdditionalArrangements> mines) {
        this.mines = mines;
        for (AdditionalArrangements mine : mines) {
            field.setCellStatus(mine.getPosition(), Cell.Status.MINE);
        }
    }

    public int getCountMines() {
        return countMines;
    }

    public PlayingField getOpponentsField() {
        return opponentsField;
    }

    public List<AdditionalArrangements> getMinesweepers() {
        return minesweepers;
    }

    public void setMinesweepers(AdditionalArrangements minesweeper) {
        this.minesweepers.add(minesweeper);
        field.setCellStatus(minesweeper.getPosition(), Cell.Status.MINESWEEPER);
    }

    public void setMinesweepers(List<AdditionalArrangements> minesweepers) {
        this.minesweepers = minesweepers;
        for (AdditionalArrangements minesweeper : minesweepers) {
            field.setCellStatus(minesweeper.getPosition(), Cell.Status.MINESWEEPER);
        }
    }

    public int getCountMinesweepers() {
        return countMinesweepers;
    }

    public List<AdditionalArrangements> getOpponentMines() {
        return opponentMines;
    }

    public void setOpponentMines(AdditionalArrangements additionalArrangements) {
        opponentMines.add(additionalArrangements);
    }

    public List<AdditionalArrangements> getSubmarineList() {
        return submarines;
    }

    public void setSubmarines(AdditionalArrangements submarine) {
        this.submarines.add(submarine);
        field.setCellStatus(submarine.getPosition(), Cell.Status.SUBMARINE);
    }

    public void setSubmarines(List<AdditionalArrangements> submarines) {
        this.submarines = submarines;
        for (AdditionalArrangements submarine : submarines) {
            field.setCellStatus(submarine.getPosition(), Cell.Status.SUBMARINE);
        }
    }

    public int getCountSubmarines() {
        return countSubmarines;
    }

    public Coordinate getShotFromASubmarine() {
        return shotFromASubmarine.pop();
    }

    public List<Coordinate> getShotFromASubmarineList() {
        return shotFromASubmarine;
    }

    public void setShotFromASubmarine(Coordinate shotFromASubmarine) {
        this.shotFromASubmarine.push(shotFromASubmarine);
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
