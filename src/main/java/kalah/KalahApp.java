package kalah;

import java.util.Scanner;

public class KalahApp {
  static Scanner input = new Scanner(System.in);

  public static void main(String[] args) {
    KalahService game = new KalahService();
    System.out.print("Enter amount of stones for plot:");
    game.populateBoard(input.nextInt());
    input.nextLine();
    System.out.print("Enter player one name: ");
    game.player1.setName(input.nextLine());
    System.out.print("Enter player two name:");
    game.player2.setName(input.nextLine());
    System.out.printf("Good Luck!%n%n");
    game.player1.setTurn(true);
    game.showGameTable();
    game.moveStones(game.chooseSlot());
  }
}