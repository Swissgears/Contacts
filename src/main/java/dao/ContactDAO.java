package dao;

import model.Contact;

import java.io.IOException;

public interface ContactDAO {
    void daoSaveContact(Contact contact);

    void daoShowContact(String name) throws IOException;

    void daoShowAllContacts();

    void daoDeleteContact(String name) throws IOException;

    void daoModifyContact(String name) throws IOException;
}
