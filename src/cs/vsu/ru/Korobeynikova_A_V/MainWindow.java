package cs.vsu.ru.Korobeynikova_A_V;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final int SIZE_MAIN_PANEL = 900;
    private final int SIZE_PLAYER_PANEL_LENGTH = SIZE_MAIN_PANEL / 2;
    private final int SIZE_PLAYER_PANEL_WIDTH = SIZE_MAIN_PANEL;
    private final int SIZE_PLAYER_FIELD_PANEL = 200;

    JPanel mainPanel;
    JPanel panelFirstPlayer;
    JPanel panelFirstPlayerField;
    JPanel panelSecondPlayer;
    JPanel panelSecondPlayerField;

    public MainWindow(String title) throws HeadlessException {
        super(title);

        this.mainPanel = new JPanel();
        this.setSize(SIZE_MAIN_PANEL, SIZE_MAIN_PANEL);

        this.panelFirstPlayer = new JPanel();
        this.panelFirstPlayerField = new JPanel();
        panelFirstPlayerField.setSize(SIZE_PLAYER_FIELD_PANEL, SIZE_PLAYER_FIELD_PANEL);
        panelFirstPlayer.setSize(SIZE_PLAYER_PANEL_WIDTH, SIZE_PLAYER_PANEL_LENGTH);
        panelFirstPlayer.add(panelFirstPlayerField);
        panelFirstPlayerField.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.add(panelFirstPlayer);

        this.panelSecondPlayer = new JPanel();
        this.panelSecondPlayerField = new JPanel();
        panelSecondPlayerField.setSize(SIZE_PLAYER_FIELD_PANEL, SIZE_PLAYER_FIELD_PANEL);
        panelSecondPlayer.setSize(SIZE_PLAYER_PANEL_WIDTH, SIZE_PLAYER_PANEL_LENGTH);
        panelSecondPlayer.add(panelFirstPlayerField);
        mainPanel.add(panelSecondPlayer);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }
}
