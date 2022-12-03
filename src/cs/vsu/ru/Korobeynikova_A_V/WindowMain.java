package cs.vsu.ru.Korobeynikova_A_V;

import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.MainWindow;

public class WindowMain {

    public static void main(String[] args) {
        Game game = new LocalGame();
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}
