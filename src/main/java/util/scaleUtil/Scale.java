package util.scaleUtil;

//Class names holder
public enum Scale {
    one;
    private static final ScaleUnit[] SCALE_UNITS = new ScaleUnit[]{
            new ScaleUnit(63, "вигинтиллион"),
            new ScaleUnit(60, "новемдециллион"),
            new ScaleUnit(57, "октодециллион"),
            new ScaleUnit(54, "септемдециллион"),
            new ScaleUnit(51, "сексдециллион"),
            new ScaleUnit(48, "квиндециллион"),
            new ScaleUnit(45, "кваттордециллион"),
            new ScaleUnit(42, "тредециллион"),
            new ScaleUnit(39, "дуодециллион"),
            new ScaleUnit(36, "андециллион"),
            new ScaleUnit(33, "дециллион"),
            new ScaleUnit(30, "нониллион"),
            new ScaleUnit(27, "октиллион"),
            new ScaleUnit(24, "септиллион"),
            new ScaleUnit(21, "секстиллион"),
            new ScaleUnit(18, "квинтиллион"),
            new ScaleUnit(15, "квадриллион"),
            new ScaleUnit(12, "триллион"),
            new ScaleUnit(9, "миллиард"),
            new ScaleUnit(6, "миллион"),
            new ScaleUnit(3, "тысяч"),
            new ScaleUnit(-1, "десятых"),
            new ScaleUnit(-2, "сотых"),
            new ScaleUnit(-3, "тысячных"),
            new ScaleUnit(-4, "десятитысячных"),
            new ScaleUnit(-5, "стотысячных"),
            new ScaleUnit(-6, "миллионных"),
    };
    public String getName(int exponent) {
        for (ScaleUnit unit : SCALE_UNITS) {
            if (unit.getZeroCount() == exponent) {
                return unit.getName(this.ordinal());
            }
        }
        return "";
    }
}
