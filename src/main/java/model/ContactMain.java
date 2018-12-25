package model;

import dao.ContactDAO;
import dao.impl.ContactArrayDAO;
import dao.impl.ContactCollectionDAO;
import dao.impl.ContactDbDAO;
import dao.impl.ContactDiscDAO;
import services.ContactService;
import ui.CommandLineService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContactMain {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ContactDAO dao = new ContactCollectionDAO(br);
        ContactService contactService = new ContactService(dao);

        CommandLineService service = new CommandLineService(contactService, br);
      //ContactArrayDAO(br);
        //ContactDiscDAO(br);
       // ContactDbDAO();
        //ContactCollectionDAO(br);
        service.run();
    }
}


//Name: s Surname: s Age: 3 Id: 1
//Name: r Surname: r Age: 6 Id: 2