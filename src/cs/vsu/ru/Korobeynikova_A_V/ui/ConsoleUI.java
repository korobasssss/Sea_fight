package cs.vsu.ru.Korobeynikova_A_V.ui;

import cs.vsu.ru.Korobeynikova_A_V.field.Cell;
import cs.vsu.ru.Korobeynikova_A_V.field.Coordinate;
import cs.vsu.ru.Korobeynikova_A_V.field.PlayingField;

import java.util.Scanner;

public class ConsoleUI implements GameUI{

    Scanner scanner = new Scanner(System.in);

    @Override
    public String setYourName(String who) {
        emptyLine();
        System.out.printf("Игрок %s, введите ваше имя : ", who);
        return scanner.nextLine();
    }

    @Override
    public void messageOfPlayersReady() {
        emptyLine();
        System.out.println("Оба игрока готовы к бою");
    }

    @Override
    public void messageOfGetCoordinates(String name, String whatPlacement) {
        emptyLine();
        System.out.printf("Игрок %s, введите координаты %s: ", name, whatPlacement);
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
            System.out.println("По вертикали: ");
            row = scanner.nextLine();
        }
        System.out.println();

        return new Coordinate(Integer.parseInt(row) - 1, intCol - 1);
    }

    @Override
    public void messageOfWrongNumberOrLetter(String name) {
        emptyLine();
        System.out.printf("Игрок %s, Ваш ввод неверный или расстановка невозможна", name);
    }

    @Override
    public void messageWhereDidMove(String name, Coordinate coordinate) {
        emptyLine();
        System.out.printf("Игрок %s походил по горизонтали на %d и по вертикали на %d.", name, coordinate.getHorizontal() + 1, coordinate.getVertical() + 1);
    }

    @Override
    public String decisionOfPlacementFigures(String name) {
        emptyLine();
        System.out.printf("Игрок %s , Вы хотите случайную расстановку(0) или желаете самостоятельно расставить корабли(1)? ", name);
        return scanner.nextLine();
    }

    @Override
    public void messageOfRandomPlacementFigures(String name) {
        emptyLine();
        System.out.printf("Игрок %s, ваша случайная расстановка кораблей: ", name);
    }

    @Override
    public String decisionOfShipOrientation(String name, String type) {
        emptyLine();
        System.out.printf("Игрок %s, %s клеточный корабль вертикальный(0) или горизонтальный(1)? ", name, type);
        return scanner.nextLine();
    }

    @Override
    public void messageOfWhoseParty(String name) {
        emptyLine();
        System.out.printf("Игрок %s делайте ход.", name);
    }

    @Override
    public String decisionOfUsingOpponentsPartOfTheShip(String name) {
        emptyLine();
        System.out.printf("Игрок %s, желаете ли вы воспользоваться координатами части корабля противника? (0 - нет, 1 - да", name);
        return scanner.nextLine();
    }

    @Override
    public void messageOfExistenceOpponentsMineOnThisCell(String name) {
        emptyLine();
        System.out.println("Вы знаете, что там стоит мина противника, выберите другую клетку: ");
    }

    @Override
    public void messageOfShipState(String name, String whatIsLocated) {
        emptyLine();
        System.out.printf("Игрок %s %s корабль другого игрока", name, whatIsLocated);
    }

    @Override
    public void messageOfMarkedCell(String name) {
        emptyLine();
        System.out.printf("Игрок %s, эта зона уже поражена.", name);
    }

    @Override
    public void messageOfEmptyCell(String name) {
        emptyLine();
        System.out.printf("Игрок %s, вы попали мимо.", name);
    }

    @Override
    public void messageOfEntryOfOpponentsMineOrMinesweeper(String name, String what, String what2) {
        emptyLine();
        System.out.printf("Игрок %s, вы попали на %s ! :( Введите координаты клетки %s! ", name, what, what2);
    }

    @Override
    public void messageThatYouAreALiar(String name, String what) {
        emptyLine();
        System.out.printf("Игрок %s, не обманывайте, там у вас нет %s, введите координаты заново! ", name, what);
    }

    @Override
    public void messageOfEntryOfSubmarine(String name) {
        emptyLine();
        System.out.printf("Игрок %s, вы попали в подводную лодку ! Ожидайте выстрела на ваше поле! ", name);
    }

    @Override
    public void messageOfWhoseField(String name) {
        emptyLine();
        System.out.printf("Поле игрока %s", name);
    }

    @Override
    public void messageOfFinish(String name) {
        emptyLine();
        System.out.printf("Победил игрок %s", name);
    }


    @Override
    public void print(Cell[][] arr) {
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
