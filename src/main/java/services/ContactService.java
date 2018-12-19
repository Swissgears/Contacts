package services;

import dao.ContactDAO;
import model.Contact;

import java.io.IOException;


public class ContactService {
    private ContactDAO dao;

    public ContactService(ContactDAO contactDAO) {
        this.dao = contactDAO;
    }

    public void create_Contact(String name, String surname, int age) {
        Contact contact = new Contact(name, surname, age);
        dao.daoSaveContact(contact);

    }

    public void modify_Contact(String nameToSearch) throws IOException {
        dao.daoModifyContact(nameToSearch);
    }

    public void delete_Contact(String nameToSearch) throws IOException {
        dao.daoDeleteContact(nameToSearch);
    }

    public void show_Contact(String nameToSearch) throws IOException {
        dao.daoShowContact(nameToSearch);
    }

    public void show_All_Contacts() {
        dao.daoShowAllContacts();
    }
}