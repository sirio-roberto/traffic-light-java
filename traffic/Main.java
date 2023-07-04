package traffic;

public class Main {
  public static void main(String[] args){
    System.out.println("Welcome to the traffic management system!");
    showMenu();
  }

  private static void showMenu() {
    System.out.println("""
            Menu:
            1. Add
            2. Delete
            3. System
            0. Quit""");
  }
}
