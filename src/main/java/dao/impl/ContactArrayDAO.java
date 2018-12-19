package dao.impl;

import dao.ContactDAO;
import model.Contact;

import java.io.BufferedReader;
import java.io.IOException;


public class ContactArrayDAO implements ContactDAO {
    private BufferedReader br;

    public static int memorySlotsUsed = 0;

    private Contact[] store = new Contact[10]; // массив контактов

    public ContactArrayDAO(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void daoSaveContact(Contact contact) {

        if (memorySlotsUsed <= 10) {
            if (memorySlotsUsed >= 10) {
                System.out.println("Memory is full");
            } else {
                store[memorySlotsUsed] = contact; // запись контактов в массив
                memorySlotsUsed++;
                System.out.println("Create contact! " + contact);
            }
        }
    }

    @Override
    public void daoShowContact(String name) {
        try {
            for (int i = 0; i < store.length; i++) {
                if (store[i].getName().equals(name)) { // поиск контакта в массиве по имени
                    System.out.println(store[i].toString());
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Contact with this name is NOT FOUND!");
        }
    }

    @Override
    public void daoShowAllContacts() {
        for (int i = 0; i < store.length; i++) {
            System.out.println((i + 1) + ". " + store[i]); // просмотреть все контакты
        }
    }

    @Override
    public void daoDeleteContact(String name) {
        try {
            for (int i = 0; i < store.length; i++) {

                if (store[i].getName().equals(name)) { // поиск контакта в массиве по имени
                    store[i] = null; // присвоение контакту нового значения "null"
                    System.out.println("Contact with this name is DELETE!");
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Contact with this name is NOT FOUND!");
        }
    }

    @Override
    public void daoModifyContact(String name) throws IOException {
        try {
            for (int i = 0; i < store.length; i++) {
                if (store[i].getName().equals(name)) { // поиск контакта в массиве по имени
                    System.out.println("Enter new name:");
                    store[i].setName(br.readLine(). // присвоение контакту нового значения "имя"
                            toLowerCase());
                    System.out.println("Enter new surname");
                    store[i].setSurname(br.readLine(). // присвоение контакту нового значения "фамилия"
                            toLowerCase());
                    System.out.println("Enter new age");
                    int age = new Integer(br.readLine());
                    store[i].setAge(age); // присвоение контакту нового значения "возраст"

                    System.out.println("Contact is MODIFY!\n" + store[i]);
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Contact with this name is NOT FOUND!");
        }
    }
}
