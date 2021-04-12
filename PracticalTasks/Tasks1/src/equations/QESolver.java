package equations;

import static java.lang.Math.sqrt;

public class QESolver {

    private double ans1;
    private double ans2;

    /**
     * @param a coefficient before x^2
     * @param b coefficient before x
     * @param c coefficient before 1
     */
    public QESolver(double a, double b, double c) {

        double discr = Discriminant.calculate(a, b, c);

        ans1 = (-b + sqrt(discr)) / 2 / a;
        ans2 = (-b - sqrt(discr)) / 2 / a;
    }

    public double getFirstRoot() {
        return ans1;
    }

    public double getSecondRoot() {
        return ans2;
    }

    /**
     * calculation of the discriminant
     */
    private static class Discriminant {
        /**
         * @param a coefficient before x^2
         * @param b coefficient before x
         * @param c coefficient before 1
         * @return discriminant
         */
        public static double calculate (double a, double b, double c) {
            return b * b - 4 * a * c;
        }
    }
}
