package cs.vsu.ru.Korobeynikova_A_V;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //вводим имена
        System.out.println("1 игрок, введите Ваше имя: ");
        String name1 = scanner.nextLine();
        System.out.println("2 игрок, введите Ваше имя: ");
        String name2 = scanner.nextLine();

        Game game = new Game();
        game.game();
    }
}
