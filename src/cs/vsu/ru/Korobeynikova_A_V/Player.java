package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import java.util.List;

public class Player {
    PlayingField field;
    PlayingField opponentsField;
    List<Ship> ships;
    int countShips;

    public Player(PlayingField field, PlayingField opponentsField, int countShips) {
        opponentsField.unknownField();

        this.field = field;
        this.opponentsField = opponentsField;
        this.countShips = countShips;
    }

    public void makeSheep(Ship ship) {
        if (ship.getOrientation() == 0) makeVerticalShip(ship);
        else makeHorizontalShip(ship);
    }

    private void makeVerticalShip(Ship ship) {
        for (int row = ship.getStartingPosition()[0]; row < ship.getStartingPosition()[1] + ship.getShipType(); row++) {
            field.setCellStatus(ship.getStartingPosition()[0], ship.getStartingPosition()[1], '1');
        }
    }

    private void makeHorizontalShip(Ship ship) {
        for (int col = ship.getStartingPosition()[1]; col < ship.getStartingPosition()[0] + ship.getShipType(); col++) {
            field.setCellStatus(ship.getStartingPosition()[0], ship.getStartingPosition()[1], '1');
        }
    }


    public boolean hurtOrKill(Ship ship) {
        if (ship.getShipType() == 0) {
            for (int row = ship.getStartingPosition()[0]; row <= ship.getStartingPosition()[0] + ship.getShipType(); row++) {
                if (field.getCellStatus(row, ship.getStartingPosition()[1]) == '1') return false; //ранил
            }
            return true; //убил
        } else if (ship.getShipType() == 1) {
            for (int col = ship.getStartingPosition()[1]; col <= ship.getStartingPosition()[1] + ship.getShipType(); col++) {
                if (field.getCellStatus(ship.getStartingPosition()[0], col) == '1') return false; //ранил
            }
            return true; //убил
        }
        return false;
    }

    public Ship findShip(int row, int col) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).getShipType() == 0) {
                for(int r = ships.get(i).getStartingPosition()[0]; r <= ships.get(i).getStartingPosition()[0] + ships.get(i).getShipType(); r++) {
                    if (r == row && col == ships.get(i).getStartingPosition()[1]) return ships.get(i);
                }
            } else {
                for(int c = ships.get(i).getStartingPosition()[1]; c <= ships.get(i).getStartingPosition()[1] + ships.get(i).getShipType(); c++) {
                    if (c == col && row == ships.get(i).getStartingPosition()[0]) return ships.get(i);
                }
            }
        }
        return new Ship(new int[] {0, 0}, 0, 0);
    }

    public boolean canMakeShipOrNot(Ship ship) {
        if (ship.getOrientation() == 0) return canVerticalMakeShipOrNot(ship);
        else return canMakeHorizontalShipOrNot(ship);
    }

    public boolean canVerticalMakeShipOrNot(Ship ship) {
        int row = ship.getStartingPosition()[0] - 1;

        while(row <= ship.getStartingPosition()[0] + ship.getShipType()) {
            if (row  >= 0 && row < field.length()) {
                int col = ship.getStartingPosition()[1] - 1;
                while (col <= ship.getStartingPosition()[1] + 1) {
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
        int row = ship.getStartingPosition()[0] - 1;

        while(row <= ship.getStartingPosition()[0] + 1) {
            if (row  >= 0 && row < field.length()) {
                int col = ship.getStartingPosition()[1] - 1;
                while (col <= ship.getStartingPosition()[1] + ship.getShipType()) {
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



    public PlayingField getField() {
        return field;
    }

    public void setField(char[][] field) {
        PlayingField newField = new PlayingField();
        newField.setField(field);
        this.field = newField;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public int getCountShips() {
        return countShips;
    }

    public PlayingField getOpponentsField() {
        return opponentsField;
    }
}
