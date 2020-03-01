package services;

import constant.MariaDBConstant;
import book.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookService {

    private static BookService objectRefrense = null;

    private BookService() {
    }

    public static BookService getInstance() {
        if (objectRefrense == null) {
            objectRefrense = new BookService();
            return objectRefrense;
        } else {
            return objectRefrense;
        }
    }


    public void create(Book book) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "INSERT INTO Books(name, description, number_of_pages, state) VALUES(?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getDescription());
                preparedStatement.setInt(3, book.getNumberOfPages());
                preparedStatement.setString(4, book.getState());
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public ArrayList<Book> findAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book();
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "SELECT * FROM Books";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    book.setName(resultSet.getString("name"));
                    book.setDescription(resultSet.getString("description"));
                    book.setNumberOfPages(resultSet.getInt("number_of_pages"));
                    book.setState(resultSet.getString("sate"));
                    books.add(book);
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return books;
    }
    public void updateBookById(int id, Book book) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "UPDATE Books SET name = ?,description = ?,number_of_pages = ?,state = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getDescription());
                preparedStatement.setInt(3, book.getNumberOfPages());
                preparedStatement.setString(4, book.getState());
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void deleteBookById(int id) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "DELETE FROM Authors WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}
