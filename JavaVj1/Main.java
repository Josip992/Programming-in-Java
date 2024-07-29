import org.junit.Test;

public class Main {
    public static void main(String[] args) {

                String hexColor = "#331339";

                MyColor c = MyColor.decode(hexColor);

                float[] hsbCode = new float[3];
                MyColor.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsbCode);

                System.out.println("Boja u HEX formatu: 0x" +
                        Integer.toHexString(c.getRGB() & 0x00FFFFFF));
                System.out.println("Boja u RGB formatu: " + c.getRed() + ", " +
                        c.getGreen() + ", " + c.getBlue());
                System.out.println("Boja u HSB formatu: " + hsbCode[0] * 360 + "°, " +
                        hsbCode[1] * 100 + "%, " + hsbCode[2] * 100 + "%");


                float[] hslCode = new float[3];
                MyColor.RGBtoHSL(c.getRed(), c.getGreen(), c.getBlue(), hslCode);

                System.out.println("Boja u HSL formatu: " + hslCode[0] * 360 + "°, " +
                        hslCode[1] * 100 + "%, " + hslCode[2] * 100 + "%");

                float[] cmykCode = new float[4];
                MyColor.RGBtoCMYK(c.getRed(), c.getGreen(), c.getBlue(), cmykCode);
                System.out.println("Boja u CMYK formatu: " + cmykCode[0] * 100 +"%, " +
                        cmykCode[1] * 100 + "%, " + cmykCode[2] * 100 + "%, " + cmykCode[3] + "%");


    }
}