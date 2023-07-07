package traffic;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
  private static final Scanner SCAN = new Scanner(System.in);

  private static QueueThread queueThread;

  private static Road[] roadArray;
  public static void main(String[] args){
    System.out.println("Welcome to the traffic management system!");

    System.out.print("Input the number of roads: ");
    int roads = getValidNumber();
    System.out.print("Input the interval: ");
    int interval = getValidNumber();

    roadArray = new Road[roads];
    queueThread = new QueueThread("QueueThread", roads, interval);
    queueThread.start();

    showMenu();
  }

  private static int getValidNumber() {
    int num = 0;
    while (num <= 0) {
      try {
        num = Integer.parseInt(SCAN.nextLine());
        if (num <= 0) {
          throw new NumberFormatException();
        }
      } catch (RuntimeException ex) {
        System.out.print("Error! Incorrect input. Try again: ");
      }
    }
    return num;
  }

  private static void showMenu() {
    String userOption;
    do {
      clearMenu();
      System.out.println("""
              Menu:
              1. Add
              2. Delete
              3. System
              0. Quit""");

      userOption = SCAN.nextLine();

      switch (userOption) {
        case "1" -> addNewRoad();
        case "2" -> System.out.println("Road deleted");
        case "3" -> showSystemState();
        case "0" -> {
          System.out.println("Bye!");
          queueThread.setKeepRunning(false);
        }
        default -> System.out.println("Incorrect option");
      }
      if (!"0".equals(userOption)) {
        SCAN.nextLine();
        queueThread.setPrintInfo(false);
      }
    } while (!"0".equals(userOption));
  }

  private static void addNewRoad() {
    if (Arrays.stream(roadArray).noneMatch(Objects::isNull)) {
      System.out.println("Queue is full");
    } else {
      System.out.print("Input road name: ");
      String roadName = SCAN.nextLine();

      Road road = new Road(roadName);
      road.setRear(true);
      if (Arrays.stream(roadArray).allMatch(Objects::isNull)) {
        road.setFront(true);
        roadArray[0] = road;
      } else {
        for (int i = 0; i < roadArray.length; i++) {
          if (roadArray[i] != null && roadArray[i].isRear()) {
            roadArray[i].setRear(false);
            int nextIndex = (i + 1) % roadArray.length;
            roadArray[nextIndex] = road;
            break;
          }
        }
      }

      System.out.println(roadName + " Added!");
//      System.out.println(Arrays.toString(roadArray));
    }
  }

  private static void showSystemState() {
      queueThread.setPrintInfo(true);
  }

  public static void clearMenu() {
    try {
      var clearCommand = System.getProperty("os.name").contains("Windows")
              ? new ProcessBuilder("cmd", "/c", "cls")
              : new ProcessBuilder("clear");
      clearCommand.inheritIO().start().waitFor();
    }
    catch (IOException | InterruptedException ignored) {}
  }
}
