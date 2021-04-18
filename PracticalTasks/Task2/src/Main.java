import animals.*;
import extended.ExtendedClass;
import game.Dice;
import vartask.RestrictedNumberImpl;

public class Main {

    public static void main (String[] argv) {

        showDiceGame();
        printSeparateLine();

        showAnimals();
        printSeparateLine();

        showExtendedClass();
        printSeparateLine();

        showRestrictedNumberImp();
        printSeparateLine();
    }

    private static void showDiceGame() {
        Dice dice = new Dice();
        dice.startGame();
        dice.prizeTheWinner();
        dice.endGame();
    }

    private static void showAnimals() {
        Cat cat = new Cat();
        Dog dog = new Dog();
        Cow cow = new Cow();

        cat.voice();
        dog.voice();
        cow.voice();
    }

    private static void showExtendedClass() {

        ExtendedClass eClass1 = new ExtendedClass((byte) 2, -20, 3.14, "Hi");
        ExtendedClass eClass2 = new ExtendedClass((byte) 2, -20, 3.14, "Hi");
        ExtendedClass eClass3 = new ExtendedClass((byte) 2, -20, 3.141, "Hi");

        System.out.println("eClass1: "  + eClass1 + "\nhashCode = " + eClass1.hashCode() + "\n");
        System.out.println("eClass2: "  + eClass2 + "\nhashCode = " + eClass2.hashCode() + "\n");
        System.out.println("eClass3: "  + eClass3 + "\nhashCode = " + eClass3.hashCode() + "\n");

        System.out.println("eClass1 == eClass2? : " + eClass1.equals(eClass2));
        System.out.println("eClass2 == eClass3? : " + eClass2.equals(eClass3));
    }

    private static void showRestrictedNumberImp() {

        RestrictedNumberImpl n1 = new RestrictedNumberImpl();
        RestrictedNumberImpl n2 = new RestrictedNumberImpl();
        RestrictedNumberImpl n3 = new RestrictedNumberImpl();

        System.out.println("Set 20 to n1, 100 to n2, -100 to n3");

        n1.setValue(20);
        n2.setValue(100);
        n3.setValue(-100);

        System.out.println("n1: ");
        n1.getValue();

        System.out.println("n2: ");
        n2.getValue();

        System.out.println("n3: ");
        n3.getValue();
    }

    private static void printSeparateLine() {
        System.out.println("---------------------------------------");
    }
}
