package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.Figure.Mine;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import java.util.List;
import java.util.Stack;

public class Player {
    PlayingField field;
    PlayingField opponentsField;
    List<Ship> ships;
    List<Mine> mines;
    Stack<Coordinate> opponentShipCells;
    int countShips;
    int countMines;


    public Player(PlayingField field, List<Ship> ships, List<Mine> mines, PlayingField opponentsField, int countShips, int countMines) {
        opponentsField.unknownField();

        this.field = field;
        this.ships = ships;
        this.mines = mines;
        this.opponentsField = opponentsField;
        this.countShips = countShips;
        this.countMines = countMines;
        this.opponentShipCells = new Stack<>();
    }

    public void makeSheep(Ship ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) makeVerticalShip(ship);
        else makeHorizontalShip(ship);
    }

    private void makeVerticalShip(Ship ship) {
        for (int row = ship.getStartingPosition().getVertical(); row < ship.getStartingPosition().getVertical() + ship.getShipType(); row++) {
            field.setCellStatus(row, ship.getStartingPosition().getHorizontal(), Cell.Status.SHIP);
        }
    }

    private void makeHorizontalShip(Ship ship) {
        for (int col = ship.getStartingPosition().getHorizontal(); col < ship.getStartingPosition().getHorizontal() + ship.getShipType(); col++) {
            field.setCellStatus(ship.getStartingPosition().getVertical(), col, Cell.Status.SHIP);
        }
    }


    public boolean hurtOrKill(PlayingField opponentsField, Ship ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) {
            for (int row = ship.getStartingPosition().getVertical(); row < ship.getStartingPosition().getVertical() + ship.getShipType(); row++) {
                if (opponentsField.getCellStatus(row, ship.getStartingPosition().getHorizontal()) == Cell.Status.SHIP) return false; //ранил
            }
            return true; //убил
        } else if (ship.getOrientation() == Ship.Orientation.HORIZONTAL) {
            for (int col = ship.getStartingPosition().getHorizontal(); col < ship.getStartingPosition().getHorizontal() + ship.getShipType(); col++) {
                if (opponentsField.getCellStatus(ship.getStartingPosition().getVertical(), col) == Cell.Status.SHIP) return false; //ранил
            }
            return true; //убил
        }
        return false;
    }

    public Ship findShip(int row, int col) {
        for (Ship ship : ships) {
            if (ship.getOrientation() == Ship.Orientation.VERTICAL) {
                for (int r = ship.getStartingPosition().getVertical(); r <= ship.getStartingPosition().getVertical() + ship.getShipType(); r++) {
                    if (r == row && col == ship.getStartingPosition().getHorizontal()) {
                        return ship;
                    }
                }
            } else {
                for (int c = ship.getStartingPosition().getHorizontal(); c <= ship.getStartingPosition().getHorizontal() + ship.getShipType(); c++) {
                    if (c == col && row == ship.getStartingPosition().getVertical()) {
                        return ship;
                    }
                }
            }
        }
        return new Ship(new Coordinate(0, 0), 0, Ship.Orientation.VERTICAL);
    }

    public boolean canMakeShipOrNot(Ship ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) return canVerticalMakeShipOrNot(ship);
        else return canMakeHorizontalShipOrNot(ship);
    }

    public boolean canVerticalMakeShipOrNot(Ship ship) {
        int row = ship.getStartingPosition().getVertical() - 1;

        while(row <= ship.getStartingPosition().getVertical() + ship.getShipType()) {
            if (row  >= 0 && row < field.length()) {
                int col = ship.getStartingPosition().getHorizontal() - 1;
                while (col <= ship.getStartingPosition().getHorizontal() + 1) {
                    if (col  >= 0 && col < field.length()) {
                        if (field.getCellStatus(row, col) == Cell.Status.SHIP) return false;
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

        while(row <= ship.getStartingPosition().getVertical() + 1) {
            if (row  >= 0 && row < field.length()) {
                int col = ship.getStartingPosition().getHorizontal() - 1;
                while (col <= ship.getStartingPosition().getHorizontal() + ship.getShipType()) {
                    if (col  >= 0 && col < field.length()) {
                        if (field.getCellStatus(row, col) == Cell.Status.SHIP) return false;
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

    public void setField(Cell[][] field) {
        PlayingField newField = new PlayingField();
        newField.setField(field);
        this.field = newField;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(Ship ships) {
        this.ships.add(ships);
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
        for (Ship ship : ships) {
            makeSheep(ship);
        }
    }

    public Coordinate getOpponentShipCell() {
        return opponentShipCells.pop();
    }

    public Stack<Coordinate> getOpponentShipCells() {
        return opponentShipCells;
    }

    public void setOpponentShipCells(Coordinate coord) {
        opponentShipCells.push(coord);
    }

    public List<Mine> getMines() {
        return mines;
    }

    public void setMines(List<Mine> mines) {
        this.mines = mines;
    }

    public void setMines(Mine mines) {
        this.mines.add(mines);
    }

    public void setCountShips(int countShips) {
        this.countShips = countShips;
    }

    public int getCountMines() {
        return countMines;
    }

    public void setCountMines(int countMines) {
        this.countMines = countMines;
    }

    public int getCountShips() {
        return countShips;
    }

    public PlayingField getOpponentsField() {
        return opponentsField;
    }
}
