package menu;

import controller.AuthorController;
import controller.BookController;
import controller.CustomerController;
import controller.LibraryController;

import java.util.Scanner;

public class LibraryMenu {

    static LibraryMenu instance = new LibraryMenu();

    private LibraryMenu() {
    }

    public static void menu() {
        int num;
        do {
            System.out.println("-------------------------------------");
            System.out.println("----------------Menu-----------------");
            System.out.println("--------1  BOOKS CRUD----------------");
            System.out.println("--------2  AUTHORS CRUD--------------");
            System.out.println("--------3  CUSTOMERS CRUD------------");
            System.out.println("--------4  show all not taken BOOKS--");
            System.out.println("--------5  take BOOK from library----");
            System.out.println("--------6  return the BOOK-----------");
            System.out.println("--------7  exit ---------------------");
            System.out.println("-------------------------------------");
            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
            switch (num) {
                case 1:
                    BookController.booksMenu();
                    break;
                case 2:
                    AuthorController.authorsMenu();
                    break;
                case 3:
                    CustomerController.customersMenu();
                    break;
                case 4:
                    LibraryController.findAllBooksByState();
                    break;
                case 5:
                    LibraryController.takeBook();
                    break;
                case 6:
                    LibraryController.returnBook();
                    break;
                case 7:
                    System.out.println("GOODBYE");
                    break;
                default:
                    System.out.println("INCORRECT INPUT");
            }

        } while (num != 7);
    }
}