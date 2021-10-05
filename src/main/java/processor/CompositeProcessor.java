package processor;

import util.scaleUtil.Scale;

public class CompositeProcessor extends AbstractProcessor {

    private static final HundredProcessor hundredProcessor = new HundredProcessor();
    private static final ThousandProcessor thousandProcessor = new ThousandProcessor();
    private final AbstractProcessor lowProcessor;
    private final int exponent;
    private static final Scale SCALE = Scale.one;

    public CompositeProcessor(int exponent) {
        if (exponent <= 6) {
            lowProcessor = thousandProcessor;
        } else {
            lowProcessor = new CompositeProcessor(exponent - 3);
        }
        this.exponent = exponent;
    }

    public String getToken() {
        return SCALE.getName(getPartDivider());
    }

    protected AbstractProcessor getHighProcessor() {
        return thousandProcessor;
    }

    protected AbstractProcessor getLowProcessor() {
        return lowProcessor;
    }

    public int getPartDivider() {
        return exponent;
    }

    @Override
    public String getName(String value) {
        StringBuilder buffer = new StringBuilder();

        int numberTemp = 0;
        int numberTemp2 = 0;

        String high, low;
        if (value.length() < getPartDivider()) {
            high = "";
            low = value;
        } else {
            int index = value.length() - getPartDivider();
            high = value.substring(0, index);
            low = value.substring(index);
        }

        String highName = getHighProcessor().getName(high);
        String lowName = getLowProcessor().getName(low);

        if (!"".equals(highName)) {

            if (high.length() >= 2) {
                numberTemp = Integer.parseInt(high.substring(high.length() - 2).substring(0, 2));
                numberTemp2 = Integer.parseInt(high.substring(high.length() - 1).substring(0, 1));
            } else {
                numberTemp2 = Integer.parseInt(high.substring(high.length() - 1).substring(0, 1));
            }

            buffer.append(highName).append(SEPARATOR).append(getToken());
            if (((numberTemp2 == 2) || numberTemp2 == 3 || numberTemp2 == 4) && (numberTemp != 12) && (numberTemp != 13) && (numberTemp != 14)) {
                buffer.append("а");
            } else {
                if (numberTemp2 != 1 || (numberTemp == 11)) {
                    buffer.append("ов");
                }
            }
            if (!"".equals(lowName)) {
                buffer.append(SEPARATOR);
            }
        }

        if (!"".equals(lowName)) {
            buffer.append(lowName);
        }
        return buffer.toString();
    }
}