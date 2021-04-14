package address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

public class Group {

    private final ArrayList<Person> people;

    public Group () {
        people = new ArrayList<>();
    }

    public Group(Person[] people) {
        Objects.requireNonNull(people, "Error! If you want to create an empty Group," +
                " use empty constructor");

        this.people = new ArrayList<>(Arrays.asList(people));
    }

    public void addPerson (Person person) {
        Objects.requireNonNull(person, "Error! Cannot add null-object");
        people.add(person);
    }

    public void deletePerson (Person person) {
        Objects.requireNonNull(person, "Error! Cannot delete null-object");
        people.remove(person);
    }

    public ArrayList<Person> findStreetNeighbours (Person person) {

        Objects.requireNonNull(person, "Error! Cannot find by null-object");

        return findIf (p -> p.isStreetNeighbourTo(person));
    }

    public ArrayList<Person> findHouseNeighbours (Person person) {

        Objects.requireNonNull(person, "Error! Cannot find by null-object");

        return findIf (p -> p.isHouseNeighbourTo(person));
    }

    public ArrayList<Person> findByName (String name) {

        Objects.requireNonNull(name, "Error! Cannot find by null-object");

        return findIf (p -> p.getName().equals(name));
    }

    public ArrayList<Person> findBySurname (String surname) {

        Objects.requireNonNull(surname, "Error! Cannot find by null-object");

        return findIf (p -> p.getSurname().equals(surname));
    }

    public ArrayList<Person> findByStreet (String street) {

        Objects.requireNonNull(street, "Error! Cannot find by null-object");

        return findIf (p -> p.getAddress().getStreet().equals(street));
    }

    public ArrayList<Person> findByAddress (Address addr) {

        Objects.requireNonNull(addr, "Error! Cannot find by null-object");

        return findIf (p -> p.getAddress().equals(addr));
    }

    public ArrayList<Person> findYoungerThen (Calendar data) {

        Objects.requireNonNull(data, "Error! Cannot find by null-object");

        return findIf(p -> p.isYoungerThen(data));
    }

    public ArrayList<Person> findOlderThen (Calendar data) {

        Objects.requireNonNull(data, "Error! Cannot find by null-object");

        return findIf(p -> p.isOlderThen(data));
    }

    public ArrayList<Person> findBirthInInterval (Calendar lowerBound, Calendar upperBound) {

        Objects.requireNonNull(lowerBound, "Error! Cannot find by null-object");
        Objects.requireNonNull(upperBound, "Error! Cannot find by null-object");

        return findIf (p -> (p.isOlderThen(upperBound) && p.isYoungerThen(lowerBound)));

    }

    private ArrayList<Person> findIf (Filter filter) {

        ArrayList<Person> group = new ArrayList<>();

        for (Person p : people) {
            if (filter.isSuit(p)) {
                group.add(p);
            }
        }

        return group;
    }


    public Person findOldest () {

        if (people.isEmpty())
            return null;

        Person oldestPerson = people.get(0);

        for (Person p : people) {
            if (p.isOlderThen(oldestPerson)) {
                oldestPerson = p;
            }
        }

        return oldestPerson;
    }

    public Person findYoungest () {
        if (people.isEmpty())
            return null;

        Person youngestPerson = people.get(0);

        for (Person p : people) {
            if (p.isYoungerThen(youngestPerson)) {
                youngestPerson = p;
            }
        }

        return youngestPerson;
    }


    private interface Filter {
        boolean isSuit(Person p);
    }
}
