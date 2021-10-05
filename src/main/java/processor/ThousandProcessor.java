package processor;

import util.scaleUtil.Scale;

public class ThousandProcessor extends AbstractProcessor {

    private static final int EXPONENT = 3;
    private static final Scale SCALE = Scale.one;
    private static final HundredProcessor hundredProcessor = new HundredProcessor();


    @Override
    public String getName(String value) {
        StringBuilder buffer = new StringBuilder();

        int number;
        int numberTemp = 0;
        int numberTemp2 = 0;

        if ("".equals(value)) {
            number = 0;
        } else {
            //six signs for thousands
            if (value.length() > 6) {
                number = Integer.parseInt(value.substring(value.length() - 6));
                numberTemp = Integer.parseInt(value.substring(value.length() - 5).substring(0, 2));
            } else {
                number = Integer.parseInt(value);
            }
        }

        //last 2 numbers to detect the ending of тысяча
        if (number >= 1000) {
            if (value.length() > 5) {
                numberTemp = Integer.parseInt(value.substring(value.length() - 5).substring(0, 2));
                numberTemp2 = Integer.parseInt(value.substring(value.length() - 4).substring(0, 1));
            } else {
                if (value.length() > 4) {
                    numberTemp = Integer.parseInt(value.substring(value.length() - 5).substring(0, 2));
                    numberTemp2 = Integer.parseInt(value.substring(value.length() - 4).substring(0, 1));

                } else {
                    numberTemp2 = Integer.parseInt(value.substring(value.length() - 4).substring(0, 1));
                }
            }

            //один/одна два/две
            if ((numberTemp2 == 1 || numberTemp2 == 2) && (numberTemp != 11) && (numberTemp != 12)) {
                changeSyntax();
                buffer.append(hundredProcessor.getName(number / 1000));
                //возвращаем флаг замены
                changeSyntax();
            } else {
                buffer.append(hundredProcessor.getName(number / 1000));
            }

            buffer.append(SEPARATOR);
            buffer.append(SCALE.getName(EXPONENT));
            if (((numberTemp2 == 2) || numberTemp2 == 3 || numberTemp2 == 4) && (numberTemp != 12) && (numberTemp != 13) && (numberTemp != 14)) {
                buffer.append("и");
            }
            if ((numberTemp2 == 1) && (numberTemp != 11)) {
                buffer.append("а");
            }
        }

        String hundredsName = hundredProcessor.getName(number);

        if (!"".equals(hundredsName) && (number >= 1000)) {
            buffer.append(SEPARATOR);
        }
        buffer.append(hundredsName);

        return buffer.toString();
    }

}