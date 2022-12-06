package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.Figure.AdditionalArrangements;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Figure;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Player {
    private final Game.Who number;

    private final String name;
    private final PlayingField field;
    private final PlayingField opponentsField;
    private List<Figure> ships;
    private List<Figure> mines;
    private List<Figure> minesweepers;
    private List<Figure> submarines;
    private final Stack<Coordinate> opponentShipCells;
    private final List<Figure> opponentMines;
    private final Stack<Coordinate> shotFromASubmarine;

    public Player(LocalGame.Who number, String name, PlayingField field, List<Figure> ships, PlayingField opponentsField) {
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

    private void makeSheep(Figure ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) makeVerticalShip(ship);
        else makeHorizontalShip(ship);
    }

    private void makeVerticalShip(Figure ship) {
        Coordinate coordinate = new Coordinate(0, ship.getPosition().getHorizontal());
        for (int row = ship.getPosition().getVertical(); row < ship.getPosition().getVertical() + Integer.parseInt(ship.getShipType().getString()); row++) {
            coordinate.setVertical(row);
            field.setCellStatus(coordinate, Cell.Status.SHIP);
        }
    }

    private void makeHorizontalShip(Figure ship) {
        Coordinate coordinate = new Coordinate(ship.getPosition().getVertical(), 0);
        for (int col = ship.getPosition().getHorizontal(); col < ship.getPosition().getHorizontal() + Integer.parseInt(ship.getShipType().getString()); col++) {
            coordinate.setHorizontal(col);
            field.setCellStatus(coordinate, Cell.Status.SHIP);
        }
    }

    public boolean hurtOrKill(PlayingField opponentsField, Figure ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) {
            Coordinate coordinate = new Coordinate(0, ship.getPosition().getHorizontal());
            for (int row = ship.getPosition().getVertical(); row < ship.getPosition().getVertical() + Integer.parseInt(ship.getShipType().getString()); row++) {
                coordinate.setVertical(row);
                if (opponentsField.getCellStatus(coordinate) == Cell.Status.SHIP) return false; //ранил
            }
            ship.setStatus(Ship.Status.KILLED);
            return true; //убил
        } else if (ship.getOrientation() == Ship.Orientation.HORIZONTAL) {
            Coordinate coordinate = new Coordinate(ship.getPosition().getVertical(), 0);
            for (int col = ship.getPosition().getHorizontal(); col < ship.getPosition().getHorizontal() + Integer.parseInt(ship.getShipType().getString()); col++) {
                coordinate.setHorizontal(col);
                if (opponentsField.getCellStatus(coordinate) == Cell.Status.SHIP) return false; //ранил
            }
            ship.setStatus(Ship.Status.KILLED);
            return true; //убил
        }
        return false;
    }

    public Figure findShip(int row, int col) {
        for (Figure ship : ships) {
            if (ship.getOrientation() == Ship.Orientation.VERTICAL) {
                for (int r = ship.getPosition().getVertical(); r <= ship.getPosition().getVertical() + Integer.parseInt(ship.getShipType().getString()); r++) {
                    if (r == row && col == ship.getPosition().getHorizontal()) {
                        return ship;
                    }
                }
            } else {
                for (int c = ship.getPosition().getHorizontal(); c <= ship.getPosition().getHorizontal() + Integer.parseInt(ship.getShipType().getString()); c++) {
                    if (c == col && row == ship.getPosition().getVertical()) {
                        return ship;
                    }
                }
            }
        }
        return new Ship(new Coordinate(0, 0), Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Ship.Status.KILLED);
    }

    public void deleteFigure(Figure ship) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).getPosition() == ship.getPosition()) {
                ships.remove(i);
            }
        }

        if (ship.getOrientation() == Ship.Orientation.VERTICAL) {
            for (int r = ship.getPosition().getVertical(); r < ship.getPosition().getVertical() + Integer.parseInt(ship.getShipType().getString()); r++){
                field.setCellStatus(r, ship.getPosition().getHorizontal(), Cell.Status.EMPTY);
            }
        } else {
            for (int c = ship.getPosition().getHorizontal(); c < ship.getPosition().getHorizontal() + Integer.parseInt(ship.getShipType().getString()); c++){
                field.setCellStatus(ship.getPosition().getVertical(), c, Cell.Status.EMPTY);
            }
        }
    }

    public boolean canMakeShipOrNot(Figure ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) return canVerticalMakeShipOrNot(ship);
        else return canMakeHorizontalShipOrNot(ship);
    }

    private boolean canVerticalMakeShipOrNot(Figure ship) {
        int row = ship.getPosition().getVertical();
        if (row > 0) row = ship.getPosition().getVertical() - 1;
        int col = ship.getPosition().getHorizontal();
        int colCopy;

        Coordinate coordinate = new Coordinate(-1, -1);
        while(row <= ship.getPosition().getVertical() + Integer.parseInt(ship.getShipType().getString())) {
            if (row  >= 0 && row < field.length()) {
                if (col > 0) colCopy = ship.getPosition().getHorizontal() - 1;
                else colCopy = 0;
                while (colCopy <= ship.getPosition().getHorizontal() + 1) {
                    if (colCopy  >= 0 && colCopy < field.length()) {
                        coordinate.setVertical(row); coordinate.setHorizontal(colCopy);
                        if (field.getCellStatus(coordinate) == Cell.Status.SHIP
                                || field.getCellStatus(coordinate) == Cell.Status.MINE
                                || field.getCellStatus(coordinate) == Cell.Status.MINESWEEPER
                                || field.getCellStatus(coordinate) == Cell.Status.SUBMARINE) return false;
                    }
                    colCopy++;
                }
                row++;
            } else break;
        }
        if (ship.getShipType() != Ship.Type.ONE_CELL && ship.getPosition().getVertical() + Integer.parseInt(ship.getShipType().getString()) - 1 > field.length()) return false;
        return coordinate.getVertical() != -1;
    }

    private boolean canMakeHorizontalShipOrNot(Figure ship) {
        int row = ship.getPosition().getVertical();
        int rowCopy;
        int col = ship.getPosition().getHorizontal();
        if (col > 0) col = ship.getPosition().getHorizontal() - 1;

        Coordinate coordinate = new Coordinate(-1, -1);
        while(col <= ship.getPosition().getHorizontal() + Integer.parseInt(ship.getShipType().getString())) {
            if (col  >= 0 && col < field.length()) {
                if (row > 0) rowCopy = ship.getPosition().getVertical() - 1;
                else rowCopy = 0;
                while (rowCopy <= ship.getPosition().getVertical() + 1) {
                    if (rowCopy >= 0 && rowCopy < field.length()) {
                        coordinate.setVertical(rowCopy); coordinate.setHorizontal(col);
                        if (field.getCellStatus(coordinate) == Cell.Status.SHIP
                                || field.getCellStatus(coordinate) == Cell.Status.MINE
                                || field.getCellStatus(coordinate) == Cell.Status.MINESWEEPER
                                || field.getCellStatus(coordinate) == Cell.Status.SUBMARINE) return false;
                    }
                    rowCopy++;
                }
                col++;
            } else break;
        }
        if (ship.getShipType() != Ship.Type.ONE_CELL && ship.getPosition().getHorizontal() + Integer.parseInt(ship.getShipType().getString()) - 1 > field.length()) return false;
        return coordinate.getVertical() != -1;
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

    public List<Figure> getShips() {
        return ships;
    }

    public void setShips(List<Figure> ships) {
        this.ships = ships;
        for (Figure ship : ships) {
            makeSheep(ship);
        }
    }

    public void setShip(Figure ship) {
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

    public List<Figure> getMines() {
        return mines;
    }

    public void setMine(Figure mine) {
        this.mines.add(mine);
        field.setCellStatus(mine.getPosition(), Cell.Status.MINE);
    }

    public void setMines(List<Figure> mines) {
        this.mines = mines;
        for (Figure mine : mines) {
            field.setCellStatus(mine.getPosition(), Cell.Status.MINE);
        }
    }

    public PlayingField getOpponentsField() {
        return opponentsField;
    }

    public List<Figure> getMinesweepers() {
        return minesweepers;
    }

    public void setMinesweeper(Figure minesweeper) {
        this.minesweepers.add(minesweeper);
        field.setCellStatus(minesweeper.getPosition(), Cell.Status.MINESWEEPER);
    }

    public void setMinesweepers(List<Figure> minesweepers) {
        this.minesweepers = minesweepers;
        for (Figure minesweeper : minesweepers) {
            field.setCellStatus(minesweeper.getPosition(), Cell.Status.MINESWEEPER);
        }
    }

    public List<Figure> getOpponentMines() {
        return opponentMines;
    }

    public void setOpponentMines(Figure additionalArrangement) {
        opponentMines.add(additionalArrangement);
    }

    public List<Figure> getSubmarineList() {
        return submarines;
    }

    public void setSubmarine(Figure submarine) {
        this.submarines.add(submarine);
        field.setCellStatus(submarine.getPosition(), Cell.Status.SUBMARINE);
    }

    public void setSubmarines(List<Figure> submarines) {
        this.submarines = submarines;
        for (Figure submarine : submarines) {
            field.setCellStatus(submarine.getPosition(), Cell.Status.SUBMARINE);
        }
    }

    public int getCountSubmarines() {
        return 1;
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

    public LocalGame.Who getNumber() {
        return number;
    }
}
