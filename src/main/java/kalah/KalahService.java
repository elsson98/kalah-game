package kalah;

import java.util.Scanner;

public class KalahService {

  private static final Scanner input = new Scanner(System.in);
  private int[] kalahSlot = new int[14];
  Player player1 = new Player();
  Player player2 = new Player();

  void showGameTable() {
    System.out.print("     ");
    for (int i = 13; i >= 8; i--) {
      System.out.printf("%d ", kalahSlot[i]);
    }
    System.out.printf("%n %d                  %d%n", kalahSlot[0], kalahSlot[7]);
    System.out.print("     ");
    for (int i = 1; i < 7; i++) {
      System.out.printf("%d ", kalahSlot[i]);
    }
  }

  private boolean isGameOver() {
    int p2Slot = 0, p1Slot = 0;
    for (int i = 8; i <= 13; i++) {
      if (kalahSlot[i] == 0) {
        p2Slot++;
      }
    }
    for (int i = 1; i < 7; i++) {
      if (kalahSlot[i] == 0) {
        p1Slot++;
      }
    }
    if (p2Slot == 6 || p1Slot == 6) {
      showTheWinner();
      return true;
    } else {
      return false;
    }
  }

  public void populateBoard(int nrForSlot) {
    for (int i = 0; i < kalahSlot.length; i++) {
      if (i == 0 || i == 7) {
        kalahSlot[i] = 0;
      } else {
        kalahSlot[i] = nrForSlot;
      }
    }
  }

  public int chooseSlot() {
    int slotChosen = 0;
    while (!isGameOver()) {
      try {
        if (player1.isTurn()) {
          System.out.printf("%n%s enter the slot from 1 to 6 : %n", player1.getName());
          slotChosen = Integer.parseInt(input.nextLine());
          if (slotChosen >= 1 && slotChosen <= 6 && kalahSlot[slotChosen] != 0) {
            return slotChosen;
          } else {
            throw new NumberFormatException();
          }
        } else {
          System.out.printf("%n%s enter the slot from 1 to 6 : %n", player2.getName());
          slotChosen = Integer.parseInt(input.nextLine());
          if (slotChosen >= 1 && slotChosen <= 6 && kalahSlot[slotChosen + 7] != 0) {
            return slotChosen + 7;
          } else {
            throw new NumberFormatException();
          }
        }
      } catch (NumberFormatException e) {
        System.out.printf("Slot maybe empty or invalid slot chosen.Try again!%n%n");
      }
    }
    return slotChosen;
  }

  public void moveStones(int firstIndex) {
    int startingPosition = firstIndex;
    while (!isGameOver()) {
      int index = startingPosition;
      if (player1.isTurn()) {
        int currenValueSlotP1 = kalahSlot[startingPosition];
        kalahSlot[startingPosition] = 0;
        for (int i = 0; i < currenValueSlotP1; i++) {
          if (index == 13) {
            index = 0;
          }
          kalahSlot[++index] = kalahSlot[index] + 1;
        }
        if (index == 7) {
          System.out.printf("%nLast one to your point slot choice again:%n%n");
          showGameTable();
          startingPosition = chooseSlot();
        } else if (kalahSlot[index] > 1) {
          startingPosition = index;
        } else {
          showGameTable();
          player1.setTurn(false);
          player2.setTurn(true);
          startingPosition = chooseSlot();
        }
      } else {
        int currenValueSlotP2 = kalahSlot[startingPosition];
        kalahSlot[startingPosition] = 0;
        for (int i = 0; i < currenValueSlotP2; i++) {
          if (index == 13) {
            index = 0;
            kalahSlot[index] = kalahSlot[index] + 1;
          } else if (index == 6) {
            index = index + 2;
            kalahSlot[index] = kalahSlot[index] + 1;
          } else {
            kalahSlot[++index] = kalahSlot[index] + 1;
          }
        }
        if (index == 0) {
          showGameTable();
          System.out.printf("%nLast one to your point slot choice again:%n%n");
          startingPosition = chooseSlot();
        } else if (kalahSlot[index] > 1) {
          startingPosition = index;
        } else {
          showGameTable();
          player2.setTurn(false);
          player1.setTurn(true);
          startingPosition = chooseSlot();
        }
      }
    }
  }

  private void showTheWinner() {
    if (kalahSlot[0] > kalahSlot[7]) {
      System.out.printf("%n%nThe winner is: %s%n%n", player2.getName());
    } else if (kalahSlot[0] < kalahSlot[7]) {
      System.out.printf("%nThe winner is: %s%n%n", player1.getName());
    } else {
      System.out.printf("%nOps...  You are draw!%n%n");
    }
    System.out.printf("Final table points:%n");
    showGameTable();
  }
}