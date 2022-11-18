package cs.vsu.ru.Korobeynikova_A_V.ui;


public class MessagesForUI{ // todo добавить в консоль уи и в гуи

    public String setYourName() {
        return "Игрок %s, введите ваше имя";
    }

    public String messageOfPlayersReady() {
        return "Оба игрока готовы к бою";
    }

    public String messageOfGetCoordinates() {
        return "Игрок %s, введите координаты %s";
    }

    public String getCoordinates() {
        return null;
    }

    public String messageOfWrongNumberOrLetter() {
        return "Игрок %s, ваш ввод неверный или расстановка невозможна";
    }

    public String messageWhereDidMove() {
        return "Игрок %s походил по горизонтали на %s и по вертикали на %s";
    }

    public String decisionOfPlacementFigures() {
        return "Игрок %s, вы хотите случайную расстановку(введите 0) или желаете самостоятельно расставить корабли(введите 1)? ";
    }

    public String messageOfRandomPlacementFigures() {
        return "Игрок %s, ваша случайная расстановка кораблей: ";
    }

    public String decisionOfShipOrientation() {
        return "Игрок %s, клеточный корабль вертикальный(введите 0) или горизонтальный(введите 1)?  ";
    }

    public String messageOfWhoseParty() {
        return "Игрок %s, делайте ход. ";
    }

    public String decisionOfUsingOpponentsPartOfTheShip() {
        return "Игрок %s, желаете ли вы воспользоваться координатами части корабля противника? (0 - нет, 1 - да)";
    }

    public String messageOfExistenceOpponentsMineOnThisCell() {
        return "Игрок %s, вы знаете, что там стоит мина противника, выберите другую клетку:";
    }

    public String messageOfShipState() {
        return "Игрок %s %s корабль другого игрока";
    }

    public String messageOfMarkedCell() {
        return "Игрок %s, эта зона уже поражена";
    }

    public String messageOfEmptyCell() {
        return "Игрок %s, вы попали мимо.";
    }

    public String messageOfEntryOfOpponentsMineOrMinesweeper() {
        return "Игрок %s, вы попали на %s! :( Введите координаты клетки %s";
    }

    public String messageThatYouAreALiar() {
        return "Игрок %ы, не обманывайте, там у вас нет %s, введите координаты заново!";
    }

    public String messageOfEntryOfSubmarine() {
        return "Игрок %s, вы попали в подводную лодку ! Ожидайте выстрела на ваше поле!";
    }

    public String messageOfWhoseField() {
        return "Поле игрока %s: ";
    }

    public String messageOfFinish() {
        return "Игрок %s выиграл!!!!!!";
    }

    public String print() {
        return null;
    }
}
