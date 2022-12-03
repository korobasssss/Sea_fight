package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.GameUpdateListener;

public interface Game {
    void placementOfFigures(Player player, GameUI gameUI);

    void attacks(Player player1, Player player2, GameUI gameUI);

    void finish(Player player1, Player player2, GameUI gameUI);

    void addGameUpdateListener(GameUpdateListener listener);
}
