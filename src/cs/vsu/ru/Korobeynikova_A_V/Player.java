package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import java.util.List;

public class Player {
    PlayingField field;
    PlayingField opponentsField;
    List<Ship> ships;
    int countShips;

    public Player(PlayingField field, List<Ship> ships, PlayingField opponentsField, int countShips) {
        opponentsField.unknownField();

        this.field = field;
        this.ships = ships;
        this.opponentsField = opponentsField;
        this.countShips = countShips;
    }

    public void makeSheep(Ship ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) makeVerticalShip(ship);
        else makeHorizontalShip(ship);
    }

    private void makeVerticalShip(Ship ship) {
        for (int row = ship.getStartingPosition()[0]; row < ship.getStartingPosition()[0] + ship.getShipType(); row++) {
            field.setCellStatus(row, ship.getStartingPosition()[1], Cell.Status.SHIP);
        }
    }

    private void makeHorizontalShip(Ship ship) {
        for (int col = ship.getStartingPosition()[1]; col < ship.getStartingPosition()[1] + ship.getShipType(); col++) {
            field.setCellStatus(ship.getStartingPosition()[0], col, Cell.Status.SHIP);
        }
    }


    public boolean hurtOrKill(PlayingField opponentsField, Ship ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) {
            for (int row = ship.getStartingPosition()[0]; row < ship.getStartingPosition()[0] + ship.getShipType(); row++) {
                if (opponentsField.getCellStatus(row, ship.getStartingPosition()[1]) == Cell.Status.SHIP) return false; //ранил
            }
            return true; //убил
        } else if (ship.getOrientation() == Ship.Orientation.HORIZONTAL) {
            for (int col = ship.getStartingPosition()[1]; col < ship.getStartingPosition()[1] + ship.getShipType(); col++) {
                if (opponentsField.getCellStatus(ship.getStartingPosition()[0], col) == Cell.Status.SHIP) return false; //ранил
            }
            return true; //убил
        }
        return false;
    }

    public Ship findShip(int row, int col) {
        for (Ship ship : ships) {
            if (ship.getOrientation() == Ship.Orientation.VERTICAL) {
                for (int r = ship.getStartingPosition()[0]; r <= ship.getStartingPosition()[0] + ship.getShipType(); r++) {
                    if (r == row && col == ship.getStartingPosition()[1]) {
                        return ship;
                    }
                }
            } else {
                for (int c = ship.getStartingPosition()[1]; c <= ship.getStartingPosition()[1] + ship.getShipType(); c++) {
                    if (c == col && row == ship.getStartingPosition()[0]) {
                        return ship;
                    }
                }
            }
        }
        return new Ship(new int[] {0, 0}, 0, Ship.Orientation.VERTICAL);
    }

    public boolean canMakeShipOrNot(Ship ship) {
        if (ship.getOrientation() == Ship.Orientation.VERTICAL) return canVerticalMakeShipOrNot(ship);
        else return canMakeHorizontalShipOrNot(ship);
    }

    public boolean canVerticalMakeShipOrNot(Ship ship) {
        int row = ship.getStartingPosition()[0] - 1;

        while(row <= ship.getStartingPosition()[0] + ship.getShipType()) {
            if (row  >= 0 && row < field.length()) {
                int col = ship.getStartingPosition()[1] - 1;
                while (col <= ship.getStartingPosition()[1] + 1) {
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
        int row = ship.getStartingPosition()[0] - 1;

        while(row <= ship.getStartingPosition()[0] + 1) {
            if (row  >= 0 && row < field.length()) {
                int col = ship.getStartingPosition()[1] - 1;
                while (col <= ship.getStartingPosition()[1] + ship.getShipType()) {
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

    public int getCountShips() {
        return countShips;
    }

    public PlayingField getOpponentsField() {
        return opponentsField;
    }
}
