import equations.QESolver;
import zonk.ZonkGame;

public class Main {

    public static void main(String[] args) {

        QESolver solver = new QESolver(1, 2, 1);
        System.out.println(solver.getFirstRoot());
        System.out.println(solver.getSecondRoot());
        ZonkGame.startGame();
    }
}
