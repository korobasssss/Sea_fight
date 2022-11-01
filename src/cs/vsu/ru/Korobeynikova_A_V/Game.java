package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import java.util.ArrayList;

public class Game {

    private void attacks(Player player1, Player player2, Console console) {
        int who = 1; // 1 - 1 игрок, 2 - 2 игрок

        while (player1.getCountShips() > 0 && player2.getCountShips() > 0) {
            if (who == 1) {
                console.moveOnTheOpponent(who, player1, player2, player2.getField(), player1.getOpponentsField());
                who = 2;
            } else {
                console.moveOnTheOpponent(who, player2, player1, player1.getField(), player2.getOpponentsField());
                who = 1;
            }
        }
    }
    public void game() {
        Player player1 = new Player(new PlayingField(), new ArrayList<>(), new ArrayList<>(), new PlayingField());
        Player player2 = new Player(new PlayingField(), new ArrayList<>(), new ArrayList<>(), new PlayingField());

        Console console = new Console();

        // расставляем фигуры на оба поля
        console.placementOfFigures(1, player1);
        console.placementOfFigures(2, player2);

        System.out.println("Оба игрока готовы к бою.");

        //ведем бой пока счетчик кол-ва кораблей одного из игроков не станет равным нулю
        attacks(player1, player2, console);

        //конец
        console.finish(player1, player2);
    }
}
