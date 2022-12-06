package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI;

import cs.vsu.ru.Korobeynikova_A_V.LocalGame;
import cs.vsu.ru.Korobeynikova_A_V.Player;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.MessagesForUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.CellRenderer;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.FieldTableModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class WindowUI extends JFrame implements GameUI {

    MessagesForUI messagesForUI = new MessagesForUI();
    JTextArea textMessage = new JTextArea();

    public WindowUI() {
    }

    @Override
    public void messageOfPlayersReady() {
        setTextMessage(messagesForUI.messageOfPlayersReady());
    }

    @Override
    public void messageOfGetCoordinates(Player player) {
        if (player.getShips().size() >= player.getField().getOneCellShip()
                + player.getField().getTwoCellShip()
                + player.getField().getThreeCellShip()
                + player.getField().getFourCellShip()) {
            if (player.getMinesweepers().size() == player.getField().getCountMinesweepers())
                textMessage.setText(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "подлодки"));
            else if (player.getMines().size() == player.getField().getCountMines())
                textMessage.setText(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "минного тральщика"));
            else
                textMessage.setText(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "мины"));
        }

        else if (player.getShips().size() >= player.getField().getOneCellShip()
                + player.getField().getTwoCellShip()
                + player.getField().getThreeCellShip()) {
            textMessage.setText(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "4 клеточного корабля"));
        }

        else if (player.getShips().size() >= player.getField().getOneCellShip()
                + player.getField().getTwoCellShip()) {
            textMessage.setText(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "3 клеточного корабля"));
        }

        else if (player.getShips().size() >= player.getField().getOneCellShip()) {
            textMessage.setText(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "2 клеточного корабля"));
        }

        else textMessage.setText(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "1 клеточного корабля"));
    }

    @Override
    public Coordinate getCoordinates(Player player) {
        String coord = JOptionPane.showInputDialog(new JFrame(), messagesForUI.getCoordinates().formatted(player.getName()));
        while (Objects.equals(coord, null)) {
            this.messageOfWrongNumberOrLetter(player.getName());
            coord = JOptionPane.showInputDialog(new JFrame(), messagesForUI.getCoordinates().formatted(player.getName()));
        }
        String col = separation(coord)[0];
        int intCol = changeToInt(col);
        while(intCol <= 0 || intCol > PlayingField.getSize()){
            this.messageOfWrongNumberOrLetter(player.getName());
            intCol= changeToInt(separation(JOptionPane.showInputDialog(new JFrame(), messagesForUI.getCoordinates().formatted(player.getName())))[0]);
        }

        String row = separation(coord)[1];

        while(!isNumeric(row) || (Integer.parseInt(row) <= 0 || Integer.parseInt(row) > PlayingField.getSize())) {
            this.messageOfWrongNumberOrLetter(player.getName());
            row = separation(JOptionPane.showInputDialog(new JFrame(), messagesForUI.getCoordinates().formatted(player.getName())))[1];
        }

        return new Coordinate(Integer.parseInt(row) - 1, intCol - 1);
    }

    @Override
    public void messageOfWrongNumberOrLetter(String name) {
        JOptionPane.showMessageDialog(null, messagesForUI.messageOfWrongNumberOrLetter().formatted(name), "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void messageWhereDidMove(String name, Coordinate coordinate) {
        setTextMessage(messagesForUI.messageWhereDidMove().formatted(name, coordinate.getHorizontal() + 1, coordinate.getVertical() + 1));
    }

    @Override
    public String decisionOfPlacementFigures(String name) {
        JFrame jFrame = new JFrame();
        return JOptionPane.showInputDialog(jFrame, messagesForUI.decisionOfPlacementFigures().formatted(name));
    }

    @Override
    public void messageOfRandomPlacementFigures(String name) {
        setTextMessage(messagesForUI.messageOfRandomPlacementFigures().formatted(name));
    }

    @Override
    public String decisionOfShipOrientation(String name, String type) {
        return JOptionPane.showInputDialog(new JFrame(), messagesForUI.decisionOfShipOrientation().formatted(name, type));
    }

    @Override
    public void messageOfWhoseParty(String name) {

    }

    @Override
    public void messageOfNotYourParty(String name) {
        setTextMessage(messagesForUI.messageOfNotYourParty().formatted(name));
    }

    @Override
    public Integer decisionOfUsingOpponentsPartOfTheShip(String name) {
        String str = JOptionPane.showInputDialog(new JFrame(), messagesForUI.decisionOfUsingOpponentsPartOfTheShip().formatted(name));
        while (Integer.parseInt(str) != 0 && Integer.parseInt(str) != 1) {
            messageOfWrongNumberOrLetter(name);
            str = JOptionPane.showInputDialog(new JFrame(), messagesForUI.decisionOfUsingOpponentsPartOfTheShip().formatted(name));
        }
        return Integer.parseInt(str);
    }

    @Override
    public void messageOfExistenceOpponentsMineOnThisCell(String name) {
        setTextMessage(messagesForUI.messageOfExistenceOpponentsMineOnThisCell().formatted(name));
    }

    @Override
    public void messageOfShipState(String name, String whatIsLocated) {
        setTextMessage(messagesForUI.messageOfShipState().formatted(name, whatIsLocated));
    }

    @Override
    public void messageOfMarkedCell(String name) {
        setTextMessage(messagesForUI.messageOfMarkedCell().formatted(name));
    }

    @Override
    public void messageOfEmptyCell(String name) {
        setTextMessage(messagesForUI.messageOfEmptyCell().formatted(name));
    }

    @Override
    public void messageOfEntryOfOpponentsMineOrMinesweeper(String name, String what, String what2) {
        setTextMessage(messagesForUI.messageOfEntryOfOpponentsMineOrMinesweeper().formatted(name, what, what2));
    }


    @Override
    public void messageThatYouAreALiar(String name, String what) {
        setTextMessage(messagesForUI.messageThatYouAreALiar().formatted(name, what));
    }

    @Override
    public void messageOfEntryOfSubmarine(String name) {
        setTextMessage(messagesForUI.messageOfEntryOfSubmarine().formatted(name));
    }

    @Override
    public void messageOfWhoseField(String name) {

    }

    @Override
    public void messageOfFinish(String name) {
        JOptionPane.showMessageDialog(null, messagesForUI.messageOfFinish().formatted(name), "WINNER!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void print(Player player) {

    }

    public void setTextMessage(String message) {
        textMessage.setText(message+ "\n");
        repaint();
    }

    public void setTextMessage(JTextArea textMessage) {
        this.textMessage = textMessage;
    }

    private String[] separation(String coord) {
        String[] ans = new String[]{String.valueOf(coord.charAt(0)), null};
        String a = "";

        for(int i = 1; i < coord.length(); ++i) {
            a = a + coord.charAt(i);
        }

        ans[1] = a;
        return ans;
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
