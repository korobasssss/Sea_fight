package cs.vsu.ru.Korobeynikova_A_V.ui;

import cs.vsu.ru.Korobeynikova_A_V.Player;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import javax.swing.*;
import java.util.Scanner;

public class ConsoleUI implements GameUI{

    Scanner scanner = new Scanner(System.in);
    MessagesForUI messagesForUI = new MessagesForUI();

    @Override
    public void messageOfPlayersReady() {
        emptyLine();
        System.out.print(messagesForUI.messageOfPlayersReady());
    }

    @Override
    public void messageOfGetCoordinates(Player player) {
        emptyLine();
        if (player.getShips().size() >= player.getField().getOneCellShip()
                + player.getField().getTwoCellShip()
                + player.getField().getThreeCellShip()
                + player.getField().getFourCellShip()) {
            if (player.getMinesweepers().size() == player.getField().getCountMinesweepers()) System.out.println(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "подлодки"));
            else if (player.getMines().size() == player.getField().getCountMines()) System.out.println(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "минного тральщика"));
            else System.out.println(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "мины"));
        }

        else if (player.getShips().size() >= player.getField().getOneCellShip()
                + player.getField().getTwoCellShip()
                + player.getField().getThreeCellShip()) {
            System.out.println(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "4 клеточного"));
        }

        else if (player.getShips().size() >= player.getField().getOneCellShip()
                + player.getField().getTwoCellShip()) {
            System.out.println(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "3 клеточного"));
        }

        else if (player.getShips().size() >= player.getField().getOneCellShip()) {
            System.out.println(messagesForUI.messageOfGetCoordinates().formatted(player.getName(), "2 клеточного"));
        }
    }

    @Override
    public Coordinate getCoordinates(Player player) {
        emptyLine();
        System.out.println("По горизонтали: ");
        String col = scanner.nextLine();
        int intCol = changeToInt(col);
        while (intCol < 0 || intCol > PlayingField.getSize()) {
            messageOfWrongNumberOrLetter(player.getName());
            emptyLine();
            col = scanner.nextLine();
            intCol = changeToInt(col);
        }
        System.out.println("По вертикали: ");
        String row = scanner.nextLine();
        while (!isNumeric(row) || (isNumeric(row) && (Integer.parseInt(row) < 0 || Integer.parseInt(row) > PlayingField.getSize()))) {
            messageOfWrongNumberOrLetter(player.getName());
            emptyLine();
            row = scanner.nextLine();
        }
        emptyLine();

        return new Coordinate(Integer.parseInt(row) - 1, intCol - 1);
    }

    @Override
    public void messageOfWrongNumberOrLetter(String name) {
        emptyLine();
        System.out.print(messagesForUI.messageOfWrongNumberOrLetter().formatted(name));
    }

    @Override
    public void messageWhereDidMove(String name, Coordinate coordinate) {
        emptyLine();
        System.out.print(messagesForUI.messageWhereDidMove().formatted(name, coordinate.getHorizontal() + 1, coordinate.getVertical() + 1));
    }

    @Override
    public String decisionOfPlacementFigures(String name) {
        emptyLine();
        System.out.print(messagesForUI.decisionOfPlacementFigures().formatted(name));
        return scanner.nextLine();
    }

    @Override
    public void messageOfRandomPlacementFigures(String name) {
        emptyLine();
        System.out.print(messagesForUI.messageOfRandomPlacementFigures().formatted(name));
    }

    @Override
    public String decisionOfShipOrientation(String name, String type) {
        emptyLine();
        System.out.print(messagesForUI.decisionOfShipOrientation().formatted(name, type));
        return scanner.nextLine();
    }

    @Override
    public void messageOfWhoseParty(String name) {
        emptyLine();
        System.out.print(messagesForUI.messageOfWhoseParty().formatted(name));
    }

    @Override
    public void messageOfNotYourParty(String name) {
        System.out.println(messagesForUI.messageOfNotYourParty().formatted(name));
    }

    @Override
    public Integer decisionOfUsingOpponentsPartOfTheShip(String name) {
        emptyLine();
        System.out.print(messagesForUI.decisionOfUsingOpponentsPartOfTheShip().formatted(name));
        String str = scanner.nextLine();
        while (Integer.parseInt(str) != 0 && Integer.parseInt(str) != 1) {
            messageOfWrongNumberOrLetter(name);
            str = scanner.nextLine();;
        }
        return Integer.parseInt(str);
    }

    @Override
    public void messageOfExistenceOpponentsMineOnThisCell(String name) {
        emptyLine();
        System.out.println(messagesForUI.messageOfExistenceOpponentsMineOnThisCell());
    }

    @Override
    public void messageOfShipState(String name, String whatIsLocated) {
        emptyLine();
        System.out.print(messagesForUI.messageOfShipState().formatted(name, whatIsLocated));
    }

    @Override
    public void messageOfMarkedCell(String name) {
        emptyLine();
        System.out.print(messagesForUI.messageOfMarkedCell().formatted(name));
    }

    @Override
    public void messageOfEmptyCell(String name) {
        emptyLine();
        System.out.print(messagesForUI.messageOfEmptyCell().formatted(name));
    }

    @Override
    public void messageOfEntryOfOpponentsMineOrMinesweeper(String name, String what, String what2) {
        emptyLine();
        System.out.print(messagesForUI.messageOfEntryOfOpponentsMineOrMinesweeper().formatted(name, what, what2));
    }

    @Override
    public void messageThatYouAreALiar(String name, String what) {
        emptyLine();
        System.out.print(messagesForUI.messageThatYouAreALiar().formatted(name, what));
    }

    @Override
    public void messageOfEntryOfSubmarine(String name) {
        emptyLine();
        System.out.print(messagesForUI.messageOfEntryOfSubmarine().formatted(name));
    }

    @Override
    public void messageOfWhoseField(String name) {
        System.out.print(messagesForUI.messageOfWhoseField().formatted(name));
    }

    @Override
    public void messageOfFinish(String name) {
        emptyLine();
        System.out.print(messagesForUI.messageOfFinish().formatted(name));
    }

    @Override
    public void print(Player player) {
        emptyLine();
        System.out.println();
        for (Cell[] cells : player.getField().getField()) {
            for (int col = 0; col < player.getField().getField().length; col++) {
                System.out.print(cells[col].getVisual());
            }
            System.out.println();
        }
        for (Cell[] cells : player.getOpponentsField().getField()) {
            for (int col = 0; col < player.getOpponentsField().getField().length; col++) {
                System.out.print(cells[col].getVisual());
            }
            System.out.println();
        }
    }

    private void emptyLine() {
        System.out.println();
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
