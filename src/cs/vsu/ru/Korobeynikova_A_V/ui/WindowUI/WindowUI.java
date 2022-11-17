package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI;

import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;

import javax.swing.*;
import java.awt.*;

public class WindowUI extends JFrame implements GameUI {

    MainWindow mainWindow = new MainWindow();

    @Override
    public String setYourName(String who) {
        JFrame jFrame = new JFrame();
        String message = "Игрок " + who + ", введите ваше имя";
        return JOptionPane.showInputDialog(jFrame, message);
    }

    @Override
    public void messageOfPlayersReady() {
        mainWindow.getPanelForMessage().add(new JLabel("Оба игрока готовы к бою"));
    }

    @Override
    public void messageOfGetCoordinates(String name, String whatPlacement) {
        String message = "Игрок " + name + "введите координаты" + whatPlacement;
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
        String message = "Игрок %s, ваш ввод неверный или расстановка невозможна".formatted(name); // todo сделать в таком формате
        JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void messageWhereDidMove(String name, Coordinate coordinate) {
        String message = "Игрок " + name + ", походил по горизонтали на" + coordinate.getHorizontal() + 1 + " и по вертикали на " + coordinate.getVertical() + 1;
        mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public String decisionOfPlacementFigures(String name) {
        JFrame jFrame = new JFrame();
        String message = "Игрок " + name + ", вы хотите случайную расстановку(введите 0) или желаете самостоятельно расставить корабли(введите 1)?";
        return JOptionPane.showInputDialog(jFrame, message);
    }

    @Override
    public void messageOfRandomPlacementFigures(String name) {
        //String message = "Игрок " + name + ", ваша случайная расстановка кораблей: ";
        //mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public String decisionOfShipOrientation(String name, String type) {
        JFrame jFrame = new JFrame();
        String message = "Игрок " + name + ", клеточный корабль вертикальный(введите 0) или горизонтальный(введите 1)?  ";
        return JOptionPane.showInputDialog(jFrame, message);
    }

    @Override
    public void messageOfWhoseParty(String name) {
        String message = "Игрок " + name + " делайте ход.";
        mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public String decisionOfUsingOpponentsPartOfTheShip(String name) {
        JFrame jFrame = new JFrame();
        String message = "Игрок " + name + ", желаете ли вы воспользоваться координатами части корабля противника? (0 - нет, 1 - да)";
        return JOptionPane.showInputDialog(jFrame, message);
    }

    @Override
    public void messageOfExistenceOpponentsMineOnThisCell(String name) {
        String message = "Игрок " + name + ", вы знаете, что там стоит мина противника, выберите другую клетку:";
        mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public void messageOfShipState(String name, String whatIsLocated) {
        String message = "Игрок " + name + whatIsLocated + " корабль другого игрока";
        mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public void messageOfMarkedCell(String name) {
        String message = "Игрок " + name + ", эта зона уже поражена";
        mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public void messageOfEmptyCell(String name) {
        String message = "Игрок " + name + ", вы попали мимо.";
        mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public void messageOfEntryOfOpponentsMineOrMinesweeper(String name, String what, String what2) {
        String message = "Игрок " + name + ", вы попали на " + what + "! :( Введите координаты клетки " + what2;
        mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public void messageThatYouAreALiar(String name, String what) {
        String message = "Игрок " + name + ", не обманывайте, там у вас нет " + what + ", введите координаты заново!";
        mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public void messageOfEntryOfSubmarine(String name) {
        String message = "Игрок " + name + ", вы попали в подводную лодку ! Ожидайте выстрела на ваше поле!";
        mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public void messageOfWhoseField(String name) {
        String message = "Игрок " + name + ", вы попали мимо.";
        mainWindow.getPanelForMessage().add(new JLabel(message));
    }

    @Override
    public void messageOfFinish(String name) {
        String message = "Игрок " + name + " выиграл!!!!!!";
        JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.INFORMATION_MESSAGE);
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
