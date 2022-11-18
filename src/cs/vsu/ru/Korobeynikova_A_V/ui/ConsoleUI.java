package cs.vsu.ru.Korobeynikova_A_V.ui;

import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import java.util.Scanner;

public class ConsoleUI implements GameUI{

    Scanner scanner = new Scanner(System.in);
    MessagesForUI messagesForUI = new MessagesForUI();

    @Override
    public String setYourName(String who) {
        emptyLine();
        System.out.print(messagesForUI.setYourName().formatted(who));
        return scanner.nextLine();
    }

    @Override
    public void messageOfPlayersReady() {
        emptyLine();
        System.out.print(messagesForUI.messageOfPlayersReady());
    }

    @Override
    public void messageOfGetCoordinates(String name, String whatPlacement) {
        emptyLine();
        System.out.print(messagesForUI.messageOfGetCoordinates().formatted(name, whatPlacement));
    }

    @Override
    public Coordinate getCoordinates(String name) {
        emptyLine();
        System.out.println("По горизонтали: ");
        String col = scanner.nextLine();
        int intCol = changeToInt(col);
        while (intCol < 0 || intCol > PlayingField.getSize()) {
            messageOfWrongNumberOrLetter(name);
            emptyLine();
            col = scanner.nextLine();
            intCol = changeToInt(col);
        }
        System.out.println("По вертикали: ");
        String row = scanner.nextLine();
        while (!isNumeric(row) || (isNumeric(row) && (Integer.parseInt(row) < 0 || Integer.parseInt(row) > PlayingField.getSize()))) {
            messageOfWrongNumberOrLetter(name);
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
    public String decisionOfUsingOpponentsPartOfTheShip(String name) {
        emptyLine();
        System.out.print(messagesForUI.decisionOfUsingOpponentsPartOfTheShip().formatted(name));
        return scanner.nextLine();
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
        System.out.print(messagesForUI.messageOfEmptyCell());
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
    public void print(Cell[][] arr, String who) {
        emptyLine();
        System.out.println();
        for (Cell[] cells : arr) {
            for (int col = 0; col < arr.length; col++) {
                System.out.print(cells[col].getVisual());
            }
            System.out.println();
        }
        System.out.println();
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
