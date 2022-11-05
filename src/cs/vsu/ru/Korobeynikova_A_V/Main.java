package cs.vsu.ru.Korobeynikova_A_V;


import cs.vsu.ru.Korobeynikova_A_V.ui.ConsoleUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;

public class Main {

    public static void main(String[] args) {
        GameUI gameUI = new ConsoleUI();

        Game game = new Game();
        game.game(gameUI);

//        MainWindow mainWindow = new MainWindow("Морской бой");
//        mainWindow.setVisible(true);
    }
}
