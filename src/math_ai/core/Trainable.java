package math_ai.core;

public interface Trainable {

    /**
     * Addestra il modello su un dataset:
     * X = array di input
     * Y = array di output (scalari)
     */
    void train(InputValue[] X, double[] Y, double lr, int epochs);

    /**
     * Predizione usata in fase di training
     * (di solito identica a predict).
     */
    double predictTrain(InputValue x);

    /**
     * Loss MSE su un dataset.
     */
    default double loss(InputValue[] X, double[] Y) {
        double sum = 0;
        for (int i = 0; i < X.length; i++) {
            double pred = predictTrain(X[i]);
            double err = pred - Y[i];
            sum += err * err;
        }
        return sum / X.length;
    }
}
