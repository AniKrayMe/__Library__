package controller;

import services.LibraryService;

import java.util.Scanner;

public class LibraryController {

    static LibraryController instance = new LibraryController();

    private LibraryController() {
    }

    public static void findAllBooksByState(){
        LibraryService libraryService = LibraryService.getInstance();
        String state = "NOT_TAKEN";
        System.out.println(libraryService.findAllBooksByState(state));
    }

    public static void takeBook() {
        LibraryService libraryService = LibraryService.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please print book id");
        int bookID = scanner.nextInt();
        if (libraryService.checkStateByBookID(bookID)) {
            System.out.println("Please print customer id");
            int customerId = scanner.nextInt();
            libraryService.takeBook(customerId, bookID);
        } else {
            System.out.println("At this moment this book has taken");
        }
    }

    public static void returnBook() {
        LibraryService libraryService = LibraryService.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please print book id");
        int bookId = scanner.nextInt();
        System.out.println("Please print customer id");
        int customerId = scanner.nextInt();
        libraryService.returnBook(bookId, customerId);
    }
}