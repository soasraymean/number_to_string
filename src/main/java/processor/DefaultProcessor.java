package processor;

import util.scaleUtil.Scale;

import java.util.Arrays;

public class DefaultProcessor extends AbstractProcessor {

    private static final String MINUS = "минус";
    private static final String ZERO = "ноль";
    private static final Scale SCALE = Scale.one;
    private static final AbstractProcessor processor = new CompositeProcessor(63);

    @Override
    public String getName(String value) {

        boolean negative = false;
        if (value.startsWith("-")) {
            negative = true;
            value = value.substring(1);
        }

        int decimals = value.indexOf(".");
        String decimalValue = null;
        if (decimals >= 0) {
            decimalValue = value.substring(decimals + 1);
            value = value.substring(0, decimals);
        }

        String name = processor.getName(value);

        if ("".equals(name)) {
            name = ZERO;
        } else {
            if (negative) {
                name = MINUS.concat(SEPARATOR).concat(name);
            }
        }

        if (!(decimalValue == null || "".equals(decimalValue))) {

            String zeroDecimalValue = "";
            for (int i = 0; i < decimalValue.length(); i++) {
                zeroDecimalValue = zeroDecimalValue.concat("0");
            }
            if (decimalValue.equals(zeroDecimalValue)) {
                name = name.concat(SEPARATOR).concat("и").concat(SEPARATOR).concat(
                        ZERO).concat(SEPARATOR).concat(
                        SCALE.getName(-decimalValue.length()));
            } else {
                name = name.concat(SEPARATOR).concat("и").concat(SEPARATOR).concat(
                        processor.getName(decimalValue)).concat(SEPARATOR).concat(
                        SCALE.getName(-decimalValue.length()));
                name = nameLastPreparationStep(name);

            }
        }

        return name;
    }

    //одна-две-три-четыре handling
    private String nameLastPreparationStep(String name) {
        String[] words = name.split(SEPARATOR);
        String preLastWord = words[words.length - 2];
        if ("один".equals(preLastWord)) {
            preLastWord = "одна";
            words[words.length - 1] = words[words.length - 1].replace("ых", "ая");
        } else if ("два".equals(preLastWord)) {
            preLastWord = "две";
            words[words.length - 1] = words[words.length - 1].replace("ых", "ые");
        } else if ("три".equals(preLastWord)){
            words[words.length - 1] = words[words.length - 1].replace("ых", "ые");
        } else if ("четыре".equals(preLastWord)) {
            preLastWord = "четыре";
            words[words.length - 1] = words[words.length - 1].replace("ых", "ые");
        }
        words[words.length - 2] = preLastWord;
        name = Arrays.toString(words).replaceAll(",", "");
        return name.substring(1, name.length() - 1);
    }
}