import java.awt.Color;

public class MyColor {
    int value;

    public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null) {
            hsbvals = new float[3];
        }
        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }

    public static MyColor decode(String nm) throws NumberFormatException {
        Integer intval = Integer.decode(nm);
        int i = intval.intValue();
        MyColor myColor = new MyColor();
        myColor.value = (((i >> 16) & 0xFF) << 16) | (((i >> 8) & 0xFF) << 8) | (i & 0xFF);
        return myColor;
    }

    public int getRed() {
        return (getRGB() >> 16) & 0xFF;
    }

    public int getGreen() {
        return (getRGB() >> 8) & 0xFF;
    }

    public int getBlue() {
        return (getRGB() >> 0) & 0xFF;
    }

    public int getRGB() {
        return value;
    }


    public static float[] RGBtoHSL(int red, int green, int blue, float[] hslvals) {
        if (hslvals == null || hslvals.length < 3) {
            throw new IllegalArgumentException("Must be an array of length 3.");
        }

        float fr = red / 255.0f;
        float fg = green / 255.0f;
        float fb = blue / 255.0f;

        float min = Math.min(Math.min(fr, fg), fb);
        float max = Math.max(Math.max(fr, fg), fb);
        float delta = max - min;

        float saturation, lightness, hue;

        lightness = (max + min) / 2;

        if (delta == 0) {
            saturation = 0.0f;
        } else {
            saturation = (lightness <= 0.5) ? (delta / (max + min)) : (delta / (2 - max - min));
        }
        if (max == min) {
            hue = 0;
        } else {
            float d = max - min;
            if (max == fr) {
                hue = (fg - fb) / d + (fg < fb ? 6 : 0);
            } else if (max == fg) {
                hue = (fb - fr) / d + 2;
            } else {
                hue = (fr - fg) / d + 4;
            }

            hue /= 6;
        }

        hslvals[0] = hue;
        hslvals[1] = saturation;
        hslvals[2] = lightness;
        return hslvals;
    }


    public static float[] RGBtoCMYK(int r, int g, int b, float[] cmykCode) {
        float percentageR = r / 255.0f * 100;
        float percentageG = g / 255.0f * 100;
        float percentageB = b / 255.0f * 100;

        float k = 100 - Math.max(Math.max(percentageR, percentageG), percentageB);

        if (k == 100) {
            cmykCode[0] = 0;
            cmykCode[1] = 0;
            cmykCode[2] = 0;
            cmykCode[3] = 100;
            return cmykCode;
        }

        float c = ((100 - percentageR - k) / (100 - k));
        float m = ((100 - percentageG - k) / (100 - k));
        float y = ((100 - percentageB - k) / (100 - k));

        cmykCode[0] = c;
        cmykCode[1] = m;
        cmykCode[2] = y;
        cmykCode[3] = k;
        return cmykCode;
    }











}