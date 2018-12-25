package dao.impl;

import dao.ContactDAO;
import model.Contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class ContactCollectionDAO implements ContactDAO {
    private BufferedReader br;

    public ContactCollectionDAO(BufferedReader br) {
        this.br = br;
    }

    private List<Contact> store = new ArrayList<>();

    @Override
    public void daoSaveContact(Contact contact) {
        store.add(contact);
        System.out.println("Create contact: " + contact);
    }

    @Override
    public void daoShowContact(String name) {

        int counter = 0;

        if (!store.isEmpty()) { // boolean isEmpty: возвращает true, если коллекция пуста

            for (int i = 0; i < store.size(); i++) {
                if (store.get(i).getName().equals(name)) {
                    System.out.println(store.get(i).toString());
                    break;
                } else counter++; //счетчик несовпадений
                if (counter == store.size()) {
                    System.out.println("Contact with this name is NOT FOUND!");
                }
            }
        } else System.out.println("Contact list is empty!");
    }

    @Override
    public void daoShowAllContacts() {

        if (!store.isEmpty()) { // boolean isEmpty: возвращает true, если коллекция пуста

            for (int i = 0; i < store.size(); i++) {
                System.out.println(store.get(i));
            }
        } else System.out.println("Contact list is empty!");
    }

    @Override
    public void daoDeleteContact(String name) {

        int counter = 0;

        if (!store.isEmpty()) { // boolean isEmpty: возвращает true, если коллекция пуста

            for (int i = 0; i < store.size(); i++) {
                if (store.get(i).getName().equals(name)) {
                    store.remove(i);
                    System.out.println("Contact with this name is DELETE!");
                    break;
                } else counter++; //счетчик несовпадений
                if (counter == store.size()) {
                    System.out.println("Contact with this name is NOT FOUND!");
                }
            }
        } else System.out.println("Contact list is empty!");
    }

    @Override
    public void daoModifyContact(String name) throws IOException {

        int counter = 0;

        if (!store.isEmpty()) { // boolean isEmpty: возвращает true, если коллекция пуста

            for (int i = 0; i < store.size(); i++) {

                if (store.get(i).getName().equals(name)) { // поиск контакта в массиве по имени

                    System.out.println("Enter new name:");
                    store.get(i).setName(br.readLine(). // присвоение контакту нового значения "имя"
                            toLowerCase());

                    System.out.println("Enter new surname");
                    store.get(i).setSurname(br.readLine(). // присвоение контакту нового значения "фамилия"
                            toLowerCase());

                    System.out.println("Enter new age");
                    int age = new Integer(br.readLine());
                    store.get(i).setAge(age); // присвоение контакту нового значения "возраст"

                    System.out.println("Contact is MODIFY!\n" + store.get(i));
                    break;
                } else counter++; // счетчик несовпадений
                if (counter == store.size()) {
                    System.out.println("Contact with this name is NOT FOUND!");
                }
            }
        } else System.out.println("Contact list is empty!");
    }

}
