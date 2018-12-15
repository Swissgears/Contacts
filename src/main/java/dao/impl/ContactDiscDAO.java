package dao.impl;

import dao.ContactDAO;
import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDiscDAO implements ContactDAO {

    private File file;
    private static int generatorId = 0;

    public ContactDiscDAO() {
        this.file = new File("ContactBook. txt");

    }

    @Override

    public void daoSaveContact(Contact contact) {
        try (FileWriter fileWriter = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            contact.setId(generatorId++);
            printWriter.println(contact);

        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    @Override
    public void daoShowContact(String name) throws IOException {
        file = new File("ContactBook. txt");
        List<Contact> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader))
        {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] strings = line.split(" ");
                String name1 = strings[1];
                String surname = strings[3];
                int age = Integer.valueOf(strings[5]);
                int id = Integer.valueOf(strings[7]);
                list.add(new Contact(name1, surname, age, id));
            }
        }catch (IOException e){
            System.out.println("ERROR");
        }
        for (Contact contact : list){
            System.out.println(contact);
        }
    }

    @Override
    public void daoShowAllContacts() {


    }

    @Override
    public void daoDeleteContact(String name) throws IOException {

    }

    @Override
    public void daoModifyContact(String name) throws IOException {

    }
}
