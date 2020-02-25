package forTest.Graphsample;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;

//    public String getFirstName() {
//        return firstName.get();
//    }
//
//    public String getLastName() {
//        return lastName.get();
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName.set(firstName);
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName.set(lastName);
//    }

    public Person(SimpleStringProperty firstName, SimpleStringProperty lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
    }

    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }
}