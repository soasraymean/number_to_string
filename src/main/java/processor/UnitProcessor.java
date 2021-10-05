package processor;

public class UnitProcessor extends AbstractProcessor {

    private static final String[] TOKENS = new String[]{"один", "два", "три", "четыре",
            "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать",
            "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};

    // 1 - один/одна 2 - два/две
    private static final String[] TOKENS_VARIABLE = new String[]{"одна", "две"};

    @Override
    public String getName(String value) {
        StringBuilder buffer = new StringBuilder();

        int offset = NO_VALUE;
        int number;
        if (value.length() > 3) {
            number = Integer.parseInt(value.substring(value.length() - 3));
        } else {
            number = Integer.parseInt(value);
        }

        number %= 100;

        if (number < 10) {
            offset = (number % 10) - 1;
        } else if (number < 20) {
            offset = (number % 20) - 1;
        }

        if (offset != NO_VALUE && offset < TOKENS.length) {
            if (!isFlagSyntax()) {
                buffer.append(TOKENS[offset]);
            } else {
                buffer.append(TOKENS_VARIABLE[offset]);
            }
        }
        return buffer.toString();
    }
}