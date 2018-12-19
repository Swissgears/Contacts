package model;

public class Contact {
  //  private int id;
    private int age;
    private String name;
    private String surname;

    public Contact(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
       // this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
    @Override
    public String toString() {
        return "Name: " + this.name + " Surname: " + this.surname + " Age: " + this.age;
    }

}