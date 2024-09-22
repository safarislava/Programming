import java.lang.Math;

public class MyProgram {
    public static float CalculateCell(short[] n, float[] x, int i, int j) {
        if (n[i] == 13) {
            return (float) Math.cos(Math.pow(Math.pow(x[j] / 2, x[j]) / Math.PI, 3));
        }
        else if ((n[i] >= 5 && n[i] <= 10) || n[i] == 14) {
            float a = (float) Math.pow(Math.E, Math.pow(2.0f/3 * x[j], 3));
            return (float) Math.log(Math.pow(Math.cos(a), 2));
        }
        else {
            float a = (float) Math.pow(4 / Math.tan(x[j]), 2), 
                b = (float) Math.pow(Math.sin(x[j]), Math.pow(2.0f/3 * x[j], x[j]) / 3) - 1.0f/2;
            return (float) Math.cos(Math.pow(a / b, 3));
        }
    }

    public static void PrintMatrix(float[][] w, int width, int height) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.printf("%10.5f", w[j][i]);
            }        
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        short elementBegin = 4, elementEnd = 15;
        short[] n = new short[elementEnd - elementBegin + 1];
        for (short i = elementBegin; i <= elementEnd; i++) {
            n[i - elementBegin] = i;
        }

        int countNumbers = 17;
        float boundLower = -7.0f, boundUpper = 2.0f;
        float[] x = new float[countNumbers];
        for (int i = 0; i < countNumbers; i++) {
            x[i] = (float) Math.random() * (boundUpper - boundLower) + boundLower;
        }

        int width = 12, height = 17;
        float[][] w = new float[height][width];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                w[j][i] = CalculateCell(n, x, i, j);
            }        
        }

        PrintMatrix(w, width, height);
    }
}