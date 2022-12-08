package cs.vsu.ru.Korobeynikova_A_V.ui;


import java.util.Locale;
import java.util.ResourceBundle;



public class MessagesForUI{

    private String language = "en";
    private String country = "US";
    private Locale currentLocale = new Locale(language, country);
    private ResourceBundle messages = ResourceBundle.getBundle("message", currentLocale);


    public String setYourName() {
        return messages.getString("setYourName");
    }

    public String messageOfPlayersReady() {
        return messages.getString("messageOfPlayersReady");
    }

    public String messageOfGetCoordinates() {
        return messages.getString("messageOfGetCoordinates");
    }

    public String getCoordinates() {
        return messages.getString("getCoordinates");
    }

    public String messageOfWrongNumberOrLetter() {
        return messages.getString("messageOfWrongNumberOrLetter");
    }

    public String messageWhereDidMove() {
        return messages.getString("messageWhereDidMove");
    }

    public String decisionOfPlacementFigures() {
        return messages.getString("decisionOfPlacementFigures");
    }

    public String messageOfRandomPlacementFigures() {
        return messages.getString("messageOfRandomPlacementFigures");
    }

    public String decisionOfShipOrientation() {
        return messages.getString("decisionOfShipOrientation");
    }

    public String messageOfWhoseParty() {
        return messages.getString("messageOfWhoseParty");
    }

    public String messageOfNotYourParty(){
        return messages.getString("messageOfNotYourParty");
    }

    public String decisionOfUsingOpponentsPartOfTheShip() {
        return messages.getString("decisionOfUsingOpponentsPartOfTheShip");
    }

    public String messageOfExistenceOpponentsMineOnThisCell() {
        return messages.getString("messageOfExistenceOpponentsMineOnThisCell");
    }

    public String messageOfShipState() {
        return messages.getString("messageOfShipState");
    }

    public String messageOfMarkedCell() {
        return messages.getString("messageOfMarkedCell");
    }

    public String messageOfEmptyCell() {
        return messages.getString("messageOfEmptyCell");
    }

    public String messageOfEntryOfOpponentsMineOrMinesweeper() {
        return messages.getString("messageOfEntryOfOpponentsMineOrMinesweeper");
    }

    public String messageThatYouAreALiar() {
        return messages.getString("messageThatYouAreALiar");
    }

    public String messageOfEntryOfSubmarine() {
        return messages.getString("messageOfEntryOfSubmarine");
    }

    public String messageOfWhoseField() {
        return messages.getString("messageOfWhoseField");
    }

    public String messageOfFinish() {
        return messages.getString("messageOfFinish");
    }
}
