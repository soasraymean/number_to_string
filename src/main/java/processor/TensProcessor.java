package processor;

public class TensProcessor extends AbstractProcessor {


    private static final UnitProcessor unitProcessor = new UnitProcessor();

    static private final String[] TOKENS = new String[]{"двадцать", "тридцать", "сорок",
            "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};

    @Override
    public String getName(String value) {
        StringBuilder buffer = new StringBuilder();
        boolean tensFound = false;

        int number;
        if (value.length() > 3) {
            number = Integer.parseInt(value.substring(value.length() - 3));
        } else {
            number = Integer.parseInt(value);
        }

        number %= 100;

        //numbers 20 - 99
        if (number >= 20) {
            buffer.append(TOKENS[(number / 10) - 2]);
            number %= 10;
            tensFound = true;
        }

        if (number != 0) {
            if (tensFound) {
                buffer.append(SEPARATOR);
            }
            buffer.append(unitProcessor.getName(number));
        }
        return buffer.toString();
    }

}