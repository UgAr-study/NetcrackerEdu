import address.Address;
import address.Group;
import address.Person;
import equations.QESolver;
import game.Dice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
       showEquationSolver();
       showDiceGame();
       showAddressTask();
    }


    /**
     * these methods illustrate the result of my work
     */
    private static void showEquationSolver() {

        System.out.println("Equation solver: x^2 + 2x + 1 = 0");
        QESolver solver = new QESolver(1, 2, 1);
        System.out.println("First root is: " + solver.getFirstRoot());
        System.out.println("Second root is: " + solver.getSecondRoot());
        System.out.println("\n===================================\n");
    }

    private static void showDiceGame() {

        System.out.println("Dice game");
        Dice.startGame();
        System.out.println("\n===================================\n");
    }

    private static void showAddressTask() {

        System.out.println("Address task");
        Group group = new Group(generatePeople());

        ArrayList<Person> people = new ArrayList<>();

        people = group.findBySurname("Ivanov");

        System.out.println("\n--------------------------------\n");
        System.out.println("\nPeople with surname 'Ivanov'");
        for (Person p : people) {
            System.out.println(p);
        }

        people = group.findByAddress(new Address("B", "a", "3", "14"));

        System.out.println("\n--------------------------------\n");
        System.out.println("\nPeople who live in 'city (B), street (a), house (3), flat (14)'");
        for (Person p : people) {
            System.out.println(p);
        }

        people = group.findBirthInInterval(
                new GregorianCalendar(1999, Calendar.MARCH, 1),
                new GregorianCalendar(2005, Calendar.DECEMBER, 22)
        );

        System.out.println("\n--------------------------------\n");
        System.out.println("\nPeople who was born from 01.03.1999 to 22.12.2005");
        for (Person p : people) {
            System.out.println(p);
        }

        System.out.println("\n--------------------------------\n");
        System.out.println("\nThe oldest man is" + group.findOldest());

        System.out.println("\n--------------------------------\n");
        System.out.println("\nThe youngest man is" + group.findYoungest());

        System.out.println("\n--------------------------------\n");
        System.out.println("\nPeople who live in the street: " + people.get(0).getAddress().getStreet());
        people = group.findStreetNeighbours(people.get(0));

        for (Person p : people) {
            System.out.println(p);
        }

        people = null;
    }

    /**
     * generates test array of {@code Person} to demonstrate the work of {@code Group} class
     * @return array of Person
     */
    private static Person[] generatePeople () {

        Person[] people = new Person[5];

        people[0] = new Person(
                "Ivan", "Ivanov",
                new Address("A", "a", "1", "10"),
                new GregorianCalendar(2000, Calendar.APRIL, 22));

        people[1] = new Person(
                "Oleg", "Sidorov",
                new Address("A", "a", "2", "11"),
                new GregorianCalendar(1999, Calendar.JULY, 9));

        people[2] = new Person(
                "Nick", "Potapov",
                new Address("B", "a", "3", "14"),
                new GregorianCalendar(2001, Calendar.OCTOBER, 5));

        people[3] = new Person(
                "Ivan", "Vladimirov",
                new Address("A", "b", "5", "15"),
                new GregorianCalendar(2013, Calendar.DECEMBER, 19));

        people[4] = new Person(
                "Georg", "Ivanov",
                new Address("B", "b", "6", "19"),
                new GregorianCalendar(1975, Calendar.JANUARY, 1));

        return people;
    }
}
