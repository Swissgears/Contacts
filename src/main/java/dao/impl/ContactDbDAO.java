package dao.impl;

import dao.ContactDAO;
import model.Contact;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDbDAO implements ContactDAO {

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/ContactBook";
    private static final String USER = "test";
    private static final String PASSWORD = "test";

    public ContactDbDAO()  {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){
         statement.execute("CREATE TABLE IF NOT EXISTS CONTACT(ID BIGINT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), SURNAME VARCHAR(255), AGE INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void daoSaveContact(Contact contact) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CONTACT(NAME, SURNAME, AGE) VALUES(?,?,?);")){
preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setInt(3, contact.getAge());


            preparedStatement.execute();





        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void daoShowContact(String name) throws IOException {

    }

    @Override
    public void daoShowAllContacts() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER,PASSWORD);
             Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT * FROM CONTACT");
            List<Contact> list = new ArrayList<>();
            while (rs.next()){
                list.add(new Contact(
                       // rs.getInt("ID"),
                        rs.getString("name"),
                        rs.getString("SURNAME"),
                        rs.getInt(4)));

            }
    for (Contact contact : list){
        System.out.println(contact);
    }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void daoDeleteContact(String name) throws IOException {

    }

    @Override
    public void daoModifyContact(String name) throws IOException {

    }
}
