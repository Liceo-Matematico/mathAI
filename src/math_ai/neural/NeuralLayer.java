package math_ai.neural;

public class NeuralLayer extends Layer {

    private double[][] W;      // pesi [neuroni_out][neuroni_in]
    private double[] b;        // bias [neuroni_out]

    private double[] lastInput;   // x
    private double[] lastOutput;  // y = f(z)

    private Activation activation;

    /**
     * Crea un layer neurale con:
     * - inputSize neuroni in ingresso
     * - outputSize neuroni in uscita
     * - activation funzione di attivazione
     */
    public NeuralLayer(int inputSize, int outputSize, Activation activation) {

        this.activation = activation;

        W = new double[outputSize][inputSize];
        b = new double[outputSize];

        // Inizializzazione semplice
        for (int i = 0; i < outputSize; i++) {
            for (int j = 0; j < inputSize; j++) {
                W[i][j] = Math.random() * 0.2 - 0.1;
            }
            b[i] = 0.0;
        }
    }

    @Override
    public double[] forward(double[] x) {
        lastInput = x;

        int outSize = b.length;
        int inSize  = x.length;

        double[] z = new double[outSize];

        // parte lineare: z = W·x + b
        for (int i = 0; i < outSize; i++) {
            double sum = b[i];
            for (int j = 0; j < inSize; j++) {
                sum += W[i][j] * x[j];
            }
            z[i] = sum;
        }

        // attivazione
        lastOutput = new double[outSize];
        for (int i = 0; i < outSize; i++) {
            lastOutput[i] = ActivationFunctions.f(activation, z[i]);
        }

        return lastOutput;
    }

    @Override
    public double[] backward(double[] grad, double lr) {

        int outSize = grad.length;
        int inSize  = W[0].length;

        double[] gradAfterAct = new double[outSize];
        double[] gradInput    = new double[inSize];

        // 1) gradiente rispetto a z (dopo l'attivazione)
        for (int i = 0; i < outSize; i++) {
            double dAct = ActivationFunctions.df(activation, lastOutput[i]);
            gradAfterAct[i] = grad[i] * dAct;
        }

        // 2) gradiente rispetto all'input: W^T · gradAfterAct
        for (int j = 0; j < inSize; j++) {
            double sum = 0;
            for (int i = 0; i < outSize; i++) {
                sum += gradAfterAct[i] * W[i][j];
            }
            gradInput[j] = sum;
        }

        // 3) aggiornamento dei pesi e bias
        for (int i = 0; i < outSize; i++) {
            for (int j = 0; j < inSize; j++) {
                W[i][j] -= lr * gradAfterAct[i] * lastInput[j];
            }
            b[i] -= lr * gradAfterAct[i];
        }

        return gradInput;
    }

    @Override
    public String toString() {
        return "NeuralLayer(" +
               "in=" + W[0].length +
               ", out=" + W.length +
               ", activation=" + activation + ")";
    }
}
