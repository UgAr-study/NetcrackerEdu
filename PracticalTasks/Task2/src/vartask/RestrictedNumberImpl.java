package vartask;

/**
 * this class contains integer value in range from -50 to 50
 */
public class RestrictedNumberImpl implements RestrictedNumber{

    private int x;
    private int MAX_ABS = 50;

    /**
     * sets the {@code x} to given value, if it is from -50 to 50,
     * otherwise x is assigned to 50 or -50
     * @param value number which x must be assigned to
     */
    @Override
    public void setValue(int value) {
        x = value > MAX_ABS ? MAX_ABS : Math.max(value, -MAX_ABS);
    }

    /**
     * print {@code x} to console
     * @return {@code x} the value of x
     */
    @Override
    public int getValue() {
        System.out.println(x);
        return x;
    }
}
