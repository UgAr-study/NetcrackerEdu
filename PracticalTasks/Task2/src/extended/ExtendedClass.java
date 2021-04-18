package extended;

/**
 * class for storing the {@code byte}, {@code int},
 * {@code double} and {@code String} values
 */
public class ExtendedClass {

    private byte b;
    private int i;
    private double d;
    private String s;

    /**
     * Constructor
     * @param b the value of {@code byte}
     * @param i the value of {@code int}
     * @param d the value of {@code double}
     * @param s the value of {@code String}
     */
    public ExtendedClass(byte b, int i, double d, String s) {
        this.b = b;
        this.i = i;
        this.d = d;
        this.s = s;
    }

    /**
     * check if this object is equal to {@code anotherObj} by comparing their hashes
     * @param anotherObj the object to compare with
     * @return {@code true} if {@code anotherObj} is equal to this object or
     * {@code false} if it is not, or even {@code anotherObj} is not the instance of our class
     */
    public boolean equals (Object anotherObj) {

        if (anotherObj instanceof ExtendedClass) {
            return  hashCode() == anotherObj.hashCode();
        }

        return false;
    }

    /**
     * compute the hash of this object
     * @return the hash
     */
    public int hashCode () {
        return s.hashCode() + Double.hashCode(d) + Integer.hashCode(i) + Byte.hashCode(b);
    }

    /**
     * convert this object to {@code String}
     * @return the converted {@code String} value
     */
    public String toString () {
        return "\nb = " + b + "\ni = " + i + "\nd = " + d + "\ns = " + s;
    }
}
