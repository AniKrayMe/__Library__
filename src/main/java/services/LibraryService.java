package services;

import constant.MariaDBConstant;
import book.Book;

import java.sql.*;
import java.util.ArrayList;

public class LibraryService {
    private static LibraryService objectRefrense = null;

    private LibraryService() {
    }

    public static LibraryService getInstance() {
        if (objectRefrense == null) {
            objectRefrense = new LibraryService();
            return objectRefrense;
        } else {
            return objectRefrense;
        }
    }

    public boolean checkStateByBookID(int id) {
        Book book = new Book();
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "SELECT state From books WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    book.setState(resultSet.getString("state"));
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return book.getState().equals("NOT_TAKEN");
    }

    public ArrayList<Book> findAllBooksByState(String state) {
        Book book = new Book();
        ArrayList<Book> books = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "SELECT * From books WHERE state = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, state);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    book.setState(resultSet.getString("state"));
                    books.add(book);
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return books;
    }

    private void createOrders(int customerId, int bookId) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "INSERT INTO Orders(return_date,customer_id,book_id)VALUES(DATE_ADD(now(),INTERVAL 30 DAY),?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, customerId);
                preparedStatement.setInt(2, bookId);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }


    }

    private void updateTakenBooksById(int bookId) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "UPDATE Books SET state = 'TAKEN' WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, bookId);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createArchive(int customerId, int bookId) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            String query = "INSERT INTO Archive(customer_id,book_id)VALUES(?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void takeBook(int customerId, int bookId) {
        createOrders(customerId, bookId);
        createArchive(customerId, bookId);
        updateTakenBooksById(bookId);
    }

    private void updateReturnBooks(int bookId) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "UPDATE Books SET state = 'NOT_TAKEN' WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, bookId);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void deleteOrder(int customerId) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "DELETE FROM Orders WHERE customer_id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, customerId);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void returnBook(int bookId, int customerId) {
        LibraryService libraryService = LibraryService.getInstance();
        libraryService.updateReturnBooks(bookId);
        libraryService.deleteOrder(customerId);
    }
}