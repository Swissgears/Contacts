package services;

import dao.ContactDAO;
import model.Contact;
import dao.impl.ContactArrayDAO;
import java.io.IOException;
import static dao.impl.ContactArrayDAO.memorySlotsUsed;
import static ui.CommandLineService.br;


public class ContactService {

    private  String name_Show_Contact;
    private ContactDAO dao;// = new ContactArrayDAO();
    public ContactService(ContactDAO contactDAO){
        this.dao = contactDAO;
    }

    public  void create_Contact(String name, String surname, int age, int id) {

//        if (memorySlotsUsed <= 10) {
//
//                if (memorySlotsUsed >= 10) {
//                    System.out.println("Memory is full");
//                } else {
                    Contact contact = new Contact(name, surname, age, id);
                    System.out.println("Create contact! " + contact);
                    dao.daoSaveContact(contact);
                    memorySlotsUsed++;
//                }
//        }
    }
    public  void modify_Contact() throws IOException {
        dao.daoModifyContact(name_Show_Contact);
    }

    public  void delete_Contact() throws IOException {
        dao.daoDeleteContact(name_Show_Contact);
    }

    public  void show_Contact() throws IOException {

        System.out.println("Enter name:");
        name_Show_Contact = br.readLine();
        dao.daoShowContact(name_Show_Contact);
    }

    public void show_All_Contacts() {
        dao.daoShowAllContacts();
    }
}