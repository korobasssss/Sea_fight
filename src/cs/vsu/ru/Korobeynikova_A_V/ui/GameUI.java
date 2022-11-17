package cs.vsu.ru.Korobeynikova_A_V.ui;

import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;

public interface GameUI {

    String setYourName(String who);

    void messageOfPlayersReady();

    void messageOfGetCoordinates(String name, String whatPlacement);

    Coordinate getCoordinates(String name);

    void messageOfWrongNumberOrLetter(String name);

    void messageWhereDidMove(String name, Coordinate coordinate);

    String decisionOfPlacementFigures(String name);

    void messageOfRandomPlacementFigures(String name);

    String decisionOfShipOrientation(String name, String type);

    void messageOfWhoseParty(String name);

    String decisionOfUsingOpponentsPartOfTheShip(String name);

    void messageOfExistenceOpponentsMineOnThisCell(String name);

    void messageOfShipState(String name, String whatIsLocated);

    void messageOfMarkedCell(String name);

    void messageOfEmptyCell(String name);

    void messageOfEntryOfOpponentsMineOrMinesweeper(String name, String what, String what2);

    void messageThatYouAreALiar(String name, String what);

    void messageOfEntryOfSubmarine(String name);

    void messageOfWhoseField(String name);

    void messageOfFinish(String name);

    void print(Cell[][] arr, String who);
}
