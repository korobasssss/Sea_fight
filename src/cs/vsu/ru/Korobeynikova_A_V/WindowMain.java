package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.PlayingWindow;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.StartWindow;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.WindowUI;

import java.util.ArrayList;

public class WindowMain {

    public static void main(String[] args) {
        WindowUI windowUI = new WindowUI();
        Player player1 = new Player(LocalGame.Who.FIRST_PLAYER, "1", new PlayingField(), new ArrayList<>(), new PlayingField());
        Player player2 = new Player(LocalGame.Who.SECOND_PLAYER, "2", new PlayingField(), new ArrayList<>(), new PlayingField());

        Game game = new LocalGame(player1, player2, windowUI);

        StartWindow startWindow = new StartWindow(player1, player2, game, windowUI);
        startWindow.setVisible(true);

    }
}
