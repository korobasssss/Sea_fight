package cs.vsu.ru.Korobeynikova_A_V.background;
//чисто поле для боя, голое или бой проводить уже тут
public class PlayingField {
    private final boolean[][] field = new boolean[10][10];

    private int changeToIng(char symbol) { //меняем букву на число
        int num;
        switch (symbol) {
            case 'A': num = 1;
            case 'B': num = 2;
            case 'C': num = 3;
            case 'D': num = 4;
            case 'E': num = 5;
            case 'F': num = 6;
            case 'G': num = 7;
            case 'H': num = 8;
            case 'I': num = 9;
            case 'J': num = 10;
            default: num = 0;
        }
        return num;
    }

    public int setHit(char horizontalSymbol, int vertical) { // "удар" по клетке
        int horizontal = changeToIng(horizontalSymbol);
        if (field[vertical][horizontal] = true) { //если на клетке есть часть корабля
            field[vertical][horizontal] = false;
            return 1;
        } else { return 0;} // если на клетке пусто
    }

    public boolean fullCheck() { // проверка на присутствие кораблей
        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[0].length; c++) {
                if (field[r][c]) {return false;}
            }
        }
        return true;
    }
}
