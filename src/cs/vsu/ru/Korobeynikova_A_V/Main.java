package cs.vsu.ru.Korobeynikova_A_V;


import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.ui.ConsoleUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        GameUI gameUIC = new ConsoleUI();

        Player player1 = new Player(LocalGame.Who.FIRST_PLAYER, "1", new PlayingField(), new ArrayList<>(), new PlayingField());
        Player player2 = new Player(LocalGame.Who.SECOND_PLAYER, "2", new PlayingField(), new ArrayList<>(), new PlayingField());

        LocalGame game = new LocalGame(player1, player2, gameUIC);
    }
}
