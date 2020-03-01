package services;

import constant.MariaDBConstant;
import author.Author;

import java.sql.*;
import java.util.ArrayList;

public class AuthorService {
    private static AuthorService objectRefrense = null;

    private AuthorService() {
    }

    public static AuthorService getInstance() {
        if (objectRefrense == null) {
            objectRefrense = new AuthorService();
            return objectRefrense;
        } else {
            return objectRefrense;
        }
    }

    public void createAuthor(Author author) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass);
            if (conn != null) {
                String query = "INSERT iNTO Authors (name, surename) VALUES (?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getSurname());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Author> findAllAuthors() {
        ArrayList<Author> authors = new ArrayList<>();
        Author author = new Author();
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "SELECT * FROM Authors;";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    author.setName(resultSet.getString("name"));
                    author.setSurname(resultSet.getString("surname"));
                    authors.add(author);
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return authors;
    }


    public void updateAuthorById(int id, Author author) {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "UPDATE Authors SET name = ?, surename = ? WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getSurname());
                preparedStatement.setInt(3, id);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }


    public void deleteAuthorById(int id) {
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
