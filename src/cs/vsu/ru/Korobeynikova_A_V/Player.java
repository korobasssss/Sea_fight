package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

public class Player {
    PlayingField field;

    public Player(PlayingField field) {
        this.field = field;
    }

    public void addShip() {

    }
    public char attack(int col, int row) {
        return field.getCellStatus(row, col);
    }
}
