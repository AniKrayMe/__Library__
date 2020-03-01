package controller;

import book.Book;
import services.BookService;

import java.util.Scanner;

public class BookController {
    private static final BookController instance = new BookController();
    private BookService bookService = BookService.getInstance();
    private Scanner scanner = new Scanner(System.in);

    private BookController(){
    }

    private void createBook() {
        System.out.println("Please print book name");
        String name = scanner.nextLine();
        System.out.println("Please print book description");
        String description = scanner.nextLine();
        System.out.println("Please print number of book pages");
        int pageNumbers = scanner.nextInt();
        System.out.println("Please print book state");
        String state = scanner.nextLine();
        bookService.create(new Book(name,description,pageNumbers,state));
    }

    private void showAllBooks() {
        bookService.findAllBooks();
    }

    private void updateBookByID() {
        System.out.println("Please print book id");
        int id = scanner.nextInt();
        System.out.println("Please print book new name");
        String name = scanner.nextLine();
        System.out.println("Please print book new description");
        String description = scanner.nextLine();
        System.out.println("Please print new number of book pages");
        int pageNumbers = scanner.nextInt();
        System.out.println("Please print book new state");
        String state = scanner.nextLine();
        bookService.updateBookById(id,new Book(name,description,pageNumbers,state));
    }

    private void deleteBookByID() {
        System.out.println("Please print book id");
        int id = scanner.nextInt();
        bookService.deleteBookById(id);
    }

    public static void booksMenu() {
        BookController bookController = BookController.instance;
        int num;
        do {
            System.out.println("----------------------------------------");
            System.out.println("---------------Book Menu----------------");
            System.out.println("---------1  create book-----------------");
            System.out.println("---------2  select all book-------------");
            System.out.println("---------3  update book-----------------");
            System.out.println("---------4  delete book-----------------");
            System.out.println("---------5  return menu-----------------");
            System.out.println("----------------------------------------");

            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
            String test = scanner.nextLine();
            switch (num) {
                case 1:
                    bookController.createBook();
                    break;
                case 2:
                    bookController.showAllBooks();
                    break;
                case 3:
                    bookController.updateBookByID();
                    break;
                case 4:
                    bookController.deleteBookByID();
                    break;
                case 5:
                    System.out.println("GOODBYE");
                    break;
                default:
                    System.out.println("INCORRECT INPUT");
            }
        } while (num != 6);
    }
}
