package ui;

import services.ContactService;

import static dao.impl.ContactArrayDAO.generatorId;
import static dao.impl.ContactArrayDAO.memorySlotsUsed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineService {
    private ContactService contactService;
    private BufferedReader br;// = new BufferedReader(new InputStreamReader(System.in));

    public CommandLineService(ContactService contactService, BufferedReader br) {
        this.contactService = contactService;
        this.br = br;
    }

    public String input_Menu() throws IOException { // выбор пункта меню
        String s;
        while ((s = br.readLine()) == null) {
            return input_Menu();
        }
        return s;
    }

    public boolean validate_Age(int age) { // проверка ввода возраст
        try {
//            age = new Integer(br.readLine()); // возраст в пределах от 1 - 99
            while (age > 0 && age < 100) {
                return age;
            }
        } catch (IOException | NumberFormatException e) { // проверка ввода возраста цифрами
            System.out.println("Wrong format!");
            return valid_Age();
        }
        return ;
    }

    public void show_Contact_Creation_Menu() throws IOException {
        if (memorySlotsUsed == 10) {
            System.out.println("Memory is full");
            run();
        } else {
            System.out.println("Enter name:");
            String name = br.readLine().toLowerCase();
            System.out.println("Enter surname");
            String surname = br.readLine().toLowerCase();

            int age = -1;
            while (validate_Age(age) != true)
            {
                System.out.println("Enter age:");
                int age = new Integer(br.readLine());
            }
            contactService.create_Contact(name, surname, age, id);

            int id = generatorId++;
        }
    }

    public void run() throws IOException {
        while (true) {
            System.out.println("\n Menu");
            System.out.println("1. Create contact");
            System.out.println("2. Show contact");
            System.out.println("3. Show all contacts");
            System.out.println("0. Exit\n");

            switch (input_Menu()) {
                case "1":
                    show_Contact_Creation_Menu(); // меню выбора "создать контакт"
                    break;
                case "2":
                    contactService.show_Contact(); // меню выбора "просмотреть контакты"

                    while (true) {
                        System.out.println("\n1. Modify contact");
                        System.out.println("2. Delete contact");
                        System.out.println("0. Return to main menu\n");

                        switch (input_Menu()) {
                            case "1":
                                contactService.modify_Contact(); // меню выбора "изменить контакт"
                                break;
                            case "2":
                                contactService.delete_Contact(); // меню выбора "удалить контакт"
                                break;
                            case "0":
                                //  run(); // возврат в главное меню
                                break;
                            default:
                                System.out.println("Error! Please repeat the action!" + "\n");
                                break;
                        }
                    }
                    // break;
                case "3":
                    contactService.show_All_Contacts(); // меню выбора "просмотреть все контакты"
                    break;
                case "0":
                    System.exit(0); // меню выбора "выход из программы"
                    break;
                default:
                    System.out.println("Error! Please repeat the action!" + "\n");
                    break;
            }
        }
    }
}


