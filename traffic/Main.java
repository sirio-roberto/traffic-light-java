package traffic;

import java.util.Scanner;

public class Main {
  private static final Scanner SCAN = new Scanner(System.in);
  public static void main(String[] args){
    System.out.println("Welcome to the traffic management system!");

    System.out.print("Input the number of roads: ");
    int roads = Integer.parseInt(SCAN.nextLine());
    System.out.print("Input the interval: ");
    int interval = Integer.parseInt(SCAN.nextLine());

    showMenu();
  }

  private static void showMenu() {
    String userOption;
    do {
      System.out.println("""
              Menu:
              1. Add
              2. Delete
              3. System
              0. Quit""");

      userOption = SCAN.nextLine();

      switch (userOption) {
        case "1" -> System.out.println("Road added");
        case "2" -> System.out.println("Road deleted");
        case "3" -> System.out.println("System opened");
        case "0" -> System.out.println("Bye!");
        default -> System.out.println("Invalid option!");
      }
    } while (!"0".equals(userOption));
  }
}
