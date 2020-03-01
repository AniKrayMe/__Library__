import database.Database;
import menu.LibraryMenu;

public class Main {
    public static void main(String[] args) {

        Database.createAndInitialiseDatabase();
        LibraryMenu.menu();
    }
}
