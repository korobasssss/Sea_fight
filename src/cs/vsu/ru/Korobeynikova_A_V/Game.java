package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.Figure.Figure;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.GameUpdateListener;

import java.util.ArrayList;
import java.util.List;

public interface Game {

    enum Who {
        FIRST_PLAYER,
        SECOND_PLAYER
    }

    void placementOfFigures(Player player, Figure figure);

    void randomPlacement(Player player);

    void moveOnTheOpponent(Player player, Player playerAttacked, Coordinate coordinate);

    boolean cellIsOpponentMine(List<Figure> oppMine, Coordinate coordinate);

    void addGameUpdateListener(GameUpdateListener listener);

    boolean shipsLifeStatus(List<Figure> ships);

    Player getPlayer1();

    Player getPlayer2();

    List<GameUpdateListener> getListeners();

    Boolean getFlagOfUsingOppCell();

    void setFlagOfUsingOppCell(Boolean flagOfUsingOppCell);
}
