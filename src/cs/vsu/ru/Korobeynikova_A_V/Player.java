package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import java.util.List;

public class Player {
    PlayingField field;
    List<Ship> ships;

    public Player(PlayingField field) {
        this.field = field;
    }

    public char attack(int col, int row) {
        return field.getCellStatus(row, col);
    }

    public void addShip() {

    }

    public boolean hurtOrKill(Ship ship) {
        if (ship.shipType == 0) {
            for (int row = ship.startingPosition[0]; row <= ship.startingPosition[0] + ship.shipType; row++) {
                if (field.getCellStatus(row, ship.startingPosition[1]) == '1') return false; //ранил
            }
            return true; //убил
        } else if (ship.shipType == 1) {
            for (int col = ship.startingPosition[1]; col <= ship.startingPosition[1] + ship.shipType; col++) {
                if (field.getCellStatus(ship.startingPosition[0], col) == '1') return false; //ранил
            }
            return true; //убил
        }
        return false;
    }

    private Ship findShip(int row, int col) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).shipType == 0) {
                for(int r = ships.get(i).startingPosition[0]; r <= ships.get(i).startingPosition[0] + ships.get(i).shipType; r++) {
                    if (r == row && col == ships.get(i).startingPosition[1]) return ships.get(i);
                }
            } else {
                for(int c = ships.get(i).startingPosition[1]; c <= ships.get(i).startingPosition[1] + ships.get(i).shipType; c++) {
                    if (c == col && row == ships.get(i).startingPosition[0]) return ships.get(i);
                }
            }
        }
        return new Ship(new int[] {0, 0}, 0, 0);
    }

    public boolean canMakeShipOrNot(Ship ship) {
        if (ship.orientation == 0) return canVerticalMakeShipOrNot(ship);
        else return canMakeHorizontalShipOrNot(ship);
    }

    public boolean canVerticalMakeShipOrNot(Ship ship) {
        int row = ship.startingPosition[0] - 1;

        while(row <= ship.startingPosition[0] + ship.shipType) {
            if (row  >= 0 && row < field.length()) {
                int col = ship.startingPosition[1] - 1;
                while (col <= ship.startingPosition[1] + 1) {
                    if (col  >= 0 && col < field.length()) {
                        if (field.getCellStatus(row, col) == '1') return false;
                    }
                    col++;
                }
            }
            row++;
        }
        return true;
    }

    public boolean canMakeHorizontalShipOrNot(Ship ship) {
        int row = ship.startingPosition[0] - 1;

        while(row <= ship.startingPosition[0] + 1) {
            if (row  >= 0 && row < field.length()) {
                int col = ship.startingPosition[1] - 1;
                while (col <= ship.startingPosition[1] + ship.shipType) {
                    if (col  >= 0 && col < field.length()) {
                        if (field.getCellStatus(row, col) == '1') return false;
                    }
                    col++;
                }
            }
            row++;
        }
        return true;
    }

    public void makeSheep(Ship ship) {
        if (ship.orientation == 0) makeVerticalShip(ship);
        else makeHorizontalShip(ship);
    }

    private void makeVerticalShip(Ship ship) {
        for (int row = ship.startingPosition[0]; row < ship.startingPosition[1] + ship.shipType; row++) {
            field.setCellStatus(ship.startingPosition[0], ship.startingPosition[1], '1');
        }
    }

    private void makeHorizontalShip(Ship ship) {
        for (int col = ship.startingPosition[1]; col < ship.startingPosition[0] + ship.shipType; col++) {
            field.setCellStatus(ship.startingPosition[0], ship.startingPosition[1], '1');
        }
    }
}
