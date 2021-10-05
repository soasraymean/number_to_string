package util.scaleUtil;

public class ScaleUnit {
    private final int zeroCount;
    private final String[] name;

   public ScaleUnit(int zeroCount, String... name) {
        this.zeroCount = zeroCount;
        this.name = name;
    }

    public int getZeroCount() {
        return zeroCount;
    }

    public String getName(int index) {
        return name[index];
    }
}