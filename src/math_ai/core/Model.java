package math_ai.core;

// Test
public abstract class Model {

    /**
     * 
     * Predizione su un singolo input.
     */
    public abstract double predict(InputValue x);

    /**
     * Predizione su molti input.
     */
    public double[] predictAll(InputValue[] X) {
        double[] out = new double[X.length];
        for (int i = 0; i < X.length; i++) {
            out[i] = predict(X[i]);
        }
        return out;
    }

    @Override
    public String toString() {
        return "Modello: " + getClass().getSimpleName();
    }
}
