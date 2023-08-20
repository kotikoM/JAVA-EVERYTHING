package fop.w7color;

public class RgbaColor extends RgbColor {
    private final int alpha;

    public RgbaColor(int bitDepth, int red, int green, int blue, int alpha) {
        super(bitDepth, red, green, blue);

        if (alpha<0 || alpha>IntMath.powerOfTwo(bitDepth)-1)
            ExceptionUtil.unsupportedOperation("Error: Wrong Alpha Value");
        this.alpha=alpha;
    }

    public int getAlpha(){return alpha;}

    @Override
    public RgbColor8Bit toRgbColor8Bit() {
        return super.toRgbColor8Bit();
    }

}
