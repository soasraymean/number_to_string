package processor;

public class HundredProcessor extends AbstractProcessor {

    private static final TensProcessor tensProcessor = new TensProcessor();

    private static final String[] TOKENS = new String[]{"сто", "двести", "триста",
            "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};

    @Override
    public String getName(String value) {
        StringBuilder buffer = new StringBuilder();
        boolean tensFound = false;

        int number;

        if (value.length() > 4) {
            number = Integer.parseInt(value.substring(value.length() - 4));
        } else {
            number = Integer.parseInt(value);
        }

        number %= 1000;

        //numbers 100-999
        if (number >= 100) {
            buffer.append(TOKENS[(number / 100) - 1]);
            number %= 100;
            tensFound = true;
        }

        if (number != 0) {
            if (tensFound) {
                buffer.append(SEPARATOR);
            }
            buffer.append(tensProcessor.getName(number));
        }
        return buffer.toString();
    }

}