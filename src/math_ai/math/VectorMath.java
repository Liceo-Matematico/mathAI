package math_ai.math;

import math_ai.core.*;

public class VectorMath {

    // MSE su dataset per un Model qualsiasi
    public static double mse(InputValue[] X, double[] Y, Model m) {
        double sum = 0;
        for (int i = 0; i < X.length; i++) {
            double pred = m.predict(X[i]);
            double err = pred - Y[i];
            sum += err * err;
        }
        return sum / X.length;
    }

    public static double rmse(InputValue[] X, double[] Y, Model m) {
        return Math.sqrt(mse(X, Y, m));
    }

    public static double mae(InputValue[] X, double[] Y, Model m) {
        double sum = 0;
        for (int i = 0; i < X.length; i++) {
            double pred = m.predict(X[i]);
            double err = Math.abs(pred - Y[i]);
            sum += err;
        }
        return sum / X.length;
    }
}
