package dao.impl;

import dao.ContactDAO;
import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDiscDAO implements ContactDAO {
    private BufferedReader br;

    public ContactDiscDAO(BufferedReader br) {
        this.br = br;
    }

    private File sourceFile;

    public ContactDiscDAO() {
        this.sourceFile = new File("ContactBook.txt");
    }

    @Override
    public void daoSaveContact(Contact contact) {
        try (FileWriter fileWriter = new FileWriter(sourceFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println(contact);
            System.out.println("Create contact! " + contact);
        } catch (NullPointerException e) {
            System.out.println("ERROR");
        } catch (IOException e) {
            System.out.println("File NOT FOUND!");
        }
    }

    @Override
    public void daoShowContact(String name) {
        sourceFile = new File("ContactBook.txt");
        List<Contact> list = new ArrayList<>();
        try {
            try (FileReader fileReader = new FileReader(sourceFile);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    String[] strings = line.split(" ");
                    String name1 = strings[1];
                    String surname = strings[3];
                    int age = Integer.valueOf(strings[5]);
                    list.add(new Contact(name1, surname, age));
                }
                int counter = 0;
                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i).getName().equals(name)) { // поиск контакта в массиве по имени
                        System.out.println(list.get(i));
                        break;
                    } else counter++; //счетчик несовпадений
                    if (counter == list.size()) {
                        System.out.println("Contact with this name is NOT FOUND!");
                    }
                }
            } catch (IOException e) {
                System.out.println("File NOT FOUND!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Contact with this name is NOT FOUND!");
        }
    }


    @Override
    public void daoShowAllContacts() {
        sourceFile = new File("ContactBook.txt");
        List<Contact> list = new ArrayList<>();
        try {
            try (FileReader fileReader = new FileReader(sourceFile);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    String[] strings = line.split(" ");
                    String name1 = strings[1];
                    String surname = strings[3];
                    int age = Integer.valueOf(strings[5]);
                    list.add(new Contact(name1, surname, age));
                }
            } catch (IOException e) {
                System.out.println("ERROR");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Contacts is NOT FOUND!");
        }
        for (Contact contact : list) {
            System.out.println(contact);
        }
    }


    @Override
    public void daoDeleteContact(String name) {
        sourceFile = new File("ContactBook.txt");
        List<Contact> list = new ArrayList<>();
        try {
            try (FileReader fileReader = new FileReader(sourceFile);

                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    String[] strings = line.split(" ");
                    String name1 = strings[1];
                    String surname = strings[3];
                    int age = Integer.valueOf(strings[5]);
                    list.add(new Contact(name1, surname, age));
                }
                int counter = 0;
                for (Contact aList : list) {
                    if (aList.getName().equals(name)) { // поиск контакта в массиве по имени
                        list.remove(aList); // удаление контакта из массива
                        System.out.println("Contact with this name is DELETE!");
                        break;
                    } else counter++; //счетчик несовпадений
                    if (counter == list.size()) {
                        System.out.println("Contact with this name is NOT FOUND!");
                    }
                }

                File outputFile = new File("ContactBookNew.txt");
                FileWriter fileWriter = new FileWriter(outputFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                for (Contact aList : list) {
                    printWriter.println(aList);

                }
                printWriter.close();
                bufferedWriter.close();
                fileWriter.close();
                bufferedReader.close();
                fileReader.close();

                sourceFile.delete();
                outputFile.renameTo(sourceFile);


            } catch (IOException e) {
                System.out.println("File NOT FOUND!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Contact with this name is NOT FOUND!");
        }
    }

    @Override
    public void daoModifyContact(String name) {

        sourceFile = new File("ContactBook.txt");
        List<Contact> list = new ArrayList<>();
        try {
            try (FileReader fileReader = new FileReader(sourceFile);

                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    String[] strings = line.split(" ");
                    String name1 = strings[1];
                    String surname = strings[3];
                    int age = Integer.valueOf(strings[5]);
                    list.add(new Contact(name1, surname, age));
                }

                for (Contact aList : list) {
                    if (aList.getName().equals(name)) { // поиск контакта в массиве по имени


                        System.out.println("Enter new name:");
                        aList.setName(br.readLine(). // присвоение контакту нового значения "имя"
                                toLowerCase());
                        System.out.println("Enter new surname");
                        aList.setSurname(br.readLine(). // присвоение контакту нового значения "фамилия"
                                toLowerCase());
                        System.out.println("Enter new age");
                        int age = new Integer(br.readLine());
                        aList.setAge(age); // присвоение контакту нового значения "возраст"

                        System.out.println("Contact is MODIFY!\n" + aList);
                        break;
                    }
                }

                File outputFile = new File("ContactBookNew.txt");
                FileWriter fileWriter = new FileWriter(outputFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                for (Contact aList : list) {
                    printWriter.println(aList);

                }
                printWriter.close();
                bufferedWriter.close();
                fileWriter.close();
                bufferedReader.close();
                fileReader.close();

                sourceFile.delete();
                outputFile.renameTo(sourceFile);

            } catch (IOException e) {
                System.out.println("File NOT FOUND!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Contact with this name is NOT FOUND!");
        }
    }
}
