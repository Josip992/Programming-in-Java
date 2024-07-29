import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyColorTest {
    @Test
    public void testDecodeHexColor() {
        String hexColor = "#C13D0B";
        MyColor testColor = MyColor.decode(hexColor);

        assertEquals(193, testColor.getRed());
        assertEquals(61, testColor.getGreen());
        assertEquals(11, testColor.getBlue());

    }
    @Test
    public void is_97_68_0_1cmyk_Equal_To_rgb_8_81_252(){
        String hexColor = "#0851FC";

        MyColor testColor = MyColor.decode(hexColor);

        float[] CMYKcode = new float[4];
        MyColor.RGBtoCMYK(testColor.getRed(), testColor.getGreen(), testColor.getBlue(), CMYKcode);
        assertEquals(96.825397f,CMYKcode[0] * 100);
        assertEquals(67.85714f,CMYKcode[1] * 100);
        assertEquals(0f,CMYKcode[2]);
        assertEquals(1.1764679f,CMYKcode[3]);
    }
    @Test
    public void is_16_94_76_hsb_Equal_To_rgb_193_61_11(){
        String hexColor = "#C13D0B";

        MyColor testColor = MyColor.decode(hexColor);

        float[] HSBcode = new float[3];
        MyColor.RGBtoHSB(testColor.getRed(), testColor.getGreen(), testColor.getBlue(), HSBcode);
        assertEquals(16.483515f,HSBcode[0]*360);
        assertEquals(94.30052f,HSBcode[1]*100);
        assertEquals(75.68628f,HSBcode[2]*100);
    }
    @Test
    public void is_290_50_15_hsl_Equal_To_rgb_51_19_57(){
        String hexColor = "#331339";

        MyColor testColor = MyColor.decode(hexColor);

        float[] HSLcode = new float[3];
        MyColor.RGBtoHSL(testColor.getRed(), testColor.getGreen(), testColor.getBlue(), HSLcode);
        assertEquals(290.52634f,HSLcode[0] * 360);
        assertEquals(49.999992f,HSLcode[1] * 100);
        assertEquals(14.901961f,HSLcode[2] * 100);
    }
}