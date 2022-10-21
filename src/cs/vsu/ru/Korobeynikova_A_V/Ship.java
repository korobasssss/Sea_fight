package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import java.util.ArrayList;
import java.util.List;


//создать фигуры, брать потом отсюда их
public class Ship {

    public int[] startingPosition;
    public int shipType;
    public int orientation;

    public Ship(int[] startingPosition, int shipType, int orientation) {
        this.startingPosition = startingPosition;
        this.shipType = shipType;
        this.orientation = orientation;
    }

    //List<int[]> ship = new ArrayList<>(); // лист массивов для каждого корабля, масиив ключает начальную точку, длину, вертикальный/горизонтальный(0/1)

}
