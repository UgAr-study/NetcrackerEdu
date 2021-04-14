package address;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Person {

    private Address address;
    private String name;
    private String surname;
    private GregorianCalendar birthday;

    public Person (String name, String surname, Address address, GregorianCalendar birthday) {

        this.address = address;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public boolean isStreetNeighbourTo (Person anotherPerson) {
        return address.isSameStreet(anotherPerson.address);
    }

    public boolean isHouseNeighbourTo (Person anotherPerson) {
        return address.isSameBuilding(anotherPerson.address);
    }

    public boolean isOlderThen (Person anotherPerson) {
        return birthday.before(anotherPerson.birthday);
    }

    public boolean isOlderThen (Calendar data) {
        return birthday.before(data);
    }

    public boolean isYoungerThen (Person anotherPerson) {
        return birthday.after(anotherPerson.birthday);
    }

    public boolean isYoungerThen (Calendar data) {
        return birthday.after(data);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public String toString() {

        return  "\nName: " + name +
                "\nSurname: " + surname +
                "\nAddress: " + address.toString() +
                "\nBirthday: " + birthday
                                 .toZonedDateTime()
                                 .format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
    }
}
