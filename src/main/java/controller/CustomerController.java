package controller;

import customer.Customer;
import services.CustomerService;

import java.util.Scanner;

public class CustomerController {

    private static final CustomerController instance = new CustomerController();
    private CustomerService customerService = CustomerService.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private CustomerController() {
    }

    private void createCustomer() {
        System.out.println("Please print customer name");
        String name = scanner.nextLine();
        System.out.println("Please print customer surname");
        String surname = scanner.nextLine();
        System.out.println("Please print customer password");
        String password = scanner.nextLine();
        customerService.create(new Customer(name, surname, password));
    }

    private void selectAllCustomers() {
        customerService.findAllCustomers();
    }
    private void updateCustomerByID() {
        System.out.println("Please print customer id");
        int id = scanner.nextInt();
        System.out.println("Please print customer new name");
        String name = scanner.nextLine();
        System.out.println("Please print customer new surname");
        String surname = scanner.nextLine();
        System.out.println("Please print customer new password");
        String password = scanner.nextLine();
        customerService.updateCustomerById(id, new Customer(name, surname, password));
    }

    private void deleteCustomerByID() {
        System.out.println("Please print customer id");
        int id = scanner.nextInt();
        customerService.deleteCustomerById(id);
    }

    private void login() {
        System.out.println("Please print customer id");
        int id = scanner.nextInt();
        System.out.println("Please print customer password");
        String password = scanner.nextLine();
        if (customerService.login(id, password)) {
            System.out.println("You are login");
        } else {
            System.out.println("INCORRECT password or username");
        }
    }

    public static void customersMenu() {
        CustomerController customerController = CustomerController.instance;
        int num;
        do {
            System.out.println("----------------------------------------");
            System.out.println("---------------Book Menu----------------");
            System.out.println("---------1  create book-----------------");
            System.out.println("---------2  select all book-------------");
            System.out.println("---------3  update book-----------------");
            System.out.println("---------4  delete book-----------------");
            System.out.println("---------5  return menu-----------------");
            System.out.println("---------6  login customer--------------");
            System.out.println("---------7  return menu-----------------");
            System.out.println("----------------------------------------");

            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
            String test = scanner.nextLine();
            switch (num) {
                case 1:
                    customerController.createCustomer();
                    break;
                case 2:
                    customerController.selectAllCustomers();
                    break;

                case 3:
                    customerController.updateCustomerByID();
                    break;
                case 4:
                    customerController.deleteCustomerByID();
                    break;
                case 5:
                    customerController.login();
                    break;
                case 6:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Plese write valid order");
            }
        } while (num != 7);
    }
}