package cs.vsu.ru.Korobeynikova_A_V;


import cs.vsu.ru.Korobeynikova_A_V.ui.ConsoleUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;

public class Main {

    public static void main(String[] args) {
        GameUI gameUIC = new ConsoleUI();

        LocalGame game = new LocalGame(gameUIC);
        game.game();
    }
}
