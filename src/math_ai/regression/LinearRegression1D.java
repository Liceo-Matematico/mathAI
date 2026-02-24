package math_ai.regression;

import math_ai.core.*;

public class LinearRegression1D extends Model implements Trainable {

    private double m = 0.0;
    private double q = 0.0;

    @Override
    public double predict(InputValue x) {
        double v = x.get();       // usa il valore scalare
        return m * v + q;
    }

    @Override
    public double predictTrain(InputValue x) {
        return predict(x);
    }

    @Override
    public void train(InputValue[] X, double[] Y, double lr, int epochs) {

        int n = X.length;

        for (int e = 0; e < epochs; e++) {

            double dm = 0;
            double dq = 0;

            for (int i = 0; i < n; i++) {
                double xi = X[i].get();
                double yi = Y[i];

                double pred = m * xi + q;
                double err  = pred - yi;

                dm += err * xi;
                dq += err;
            }

            dm /= n;
            dq /= n;

            m -= lr * dm;
            q -= lr * dq;

            if (e % 100 == 0) {
                System.out.println("Epoch " + e + " | loss=" + loss(X, Y));
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nm = " + m +
               "\nq = " + q; 
    }
}
