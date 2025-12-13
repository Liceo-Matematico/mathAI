package math_ai.neural;

public abstract class Layer {

    /**
     * Forward: input vettore → output vettore.
     */
    public abstract double[] forward(double[] x);

    /**
     * Backward:
     * grad = gradiente della loss rispetto all'uscita di questo layer.
     * Restituisce il gradiente rispetto all'input.
     */
    public abstract double[] backward(double[] grad, double lr);
}
