package dao.impl;

import dao.ContactDAO;
import model.Contact;
import services.ContactService;
import ui.CommandLineService;

import java.io.IOException;

import static ui.CommandLineService.*;

public class ContactArrayDAO implements ContactDAO {


    private static ContactDAO dao = new ContactArrayDAO();

        this.dao = ContactDAO;

    public static int memorySlotsUsed = 0;
    public static int generatorId = 0;

    private Contact[] store = new Contact[10]; // массив контактов

    @Override
    public void daoSaveContact(Contact contact) {
        if (memorySlotsUsed <= 10) {

        if (memorySlotsUsed >= 10) {
            System.out.println("Memory is full");
        } else {
            contact = new Contact(name, surname, age, id);
            System.out.println("Create contact! " + contact);
        store[memorySlotsUsed] = contact; // запись контактов в массив
            memorySlotsUsed++;
    }




//
//    if (memorySlotsUsed <= 10) {
//
//        if (memorySlotsUsed >= 10) {
//            System.out.println("Memory is full");
//        } else {
//            Contact contact = new Contact(name, surname, age, id);
//            System.out.println("Create contact! " + contact);
//            dao.daoSaveContact(contact);
//            memorySlotsUsed++;
//        }
//
//
//

    @Override
    public void daoShowContact(String name) throws IOException {
        Contact localContact;
        try {
            for (int i = 0; i < store.length; i++) {
                if (store[i].getName().equals(name)) { // поиск контакта в массиве по имени
                    localContact = store[i];
                    System.out.println(localContact.toString());
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Contact with this name is NOT FOUND!");
            //run();
       }
  }

    @Override
    public void daoShowAllContacts() {
        for (int i = 0; i < store.length; i++) {
            System.out.println((i + 1) + ". " + store[i]); // просмотреть все контакты
        }
    }

    @Override
    public void daoDeleteContact(String name) throws IOException {
        try {
            for (int i = 0; i < store.length; i++) {

                if (store[i].getName().equals(name)) { // поиск контакта в массиве по имени
                    store[i] = null; // присвоение контакту нового значения "null"
                    System.out.println("Contact with this name is DELETE!");
                  //  contactArrayDAO.run();
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Contact with this name is NOT FOUND!");
          //  run();
        }
    }

    @Override
    public void daoModifyContact(String name) throws IOException {
        try {
            for (int i = 0; i < store.length; i++) {
                if (store[i].getName().equals(name)) { // поиск контакта в массиве по имени

                    System.out.println("Enter name:");
                    store[i].setName(br.readLine(). // присвоение контакту нового значения "имя"
                            toLowerCase());
                    System.out.println("Enter surname");
                    store[i].setSurname(br.readLine(). // присвоение контакту нового значения "фамилия"
                            toLowerCase());
                  // store[i].setAge(.valid_Age()); // присвоение контакту нового значения "возраст"

                    System.out.println("Contact is MODIFY!\n" + store[i]);
                   // run();
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Contact with this name is NOT FOUND!");
            //run();
        }
    }
}
