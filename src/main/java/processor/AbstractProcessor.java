package processor;

public abstract class AbstractProcessor {

    protected static final String SEPARATOR = " ";
    protected static final int NO_VALUE = -1;

    //syntax flag (один/одна два/две)
    private static boolean flagSyntax = false;

    public static boolean isFlagSyntax() {
        return flagSyntax;
    }

    public String getName(long value) {
        return getName(Long.toString(value));
    }

    public String getName(double value) {
        return getName(Double.toString(value));
    }

    public static void changeSyntax() {
        flagSyntax = !flagSyntax;
    }

    public abstract String getName(String value);
}