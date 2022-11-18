package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI;

import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.MessagesForUI;

import javax.swing.*;

public class WindowUI extends JFrame implements GameUI {

    MainWindow mainWindow = new MainWindow();
    MessagesForUI messagesForUI = new MessagesForUI();

    @Override
    public String setYourName(String who) {
        JFrame jFrame = new JFrame();
        return JOptionPane.showInputDialog(jFrame, messagesForUI.setYourName().formatted(who));
    }

    @Override
    public void messageOfPlayersReady() {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageOfPlayersReady()));
    }

    @Override
    public void messageOfGetCoordinates(String name, String whatPlacement) {
        String message = messagesForUI.messageOfGetCoordinates().formatted(name, whatPlacement);
        mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public Coordinate getCoordinates(String name) {
        String col = mainWindow.getTextFieldGetCoordX().getText();
        int intCol = changeToInt(col);
        while (intCol < 0 || intCol > PlayingField.getSize()) {
            messageOfWrongNumberOrLetter(name);
            col = mainWindow.getTextFieldGetCoordX().getText();
            intCol = changeToInt(col);
        }
        String row = mainWindow.getTextFieldGetCoordY().getText();
        while (!isNumeric(row) || (isNumeric(row) && (Integer.parseInt(row) < 0 || Integer.parseInt(row) > PlayingField.getSize()))) {
            messageOfWrongNumberOrLetter(name);
            row = mainWindow.getTextFieldGetCoordY().getText();
        }

        return new Coordinate(Integer.parseInt(row) - 1, intCol - 1);
    }

    @Override
    public void messageOfWrongNumberOrLetter(String name) {
        JOptionPane.showMessageDialog(null, messagesForUI.messageOfWrongNumberOrLetter().formatted(name), "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void messageWhereDidMove(String name, Coordinate coordinate) {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageWhereDidMove().formatted(name, coordinate.getHorizontal() + 1, coordinate.getVertical() + 1)));
    }

    @Override
    public String decisionOfPlacementFigures(String name) {
        JFrame jFrame = new JFrame();
        return JOptionPane.showInputDialog(jFrame, messagesForUI.decisionOfPlacementFigures().formatted(name));
    }

    @Override
    public void messageOfRandomPlacementFigures(String name) {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageOfRandomPlacementFigures().formatted(name)));
    }

    @Override
    public String decisionOfShipOrientation(String name, String type) {
        return JOptionPane.showInputDialog(new JFrame(), messagesForUI.decisionOfShipOrientation().formatted(name, type));
    }

    @Override
    public void messageOfWhoseParty(String name) {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageOfWhoseParty().formatted(name)));
    }

    @Override
    public String decisionOfUsingOpponentsPartOfTheShip(String name) {
        return JOptionPane.showInputDialog(new JFrame(), messagesForUI.decisionOfUsingOpponentsPartOfTheShip().formatted(name));
    }

    @Override
    public void messageOfExistenceOpponentsMineOnThisCell(String name) {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageOfExistenceOpponentsMineOnThisCell()));
    }

    @Override
    public void messageOfShipState(String name, String whatIsLocated) {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageOfShipState().formatted(name, whatIsLocated)));
    }

    @Override
    public void messageOfMarkedCell(String name) {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageOfMarkedCell().formatted(name)));
    }

    @Override
    public void messageOfEmptyCell(String name) {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageOfEmptyCell()));
    }

    @Override
    public void messageOfEntryOfOpponentsMineOrMinesweeper(String name, String what, String what2) {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageOfEntryOfOpponentsMineOrMinesweeper().formatted(name, what, what2)));
    }

    @Override
    public void messageThatYouAreALiar(String name, String what) {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageThatYouAreALiar().formatted(name, what)));
    }

    @Override
    public void messageOfEntryOfSubmarine(String name) {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageOfEntryOfSubmarine().formatted(name)));
    }

    @Override
    public void messageOfWhoseField(String name) {
        mainWindow.getPanelForMessage().add(new JLabel(messagesForUI.messageOfWhoseField().formatted(name)));
    }

    @Override
    public void messageOfFinish(String name) {
        JOptionPane.showMessageDialog(null, messagesForUI.messageOfFinish().formatted(name), "WINNER!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void print(Cell[][] arr, String who) {
        FieldUI fieldUI = new FieldUI();
        switch (who) {
            case "1" -> fieldUI.setValuesToField(mainWindow.getTableFirstPlayer(), arr);
            case "2" -> fieldUI.setValuesToField(mainWindow.getTableSecondPlayer(), arr);
            case "1.1" -> fieldUI.setValuesToField(mainWindow.getTableOpponentForFirstPlayer(), arr);
            case "2.1" -> fieldUI.setValuesToField(mainWindow.getTableOpponentForSecondPlayer(), arr);
        }
    }

    private int changeToInt(String symbol) { //меняем букву на число
        if (symbol.length() == 1) {
            return symbol.charAt(0) - 65 + 1;
        }
        return -1;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
