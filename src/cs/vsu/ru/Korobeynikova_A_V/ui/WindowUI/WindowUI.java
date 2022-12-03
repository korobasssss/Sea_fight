package cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI;

import cs.vsu.ru.Korobeynikova_A_V.LocalGame;
import cs.vsu.ru.Korobeynikova_A_V.Player;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.MessagesForUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.CellRenderer;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.FieldTableModel;
import cs.vsu.ru.Korobeynikova_A_V.ui.WindowUI.TableModel.GetCoordFromTable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class WindowUI extends JFrame implements GameUI {

    MessagesForUI messagesForUI = new MessagesForUI();
    GetCoordFromTable getCoordFromTable = new GetCoordFromTable();
    ArrayList<FieldTableModel> models;

    ArrayList<JTable> tables = new ArrayList<>();
    JTextArea textMessage;
    Coordinate coordinate = new Coordinate(-1, -1);

    public WindowUI(ArrayList<FieldTableModel> models, JTextArea areaForMessage) {
        this.models = models;
        this.textMessage = areaForMessage;
    }

    @Override
    public String setYourName(LocalGame.Who who) {
        String str = "";
        if (who == LocalGame.Who.FIRST_PLAYER)  str = "1";
        if (who == LocalGame.Who.SECOND_PLAYER)  str = "2";
        return JOptionPane.showInputDialog(new JFrame(), messagesForUI.setYourName().formatted(str));
    }

    @Override
    public void messageOfPlayersReady() {
        setTextMessage(messagesForUI.messageOfPlayersReady());
    }

    @Override
    public void messageOfGetCoordinates(String name, String whatPlacement) {
        setTextMessage(messagesForUI.messageOfGetCoordinates().formatted(name, whatPlacement));
    }

    @Override
    public Coordinate getCoordinates(Player player) {
        getCoordFromTable.setTable(getTable(player.getOppNumber()));



        getCoordFromTable.setStatus(false);







//        String coord = JOptionPane.showInputDialog(new JFrame(), messagesForUI.getCoordinates().formatted(name));
//        while (Objects.equals(coord, null)) {
//            this.messageOfWrongNumberOrLetter(name);
//            coord = JOptionPane.showInputDialog(new JFrame(), messagesForUI.getCoordinates().formatted(name));
//        }
//        String col = separation(coord)[0];
//        int intCol = changeToInt(col)-1;
//        while(intCol <= 0 || intCol > PlayingField.getSize()){
//            this.messageOfWrongNumberOrLetter(name);
//            intCol= changeToInt(separation(JOptionPane.showInputDialog(new JFrame(), messagesForUI.getCoordinates().formatted(name)))[0]);
//        }
//
//        String row = separation(coord)[1];
//
//        while(!isNumeric(row) || (Integer.parseInt(row) <= 0 || Integer.parseInt(row) > PlayingField.getSize())) {
//            this.messageOfWrongNumberOrLetter(name);
//            row = separation(JOptionPane.showInputDialog(new JFrame(), messagesForUI.getCoordinates().formatted(name)))[0];
//        }

        return coordinate;
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
        //setTextMessage(messagesForUI.messageOfWhoseParty().formatted(name));
    }

    @Override
    public String decisionOfUsingOpponentsPartOfTheShip(String name) {
        return JOptionPane.showInputDialog(new JFrame(), messagesForUI.decisionOfUsingOpponentsPartOfTheShip().formatted(name));
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
        //setTextMessage(messagesForUI.messageOfWhoseField().formatted(name));
    }

    @Override
    public void messageOfFinish(String name) {
        JOptionPane.showMessageDialog(null, messagesForUI.messageOfFinish().formatted(name), "WINNER!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void print(Player player, LocalGame.Who who) {
        setTable(player, who);
    }

    private int changeToInt(String symbol) {
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

    private String[] separation(String coord) {
        String[] ans = new String[2];
        ans[0] = String.valueOf(coord.charAt(0));
        String a = "";
        for (int i = 1; i < coord.length(); i++) {
            a += String.valueOf(coord.charAt(i));
        }
        ans[1] = a;
        return ans;
    }

    public void setTextMessage(String message) {
        textMessage.setText(message+ "\n");
        repaint();
    }

    public void setTable(Player player, LocalGame.Who who) {

        switch (who){
            case FIRST_PLAYER -> {
                models.get(0).setField(player.getField().getField());
                tables.get(0).setDefaultRenderer(String.class, new CellRenderer());
            }
            case SECOND_PLAYER -> {
                models.get(1).setField(player.getField().getField());
                tables.get(1).setDefaultRenderer(String.class, new CellRenderer());
            }
            case FIRST_OPP -> {
                models.get(2).setField(player.getOpponentsField().getField());
                tables.get(2).setDefaultRenderer(String.class, new CellRenderer());
            }
            case SECOND_OPP -> {
                models.get(3).setField(player.getOpponentsField().getField());
                tables.get(3).setDefaultRenderer(String.class, new CellRenderer());
            }
        }
        repaint();
    }

    public void setModels(FieldTableModel model) {
        this.models.add(model);
    }

    public void setTable(JTable tables) {
        this.tables.add(tables);
    }

    public ArrayList<JTable> getTables() {
        return tables;
    }

    private JTable getTable(LocalGame.Who who) {
        switch (who){
            case FIRST_PLAYER -> {
                return tables.get(0);
            }
            case SECOND_PLAYER -> {
                return tables.get(1);
            }
            case FIRST_OPP -> {
                return tables.get(2);
            }
            case SECOND_OPP -> {
                return tables.get(3);
            }
        }
        return null;
    }
}
