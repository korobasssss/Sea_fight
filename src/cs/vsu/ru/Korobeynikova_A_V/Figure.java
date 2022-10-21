package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import java.util.Arrays;
import java.util.Scanner;

//создать фигуры, брать потом отсюда их
public class Figure {
    public int pos = 0;

    public PlayingField makeSheep(PlayingField field, int vert, int hor, int shepType) {
        if (pos == 0) makeVerticalShep(field, vert, hor, shepType);
        else makeHorizontalShep(field, vert, hor, shepType);
        return field;
    }

    private PlayingField makeVerticalShep(PlayingField field, int vert, int hor, int shepType) {
        for (int row = vert; row < vert + shepType; row++) {
            field.setCellStatus(row, hor, '1');
        }
        return field;
    }

    private PlayingField makeHorizontalShep(PlayingField field, int vert, int hor, int shepType) {
        for (int col = hor; col < shepType + hor; col++) {
            field.setCellStatus(vert, col, '1');
        }
        return field;
    }
}
