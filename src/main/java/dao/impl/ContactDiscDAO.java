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
    private int generatorId = 0;

    public ContactDiscDAO() {
        this.sourceFile = new File("ContactBook.txt");
    }

    @Override
    public void daoSaveContact(Contact contact) {
        try (FileWriter fileWriter = new FileWriter(sourceFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            contact.setId(generatorId++);
            printWriter.println(contact);

        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    @Override
    public void daoShowContact(String name) {
        sourceFile = new File("ContactBook.txt");
        List<Contact> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(sourceFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] strings = line.split(" ");
                String name1 = strings[1];
                String surname = strings[3];
                int age = Integer.valueOf(strings[5]);
                int id = Integer.valueOf(strings[7]);
                list.add(new Contact(name1, surname, age, id));
            }

            for (Contact aList : list) {

                if (aList.getName().equals(name)) { // поиск контакта в массиве по имени
                    System.out.println(aList);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    @Override
    public void daoShowAllContacts() {
        sourceFile = new File("ContactBook.txt");
        List<Contact> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(sourceFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] strings = line.split(" ");
                String name1 = strings[1];
                String surname = strings[3];
                int age = Integer.valueOf(strings[5]);
                int id = Integer.valueOf(strings[7]);
                list.add(new Contact(name1, surname, age, id));
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
        for (Contact contact : list) {
            System.out.println(contact);
        }
    }

    @Override
    public void daoDeleteContact(String name) throws IOException {
        sourceFile = new File("ContactBook.txt");
        List<Contact> list = new ArrayList<>();

        try (FileReader fileReader = new FileReader(sourceFile);

             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] strings = line.split(" ");
                String name1 = strings[1];
                String surname = strings[3];
                int age = Integer.valueOf(strings[5]);
                int id = Integer.valueOf(strings[7]);
                list.add(new Contact(name1, surname, age, id));
            }

            for (Contact aList : list) {
                if (aList.getName().equals(name)) { // поиск контакта в массиве по имени
                    list.remove(aList); // удаление контакта из массива
                    System.out.println("Contact with this name is DELETE!");
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
            System.out.println("Contact with this name is NOT FOUND!");
        }
    }


    @Override
    public void daoModifyContact(String name) {

        sourceFile = new File("ContactBook.txt");
        List<Contact> list = new ArrayList<>();

        try (FileReader fileReader = new FileReader(sourceFile);

             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] strings = line.split(" ");
                String name1 = strings[1];
                String surname = strings[3];
                int age = Integer.valueOf(strings[5]);
                int id = Integer.valueOf(strings[7]);
                list.add(new Contact(name1, surname, age, id));
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
            System.out.println("Contact with this name is NOT FOUND!");
        }
    }
}
