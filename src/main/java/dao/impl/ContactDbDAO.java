package dao.impl;

import dao.ContactDAO;
import model.Contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDbDAO implements ContactDAO {
    private BufferedReader br;
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/ContactBook";
    private static final String USER = "test";
    private static final String PASSWORD = "test";

    public ContactDbDAO(BufferedReader br) {
        this.br = br;

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS CONTACT(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255) , SURNAME VARCHAR(255), AGE INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void daoSaveContact(Contact contact) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CONTACT(NAME, SURNAME, AGE) VALUES(?,?,?);")) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setInt(3, contact.getAge());
            preparedStatement.execute();
            System.out.println("Create contact! " + contact);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void daoShowContact(String name) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM contact WHERE NAME =" + "'" + name + "'");
            List<Contact> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Contact(
                        rs.getString("NAME"),
                        rs.getString("SURNAME"),
                        rs.getInt(4)));
            }
            for (Contact contact : list) {
                System.out.println(contact);

            }
            if (list.size() == 0) { // если контакт найден list.size() = 1
                System.out.println("Contact with this name is NOT FOUND!"); // отсутствие искомого контакта в БД
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void daoShowAllContacts() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM CONTACT");
            List<Contact> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Contact(
                        rs.getString("NAME"),
                        rs.getString("SURNAME"),
                        rs.getInt(4)));
            }
            for (Contact contact : list) {
                System.out.println(contact);
            }
            if (list.size() == 0) { // если контакт найден list.size() = 1
                System.out.println("Contact list is empty!"); // отсутствие искомого контакта в БД
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void daoDeleteContact(String name) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM contact WHERE NAME =" + "'" + name + "'");
            List<Contact> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Contact(
                        rs.getString("NAME"),
                        rs.getString("SURNAME"),
                        rs.getInt(4)));
            }
            if (list.size() == 0) { // если контакт найден list.size() = 1
                System.out.println("Contact with this name is NOT FOUND!"); // отсутствие искомого контакта в БД

            } else {
                statement.executeUpdate("DELETE FROM contact WHERE NAME =" + "'" + name + "'");
                System.out.println("Contact with this name is DELETE!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void daoModifyContact(String name) throws IOException {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM contact WHERE NAME =" + "'" + name + "'");
            List<Contact> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Contact(
                        rs.getString("NAME"),
                        rs.getString("SURNAME"),
                        rs.getInt(4)));
            }
            if (list.size() == 0) { // если контакт найден list.size() = 1
                System.out.println("Contact with this name is NOT FOUND!"); // отсутствие искомого контакта в БД
            } else {
                statement.executeUpdate("DELETE FROM contact WHERE NAME =" + "'" + name + "'");

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CONTACT(NAME, SURNAME, AGE) VALUES(?,?,?);");
                {
                    System.out.println("Enter new name:");
                    String newName = (br.readLine().toLowerCase());
                    preparedStatement.setString(1, newName); // присвоение контакту нового значения "имя"

                    System.out.println("Enter new surname");
                    String newSurname = (br.readLine().toLowerCase());
                    preparedStatement.setString(2, newSurname); // присвоение контакту нового значения "фамилия"

                    System.out.println("Enter new age");
                    int newAge = new Integer(br.readLine());
                    preparedStatement.setInt(3, newAge); // присвоение контакту нового значения "возраст"

                    preparedStatement.execute();
                    System.out.println("Contact is MODIFY!\n");
                    System.out.println("Name: " + newName + " Surname: " + newSurname + " Age: " + newAge);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } //catch (IOException | NumberFormatException e) { // проверка ввода возраста цифрами
            //System.out.println("Wrong format!");
        //}
    }
}
