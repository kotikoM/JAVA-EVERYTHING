package fop.w7color;

public class RgbColor {

    private final int red;
    private final int green;
    private final int blue;
    private final int bitDepth;


    public RgbColor(int bitDepth, int red, int green, int blue) {
        if (bitDepth <= 0 || bitDepth > 31)
            ExceptionUtil.unsupportedOperation("Error: Wrong bitDepth Value");
        this.bitDepth = bitDepth;

        if (red < 0 || red > IntMath.powerOfTwo(bitDepth) - 1)
            ExceptionUtil.unsupportedOperation("Error: Wrong Red Value");
        this.red = red;

        if (green < 0 || green > IntMath.powerOfTwo(bitDepth) - 1)
            ExceptionUtil.unsupportedOperation("Error: Wrong Green Value");
        this.green = green;

        if (blue < 0 || blue > IntMath.powerOfTwo(bitDepth) - 1)
            ExceptionUtil.unsupportedOperation("Error: Wrong Blue Value");
        this.blue = blue;
    }


    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getBitDepth() {
        return bitDepth;
    }


    public RgbColor8Bit toRgbColor8Bit() {
        if (this.bitDepth > 8) {
            int red = getRed();
            int green = getGreen();
            int blue = getBlue();
            int difference = bitDepth - 8;


            int temp = red / (int) Math.pow(2, difference - 1);
            int lastBit = temp % 2;  //reducing instructions
            temp = temp / 2;

            if (lastBit == 1) {
                temp += 1;
                red = temp;
            }

            if (temp > 256) {
                red = 255;
            }


            temp = green / (int) Math.pow(2, difference - 1);
            lastBit = green % 2;
            temp = temp / 2;

            if (green == 1) {
                temp += 1;
                green = temp;
            }

            if (temp > 256) {
                green = 255;
            }


            temp = blue / (int) Math.pow(2, difference - 1);
            lastBit = blue % 2;
            temp = temp / 2;

            if (blue == 1) {
                temp += 1;
                blue = temp;
            }

            if (temp > 256) {
                blue = 255;
            }

            return new RgbColor8Bit(red, green, blue);
        } else if (bitDepth < 8) {
            String temp = Integer.toBinaryString(red); //from decimal to binary string
            while (temp.length() < 8) {
                temp += temp;
            }

            temp = temp.substring(0, 8); //choosing first 8 bits
            int red = Integer.parseInt(temp); //form string to int

            //repeat for every color
            temp = Integer.toBinaryString(green);
            while (temp.length() < 8) {
                temp += temp;
            }

            temp = temp.substring(0, 8);
            int green = Integer.parseInt(temp);


            temp = Integer.toBinaryString(blue);
            while (temp.length() < 8) {
                temp += temp;
            }

            temp = temp.substring(0, 8);
            int blue = Integer.parseInt(temp);

            return new RgbColor8Bit(red, green, blue);
        } else return new RgbColor8Bit(this.red, this.green, this.blue);


    }


}

