package cs.vsu.ru.Korobeynikova_A_V;


import cs.vsu.ru.Korobeynikova_A_V.ui.ConsoleUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.WindowUI;

public class Main {

    public static void main(String[] args) {
        GameUI gameUIC = new ConsoleUI();

        Game game = new Game();
        game.game(gameUIC);

//        MainWindow mainWindow = new MainWindow();
//        mainWindow.setVisible(true);
    }
}
