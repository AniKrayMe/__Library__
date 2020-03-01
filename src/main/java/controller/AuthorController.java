package controller;

import author.Author;
import services.AuthorService;

import java.util.Scanner;

public class AuthorController {


    private static final AuthorController instance = new AuthorController();
    private AuthorService authorService = AuthorService.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private AuthorController() {
    }

    private void createAuthor() {
        System.out.println("Please write author name and surename");
        String name = scanner.nextLine();
        String surname = scanner.nextLine();
        authorService.createAuthor(new Author(name, surname));
    }
    private void showAllAuthors() {
        authorService.findAllAuthors();
    }
    private void updateAuthorByID() {
        System.out.println("Please write author id");
        int id = scanner.nextInt();
        System.out.println("Please write author new name");
        String name = scanner.nextLine();
        System.out.println("Please write author new surename");
        String surname = scanner.nextLine();
        authorService.updateAuthorById(id, new Author(name, surname));
    }
    private void deleteAuthorByID() {
        System.out.println("Please write author id");
        int id = scanner.nextInt();
        authorService.deleteAuthorById(id);
    }

    public static void authorsMenu() {
        AuthorController authorController = AuthorController.instance;
        int num;
        do {
            System.out.println("----------------------------------------");
            System.out.println("--------------Authors Menu--------------");
            System.out.println("---------1  create author---------------");
            System.out.println("---------2  select all authors----------");
            System.out.println("---------3  update author---------------");
            System.out.println("---------4  delete author---------------");
            System.out.println("---------5  return menu-----------------");
            System.out.println("----------------------------------------");

            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
            String test = scanner.nextLine();
            switch (num) {
                case 1:
                    authorController.createAuthor();
                    break;
                case 2:
                    authorController.showAllAuthors();
                    break;
                case 3:
                    authorController.updateAuthorByID();
                    break;
                case 4:
                    authorController.deleteAuthorByID();
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

