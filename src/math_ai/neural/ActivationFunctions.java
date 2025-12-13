package math_ai.neural;

public class ActivationFunctions {

    public static double f(Activation a, double x) {
        switch (a) {
            case SIGMOID:
                return 1.0 / (1.0 + Math.exp(-x));
            case RELU:
                return Math.max(0, x);
            default:
                throw new IllegalArgumentException("Activation sconosciuta: " + a);
        }
    }

    /**
     * Derivata rispetto all'uscita attivata y = f(z)
     */
    public static double df(Activation a, double y) {
        switch (a) {
            case SIGMOID:
                return y * (1 - y);
            case RELU:
                return (y > 0) ? 1.0 : 0.0;
            default:
                throw new IllegalArgumentException("Activation sconosciuta: " + a);
        }
    }
}
