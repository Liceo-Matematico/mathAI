package math_ai.core;


public class InputValue {

    private double[] data;

    // input scalare
    public InputValue(double x) {
        this.data = new double[]{x};
    }

    // input vettoriale (es. ND)
    public InputValue(double... x) {
        this.data = x;
    }

    // comodo per i modelli 1D
    public double get() {
        return data[0];
    }

    public double[] getVector() {
        return data;
    }

    public int size() {
        return data.length;
    }

    @Override
    public String toString() {

        String descr = "";
        
        if (data.length != 1) { 
            descr += data[0];
        } else {
            descr = java.util.Arrays.toString(data);
        }

        return descr;
    }
}
