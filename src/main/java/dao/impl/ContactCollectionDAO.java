package dao.impl;
import ui.CommandLineService.*;
import dao.ContactDAO;
import model.Contact;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ContactCollectionDAO implements ContactDAO {

    private Map<Integer, Contact> store;
   // private int generatorId = 0;

    public ContactCollectionDAO() {
        store = new HashMap<>();
    }

    @Override
    public void daoSaveContact(Contact contact) {
     //   contact.setId(generatorId++);
        store.put(contact.getId(), contact);
    }

    @Override
    public void daoShowContact(String name) throws IOException {
        for (Contact contact : store.values()) {
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
