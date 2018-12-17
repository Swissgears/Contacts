package ui;

import services.ContactService;

import java.io.BufferedReader;
import java.io.IOException;

import static dao.impl.ContactArrayDAO.memorySlotsUsed;


public class CommandLineService {
    private ContactService contactService;
    private BufferedReader br;
    public static int generatorId = 0;


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

    public int get_Valid_Age() { // проверка ввода возраста
        int age;
        System.out.println("Enter age:");
        try {
            age = new Integer(br.readLine()); // возраст в пределах от 1 - 99
            if (age > 0 && age < 100) {
                return age;
            } else return get_Valid_Age();

        } catch (IOException | NumberFormatException e) { // проверка ввода возраста цифрами
            System.out.println("Wrong format!");

        }
        return get_Valid_Age();
    }

    public String name_To_Search() throws IOException { // ввод имени для поиска
        System.out.println("Enter name to search:");
        return br.readLine();
    }

    public void show_Contact_Creation_Menu() throws IOException {

        if (memorySlotsUsed == 10) {
            System.out.println("Memory is full");

        } else {
            System.out.println("Enter name:");
            String name = br.readLine().toLowerCase();
            System.out.println("Enter surname");
            String surname = br.readLine().toLowerCase();
            int age = get_Valid_Age();
            int id = generatorId++;
            contactService.create_Contact(name, surname, age, id);

        }
    }

    public void run() throws IOException {
        while (true) {
            System.out.println("\n Menu");
            System.out.println("1. Create contact");
            System.out.println("2. Show contact");
            System.out.println("3. Modify contact");
            System.out.println("4. Delete contact");
            System.out.println("5. Show all contacts");
            System.out.println("0. Exit\n");

            switch (input_Menu()) {
                case "1":
                    show_Contact_Creation_Menu(); // меню выбора "создать контакт"
                    break;
                case "2":
                    contactService.show_Contact(name_To_Search()); // меню выбора "просмотреть контакты"
                    break;
                case "3":
                    contactService.modify_Contact(name_To_Search()); // меню выбора "изменить контакт"
                    break;
                case "4":
                    contactService.delete_Contact(name_To_Search()); // меню выбора "удалить контакт"
                    break;
                case "5":
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


