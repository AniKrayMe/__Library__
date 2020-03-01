package database;

import constant.MariaDBConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static Database objectRefrense = null;

    private Database() {
    }

    public static Database getInstance() {
        if (objectRefrense == null) {
            objectRefrense = new Database();
            return objectRefrense;
        } else {
            return objectRefrense;
        }
    }

    private void createDatabase() {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "CREATE DATABASE IF NOT EXISTS Library";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }


    private void createBooksTable() {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "use library;" +
                        "CREATE TABLE IF NOT EXISTS Books" +
                        "(" +
                        "id int AUTO_INCREMENT NOT NULL ," +
                        "name VARCHAR(50) NOT NULL ," +
                        "description VARCHAR(50) NOT NULL ," +
                        "number_of_pages int NOT NULL ," +
                        "state ENUM ('NOT_TAKEN','TAKEN') ," +
                        "PRIMARY KEY (id)" +
                        ");";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }


    private void createAuthorsTable() {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "use library;" +
                        "CREATE TABLE if NOT EXISTS Authors" +
                        "(" +
                        "id int AUTO_INCREMENT NOT NULL ," +
                        "name VARCHAR(50) NOT NULL ," +
                        "surname VARCHAR(50) NOT NULL ," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createCustomersTable() {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "use library;" +
                        "CREATE TABLE if NOT EXISTS Customers" +
                        "(" +
                        "id int AUTO_INCREMENT NOT NULL ," +
                        "name VARCHAR(50) NOT NULL ," +
                        "surname VARCHAR(50) NOT NULL ," +
                        "password VARCHAR(50) NOT NULL ," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }




    private void createOrderTable() {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "USE library;" +
                        "CREATE TABLE IF NOT EXISTS Orders" +
                        "(" +
                        "id int AUTO_INCREMENT NOT NULL ," +
                        "return_date DATE," +
                        "customer_id int UNIQUE ," +
                        "book_id int UNIQUE ," +
                        "PRIMARY KEY (id)," +
                        "FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE ," +
                        "FOREIGN KEY (book_id) REFERENCES  books(id) ON DELETE CASCADE " +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createTableBooksAndAuthors() {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "USE library;" +
                        "CREATE TABLE IF NOT EXISTS Books_and_authors" +
                        "(" +
                        "id INT AUTO_INCREMENT NOT NULL ," +
                        "book_id INT," +
                        "author_id INT," +
                        "PRIMARY KEY (id)," +
                        "FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE ," +
                        "FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE " +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createArchiveTable() {
        try (Connection conn = DriverManager.getConnection(MariaDBConstant.DB_URL, MariaDBConstant.user, MariaDBConstant.pass)) {
            if (conn != null) {
                String query = "use library;" +
                        "CREATE TABLE if NOT EXISTS Archive" +
                        "(" +
                        "id int AUTO_INCREMENT NOT NULL ," +
                        "customer_id int UNIQUE ," +
                        "book_id int ," +
                        "PRIMARY KEY (id)," +
                        "FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE ," +
                        "FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE " +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public static void createAndInitialiseDatabase() {

        Database database = Database.getInstance();
        database.createDatabase();
        database.createBooksTable();
        database.createAuthorsTable();
        database.createCustomersTable();
        database.createOrderTable();
        database.createTableBooksAndAuthors();
        database.createArchiveTable();
    }
}
