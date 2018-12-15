package model;

import dao.ContactDAO;
import dao.impl.ContactArrayDAO;
import dao.impl.ContactCollectionDAO;
import dao.impl.ContactDiscDAO;
import services.ContactService;
import ui.CommandLineService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContactMain {
    public static void main(String[] args) throws IOException {
        ContactDAO dao = new ContactDiscDAO();
        ContactService contactService = new ContactService(dao);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CommandLineService service = new CommandLineService(contactService, br);
        service.run();
    }
}
