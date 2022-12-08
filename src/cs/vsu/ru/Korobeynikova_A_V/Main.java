package cs.vsu.ru.Korobeynikova_A_V;


import cs.vsu.ru.Korobeynikova_A_V.Figure.AdditionalArrangements;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Figure;
import cs.vsu.ru.Korobeynikova_A_V.Figure.Ship;
import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;
import cs.vsu.ru.Korobeynikova_A_V.ui.ConsoleUI;
import cs.vsu.ru.Korobeynikova_A_V.ui.GameUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

    private static final List<Player> players = new ArrayList<>();

    public static void main(String[] args) {

//        String language = "en";
//        String country = "US";
//        Locale currentLocale = new Locale(language, country);
//        ResourceBundle messages = ResourceBundle.getBundle("message", currentLocale);
//
//        System.out.println(messages.getString("setYourName"));

        GameUI gameUIC = new ConsoleUI();

        Player player1 = new Player(LocalGame.Who.FIRST_PLAYER, "1", new PlayingField(), new ArrayList<>(), new PlayingField());
        players.add(player1);
        Player player2 = new Player(LocalGame.Who.SECOND_PLAYER, "2", new PlayingField(), new ArrayList<>(), new PlayingField());
        players.add(player2);

        Game game = new LocalGame(player1, player2, gameUIC);

        for (Player player : players) {
            Coordinate coordinate = new Coordinate(gameUIC.getCoordinates(player).getVertical(), gameUIC.getCoordinates(player).getHorizontal());
            if (player.getShips().size() < player.getField().getCountShips()) {
                Figure ship = new Ship(coordinate, Ship.Type.ONE_CELL, Ship.Orientation.VERTICAL, Figure.Status.ALIVE);
                game.placementOfFigures(player, ship);
            } else {
                Figure addArg = new AdditionalArrangements(coordinate, Figure.Status.ALIVE);
                game.placementOfFigures(player, addArg);
            }
        }

        Game.Who whoseTurn = Game.Who.FIRST_PLAYER;

        while (game.shipsLifeStatus(player1.getShips()) && game.shipsLifeStatus(player2.getShips())) {
            Coordinate coordinate = new Coordinate(gameUIC.getCoordinates(whoseTurn(whoseTurn)).getVertical(), gameUIC.getCoordinates(whoseTurn(whoseTurn)).getHorizontal());
            if (whoseTurn == Game.Who.FIRST_PLAYER) {
                if (game.getFlagOfUsingOppCell()) game.setFlagOfUsingOppCell(false);
                else if (game.getPlayer2().getField().getCellStatus(coordinate) != Cell.Status.SHIP) {
                    gameUIC.messageOfWhoseParty(game.getPlayer2().getName());
                    whoseTurn = Game.Who.SECOND_PLAYER;
                }
                game.moveOnTheOpponent(game.getPlayer1(), game.getPlayer2(), coordinate);
            } else {
                if (game.getFlagOfUsingOppCell()) game.setFlagOfUsingOppCell(false);
                else if (game.getPlayer1().getField().getCellStatus(coordinate) != Cell.Status.SHIP) {
                    gameUIC.messageOfWhoseParty(game.getPlayer1().getName());
                    whoseTurn = Game.Who.FIRST_PLAYER;
                }
                game.moveOnTheOpponent(game.getPlayer2(), game.getPlayer1(), coordinate);
            }
        }
    }

    private static Player whoseTurn(Game.Who who) {
        if (who == Game.Who.FIRST_PLAYER) return players.get(0);
        return players.get(1);
    }
}
